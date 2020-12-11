package com.example.newmsg;

import java.util.List;

public class User {
    private String id;
    private String email;
    private String name;
    private Integer height;
    private Integer weight;
    private Integer age;


    public User() {
    }

    public User(String id, String email, String name, Integer height, Integer weight, Integer age) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.height=height;
        this.weight=weight;
        this.age=age;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHeight(){
        return height;
    }
    public void setHeight(Integer height){
        this.height=height;
    }

    public Integer getWeight(){
        return weight;
    }
    public void setWeight(Integer weight){
        this.weight=weight;
    }

    public Integer getAge(){
        return age;
    }
    public void setAge(Integer age){
        this.age=age;
    }

}
