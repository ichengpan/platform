package com.iviui.platform.framework.config;

import com.iviui.platform.entity.Module;
import com.iviui.platform.entity.Role;
import com.iviui.platform.entity.User;
import com.iviui.platform.framework.filters.JWTUtil;
import com.iviui.platform.framework.filters.JwtToken;
import com.iviui.platform.framework.utils.RedisUtils;
import com.iviui.platform.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义权限控制类
 *
 * @author ChengPan
 * @Description:
 */
@Slf4j
@Service
public class MyRealm extends AuthorizingRealm{

    private UserService userService;
    private RedisUtils redisUtils;
    @Autowired
    public MyRealm(UserService userService, RedisUtils redisUtils) {
        super();
        this.userService=userService;
        this.redisUtils=redisUtils;
    }

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 认证.登录
     * @param auth
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String username = JWTUtil.getUsername(token);
        if (username == null) {
            throw new AuthenticationException("令牌无效");
        }
        User userBean = (User) redisUtils.get(token);
        if (userBean == null) {
            throw new AuthenticationException("令牌已过期");
        } else {
            redisUtils.expire(token, 60);
            return new SimpleAuthenticationInfo(token, token, "MyRealm");
        }
    }

    /**
     * 授权
     * @param principal
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        String token =(String)principal.getPrimaryPrincipal();
        String username = JWTUtil.getUsername(token);
        User user= userService.findUserByUserName(username);
        List<String> permissions=new ArrayList<>();
        List<String> rolesName=new ArrayList<>();
        List<Role> roles = user.getRoles();
        if(roles.size()>0) {
            for(Role role : roles) {
                rolesName.add(role.getRname());
                List<Module> modules = role.getModules();
                if(modules.size()>0) {
                    for(Module module : modules) {
                        permissions.add(module.getMname());
                    }
                }
            }
        }
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        //将角色放入shiro中
        info.addRoles(rolesName);
        //将权限放入shiro中
        info.addStringPermissions(permissions);
        return info;
    }

}
