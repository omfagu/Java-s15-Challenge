package com.workintech.model;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

import com.workintech.enums.Status;
public class Book {
    private String book_ID;
    private String author;
    private String name;
    private Status status;

    private LocalDate dateOfPurchase;

    private String category;
    private String owner;

    public Book(String book_ID, String author, String name, Status status, LocalDate dateOfPurchase, String category, String owner) {
        this.book_ID = book_ID;
        this.author = author;
        this.name = name;
        this.status = status;
        this.dateOfPurchase = dateOfPurchase;
        this.category = category;
        this.owner = "Library";
    }

    public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public String getBook_ID() {
        return book_ID;
    }

    public String getCategory() {
        return category;
    }

    public LocalDate getDateOfPurchase() {
        return dateOfPurchase;
    }
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setDateOfPurchase(LocalDate dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }
    public static String generateNewID(Map<String, Book> bookMap) {
        int maxID = 0;
        for (Map.Entry<String, Book> entry : bookMap.entrySet()) {
            int id = Integer.parseInt(entry.getKey());
            if (id > maxID) {
                maxID = id;
            }
        }
        return String.valueOf(maxID + 1);
    }

}
