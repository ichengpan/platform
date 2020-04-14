package com.iviui.platform.dao;

import com.iviui.platform.entity.Module;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Module)表数据库访问层
 * @author ChengPan
 */
public interface ModuleDao {

    /**
     * 通过ID查询单条数据
     *
     * @param mid 主键
     * @return 实例对象
     */
    Module queryById(Integer mid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Module> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param module 实例对象
     * @return 对象列表
     */
    List<Module> queryAll(Module module);

    /**
     * 新增数据
     *
     * @param module 实例对象
     * @return 影响行数
     */
    int insert(Module module);

    /**
     * 修改数据
     *
     * @param module 实例对象
     * @return 影响行数
     */
    int update(Module module);

    /**
     * 通过主键删除数据
     *
     * @param mid 主键
     * @return 影响行数
     */
    int deleteById(Integer mid);

}
