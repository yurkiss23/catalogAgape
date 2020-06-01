package com.agape.datacatalog.lessonsView.lessonsPack.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LessonDTO {
    @SerializedName("pk")
    @Expose
    private int pk;
    @SerializedName("number_lessons")
    @Expose
    private int number_lessons;
    @SerializedName("title")
    @Expose
    private String title;

    public int getPk() { return pk; }

    public void setPk(int pk) { this.pk = pk; }

    public int getNumber_lessons() { return number_lessons; }

    public void setNumber_lessons(int number_lessons) { this.number_lessons = number_lessons; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    @Override
    public String toString() {
        return "LessonDTO{" +
                "pk=" + pk +
                ", number_lessons=" + number_lessons +
                ", title='" + title + '\'' +
                '}';
    }
}
