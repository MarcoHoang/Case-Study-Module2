package model;

import java.io.Serializable;

public class Category implements Serializable {
    private String name;
    private String description;
    private int minAge;

    public Category(String name, String description, int minAge) {
        this.name = name;
        this.description = description;
        this.minAge = minAge;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    @Override
    public String toString() {
        return String.format(
                "\n" +
                        "╔════════════════════════════════════════════╗\n" +
                        "║ Category Name : %-25s ║\n" +
                        "║ Description   : %-25s ║\n" +
                        "║ Min Age       : %-25d ║\n" +
                        "╚════════════════════════════════════════════╝\n",
                name, description, minAge
        );
    }

}
