package com.enggemy22.personalnotebad;

public class Data {
    public String title, body;

    public Data() {
//empty Constructor
    }

    public Data(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

}