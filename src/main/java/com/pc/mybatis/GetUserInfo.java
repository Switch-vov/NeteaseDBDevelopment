package com.pc.mybatis;

/**
 * Created by switch on 16/8/30.
 * 获取用户信息的映射器接口
 */
public interface GetUserInfo {
    /**
     * 获取用户名
     * @param id 用户标识
     * @return 用户对象
     */
    public User getUser(Integer id);
}
