package com.workintech.model;

import javax.xml.namespace.QName;
import java.time.LocalDate;

public class Member extends Person {
    private int member_id;
    private LocalDate date_of_membership;
    private int max_book_limit;


    public Member(String name,int member_id, LocalDate date_of_membership, int max_book_limit) {
        super(name);
        this.member_id = member_id;
        this.date_of_membership = date_of_membership;
        this.max_book_limit = 5;

    }

    public String getMember() {
        return super.getName();
    }

    public int getMaxBookLimit() {
        return max_book_limit;
    }

    public void decreaseBookLimit() {
        max_book_limit--;
    }

    public void increaseBookLimit() {
        max_book_limit++;
    }

    public boolean canBorrowBook() {
        return max_book_limit > 0;
    }

    public void payBill() {
        System.out.println("Fatura ödendi: -15 TL");
    }

    public void returnBook() {
        System.out.println("Kitap iade edildi.");
        payBill();
    }
}
