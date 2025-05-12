package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Book implements Serializable {
    private String id;
    private String title;
    private Author author;
    private String publisher;
    private LocalDate publishDate;
    private Category category;
    private String language;
    private int totalCopies;
    private int availableCopies;
    private String description;

    public Book(String id, String title, Author author, String publisher,
                LocalDate publishDate, Category category, String language,
                int totalCopies, int availableCopies, String description) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.category = category;
        this.language = language;
        this.totalCopies = totalCopies;
        this.availableCopies = availableCopies;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return String.format(
                "\n" +
                        "╔═════════════════════════════════════════════╗\n" +
                        "║ ID              : %-25s ║\n" +
                        "║ Title           : %-25s ║\n" +
                        "║ Author          : %-25s ║\n" +
                        "║ Nationality     : %-25s ║\n" +
                        "║ Publisher       : %-25s ║\n" +
                        "║ Publish Date    : %-25s ║\n" +
                        "║ Category        : %-25s ║\n" +
                        "║ Min Age         : %-25d ║\n" +
                        "║ Language        : %-25s ║\n" +
                        "║ Total Copies    : %-25d ║\n" +
                        "║ Available Copies: %-25d ║\n" +
                        "║ Description     : %-25s ║\n" +
                        "╚═════════════════════════════════════════════╝\n",
                id, title, author.getName(), author.getNationality(), publisher, publishDate,
                category.getName(), category.getMinAge(), language, totalCopies, availableCopies, description
        );
    }

}
