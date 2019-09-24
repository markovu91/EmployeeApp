/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employermain;

import java.io.Serializable;

/**
 *
 * @author marko
 */
public class Person implements Serializable{
    
    private int id, age;
    private String name, address;
    private double income;
    
    public Person (){
    
    }
    
    public Person (int id, String name, int age, String address, double income ) {
        this.id=id;
        this.name=name;
        this.age=age;
        this.address=address;
        this.income=income;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName (String name) {
        this.name=name;
    }
    
    public String getAddress(){
        return address;
    }
    
    public void setAddress (String address) {
        this.address=address;
    }
    
    public int getAge(){
        return age;
    }
    
    public void setAge (int age) {
        this.age=age;
    }
    
    public double getIncome(){
        return income;
    }
    
    public void setIncome (double income) {
        this.income=income;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", age=" + age + ", name=" + name + ", address=" + address + ", income=" + income + '}';
    }
}
