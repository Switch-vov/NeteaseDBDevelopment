package com.pc.mybatiscomplex;

/**
 * Created by switch on 16/9/13.
 * 课程Bean(POJO)————与course表映射
 */
public class Course {
    private Integer id;
    private String courseName;
    private Teacher teacher;

    public Course(Integer id, String courseName, Teacher teacher) {
        this.id = id;
        this.courseName = courseName;
        this.teacher = teacher;
    }

    public Course(String courseName, Teacher teacher) {
        this.courseName = courseName;
        this.teacher = teacher;
    }

    public Course() {

    }

    public Integer getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
