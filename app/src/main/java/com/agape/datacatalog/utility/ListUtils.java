package com.agape.datacatalog.utility;

import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.agape.datacatalog.application.CatalogAgape;
import com.agape.datacatalog.lessonsView.dto.LessonResArrDTO;
import com.agape.datacatalog.lessonsView.dto.LessonResDTO;
import com.agape.datacatalog.lessonsView.network.LessonDTOService;
import com.agape.datacatalog.network.entries.LessonEntry;
import com.agape.datacatalog.network.entries.LessonPackEntry;
import com.agape.datacatalog.network.entries.NewsEntry;
import com.agape.datacatalog.newsView.dto.NewsResArrDTO;
import com.agape.datacatalog.newsView.dto.NewsResDTO;
import com.agape.datacatalog.newsView.network.NewsDTOService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class ListUtils {
    private static final String TAG = "MyLOG_ListUtils";

    public static List<LessonPackEntry> lessonPackEntryList;
    public static List<LessonEntry> lessonEntryList;
    public static List<NewsEntry> newsEntryList, newsEntryDtlList;

    public static void initLists(){
//        Toast.makeText(CatalogAgape.getAppContext(), TAG, Toast.LENGTH_SHORT).show();
        lessonPackEntryList = new ArrayList<>();
        lessonEntryList = new ArrayList<>();
        newsEntryList = new ArrayList<>();
        newsEntryDtlList = new ArrayList<>();

        loadLessonRes();
        loadAllNews();
    }

    private static void loadLessonRes(){
//        lessonPackProgressBar.setVisibility(ProgressBar.VISIBLE);
        LessonDTOService.getInstance()
                .getJSONApi()
                .getLessonRes()
                .enqueue(new Callback<LessonResArrDTO>() {
                    @Override
                    public void onResponse(Call<LessonResArrDTO> call, Response<LessonResArrDTO> response) {
//                        Toast.makeText(CatalogAgape.getAppContext(), TAG + " onResponse", Toast.LENGTH_LONG).show();
                        lessonPackEntryList.clear();
                        if (response.body() != null){
                            LessonResDTO[] list = response.body().getResourcies();
                            for (LessonResDTO item : list){
                                if (item.getTitle().contains("(УКР)")){
                                    LessonPackEntry lessonPackEntry = new LessonPackEntry(item.getTitle(),
                                            null, item.getMain_image(), null, item.getPk());
                                    lessonPackEntryList.add(lessonPackEntry);
                                }
                            }
//                            lessonPackAdapter.notifyDataSetChanged();
                        }
//                        CommonUtils.setProgressBar(lessonPackProgressBar);
                    }

                    @Override
                    public void onFailure(Call<LessonResArrDTO> call, Throwable t) {
                        Toast.makeText(CatalogAgape.getAppContext(), TAG + "onFailure", Toast.LENGTH_LONG).show();
                    }
                });
    }

    private static void loadAllNews(){
        Log.d(TAG, "---loadAllNews---newsEntryList-size = " + newsEntryList.size());
//        newsProgressBar.setVisibility(ProgressBar.VISIBLE);
        NewsDTOService.getInstance()
                .getJSONApi()
                .getAllNews()
                .enqueue(new Callback<NewsResArrDTO>() {
                    @Override
                    public void onResponse(Call<NewsResArrDTO> call, Response<NewsResArrDTO> response) {
//                        Toast.makeText(CatalogAgape.getAppContext(), "---onResponse---", Toast.LENGTH_LONG).show();
                        if (response.body() != null){
                            newsEntryList.clear();
                            NewsResDTO[] list = response.body().getPopular_news();
                            for (NewsResDTO item : list){
                                NewsEntry newsEntry = new NewsEntry(item.getTitle(), null, null,
                                        NewsDTOService.getNewsUrl()[1] + item.getMain_image(), item.getPk());
                                newsEntryList.add(newsEntry);
                            }
//                            newsAdapter.notifyDataSetChanged();
                        }
//                        CommonUtils.setProgressBar(newsProgressBar);
                    }

                    @Override
                    public void onFailure(Call<NewsResArrDTO> call, Throwable t) {
                        Toast.makeText(CatalogAgape.getAppContext(), "---onFailure---", Toast.LENGTH_LONG).show();
                    }
                });
    }
}
