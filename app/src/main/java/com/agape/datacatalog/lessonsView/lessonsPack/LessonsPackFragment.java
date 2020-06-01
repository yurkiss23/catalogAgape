package com.agape.datacatalog.lessonsView.lessonsPack;

import android.os.Bundle;

import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.agape.datacatalog.NavigationHost;
import com.agape.datacatalog.R;
import com.agape.datacatalog.lessonsView.LessonsGridFragment;
import com.agape.datacatalog.lessonsView.lessonsPack.click_listener.OnClickListener;
import com.agape.datacatalog.lessonsView.lessonsPack.dto.LessonResArrDTO;
import com.agape.datacatalog.lessonsView.lessonsPack.dto.LessonResDTO;
import com.agape.datacatalog.lessonsView.lessonsPack.network.LessonDTOService;
import com.agape.datacatalog.network.entries.LessonPackEntry;
import com.agape.datacatalog.utility.CommonUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LessonsPackFragment extends Fragment implements OnClickListener {
    private final String TAG = "MyLOG_LPF";

    private RecyclerView lessonPackRecyclerView;
    private LessonPackRecyclerViewAdapter lessonPackAdapter;
    private Toolbar lessonPackToolbar;
    private List<LessonPackEntry> lessonPackEntryList;

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
//        CommonUtils.setRecyclerView(lessonPackRecyclerView,
//                lessonPackAdapter, getActivity(), getResources(), "lessonPack");
        loadLessonRes();

        return view;
    }

    private void setupView(View view){
        lessonPackRecyclerView = view.findViewById(R.id.lessons_recycler_view);
        lessonPackToolbar = view.findViewById(R.id.lessons_app_bar);
//        lessonPackAdapter = new LessonPackRecyclerViewAdapter(lessonPackEntryList, this);
    }

    private void setRecyclerView(){
        lessonPackEntryList = new ArrayList<>();
        lessonPackAdapter = new LessonPackRecyclerViewAdapter(lessonPackEntryList, this);
        CommonUtils.setRecyclerView(lessonPackRecyclerView,
                lessonPackAdapter, getActivity(), getResources(), "lessonPack");
    }

    private void loadLessonRes(){
        LessonDTOService.getInstance()
                .getJSONApi()
                .getLessonRes()
                .enqueue(new Callback<LessonResArrDTO>() {
                    @Override
                    public void onResponse(Call<LessonResArrDTO> call, Response<LessonResArrDTO> response) {
//                        Toast.makeText(getContext(), TAG + "onResponse", Toast.LENGTH_LONG).show();
                        lessonPackEntryList.clear();
                        List<LessonResDTO> list = Arrays.asList(response.body().getResourcies());
                        for (LessonResDTO item : list){
                            LessonPackEntry lessonPackEntry = new LessonPackEntry(item.getTitle(),
                                    null, item.getMain_image(), null, item.getPk());
                            lessonPackEntryList.add(lessonPackEntry);
                        }
                        lessonPackAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<LessonResArrDTO> call, Throwable t) {
                        Toast.makeText(getContext(), TAG + "onFailure", Toast.LENGTH_LONG).show();
                    }
                });
    }

    @Override
    public void setOnClick(LessonPackEntry lessonPackEntry, View v) {
        Log.d(TAG, "---setOnClick---");
//        Toast.makeText(getContext(), "click: " + lessonPackEntry.title, Toast.LENGTH_LONG).show();
        String[] subPackList = lessonPackEntry.sub;
        CommonUtils.setPopup(v, subPackList, getActivity(), getContext());
    }
}
