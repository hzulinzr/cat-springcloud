package con.lin.service;

import com.lin.dto.PayOrderDTO;

/**
 * @author lzr
 * @date 2020-01-30 17:14:13
 */
public interface PayService {
    String payOrder(PayOrderDTO payOrderDTO);
}
