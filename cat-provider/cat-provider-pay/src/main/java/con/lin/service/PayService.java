package con.lin.service;

import com.lin.dto.PayOrderDTO;

/**
 * @author lzr
 * @date 2020-01-30 17:14:13
 */
public interface PayService {
    /**
     * 发起支付
     * @param payOrderDTO 支付实体类
     * @return 返回支付页面
     */
    String payOrder(PayOrderDTO payOrderDTO);
}
