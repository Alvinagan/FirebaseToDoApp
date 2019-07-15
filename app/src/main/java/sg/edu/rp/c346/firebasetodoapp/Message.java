package sg.edu.rp.c346.firebasetodoapp;

import java.io.Serializable;

public class Message implements Serializable {

    private String title;
    private String date;

    @Override
    public String toString() {
        return "Message{" +
                "title='" + title + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public Message() {
// Default constructor required for calls to snapshot.toObject(Message.class)
    }

    public Message(String title, String date) {
        this.title = title;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
