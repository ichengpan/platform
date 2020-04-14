package com.iviui.platform.entity.goods;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description
 * @Author ChengPan
 * @Date2020/4/13 下午 01:49
 * @Version V1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel(value="商品基本信息")
public class GoodsInfo implements Serializable {

    @ApiModelProperty(value = "唯一标识")
    private Integer id;

    @ApiModelProperty(value = "商品的信息id")
    private Integer goodsId;

    @ApiModelProperty(value = "商品规格")
    private String goodsSpecificationIds;

    @ApiModelProperty(value = "商品序列号")
    private String goodsSn;

    @ApiModelProperty(value = "商品编码")
    private Integer goodsNumber;

    @ApiModelProperty(value = "零售价格")
    private BigDecimal retailPrice;

    @ApiModelProperty(value = "价格")
    private BigDecimal marketPrice;

    @ApiModelProperty(value = "商户ID")
    private Integer merchantId;

    @ApiModelProperty(value = "团购价格")
    private BigDecimal groupPrice;
}
