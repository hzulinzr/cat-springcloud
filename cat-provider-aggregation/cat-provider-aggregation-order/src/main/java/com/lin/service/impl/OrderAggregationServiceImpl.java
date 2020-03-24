package com.lin.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.lin.config.alipay.AlipayConfig;
import com.lin.config.rocketmq.RocketProducer;
import com.lin.dto.*;
import com.lin.feign.AuthUserServiceFeign;
import com.lin.feign.BookServiceFeign;
import com.lin.feign.OrderServiceFeign;
import com.lin.model.AuthUser;
import com.lin.model.Book;
import com.lin.response.PageData;
import com.lin.response.ResponseCode;
import com.lin.response.Wrapper;
import com.lin.service.OrderAggregationService;
import com.lin.tools.SnowFlake;
import com.lin.vo.BookInfoVo;
import com.lin.vo.OrderAllListVo;
import com.lin.vo.OrderListVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lzr
 * @date 2020-02-17 20:33:34
 */
@Slf4j
@Service
public class OrderAggregationServiceImpl implements OrderAggregationService {
    private RocketProducer producer;
    private AuthUserServiceFeign authUserServiceFeign;
    private OrderServiceFeign orderServiceFeign;
    private BookServiceFeign bookServiceFeign;

    public OrderAggregationServiceImpl(RocketProducer producer, AuthUserServiceFeign authUserServiceFeign, OrderServiceFeign orderServiceFeign, BookServiceFeign bookServiceFeign) {
        this.producer = producer;
        this.authUserServiceFeign = authUserServiceFeign;
        this.orderServiceFeign = orderServiceFeign;
        this.bookServiceFeign = bookServiceFeign;
    }

    @Override
    public Wrapper<PageData<OrderListVo>> orderList(OrderListDTO orderListDTO, int page, int rows) {
        return orderServiceFeign.orderList(orderListDTO, page, rows);
    }

