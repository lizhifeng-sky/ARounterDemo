package com.example.cglib;

/**
 * Created by Administrator on 2017/9/12 0012.
 */
public class Person {
    private String name;
    private String age;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void method(){
        System.out.println(name+"  "+age);
    }
}
