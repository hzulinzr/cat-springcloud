package con.lin.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.lin.dto.PayOrderDTO;
import con.lin.config.alipay.AlipayConfig;
import con.lin.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author lzr
 * @date 2020-01-30 17:14:31
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService {

    /**
     * 发起支付
     * @param payOrderDTO 支付实体类
     * @return 返回支付页面
     */
    @Override
    public String payOrder(PayOrderDTO payOrderDTO) {
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl,AlipayConfig.app_id,
                AlipayConfig.merchant_private_key,
                "json",
                AlipayConfig.charset,
                AlipayConfig.alipay_public_key,
                AlipayConfig.sign_type);

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ payOrderDTO.getOrderNo() +"\","
                + "\"total_amount\":\""+ payOrderDTO.getTotalAmount() +"\","
                + "\"subject\":\""+ payOrderDTO.getBookName() +"\","
                + "\"body\":\""+ payOrderDTO.getContent() +"\","
                + "\"timeout_express\":\""+ "1c" +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求
        String result = null;
        try {
            result = alipayClient.pageExecute(alipayRequest).getBody();
            log.info("订单支付页面");
        } catch (AlipayApiException e) {
            log.info("异常错误", e);
        }
        return result;
    }
}
