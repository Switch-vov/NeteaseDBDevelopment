package com.pc.mybatiscrud;

/**
 * Created by switch on 16/9/9.
 * 用户操作接口
 */
public interface UserOp {
    // 添加一个用户
    public void addUser(User user);

    // 更新用户信息
    public void updateUser(User user);

    // 通过id删除用户
    public void deleteUser(Integer id);

    // 通过id获取用户
    public User getUser(Integer id);
}