    /**
     * 创建订单 （支付宝扫码支付）
     *
     * @param orderInsertDTO
     * @return
     */
    @Override
    public String aliPay(OrderInsertDTO orderInsertDTO) {
        //获取书籍总金额
        double totalAmount = 0;
        List<BookListDTO> bookList = orderInsertDTO.getBookList();
        totalAmount = bookList.stream().mapToDouble(BookListDTO::getTotalAmount).sum();

        //创建订单
        long orderId = new SnowFlake(0, 0).nextId();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(orderId);
        orderDTO.setUserId(orderInsertDTO.getUserId());
        //支付宝支付
        orderDTO.setPayMethod(1);
        orderDTO.setTotalAmount(totalAmount);
        orderServiceFeign.orderAdd(orderDTO);

        //向MQ发布消息
        // producer.sendMessage("order", "OrderProcess", "", "1");

        //调支付宝沙箱的支付页面
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
                AlipayConfig.merchant_private_key,
                "json",
                AlipayConfig.charset,
                AlipayConfig.alipay_public_key,
                AlipayConfig.sign_type);
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + orderId + "\","
                + "\"total_amount\":\"" + totalAmount + "\","
                + "\"subject\":\"" + "书籍买卖" + "\","
                + "\"body\":\"" + "" + "\","
                + "\"timeout_express\":\"" + "1c" + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求
        String result = null;
        try {
            result = alipayClient.pageExecute(alipayRequest).getBody();
            String[] s = result.split("<script>");
            result = s[0];
        } catch (AlipayApiException e) {
            log.info("异常错误", e);
        }
        return result;
    }

    /**
     * 创建订单 （余额支付）
     *
     * @param orderInsertDTO
     * @return
     */
    @Override
    public Wrapper<Void> orderBalanceInsert(OrderInsertDTO orderInsertDTO) {
        //获取书籍总金额
        double totalAmount = 0;
        List<BookListDTO> bookList = orderInsertDTO.getBookList();
        totalAmount = bookList.stream().mapToDouble(BookListDTO::getTotalAmount).sum();

        //创建订单
        long orderId = new SnowFlake(0, 0).nextId();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(orderId);
        orderDTO.setUserId(orderInsertDTO.getUserId());
        //余额支付
        orderDTO.setPayMethod(0);
        orderDTO.setTotalAmount(totalAmount);
        orderServiceFeign.orderAdd(orderDTO);

        //向MQ发布消息
        producer.sendMessage("order", "OrderProcess", "", "1");

        //获取用户详情信息
        BaseAuthUser baseAuthUser = new BaseAuthUser();
        baseAuthUser.setUserId(orderInsertDTO.getUserId());
        AuthUser authUser = authUserServiceFeign.userInfo(baseAuthUser).getData();
        double balance = authUser.getBalance();
        //如果余额不充足
        if (balance < totalAmount) {
            return Wrapper.fail(ResponseCode.INSUFFICIENT_BALANCE);
        }
        BalanceUpdateDTO balanceUpdateDTO = new BalanceUpdateDTO();
        //转账类型为减
        balanceUpdateDTO.setType(-1);
        balanceUpdateDTO.setUserId(orderInsertDTO.getUserId());
        balanceUpdateDTO.setTotalAmount(totalAmount);

        authUserServiceFeign.balanceUpdate(balanceUpdateDTO);

        //转账到相应的账号余额中
        bookList.forEach(bookInfo -> {
            //获取书籍上传用户和转到账户的金额
            BaseBookDTO baseBookDTO = new BaseBookDTO();
            baseBookDTO.setBookId(bookInfo.getBookId());
            Book book = bookServiceFeign.bookInfo(baseBookDTO).getData();
            BalanceUpdateDTO balanceUpdate = new BalanceUpdateDTO();
            balanceUpdate.setUserId(book.getUploadUserId());
            balanceUpdate.setTotalAmount(bookInfo.getTotalAmount());
            //转账类型为加
            balanceUpdate.setType(1);
            //转账到相应的账号余额中
            authUserServiceFeign.balanceUpdate(balanceUpdate);

        });

        //更改订单的状态为已完成
        AliPayDTO aliPayDTO = new AliPayDTO();
        aliPayDTO.setOrderId(orderId);
        orderServiceFeign.orderFinish(aliPayDTO);

        //将数据插入书籍和订单的关联表中，状态为待评价
        BookOrderInsertDTO bookOrderInsertDTO = new BookOrderInsertDTO();
        bookOrderInsertDTO.setOrderId(orderId);
        bookOrderInsertDTO.setBookList(orderInsertDTO.getBookList());
        bookOrderInsertDTO.setId(new SnowFlake(0, 0).nextId());
        bookOrderInsertDTO.setUserId(orderInsertDTO.getUserId());
        //书籍订单关联表的状态为待评价
        bookOrderInsertDTO.setState(0);
        orderServiceFeign.orderBookInsert(bookOrderInsertDTO);

        //订单流水
        //todo

        return Wrapper.success();
    }

    /**
     * 完成订单
     *
     * @param orderFinishDTO
     * @return
     */
    @Override
    public Wrapper<Void> orderFinish(OrderFinishDTO orderFinishDTO) {
        List<BookListDTO> bookList = orderFinishDTO.getBookList();
        if (null == bookList || 0 == bookList.size()) {
            return Wrapper.fail("没有该书籍");
        }
        bookList.forEach(bookInfo -> {
            //获取书籍上传用户和转到账户的金额
            BaseBookDTO baseBookDTO = new BaseBookDTO();
            baseBookDTO.setBookId(bookInfo.getBookId());
            Book book = bookServiceFeign.bookInfo(baseBookDTO).getData();
            BalanceUpdateDTO balanceUpdateDTO = new BalanceUpdateDTO();
            balanceUpdateDTO.setUserId(book.getUploadUserId());
            balanceUpdateDTO.setTotalAmount(bookInfo.getTotalAmount());
            //转账类型为加
            balanceUpdateDTO.setType(1);
            //转账到相应的账号余额中
            authUserServiceFeign.balanceUpdate(balanceUpdateDTO);

        });

        //更改订单的状态
        AliPayDTO aliPayDTO = new AliPayDTO();
        aliPayDTO.setOrderId(orderFinishDTO.getOrderId());
        orderServiceFeign.orderFinish(aliPayDTO);

        //将数据插入书籍和订单的关联表中，状态为待评价
        BookOrderInsertDTO bookOrderInsertDTO = new BookOrderInsertDTO();
        bookOrderInsertDTO.setOrderId(orderFinishDTO.getOrderId());
        bookOrderInsertDTO.setBookList(orderFinishDTO.getBookList());
        bookOrderInsertDTO.setId(new SnowFlake(0, 0).nextId());
        bookOrderInsertDTO.setUserId(orderFinishDTO.getUserId());
        //书籍订单关联表的状态为待评价
        bookOrderInsertDTO.setState(0);
        orderServiceFeign.orderBookInsert(bookOrderInsertDTO);

        //订单流水
        //todo

        log.info("转账成功！");
        return Wrapper.success();
    }

    /**
     * 查询订单详情列表
     * @param orderDTO
     * @return
     */
    @Override
    public Wrapper<List<BookInfoVo>> orderInfoList(OrderDTO orderDTO) {
        List<Long> bookIds = orderServiceFeign.orderBookIds(orderDTO).getData();
        return bookServiceFeign.bookInfoList(bookIds);
    }

    /**
     * 查看用户的订单列表（包含详情）
     * @param orderAllListDTO
     * @param rows 行数
     * @param page 页码
     * @return 返回用户的订单列表 （包含详情）
     */
    @Override
    public Wrapper<PageData<OrderAllListVo>> orderAllList(OrderAllListDTO orderAllListDTO, int page, int rows) {
        List<OrderAllListVo> orderAllListVoList = new ArrayList<>(10);
        //查看用户的订单列表
        OrderListDTO orderListDTO = new OrderListDTO();
        orderListDTO.setUserId(orderAllListDTO.getUserId());
        orderListDTO.setState(orderAllListDTO.getState());
        PageData<OrderListVo> orderList = orderServiceFeign.orderList(orderListDTO, page, rows).getData();
        if(0 == orderList.getTotalCount()){
            return Wrapper.success(orderAllListVoList, 0);
        }
        List<OrderListVo> orderListVoList = orderList.getData();
        //获取订单的书籍详情
        orderListVoList.forEach(orderListVo -> {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setOrderId(orderListVo.getOrderId());
            List<Long> bookIds = orderServiceFeign.orderBookIds(orderDTO).getData();
            List<BookInfoVo> bookInfoVoList = bookServiceFeign.bookInfoList(bookIds).getData();

            //将订单和订单的书籍详情放入返回数据中
            OrderAllListVo orderAllListVo = new OrderAllListVo();
            BeanUtils.copyProperties(orderListVo, orderAllListVo);
            orderAllListVo.setBookInfoVoList(bookInfoVoList);
            orderAllListVoList.add(orderAllListVo);
        });

        return Wrapper.success(orderAllListVoList, orderList.getTotalCount());
    }
}
