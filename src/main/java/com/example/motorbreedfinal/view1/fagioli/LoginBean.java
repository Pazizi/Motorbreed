package com.example.motorbreedfinal.view1.fagioli;

import java.util.regex.Pattern;

public class LoginBean {

    protected String email;
    protected String password;

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


    public boolean validation(){
        Pattern rfc2822 = Pattern.compile(
                "^[a-z0-9!#$%&'*+/=?^`{|}-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$");

        return rfc2822.matcher(email).matches();
    }


}
