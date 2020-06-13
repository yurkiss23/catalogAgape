package com.agape.datacatalog.lessonsView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.agape.datacatalog.R;
import com.agape.datacatalog.lessonsView.click_listener.LessonGridOnClickListener;
import com.agape.datacatalog.lessonsView.lessonsPack.LessonsPackFragment;
import com.agape.datacatalog.lessonsView.dto.LessonArrDTO;
import com.agape.datacatalog.lessonsView.dto.LessonDTO;
import com.agape.datacatalog.lessonsView.dto.LessonDtlDTO;
import com.agape.datacatalog.lessonsView.network.LessonDTOService;
import com.agape.datacatalog.network.entries.LessonEntry;
import com.agape.datacatalog.utility.CommonUtils;
import com.agape.datacatalog.utility.ListUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LessonsGridFragment extends Fragment implements LessonGridOnClickListener {
    private final String TAG = "MyLOG_LGF";

    private RecyclerView lessonRecyclerView;
    private LessonCardRecyclerViewAdapter lessonAdapter;
    private Toolbar lessonToolbar;
    private ProgressBar lessonProgressbar;
    private FloatingActionButton lessonFab;
    private NestedScrollView lessonView;
//    private List<LessonEntry> lessonEntryList;

    private int id;
    private String title;

    public LessonsGridFragment(){}

    public LessonsGridFragment(int id, String title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        Log.d(TAG, "---LessonsGridFragment---onCreateView---" + id);
        View view = inflater.inflate(R.layout.fragment_lessons_grid, container, false);

        setupView(view);
        CommonUtils.setUpToolBar(lessonToolbar, getActivity());
        setRecyclerView();
        loadAllLesson();

        return view;
    }

    private void setupView(View view){
        lessonRecyclerView = view.findViewById(R.id.lessons_recycler_view);
        lessonToolbar = view.findViewById(R.id.lessons_app_bar);
        lessonToolbar.setTitle(title);
        lessonProgressbar = view.findViewById(R.id.pb_loading);
        lessonFab = view.findViewById(R.id.floating_action_button);
        lessonView = view.findViewById(R.id.lessons_grid);
    }

//    private void setUpToolBar(){
//        AppCompatActivity lessonActivity = (AppCompatActivity) getActivity();
//        if (lessonActivity != null){
//            lessonActivity.setSupportActionBar(lessonToolbar);
//        }
//        lessonToolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ((NavigationHost)getActivity()).navigateTo(new PackageGridFragment(), false);
//            }
//        });
//    }

    private void setRecyclerView(){
//        lessonEntryList = new ArrayList<>();
        lessonAdapter = new LessonCardRecyclerViewAdapter(ListUtils.lessonEntryList, this);
        CommonUtils.setRecyclerView(lessonRecyclerView, lessonAdapter,
                getActivity(), getResources(), new int[]{1, 2}, "2");
    }

    private void loadAllLesson(){
        lessonProgressbar.setVisibility(ProgressBar.VISIBLE);
        ListUtils.lessonEntryList.clear();
        getPages();
        CommonUtils.setProgressBar(lessonProgressbar);
        CommonUtils.setOnScrollGrid(lessonView, lessonFab);
//        LessonDTOService.getInstance()
//                .getJSONApi()
//                .getAllLesson(28, 1)
//                .enqueue(new Callback<LessonArrDTO>() {
//                    @Override
//                    public void onResponse(Call<LessonArrDTO> call, Response<LessonArrDTO> response) {
////                        Toast.makeText(getContext(), TAG + "onResponse", Toast.LENGTH_LONG).show();
//                        if (response.body() != null){
//                            LessonDTO[] list = response.body().getLessons();
//                            for (LessonDTO item : list){
//                                LessonEntry lessonEntry = new LessonEntry(
//                                        item.getNumber_lessons() + ". " + item.getTitle(),null,
//                                        "http://77.120.115.215/agape/api/media/index/logo/logo.png",
//                                        item.getPk());
//                                lessonEntryList.add(lessonEntry);
//                            }
//                            lessonAdapter.notifyDataSetChanged();
//                        }
//                        CommonUtils.setProgressBar(lessonProgressbar);
//                    }
//
//                    @Override
//                    public void onFailure(Call<LessonArrDTO> call, Throwable t) {
//                        Toast.makeText(getContext(), TAG + "onFailure", Toast.LENGTH_LONG).show();
//                    }
//                });
    }

    private void getPages(){
        LessonDTOService.getInstance()
                .getJSONApi()
                .getAllLesson(LessonsPackFragment.subPackList.get(id)[1], 1)
                .enqueue(new Callback<LessonArrDTO>() {
                    @Override
                    public void onResponse(Call<LessonArrDTO> call, Response<LessonArrDTO> response) {
                        if (response.body() != null){
                            for (int i = 1; i < response.body().getPages(); ++i){
                                getListPart(i);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<LessonArrDTO> call, Throwable t) {
                        Toast.makeText(getContext(), TAG + "onFailure", Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void getListPart(int page){
        LessonDTOService.getInstance()
                .getJSONApi()
                .getAllLesson(LessonsPackFragment.subPackList.get(id)[1], page)
                .enqueue(new Callback<LessonArrDTO>() {
                    @Override
                    public void onResponse(Call<LessonArrDTO> call, Response<LessonArrDTO> response) {
                        if (response.body() != null){
                            LessonDTO[] listPart = response.body().getLessons();
                            for (LessonDTO item : listPart){
                                Log.d(TAG, "---getListPart---onResponse---");
//                                String s = getLessonImg(item.getPk());
                                LessonEntry lessonEntry = new LessonEntry(item.getNumber_lessons(),
                                        item.getTitle(),null,
                                        "http://77.120.115.215/agape/api/media/index/logo/logo.png",
                                        item.getPk());
                                ListUtils.lessonEntryList.add(lessonEntry);
                            }
                            Log.d(TAG, "---addList---" + ListUtils.lessonEntryList.size());
                            lessonAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<LessonArrDTO> call, Throwable t) {
                        Toast.makeText(getContext(), TAG + "onFailure", Toast.LENGTH_LONG).show();
                    }
                });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void getLesson(String name, int pk) {
        lessonProgressbar.setVisibility(ProgressBar.VISIBLE);
//        Toast.makeText(getContext(), name, Toast.LENGTH_LONG).show();
        showLesson(pk);
        CommonUtils.setProgressBar(lessonProgressbar);
    }

    private void showLesson(int pk){
        LessonDTOService.getInstance()
                .getJSONApi()
                .getLesson(pk)
                .enqueue(new Callback<LessonDtlDTO>() {
                    @Override
                    public void onResponse(Call<LessonDtlDTO> call, Response<LessonDtlDTO> response) {
                        if (response.body() != null){
                            Intent showPdf = new Intent(Intent.ACTION_VIEW ,
                                    Uri.parse(LessonDTOService.getLessonUrl()[1]
                                            + response.body().getLesson().getFile()));
                            startActivity(showPdf);
                        }
                    }

                    @Override
                    public void onFailure(Call<LessonDtlDTO> call, Throwable t) {
                        Toast.makeText(getContext(), TAG + "onFailure", Toast.LENGTH_LONG).show();
                    }
                });
    }
}
