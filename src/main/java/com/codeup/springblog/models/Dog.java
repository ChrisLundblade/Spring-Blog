package com.codeup.springblog.models;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

@Entity
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(columnDefinition = "tinyint(2) UNSIGNED not null")
    private byte age;
    @Column(columnDefinition = "varchar(200) NOT NULL", unique = true)
    private String name;
    @Column(columnDefinition = "char(2) DEFAULT 'XX'")
    private String resideState;

    public long getId() {
        return id;
    }

    public byte getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getResideState() {
        return resideState;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setResideState(String resideState) {
        this.resideState = resideState;
    }
}
