package com.agape.datacatalog.lessonsView.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LessonResDtlDTO {
    @SerializedName("pk")
    @Expose
    private int pk;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("year_study")
    @Expose
    private int year_study;

    public int getPk() { return pk; }

    public void setPk(int pk) { this.pk = pk; }

    public String getImage() { return image; }

    public void setImage(String image) { this.image = image; }

    public int getYear_study() { return year_study; }

    public void setYear_study(int year_study) { this.year_study = year_study; }

    @Override
    public String toString() {
        return "LRDetailsDTO{" +
                "pk=" + pk +
                ", image='" + image + '\'' +
                ", year_study=" + year_study +
                '}';
    }
}
