package com.agape.datacatalog.newsView.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsDTO {
    @SerializedName("top_news")
    @Expose
    private Boolean top_news;
    @SerializedName("main_image")
    @Expose
    private String main_image;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("image1")
    @Expose
    private String image1;
    @SerializedName("image2")
    @Expose
    private String image2;
    @SerializedName("image3")
    @Expose
    private String image3;
    @SerializedName("image4")
    @Expose
    private String image4;

    public Boolean getTop_news() { return top_news; }
    public void setTop_news(Boolean top_news) { this.top_news = top_news; }
    public String getMain_image() { return main_image; }
    public void setMain_image(String main_image) { this.main_image = main_image; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public String getImage1() { return image1; }
    public void setImage1(String image1) { this.image1 = image1; }
    public String getImage2() { return image2; }
    public void setImage2(String image2) { this.image2 = image2; }
    public String getImage3() { return image3; }
    public void setImage3(String image3) { this.image3 = image3; }
    public String getImage4() { return image4; }
    public void setImage4(String image4) { this.image4 = image4; }

    @Override
    public String toString() {
        return "NewsDTO{" +
                "top_news=" + top_news +
                ", main_image='" + main_image + '\'' +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", image1='" + image1 + '\'' +
                ", image2='" + image2 + '\'' +
                ", image3='" + image3 + '\'' +
                ", image4='" + image4 + '\'' +
                '}';
    }
}
