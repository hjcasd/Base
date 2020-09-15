package com.hjc.base.bean.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * 1. 默认用当前实体类的类名作为表名,类中的所有属性作为表中字段
 * 2. 自定义表名可以使用@Entity(tableName = "other")
 * 3. 复合主键: 多个字段构成主键,只有主键都一直才会覆盖数据
 * 4. 单列索引和组合索引提高数据访问速度
 */
@Entity
public class Person {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uid")
    private int id;

    private String name;

    private int age;

    @Ignore
    private int money;

    @Embedded
    private Address address;


    public Person(String name, int age) {
        this.name = name;
        this.age = age;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @NonNull
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
