package com.iviui.platform.controller.sys;

import com.iviui.platform.entity.Module;
import com.iviui.platform.service.ModuleService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (Module)表控制层
 * @author ChengPan
 */
@RestController
@Api(description = "接口")
@RequestMapping("module")
public class ModuleController {
    /**
     * 服务对象
     */
    @Resource
    private ModuleService moduleService;

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
    @GetMapping("selectOne")
    public Module selectOne(Integer id) {
        return this.moduleService.queryById(id);
    }

}
