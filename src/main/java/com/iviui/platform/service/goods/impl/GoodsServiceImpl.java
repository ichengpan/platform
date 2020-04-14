package com.iviui.platform.service.goods.impl;

import com.iviui.platform.dao.GoodsDao;
import com.iviui.platform.entity.goods.GoodsInfo;
import com.iviui.platform.framework.utils.ResultVO;
import com.iviui.platform.service.goods.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description
 * @Author ChengPan
 * @Date2020/4/13 下午 02:00
 * @Version V1.0
 **/
@CacheConfig(cacheNames = "goods",cacheManager = "cacheManager")
@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private GoodsDao goodsDao;

    @Override
    public ResultVO<GoodsInfo> queryById(String id) {
        GoodsInfo goodsDao = this.goodsDao.queryById(id);
        return ResultVO.setSuccessData(goodsDao);
    }

}
