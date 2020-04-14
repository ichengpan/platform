package com.iviui.platform.service.impl;

import com.iviui.platform.dao.RoleDao;
import com.iviui.platform.entity.Role;
import com.iviui.platform.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色(Role)表服务实现类
 *
 * @author ChengPan
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleDao roleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param rid 主键
     * @return 实例对象
     */
    @Override
    public Role queryById(Integer rid) {
        return this.roleDao.queryById(rid);
    }

  /**
     *通过实例对象查询数据
     *
     * @param: Role
     * @return: 对象列表
     */
    @Override
    public List< Role> queryAll(Role role) {
       return this.roleDao.queryAll(role);
    }
    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Role> queryAllByLimit(int offset, int limit) {
        return this.roleDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    @Override
    public Role insert(Role role) {
        this.roleDao.insert(role);
        return role;
    }

    /**
     * 修改数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    @Override
    public Role update(Role role) {
        this.roleDao.update(role);
        return this.queryById(role.getRid());
    }

    /**
     * 通过主键删除数据
     *
     * @param rid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer rid) {
        return this.roleDao.deleteById(rid) > 0;
    }
}
