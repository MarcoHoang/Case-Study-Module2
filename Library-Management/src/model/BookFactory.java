package model;

import java.time.LocalDate;

public class BookFactory {
    public static Book createBook(String id, String title, String authorName, String publisher,
                                  String categoryName, String language, int totalCopies,
                                  int availableCopies, String nationality, int minAge) {
        Author author = new Author(authorName, nationality);
        Category category = new Category(categoryName, "Mô tả thể loại", minAge);
        LocalDate publishDate = LocalDate.now();
        return new Book(id, title, author, publisher, publishDate, category, language, totalCopies, availableCopies, "Mô tả sách");
    }
}
