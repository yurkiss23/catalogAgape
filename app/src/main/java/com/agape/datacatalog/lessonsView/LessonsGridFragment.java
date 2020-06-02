package com.agape.datacatalog.lessonsView;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
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
import com.agape.datacatalog.lessonsView.lessonsPack.dto.LessonArrDTO;
import com.agape.datacatalog.lessonsView.lessonsPack.dto.LessonDtlDTO;
import com.agape.datacatalog.lessonsView.lessonsPack.dto.LessonResDtlArrDTO;
import com.agape.datacatalog.lessonsView.lessonsPack.network.LessonDTOService;
import com.agape.datacatalog.network.entries.LessonEntry;
import com.agape.datacatalog.utility.CommonUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LessonsGridFragment extends Fragment {
    private final String TAG = "MyLOG_LGF";

    private RecyclerView lessonRecyclerView;
    private LessonCardRecyclerViewAdapter lessonAdapter;
    private Toolbar lessonToolbar;
    private ProgressBar lessonProgressbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "---LessonsGridFragment---onCreateView---");
        View view = inflater.inflate(R.layout.fragment_lessons_grid, container, false);

        setupView(view);
        CommonUtils.setUpToolBar(lessonToolbar, getActivity());
//        CommonUtils.setRecyclerView(lessonRecyclerView, lessonAdapter, getActivity(), getResources(), null);
        loadDTORecyclerView();

        return view;
    }

    private void setupView(View view){
        lessonRecyclerView = view.findViewById(R.id.lessons_recycler_view);
        lessonToolbar = view.findViewById(R.id.lessons_app_bar);
        lessonAdapter = new LessonCardRecyclerViewAdapter(LessonEntry.initLessonEntryList(getResources()));
        lessonProgressbar = view.findViewById(R.id.pb_loading);
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

    private void loadDTORecyclerView(){
        lessonProgressbar.setVisibility(ProgressBar.VISIBLE);
        LessonDTOService.getInstance()
                .getJSONApi()
                .getLesson(510)
                .enqueue(new Callback<LessonDtlDTO>() {
                    @Override
                    public void onResponse(Call<LessonDtlDTO> call, Response<LessonDtlDTO> response) {
                        Toast.makeText(getContext(), TAG + "onResponse", Toast.LENGTH_LONG).show();
                        CommonUtils.setProgressBar(lessonProgressbar);
                    }

                    @Override
                    public void onFailure(Call<LessonDtlDTO> call, Throwable t) {
                        Toast.makeText(getContext(), TAG + "onFailure", Toast.LENGTH_LONG).show();
                    }
                });
//        lessonProgressbar.setVisibility(ProgressBar.VISIBLE);
//        lessonRecyclerView.setHasFixedSize(true);
////        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
////            lSpanCount = 2;
////        }else {
////            lSpanCount = 3;
////        }
//        lessonRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
//        lessonAdapter = new LessonCardRecyclerViewAdapter(LessonEntry.initLessonEntryList(getResources()));
//        lessonRecyclerView.setAdapter(lessonAdapter);
//        int largePadding = getResources().getDimensionPixelSize(R.dimen.package_grid_spacing);
//        int smallPadding = getResources().getDimensionPixelSize(R.dimen.package_grid_spacing_small);
//        lessonRecyclerView.addItemDecoration(new PackageGridItemDecoration(largePadding, smallPadding));
//        CommonUtils.setProgressBar(getActivity(), lessonProgressbar, new LessonFragment());
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
