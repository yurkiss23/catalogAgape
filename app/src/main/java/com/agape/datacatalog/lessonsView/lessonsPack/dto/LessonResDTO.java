package com.agape.datacatalog.lessonsView.lessonsPack.dto;

import com.agape.datacatalog.lessonsView.lessonsPack.network.LessonDTOService;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LessonResDTO {
//    @SerializedName("text")
//    @Expose
//    private String text;
    @SerializedName("main_image")
    @Expose
    private String main_image;
    @SerializedName("pk")
    @Expose
    private int pk;
    @SerializedName("title")
    @Expose
    private String title;

//    public String getText() { return text; }
//
//    public void setText(String text) { this.text = text; }

    public String getMain_image() { return LessonDTOService.getLessonUrl()[1] + main_image; }

//    public void setMain_image(String main_image) { this.main_image = main_image; }

    public int getPk() { return pk; }

//    public void setPk(int pk) { this.pk = pk; }

    public String getTitle() { return title; }

//    public void setTitle(String title) { this.title = title; }

    @Override
    public String toString() {
        return "LessonResDTO{" +
                "text='" + '\'' +
                ", main_image='" + main_image + '\'' +
                ", pk=" + pk +
                ", title='" + title + '\'' +
                '}';
    }
}
