package com.agape.datacatalog.lessonsView.lessonsPack.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LessonResArrDTO {
    @SerializedName("resourcies")
    @Expose
    private LessonResDTO[] resourcies;

    public LessonResDTO[] getResourcies() { return resourcies; }

//    public void setResourcies(LessonDTO[] resourcies) { this.resourcies = resourcies; }
}
