package com.agape.datacatalog.videoView;

import android.content.res.Configuration;
import android.media.MediaPlayer;
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
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.agape.datacatalog.NavigationHost;
import com.agape.datacatalog.R;
import com.agape.datacatalog.network.entries.VideoEntry;
import com.agape.datacatalog.packageView.PackageGridFragment;
import com.agape.datacatalog.packageView.PackageGridItemDecoration;

import java.util.List;

public class VideosGridFragment extends Fragment implements OnPreparedListener {

    private final String TAG = "MyLOG_VGF";

    private RecyclerView videoRecyclerView;
    private List<VideoEntry> listVideoEntry;
    private VideoCardRecyclerViewAdapter videoAdapter;
    private Toolbar toolbar;
    private MediaController mediaController;
    private int pos;

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "---VideosGridFragment---onCreateView---");
        View view = inflater.inflate(R.layout.fragment_video_grid, container, false);

        setView(view);
        setRecyclerView(view);
        setUpToolBar(view);


//        videoView = view.findViewById(R.id.video_view);
//        Uri uri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.andr_test);
//        videoView.setVideoURI(uri);
//        videoView.requestFocus();
////        videoView.start();
////        mediaController = new MediaController(getContext());
////        mediaController.setAnchorView(videoView);
////        videoView.setMediaController(mediaController);
//
////        int pos = 0;
//        if (mediaController == null){
//            mediaController = new MediaController(getContext());
//            videoView.setMediaController(mediaController);
//            mediaController.setAnchorView(videoView);
//        }
//        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                videoView.seekTo(pos);
////                if (pos == 0){
////                    videoView.start();
////                }
//                mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
//                    @Override
//                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
//                        mediaController.setAnchorView(videoView);
//                    }
//                });
//            }
//        });

        return view;
    }

    private void setView(View view){
        videoRecyclerView = view.findViewById(R.id.video_recycler_view);
        toolbar = view.findViewById(R.id.video_app_bar);
//        videoView = view.findViewById(R.id.video_view);
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
        Log.d(TAG, "---recyclerView---");

        videoRecyclerView.setHasFixedSize(true);
        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            videoRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        }else {
            videoRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        }

        videoAdapter = new VideoCardRecyclerViewAdapter(VideoEntry.initVideosEntryList(getResources()),
                this, getContext());
        videoRecyclerView.setAdapter(videoAdapter);
        int largePadding = getResources().getDimensionPixelSize(R.dimen.package_grid_spacing);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.package_grid_spacing_small);
        videoRecyclerView.addItemDecoration(new PackageGridItemDecoration(largePadding, smallPadding));
    }

    @Override
    public void playItem(VideoView videoView, String title) {
//        Toast.makeText(getContext(), title, Toast.LENGTH_LONG).show();

        mediaController = new MediaController(getContext());
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.seekTo(pos);
                mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                        mediaController.setAnchorView(videoView);
                    }
                });
            }
        });
    }
}
