package com.agape.datacatalog.lessonsView.lessonsPack.network;

import com.agape.datacatalog.lessonsView.lessonsPack.dto.LessonArrDTO;
import com.agape.datacatalog.lessonsView.lessonsPack.dto.LessonDtlDTO;
import com.agape.datacatalog.lessonsView.lessonsPack.dto.LessonResDtlArrDTO;
import com.agape.datacatalog.lessonsView.lessonsPack.dto.LessonResArrDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface LessonDTOHolderApi {
    @GET("get_resource")
    public Call<LessonResArrDTO> getLessonRes();

    @GET("resource_detail/{pk}")
    public Call<LessonResDtlArrDTO> getLessonResDtl(@Path("pk") int pk);

    @GET("year_study_detail/{pk}/{page}")
    public Call<LessonArrDTO> getAllLesson(@Path("pk") int pk, @Path("page") int page);

    @GET("lesson_detail/{pk}")
    public Call<LessonDtlDTO> getLesson(@Path("pk") int pk);
}
