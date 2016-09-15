package com.pc.mybatiscomplex;

/**
 * Created by switch on 16/9/13.
 * 讲师Bean(POJO)————与teacher表映射
 */
public class Teacher {
    private Integer id;
    private String teacherName;

    public Teacher(Integer id, String teacherName) {
        this.id = id;
        this.teacherName = teacherName;
    }

    public Teacher(String teacherName) {
        this.teacherName = teacherName;
    }

    public Integer getId() {
        return id;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
