package com.lin.feign;

import com.lin.dto.BalanceUpdateDTO;
import com.lin.dto.BaseAuthUser;
import com.lin.fallback.AuthUserServiceFallback;
import com.lin.model.AuthUser;
import com.lin.response.Wrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author lzr
 * @date 2020-01-11 15:27:35
 */
@FeignClient(name = "cat-provider-user", fallback = AuthUserServiceFallback.class)
public interface AuthUserServiceFeign {
    /**
     * 用户账号余额转账
     * @param balanceUpdateDTO
     * @return
     */
    @PostMapping("/user/balance/update")
    Wrapper<Void> balanceUpdate(BalanceUpdateDTO balanceUpdateDTO);

    /**
     * 查看用户详情信息
     * @param baseAuthUser
     * @return 返回用户详情
     */
    @GetMapping("/user/person/info")
    Wrapper<AuthUser> userInfo(BaseAuthUser baseAuthUser);
}
