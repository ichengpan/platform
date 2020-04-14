package com.iviui.platform.service.goods;

import com.iviui.platform.entity.goods.GoodsInfo;
import com.iviui.platform.framework.utils.ResultVO;

public interface GoodsService {
    ResultVO<GoodsInfo> queryById(String id);
}
