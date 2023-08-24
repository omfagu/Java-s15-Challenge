package com.workintech.model;

public class Librarian extends Person {

    protected String password;
    public Librarian(String name, String password) {
        super(name);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
    public String getLibrarianUserName() {
        return super.getName();
    }
}
