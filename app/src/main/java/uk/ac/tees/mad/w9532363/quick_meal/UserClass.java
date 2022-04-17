package uk.ac.tees.mad.w9532363.quick_meal;

public class UserClass {

    String name, phone, email_Add, Password_Add;

    public UserClass() {
    }

    public UserClass(String name, String phone, String email_Add, String password_Add) {
        this.name = name;
        this.phone = phone;

        this.email_Add = email_Add;
        Password_Add = password_Add;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail_Add() {
        return email_Add;
    }

    public void setEmail_Add(String email_Add) {
        this.email_Add = email_Add;
    }

    public String getPassword_Add() {
        return Password_Add;
    }

    public void setPassword_Add(String password_Add) {
        Password_Add = password_Add;
    }
}
