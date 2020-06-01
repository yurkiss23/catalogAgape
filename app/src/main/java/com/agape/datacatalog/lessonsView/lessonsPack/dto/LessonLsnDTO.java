package com.agape.datacatalog.lessonsView.lessonsPack.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LessonLsnDTO {
    @SerializedName("number_lessons")
    @Expose
    private int number_lessons;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("images")
    @Expose
    private int images;
    @SerializedName("file")
    @Expose
    private String file;
    @SerializedName("pk")
    @Expose
    private int pk;

    public int getNumber_lessons() { return number_lessons; }

    public void setNumber_lessons(int number_lessons) { this.number_lessons = number_lessons; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public int getImages() { return images; }

    public void setImages(int images) { this.images = images; }

    public String getFile() { return file; }

    public void setFile(String file) { this.file = file; }

    public int getPk() { return pk; }

    public void setPk(int pk) { this.pk = pk; }

    @Override
    public String toString() {
        return "LessonLsnDTO{" +
                "number_lessons=" + number_lessons +
                ", title='" + title + '\'' +
                ", images=" + images +
                ", file='" + file + '\'' +
                ", pk=" + pk +
                '}';
    }
}
