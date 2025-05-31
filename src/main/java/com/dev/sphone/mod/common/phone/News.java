package com.dev.sphone.mod.common.phone;

public class News {

    private int id;
    private String title;
    private String content;
    private String image;
    private long date;
    private String author;

    public News() {
    }

    public News(int id, String title, String content, String image, long date, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.image = image;
        this.date = date;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
