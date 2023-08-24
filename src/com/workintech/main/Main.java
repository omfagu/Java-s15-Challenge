package com.workintech.main;

import com.workintech.model.Book;
import com.workintech.enums.Status;
import com.workintech.model.Librarian;
import com.workintech.model.Member;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Map<String, Book> bookMap = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        Member member = null;

        System.out.println("************************************************");
        System.out.println("           Kütüphane Sistemine Hoş Geldiniz");
        System.out.println("************************************************");

        Book book1 = new Book(Book.generateNewID(bookMap), "Leo Tolstoy", "War and Peace", Status.AVAILABLE, LocalDate.now(), "Hi-Fi", "Library");
        bookMap.put(book1.getBook_ID(), book1);

        Book book2 = new Book(Book.generateNewID(bookMap), "George Orwell", "1984", Status.AVAILABLE, LocalDate.now(), "Sci-Fi", "Library");
        bookMap.put(book2.getBook_ID(), book2);

        Book book3 = new Book(Book.generateNewID(bookMap), "Agatha Christie", "Murder on the Orient Express", Status.AVAILABLE, LocalDate.now(), "Mystery", "Library");
        bookMap.put(book3.getBook_ID(), book3);

        Book book4 = new Book(Book.generateNewID(bookMap), "Harper Lee", "To Kill a Mockingbird", Status.AVAILABLE, LocalDate.now(), "Classics", "Library");
        bookMap.put(book4.getBook_ID(), book4);

        Book book5 = new Book(Book.generateNewID(bookMap), "Stephen King", "The Shining", Status.AVAILABLE, LocalDate.now(), "Horror", "Library");
        bookMap.put(book5.getBook_ID(), book5);

        Librarian librarian = new Librarian("omer", "1234");

        while (true) {
            System.out.println("Lütfen yapmak istediğiniz işlemi seçiniz:");
            System.out.println("1- Kitap Ekle Veya Sil (Sadece Yetkili)");
            System.out.println("2- Kitap Ara");
            System.out.println("3- Kitap Ödünç Al ");
            System.out.println("4- Çıkış");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Kullanıcı Adı: ");
                    String usernameAddDelete = scanner.nextLine();
                    System.out.print("Şifre: ");
                    String passwordAddDelete = scanner.nextLine();

                    if (librarianLogin(librarian, usernameAddDelete, passwordAddDelete)) {
                        System.out.println("1- Kitap eklemek için 1`e basiniz");
                        System.out.println("2- Kitap silmek için 2`ye basiniz");
                        String addDeleteChoice = scanner.nextLine();

                        if (addDeleteChoice.equals("1")) {
                            System.out.println("Yazar:");
                            String author = scanner.nextLine();
                            System.out.println("Kitap Adı:");
                            String name = scanner.nextLine();
                            LocalDate dateOfPurchase = LocalDate.now();
                            System.out.println("Kitap Türü:");
                            String category = scanner.nextLine();
                            String owner = "Library";
                            Status status = Status.AVAILABLE;

                            Book newBook = new Book(Book.generateNewID(bookMap), author, name, status, dateOfPurchase, category, owner);
                            bookMap.put(newBook.getBook_ID(), newBook);
                            System.out.println("Kitap başarıyla eklendi.");
                        } else if (addDeleteChoice.equals("2")) {
                            System.out.println("Silmek istediğiniz kitabın ID'sini girin:");
                            String deleteID = scanner.nextLine();

                            if (bookMap.containsKey(deleteID)) {
                                bookMap.remove(deleteID);
                                System.out.println("Kitap başarıyla silindi.");
                            } else {
                                System.out.println("Belirtilen ID'ye sahip kitap bulunamadı.");
                            }
                        } else {
                            System.out.println("Geçersiz seçenek.");
                        }
                    } else {
                        System.out.println("Sadece kütüphane görevlisi kitap ekleyebilir veya silebilir.");
                    }
                    break;

                case 2:
                    System.out.println("Lütfen yapmak istediğiniz arama tipini seçiniz:");
                    System.out.println("1- Kitap ID'ye Göre Arama");
                    System.out.println("2- Kitap İsmine Göre Arama");
                    System.out.println("3- Kitap Yazarına Göre Arama");
                    System.out.println("4- Kitap Kategorisine Göre Arama");

                    int searchChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (searchChoice < 1 || searchChoice > 4) {
                        System.out.println("Geçersiz seçenek, lütfen tekrar deneyin.");
                        break;
                    }

                    System.out.println("Arama teriminizi giriniz:");
                    String searchTerm = scanner.nextLine();
                    boolean bookFound = false;

                    for (Map.Entry<String, Book> entry : bookMap.entrySet()) {
                        Book book = entry.getValue();

                        if ((searchChoice == 1 && book.getBook_ID().equals(searchTerm)) ||
                                (searchChoice == 2 && book.getName().equalsIgnoreCase(searchTerm)) ||
                                (searchChoice == 3 && book.getAuthor().equalsIgnoreCase(searchTerm)) ||
                                (searchChoice == 4 && book.getCategory().equalsIgnoreCase(searchTerm))) {

                            System.out.println("Kitap ID: " + book.getBook_ID());
                            System.out.println("Yazar: " + book.getAuthor());
                            System.out.println("Kitap Adı: " + book.getName());
                            System.out.println("Sahip: " + book.getOwner());
                            System.out.println("--------------");

                            if (book.getStatus() == Status.BORROWED) {
                                System.out.println("Kitap durumu: Ödünç Alınmış");
                                System.out.println("Kitap sahibi: " + book.getOwner());
                            }

                            bookFound = true;
                        }
                    }

                    if (!bookFound) {
                        System.out.println("Belirtilen arama kriterine uygun kitap bulunamadı.");
                    }
                    break;


                case 3:
                    System.out.println("1- Kitap Alma");
                    System.out.println("2- Kitap Iade Etme");
                    int borrowOrReturnChoice = scanner.nextInt();
                    scanner.nextLine();

                    switch (borrowOrReturnChoice) {
                        case 1:
                            System.out.println("Üye Adını Girin:");
                            String memberName = scanner.nextLine();

                            member = new Member(memberName, 1, LocalDate.now(), 5);
                            if (member.canBorrowBook()) {
                                System.out.println("Kitap ID'sini girin:");
                                String bookID = scanner.nextLine();

                                if (bookMap.containsKey(bookID)) {
                                    Book selectedBook = bookMap.get(bookID);

                                    if (selectedBook.getStatus() == Status.AVAILABLE) {
                                        selectedBook.setStatus(Status.BORROWED);
                                        selectedBook.setOwner(member.getMember());
                                        selectedBook.setDateOfPurchase(LocalDate.now());
                                        member.decreaseBookLimit();
                                        System.out.println("Kitap ödünç alındı. Kalan kitap limitiniz: " + member.getMaxBookLimit());


                                        member.payBill();
                                    } else {
                                        System.out.println("Kitap ödünç alınamadı. Belirtilen kitap zaten ödünç alınmış.");
                                    }
                                } else {
                                    System.out.println("Belirtilen ID'ye sahip kitap bulunamadı.");
                                }
                            } else {
                                System.out.println("Kitap ödünç alınamadı. Kitap limitiniz dolmuş durumda.");
                            }
                            break;

                        case 2:
                            System.out.println("Kitap ID'sini girin:");
                            String returnBookID = scanner.nextLine();

                            if (bookMap.containsKey(returnBookID)) {
                                Book returnedBook = bookMap.get(returnBookID);

                                if (returnedBook.getStatus() == Status.BORROWED) {
                                    String bookOwner = returnedBook.getOwner();

                                    if (bookOwner != null && bookOwner.equals(member.getMember())) {
                                        returnedBook.setStatus(Status.AVAILABLE);
                                        returnedBook.setOwner("Library");
                                        returnedBook.setDateOfPurchase(LocalDate.now());
                                        member.increaseBookLimit();
                                        System.out.println("Kitap iade edildia");

                                    } else {
                                        System.out.println("Belirtilen kitap başkasına ait. Kitap iade edilemez.");
                                    }
                                } else {
                                    System.out.println("Belirtilen kitap ödünç alınmamış.");
                                }
                            } else {
                                System.out.println("Belirtilen ID'ye sahip kitap bulunamadı.");
                            }
                            break;

                        default:
                            System.out.println("Geçersiz seçenek.");
                            break;
                    }
                    break;

                case 4:
                    System.out.println("************************************************");
                    System.out.println("Çıkış yapılıyor. İyi günler!");
                    System.out.println("************************************************");
                    return;

                default:
                    System.out.println("************************************************");
                    System.out.println("Geçersiz seçenek, lütfen tekrar deneyin.");
                    System.out.println("************************************************");
            }
        }
    }

    public static boolean librarianLogin(Librarian librarian, String username, String password) {
        return librarian.getLibrarianUserName().equals(username) && librarian.getPassword().equals(password);
    }
}
