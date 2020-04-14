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
 * @author ChengPan
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel(value="用户")
public class User implements Serializable {
    private static final long serialVersionUID = -33583492090793086L;

    public User(Integer uid){
     this.uid = uid;
    }

    @ApiModelProperty(value = "id")
    private Integer uid;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "密码")
    private String password;
   @ApiModelProperty(value = "角色列表")
    private List<Role> roles;


}
