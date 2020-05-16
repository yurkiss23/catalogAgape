package com.agape.datacatalog.videoView;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.agape.datacatalog.NavigationHost;
import com.agape.datacatalog.R;
import com.agape.datacatalog.network.VideoEntry;
import com.agape.datacatalog.packageView.PackageGridFragment;
import com.agape.datacatalog.packageView.PackageGridItemDecoration;

import java.util.List;

public class VideosGridFragment extends Fragment {

    private final String TAG = "MyLOG_VGF";

    private RecyclerView videoRecyclerView;
    private List<VideoEntry> listVideoEntry;
    private VideoCardRecyclerViewAdapter videoAdapter;
    private Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "---VideosGridFragment---onCreateView---");
        View view = inflater.inflate(R.layout.fragment_video_grid, container, false);

        setView(view);
        setRecyclerView(view);
        setUpToolBar(view);

        return view;
    }

    private void setView(View view){
        videoRecyclerView = view.findViewById(R.id.video_recycler_view);
        toolbar = view.findViewById(R.id.video_app_bar);
    }

    private void setUpToolBar(View view){
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null){
            activity.setSupportActionBar(toolbar);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NavigationHost)getActivity()).navigateTo(new PackageGridFragment(), false);
            }
        });
    }

    private void setRecyclerView(View view){
        videoRecyclerView.setHasFixedSize(true);
        videoRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));

        videoAdapter = new VideoCardRecyclerViewAdapter(VideoEntry.initVideosEntryList(getResources()));
        videoRecyclerView.setAdapter(videoAdapter);
        int largePadding = getResources().getDimensionPixelSize(R.dimen.package_grid_spacing);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.package_grid_spacing_small);
        videoRecyclerView.addItemDecoration(new PackageGridItemDecoration(largePadding, smallPadding));
    }
}
