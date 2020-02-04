package com.example.compiled;

public class Contacts {


    //private String name,email,mobile;
    private  String name,age,height,weight,data;

    public Contacts(String name,String age,String height,String weight,String data)
    {
        this.setName(name);
        this.setAge(age);
        this.setHeight(height);
        this.setWeight(weight);
        this.setData(data);

    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }



    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }



    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }




    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
