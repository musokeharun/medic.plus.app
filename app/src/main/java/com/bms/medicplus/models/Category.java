package com.bms.medicplus.models;

public class Category {
    String text;
    String image;

    public Category(String text, String image) {
        this.text = text;
        this.image = image;
    }


    public String getImage() {
        return image;
    }

    public String getText() {
        return text;
    }
}
