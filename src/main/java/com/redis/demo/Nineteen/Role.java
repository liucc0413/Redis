package com.redis.demo.Nineteen;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias(value = "role")
public class Role implements Serializable {
    private int id;
    private String name;
    private SexEnum sex;

    public Role(){}
    public Role(Integer id, String name ) {
        this.id = id;
        this.name = name;
    }
    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void myInit() {
        System.out.println("myInit 初始化参数　"+this.getId() + " " +this.getName()  );
    }

    public void myDestroy() {
        System.out.println(this.getId() + " " +this.getName() +"　myDestroy 销毁" );
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
