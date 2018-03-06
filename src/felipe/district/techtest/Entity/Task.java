package felipe.district.techtest.Entity;

import java.util.Date;

public class Task {
    private int id;
    private String title;
    private String description;
    private Date dueDate;

    public Task(int id, String title, String description, Date dueDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public Date getDueDate() {
        return this.dueDate;
    }

    public String toJson() {
        return "{" +
                    "\"id\": \"" + this.id + "\", " +
                    "\"title\": \"" + this.title + "\", " +
                    "\"description\": \"" + this.description + "\", " +
                    "\"dueDate\": \"" + this.dueDate.toString() + "\"" +
                "}";
    }
}
