package com.example.sqldemo;

public class PersonModel {

    public PersonModel(String firstName, int age, boolean isActive)
    {
        this.firstName = firstName;
        this.age = age;
        this.isActive = isActive;
    }

    public PersonModel(int id, String firstName, int age, boolean isActive)
    {
        this.id = id;
        this.firstName = firstName;
        this.age = age;
        this.isActive = isActive;
    }

    public PersonModel()
    {

    }

    @Override
    public String toString() {
        return "PersonModel{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", age=" + age +
                ", isActive=" + isActive +
                '}';
    }

    private int id;
    private String firstName;
    private int age;
    private boolean isActive;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
