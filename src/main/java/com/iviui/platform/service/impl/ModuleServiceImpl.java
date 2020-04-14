package com.iviui.platform.service.impl;

import com.iviui.platform.dao.ModuleDao;
import com.iviui.platform.entity.Module;
import com.iviui.platform.service.ModuleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Module)表服务实现类
 *
 * @author ChengPan
 */
@Service("moduleService")
public class ModuleServiceImpl implements ModuleService {
    @Resource
    private ModuleDao moduleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param mid 主键
     * @return 实例对象
     */
    @Override
    public Module queryById(Integer mid) {
        return this.moduleDao.queryById(mid);
    }

  /**
     *通过实例对象查询数据
     *
     * @param: Module
     * @return: 对象列表
     */
    @Override
    public List< Module> queryAll(Module module) {
       return this.moduleDao.queryAll(module);
    }
    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Module> queryAllByLimit(int offset, int limit) {
        return this.moduleDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param module 实例对象
     * @return 实例对象
     */
    @Override
    public Module insert(Module module) {
        this.moduleDao.insert(module);
        return module;
    }

    /**
     * 修改数据
     *
     * @param module 实例对象
     * @return 实例对象
     */
    @Override
    public Module update(Module module) {
        this.moduleDao.update(module);
        return this.queryById(module.getMid());
    }

    /**
     * 通过主键删除数据
     *
     * @param mid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer mid) {
        return this.moduleDao.deleteById(mid) > 0;
    }
}
