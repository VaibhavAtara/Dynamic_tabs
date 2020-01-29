package com.example.dynamic_tabs;

public class Book {

    private  String title,des,category;
    private int thumbnail;

    public Book() {
    }

    public Book(String title, String des, String category, int thumbnail) {
        this.title = title;
        this.des = des;
        this.category = category;
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
