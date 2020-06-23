package com.agape.datacatalog.lessonsView.lessonsPack;

import android.content.Intent;
import android.content.pm.ActivityInfo;
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
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.agape.datacatalog.MainActivity;
import com.agape.datacatalog.R;
import com.agape.datacatalog.StartFragment;
import com.agape.datacatalog.lessonsView.click_listener.LessonPackOnClickListener;
import com.agape.datacatalog.lessonsView.dto.LessonResArrDTO;
import com.agape.datacatalog.lessonsView.dto.LessonResDTO;
import com.agape.datacatalog.lessonsView.dto.LessonResDtlArrDTO;
import com.agape.datacatalog.lessonsView.dto.LessonResDtlDTO;
import com.agape.datacatalog.lessonsView.network.LessonDTOService;
import com.agape.datacatalog.network.entries.LessonPackEntry;
import com.agape.datacatalog.utility.CommonUtils;
import com.agape.datacatalog.utility.ListUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LessonsPackFragment extends Fragment implements LessonPackOnClickListener {
    private final String TAG = "MyLOG_LPF";

    private RecyclerView lessonPackRecyclerView;
    private LessonPackRecyclerViewAdapter lessonPackAdapter;
    private Toolbar lessonPackToolbar;
//    private List<LessonPackEntry> lessonPackEntryList;
    private ProgressBar lessonPackProgressBar;
    private FloatingActionButton lessonPackFab;
    private NestedScrollView lessonPackView;

    public static List<int[]> subPackList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        Log.d(TAG, "---onCreateView---");
        View view = inflater.inflate(R.layout.fragment_lessons_pack, container, false);

        setupView(view);
        CommonUtils.setUpToolBar(lessonPackToolbar, getActivity());
        setRecyclerView();
        loadLessonPackList();
//        loadLessonRes();

        return view;
    }

    private void setupView(View view){
        lessonPackRecyclerView = view.findViewById(R.id.lessons_recycler_view);
        lessonPackToolbar = view.findViewById(R.id.lessons_app_bar);
        lessonPackProgressBar = view.findViewById(R.id.pb_loading);
        lessonPackFab = view.findViewById(R.id.floating_action_button);
        lessonPackView = view.findViewById(R.id.lessons_grid);
    }

    private void setRecyclerView(){
//        lessonPackEntryList = new ArrayList<>();
        lessonPackAdapter = new LessonPackRecyclerViewAdapter(ListUtils.lessonPackEntryList, this);
        CommonUtils.setRecyclerView(lessonPackRecyclerView, lessonPackAdapter,
                getActivity(), getResources(), new int[]{1, 2}, null);
    }

    private void loadLessonPackList(){
        lessonPackProgressBar.setVisibility(ProgressBar.VISIBLE);
        lessonPackAdapter.notifyDataSetChanged();
        CommonUtils.setProgressBar(lessonPackProgressBar);
        CommonUtils.setOnScrollGrid(lessonPackView, lessonPackFab);
    }

//    private void loadLessonRes(){
//        lessonPackProgressBar.setVisibility(ProgressBar.VISIBLE);
//        LessonDTOService.getInstance()
//                .getJSONApi()
//                .getLessonRes()
//                .enqueue(new Callback<LessonResArrDTO>() {
//                    @Override
//                    public void onResponse(Call<LessonResArrDTO> call, Response<LessonResArrDTO> response) {
////                        Toast.makeText(getContext(), TAG + "onResponse", Toast.LENGTH_LONG).show();
//                        lessonPackEntryList.clear();
//                        if (response.body() != null){
//                            LessonResDTO[] list = response.body().getResourcies();
//                            for (LessonResDTO item : list){
//                                if (item.getTitle().contains("(УКР)")){
//                                    LessonPackEntry lessonPackEntry = new LessonPackEntry(item.getTitle(),
//                                            null, item.getMain_image(), null, item.getPk());
//                                    lessonPackEntryList.add(lessonPackEntry);
//                                }
//                            }
//                            lessonPackAdapter.notifyDataSetChanged();
//                        }
//                        CommonUtils.setProgressBar(lessonPackProgressBar);
//                    }
//
//                    @Override
//                    public void onFailure(Call<LessonResArrDTO> call, Throwable t) {
//                        Toast.makeText(getContext(), TAG + "onFailure", Toast.LENGTH_LONG).show();
//                    }
//                });
//    }

    @Override
    public void setOnClick(int pk, String title, View v) {
        Log.d(TAG, "---setOnClick---");
        loadLessonResDtl(pk, title, v);
    }

    private void loadLessonResDtl(int pk, String title, View v){
        subPackList = new ArrayList<>();
        LessonDTOService.getInstance()
                .getJSONApi()
                .getLessonResDtl(pk)
                .enqueue(new Callback<LessonResDtlArrDTO>() {
                    @Override
                    public void onResponse(Call<LessonResDtlArrDTO> call, Response<LessonResDtlArrDTO> response) {
                        if (response.body() != null){
                            subPackList.clear();
                            LessonResDtlDTO[] list = response.body().getThemeth();
                            if (list.length == 0){
                                Log.d(TAG, "---loadLessonResDtl---");
                                subPackList = null;
                            }
                            for (LessonResDtlDTO item : list){
                                int[] arr = {item.getYear_study(), item.getPk()};
                                subPackList.add(arr);
                            }

                        }else {
                            Toast.makeText(getContext(), "Вибачте, дані відсутні!", Toast.LENGTH_LONG).show();
                        }
                        CommonUtils.setPopup(v, getActivity(), getContext(), title);
                    }

                    @Override
                    public void onFailure(Call<LessonResDtlArrDTO> call, Throwable t) {

                    }
                });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == R.id.agape_logo){
            Intent intent = new Intent(this.getContext(), MainActivity.class);
            startActivity(intent);
        }
        return true;
    }

}
