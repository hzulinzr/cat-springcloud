package com.lin.fallback;

import com.lin.dto.BalanceUpdateDTO;
import com.lin.dto.BaseAuthUser;
import com.lin.feign.AuthUserServiceFeign;
import com.lin.model.AuthUser;
import com.lin.response.Wrapper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author lzr
 * @date 2020-01-11 15:27:57
 */
@Component
public class AuthUserServiceFallback implements AuthUserServiceFeign {
    @Override
    public Wrapper<Void> balanceUpdate(BalanceUpdateDTO balanceUpdateDTO) {
        return null;
    }

    @GetMapping("/user/person/info")
    @Override
    public Wrapper<AuthUser> userInfo(BaseAuthUser baseAuthUser) {
        return null;
    }
}
