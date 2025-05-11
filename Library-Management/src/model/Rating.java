package model;

import java.io.Serializable;

public class Rating implements Serializable {
    private String bookId;
    private String userId;
    private int stars;
    private String comment;

    public Rating() {}

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
}
