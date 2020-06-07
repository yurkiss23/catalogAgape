package com.agape.datacatalog.lessonsView.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LessonsImgDTO {
    @SerializedName("image")
    @Expose
    private String image;

    public String getImage() { return image; }

    public void setImage(String image) { this.image = image; }

    @Override
    public String toString() {
        return "LessonsImgDTO{" +
                "image='" + image + '\'' +
                '}';
    }
}
