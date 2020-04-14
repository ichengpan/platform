package com.iviui.platform.dao;

import com.iviui.platform.entity.goods.GoodsInfo;

public interface GoodsDao {
    GoodsInfo queryById(String id);
}
