package com.e.study1;

public class List_Item {
    private String title;
    private String body;
    private int num;

    public List_Item(String title, String body, int num) {
        this.title = title;
        this.body = body;
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public int getNum() {
        return num;
    }
}
