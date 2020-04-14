package com.iviui.platform.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 角色实体类
 *
 * @author ChengPan
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel(value="角色")
public class Role implements Serializable {
    private static final long serialVersionUID = -69368093209461616L;

    public Role(Integer rid){
     this.rid = rid;
    }

    @ApiModelProperty(value = "id")
    private Integer rid;
    @ApiModelProperty(value = "角色名")
    private String rname;
    @ApiModelProperty(value = "用户列表")
    private List<User> users;
    @ApiModelProperty(value = "权限列表")
    private List<Module> modules;

}
