package model;

import java.io.Serializable;

public class Author implements Serializable {
    private String name;
    private String nationality;

    public Author(String name, String nationality) {
        this.name = name;
        this.nationality = nationality;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }

    @Override
    public String toString() {
        return String.format(
                        "\n" +
                        "╔═══════════════════════════════════════════╗\n" +
                        "║ Author Name    : %-25s ║\n" +
                        "║ Nationality    : %-25s ║\n" +
                        "╚═══════════════════════════════════════════╝\n",
                name, nationality
        );
    }
}
