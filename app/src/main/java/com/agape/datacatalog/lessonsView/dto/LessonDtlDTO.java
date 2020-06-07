package com.agape.datacatalog.lessonsView.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class LessonDtlDTO {
    @SerializedName("images")
    @Expose
    private LessonsImgDTO[] images;
    @SerializedName("lesson")
    @Expose
    private LessonLsnDTO lesson;

    public LessonsImgDTO[] getImages() { return images; }

    public void setImages(LessonsImgDTO[] images) { this.images = images; }

    public LessonLsnDTO getLesson() { return lesson; }

    public void setLesson(LessonLsnDTO lesson) { this.lesson = lesson; }

    @Override
    public String toString() {
        return "LessonDtlDTO{" +
                "images=" + Arrays.toString(images) +
                ", lesson=" + lesson +
                '}';
    }
}
