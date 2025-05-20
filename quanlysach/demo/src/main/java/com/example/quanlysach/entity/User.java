package com.example.quanlysach.entity;

public class User {
    private long id;
    private String name;
    private String email;
    private String password;
    private String username;
    private String fullname;
    private String phoneNumber;
    private String identityNumber;
    private int age;
    private String birthday;
    private String address;

    // Constructor mặc định
    public User() {}

    // Constructor có tham số
    public User(long id, String name, String email, String password, String username, String fullname,
                String phoneNumber, String identityNumber, int age, String birthday, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.username = username;
        this.fullname = fullname;
        this.phoneNumber = phoneNumber;
        this.identityNumber = identityNumber;
        this.age = age;
        this.birthday = birthday;
        this.address = address;
    }

    // Getter và Setter cho các thuộc tính
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Thêm các getter và setter cho các thuộc tính mới
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Phương thức display
    public void displayUserInfo() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
        System.out.println("Username: " + username);
        System.out.println("Fullname: " + fullname);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Identity Number: " + identityNumber);
        System.out.println("Age: " + age);
        System.out.println("Birthday: " + birthday);
        System.out.println("Address: " + address);
    }
}
