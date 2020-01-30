package con.lin.controller;

import com.lin.dto.PayOrderDTO;
import con.lin.service.PayService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lzr
 * @date 2020-01-30 17:08:36
 */
@RestController
public class PayController {
    private PayService payService;

    public PayController(PayService payService) {
        this.payService = payService;
    }

    @PostMapping("/pay")
    public String payOrder(PayOrderDTO payOrderDTO){
        return payService.payOrder(payOrderDTO);
    }
}
