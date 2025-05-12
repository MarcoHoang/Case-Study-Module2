package model;

import java.io.Serializable;

public class Rating implements Serializable {
    private String bookId;
    private final String userId;
    private int stars;
    private String comment;

    public Rating(String bookId, String userId, int stars, String comment) {
        this.bookId = bookId;
        this.userId = userId;
        this.stars = stars;
        this.comment = comment;
    }

    public String getBookId() { return bookId; }
    public void setBookId(String bookId) { this.bookId = bookId; }

    public String getUserId() { return userId; }

    public int getStars() { return stars; }
    public void setStars(int stars) { this.stars = stars; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    @Override
    public String toString() {
        return String.format(
                        "\n" +
                        "╔══════════════════════════════════════╗\n" +
                        "║ Book ID   : %-25s ║\n" +
                        "║ User ID   : %-25s ║\n" +
                        "║ Stars     : %-25d ║\n" +
                        "║ Comment   : %-25s ║\n" +
                        "╚══════════════════════════════════════╝\n",
                bookId, userId, stars, comment == null || comment.isBlank() ? "Không có" : comment
        );
    }

}
