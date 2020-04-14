package com.iviui.platform.controller.modules.goods;

import com.iviui.platform.entity.goods.GoodsInfo;
import com.iviui.platform.framework.utils.ResultVO;
import com.iviui.platform.service.goods.GoodsService;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author ChengPan
 * @Date2020/4/13 下午 01:47
 * @Version V1.0
 **/
@Api(description = "商品基本信息接口")
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @ApiOperation(value = "单个商品单个查询",notes = "根据id查询")
    @ApiImplicitParams({
            /**
             *参数描述
             */
            @ApiImplicitParam(paramType = "query",name = "id",dataType = "String",required = true,value = "唯一标识")
    })
    @ApiResponses({
            @ApiResponse(code = 200,message = "成功"),
            @ApiResponse(code=400,message = "请求参数没填好"),
            @ApiResponse(code=401,message = "没有权限"),
            @ApiResponse(code=403,message = "禁止访问"),
            @ApiResponse(code = 404,message = "请求路径不对")
    })
    @GetMapping("selectOne")
    @RequiresRoles("admin")
    public ResultVO<GoodsInfo> selectOne(String id) {
        return goodsService.queryById(id);
    }
}
