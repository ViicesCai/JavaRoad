package edu.fdzc.beans;

/**
 * @author CAI
 * @time 2021/1/3
 */
public class Book {
    private String bookName;
    private String author;

    public Book() {
        super();
        System.out.println("Book被创建");
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
