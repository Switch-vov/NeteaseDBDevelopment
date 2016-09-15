package com.pc.mybatisanno;

import org.apache.ibatis.annotations.Select;

/**
 * Created by switch on 16/9/7.
 */
public interface GetUserInfoAnnotation {
    @Select("select * from user where id = #{id}")
    public User getUser(Integer id);
}
