package com.agape.datacatalog.newsView.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsResDTO {
    @SerializedName("pk")
    @Expose
    private int pk;
    @SerializedName("main_image")
    @Expose
    private String main_image;
    @SerializedName("data")
    @Expose
    private String data;
    @SerializedName("title")
    @Expose
    private String title;

    public int getPk() { return pk; }

    public void setPk(int pk) { this.pk = pk; }

    public String getMain_image() { return main_image; }

    public void setMain_image(String main_image) { this.main_image = main_image; }

    public String getData() { return data; }

    public void setData(String data) { this.data = data; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    @Override
    public String toString() {
        return "NewsResDTO{" +
                "pk=" + pk +
                ", main_image='" + main_image + '\'' +
                ", data='" + data + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
