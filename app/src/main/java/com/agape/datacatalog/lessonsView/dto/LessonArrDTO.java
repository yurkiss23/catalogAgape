package com.agape.datacatalog.lessonsView.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class LessonArrDTO {
    @SerializedName("lessons")
    @Expose
    private LessonDTO[] lessons;
    @SerializedName("pages")
    @Expose
    private int pages;

    public LessonDTO[] getLessons() { return lessons; }

    public void setLessons(LessonDTO[] lessons) { this.lessons = lessons; }

    public int getPages() { return pages; }

    public void setPages(int pages) { this.pages = pages; }

    @Override
    public String toString() {
        return "LessonArrDTO{" +
                "lessons=" + Arrays.toString(lessons) +
                ", pages=" + pages +
                '}';
    }
}
