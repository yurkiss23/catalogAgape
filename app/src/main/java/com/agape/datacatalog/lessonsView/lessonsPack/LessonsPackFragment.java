package com.agape.datacatalog.lessonsView.lessonsPack;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.agape.datacatalog.R;
import com.agape.datacatalog.lessonsView.click_listener.LessonPackOnClickListener;
import com.agape.datacatalog.lessonsView.dto.LessonResArrDTO;
import com.agape.datacatalog.lessonsView.dto.LessonResDTO;
import com.agape.datacatalog.lessonsView.dto.LessonResDtlArrDTO;
import com.agape.datacatalog.lessonsView.dto.LessonResDtlDTO;
import com.agape.datacatalog.lessonsView.network.LessonDTOService;
import com.agape.datacatalog.network.entries.LessonPackEntry;
import com.agape.datacatalog.utility.CommonUtils;

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
    private List<LessonPackEntry> lessonPackEntryList;
    private ProgressBar lessonPackProgressBar;

    public static List<int[]> subPackList;

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "---onCreateView---");
        View view = inflater.inflate(R.layout.fragment_lessons_pack, container, false);

        setupView(view);
        CommonUtils.setUpToolBar(lessonPackToolbar, getActivity());
        setRecyclerView();
        loadLessonRes();

        return view;
    }

    private void setupView(View view){
        lessonPackRecyclerView = view.findViewById(R.id.lessons_recycler_view);
        lessonPackToolbar = view.findViewById(R.id.lessons_app_bar);
        lessonPackProgressBar = view.findViewById(R.id.pb_loading);
    }

    private void setRecyclerView(){
        lessonPackEntryList = new ArrayList<>();
        lessonPackAdapter = new LessonPackRecyclerViewAdapter(lessonPackEntryList, this);
        CommonUtils.setRecyclerView(lessonPackRecyclerView, lessonPackAdapter,
                getActivity(), getResources(), new int[]{1, 2}, null);
    }

    private void loadLessonRes(){
        lessonPackProgressBar.setVisibility(ProgressBar.VISIBLE);
        LessonDTOService.getInstance()
                .getJSONApi()
                .getLessonRes()
                .enqueue(new Callback<LessonResArrDTO>() {
                    @Override
                    public void onResponse(Call<LessonResArrDTO> call, Response<LessonResArrDTO> response) {
//                        Toast.makeText(getContext(), TAG + "onResponse", Toast.LENGTH_LONG).show();
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
                            lessonPackAdapter.notifyDataSetChanged();
                        }
                        CommonUtils.setProgressBar(lessonPackProgressBar);
                    }

                    @Override
                    public void onFailure(Call<LessonResArrDTO> call, Throwable t) {
                        Toast.makeText(getContext(), TAG + "onFailure", Toast.LENGTH_LONG).show();
                    }
                });
    }

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
}
