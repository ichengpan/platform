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
 * 实体类
 *
 * @author ChengPan
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel(value="权限")
public class Module implements Serializable {
    private static final long serialVersionUID = -43602119704527778L;

    public Module(Integer mid){
     this.mid = mid;
    }

    @ApiModelProperty(value = "id")
    private Integer mid;
    @ApiModelProperty(value = "权限名")
    private String mname;
    @ApiModelProperty(value = "角色列表")
    private List<Role> roles;


}
