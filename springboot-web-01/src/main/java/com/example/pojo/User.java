package com.example.pojo;


import java.time.LocalDateTime;

public class User {
        public Integer id;
        private String username;
        private String password;
        private String name;
        private Integer age;
    public LocalDateTime updataTime;

    // 无参构造方法
    public User() {
    }

    // 全参构造方法
    public User(Integer id, String username, String password, String name, Integer age, LocalDateTime updataTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
        this.updataTime = updataTime;
    }

    // id 的 getter 和 setter 方法
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // username 的 getter 和 setter 方法
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // password 的 getter 和 setter 方法
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // name 的 getter 和 setter 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // age 的 getter 和 setter 方法
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    // updateTime 的 getter 和 setter 方法
    public LocalDateTime getUpdateTime() {
        return updataTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updataTime = updateTime;
    }

    // toString 方法
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", updateTime=" + updataTime +
                '}';
    }


}
