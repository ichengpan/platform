package com.iviui.platform.service;


import com.iviui.platform.entity.User;

import java.util.List;

/**
 * (User)表服务接口
 *
 * @author ChengPan
 */
public interface UserService {

    /**
     * 登陆
     * @param userName
     * @param password
     * @return
     */
//    public LoginResult login(String userName, String password);

    /**
     * 通过ID查询单条数据
     *
     * @param uid 主键
     * @return 实例对象
     */
    User queryById(Integer uid);

   /**
     * 根据实体对象查询数据
     */
    List<User> queryAll(User user);
    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<User> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User update(User user);

    /**
     * 通过主键删除数据
     *
     * @param uid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer uid);

    User findUserByUserName(String username);
}
