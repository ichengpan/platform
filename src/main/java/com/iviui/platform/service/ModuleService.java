package com.iviui.platform.service;


import com.iviui.platform.entity.Module;

import java.util.List;

/**
 * (Module)表服务接口
 *
 * @author ChengPan
 */
public interface ModuleService {

    /**
     * 通过ID查询单条数据
     *
     * @param mid 主键
     * @return 实例对象
     */
    Module queryById(Integer mid);

   /**
     * 根据实体对象查询数据
     */
    List<Module> queryAll(Module module);
    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Module> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param module 实例对象
     * @return 实例对象
     */
    Module insert(Module module);

    /**
     * 修改数据
     *
     * @param module 实例对象
     * @return 实例对象
     */
    Module update(Module module);

    /**
     * 通过主键删除数据
     *
     * @param mid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer mid);

}
