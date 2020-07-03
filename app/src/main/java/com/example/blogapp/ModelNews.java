package com.example.blogapp;

public class ModelNews {


    String newsTitle,newsDesc,newsImg,newsUrl;

    public String getNewsTitle() {
        return newsTitle;
    }

    public String getNewsDesc() {
        return newsDesc;
    }

    public String getNewsImg() {
        return newsImg;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public ModelNews(String newsTitle, String newsDesc, String newsImg, String newsUrl) {
        this.newsTitle = newsTitle;
        this.newsDesc = newsDesc;
        this.newsImg = newsImg;
        this.newsUrl = newsUrl;
    }
}
