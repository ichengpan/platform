package com.iviui.platform.controller.sys;

import com.iviui.platform.entity.User;
import com.iviui.platform.framework.filters.JWTUtil;
import com.iviui.platform.framework.utils.RedisUtils;
import com.iviui.platform.framework.utils.ResultCode;
import com.iviui.platform.framework.utils.ResultVO;
import com.iviui.platform.service.UserService;
import io.swagger.annotations.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * (User)表控制层
 *
 * @author ChengPan
 */
@RestController
@Api(description = "获取Token" ,tags = "用户模块")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;
    @Resource
    private RedisUtils redisUtils;
//    @Autowired
//    private
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
         @ApiOperation(value = "单个查询",notes = "根据id查询")
    @ApiImplicitParams({
    /**
      *参数描述
      */
      @ApiImplicitParam(paramType = "query",name = "id",dataType = "String",required = true,value = "唯一标识")
    })
    @ApiResponses({
            @ApiResponse(code = 200,message = "成功"),
            @ApiResponse(code=400,message = "请求参数没填好"),
            @ApiResponse(code = 404,message = "请求路径没对")
    })
    @GetMapping("/selectOne")
    public User selectOne(Integer id) {
        return this.userService.queryById(id);
    }

    /**
     * 获取token
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/GetToken")
    public ResultVO login(@RequestParam("username") String username, @RequestParam("password") String password) {
        if(username==null){
            return new ResultVO(401,"请输入用户名","用户名不能为空");
        }else if(password == null){
            return new ResultVO(401,"请输入密码","密码不能为空");
        }else{
            User userBean = userService.findUserByUserName(username);
            if(userBean == null){
                return new ResultVO(401,"用户不存在","获取token失败");
            }else if(userBean.getPassword()==null || !userBean.getPassword().equals(password)){
                return new ResultVO(401,"密码错误","获取token失败");
            }else{
                String token = JWTUtil.sign(username, password);
                redisUtils.set(token,userBean,60);
                return ResultVO.getSuccess(ResultCode.SUCCESS, token);
            }
        }
    }

    @GetMapping("/article")
    public ResultVO article() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return new ResultVO(200, "You are already logged in", null);
        } else {
            return new ResultVO(200, "You are guest", null);
        }
    }

    @GetMapping("/require_auth")
    @RequiresAuthentication
    public ResultVO requireAuth() {
        return new ResultVO(200, "You are authenticated", null);
    }

    @GetMapping("/require_role")
    @RequiresRoles("admin")
    public ResultVO requireRole() {
        return new ResultVO(200, "You are visiting require_role", null);
    }

    @GetMapping("/query")
    @RequiresPermissions(logical = Logical.AND, value = {"query"})
    public ResultVO requirePermission() {
        return new ResultVO(200, "You are visiting permission require query", null);
    }
    @GetMapping("/add")
    @RequiresPermissions(logical = Logical.AND, value = {"add"})
    public ResultVO add() {
        return new ResultVO(200, "You are visiting permission require add", null);
    }

    @GetMapping(path = "/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResultVO unauthorized(HttpServletRequest request) {
        String data = (String) request.getAttribute("msg");
        return new ResultVO(401,"认证失败",data);
    }

}
