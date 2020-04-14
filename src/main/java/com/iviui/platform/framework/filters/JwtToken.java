package com.iviui.platform.framework.filters;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Auther: ChengPan
 * @Description:
 */
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
