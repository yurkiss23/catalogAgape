package com.agape.datacatalog.lessonsView.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class LessonResDtlArrDTO {
//    @SerializedName("tasks")
//    @Expose
//    private Boolean tasks;
    @SerializedName("themeth")
    @Expose
    private LessonResDtlDTO[] themeth;
//    @SerializedName("tasks_meta")
//    @Expose
//    private String[] tasks_meta;

    public LessonResDtlDTO[] getThemeth() { return themeth; }

    public void setThemeth(LessonResDtlDTO[] themeth) { this.themeth = themeth; }

    @Override
    public String toString() {
        return "LessonResDtlDTO{" +
                "themeth=" + Arrays.toString(themeth) +
                '}';
    }
}
