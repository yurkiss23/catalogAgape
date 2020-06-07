package com.agape.datacatalog.videoView;

import android.media.MediaPlayer;
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
import android.webkit.WebView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.agape.datacatalog.R;
import com.agape.datacatalog.network.entries.VideoEntry;
import com.agape.datacatalog.utility.CommonUtils;
import com.google.android.youtube.player.YouTubePlayerView;
//import com.google.api.services.youtube.YouTube;

import java.util.List;

public class VideosGridFragment extends Fragment implements OnPreparedListener {

    private final String TAG = "MyLOG_VGF";

    private RecyclerView videoRecyclerView;
    private List<VideoEntry> listVideoEntry;
    private VideoCardRecyclerViewAdapter videoAdapter;
    private Toolbar videoToolbar;
    private MediaController mediaController;

    private int pos;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "---VideosGridFragment---onCreateView---");
        View view = inflater.inflate(R.layout.fragment_video_grid, container, false);

        setView(view);
        CommonUtils.setUpToolBar(videoToolbar, getActivity());
        CommonUtils.setRecyclerView(videoRecyclerView, videoAdapter,
                getActivity(), getResources(), new int[]{1, 1}, null);

        return view;
    }

    private void setView(View view){
        videoRecyclerView = view.findViewById(R.id.video_recycler_view);
        videoToolbar = view.findViewById(R.id.video_app_bar);
        videoAdapter = new VideoCardRecyclerViewAdapter(VideoEntry.initVideosEntryList(getResources()),
                this, getContext());
    }

//    private void setUpToolBar(View view){
//        AppCompatActivity activity = (AppCompatActivity) getActivity();
//        if (activity != null){
//            activity.setSupportActionBar(videoToolbar);
//        }
//        videoToolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ((NavigationHost)getActivity()).navigateTo(new PackageGridFragment(), false);
//            }
//        });
//    }
//
//    private void loadRecyclerView(View view){
//        Log.d(TAG, "---recyclerView---");
//
//        videoRecyclerView.setHasFixedSize(true);
//        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
//            vSpanCount = 1;
//        }else {
//            vSpanCount = 2;
//        }
//        videoRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),
//                vSpanCount, GridLayoutManager.VERTICAL, false));
//        videoAdapter = new VideoCardRecyclerViewAdapter(VideoEntry.initVideosEntryList(getResources()),
//                this, getContext());
//        videoRecyclerView.setAdapter(videoAdapter);
//        int largePadding = getResources().getDimensionPixelSize(R.dimen.package_grid_spacing);
//        int smallPadding = getResources().getDimensionPixelSize(R.dimen.package_grid_spacing_small);
//        videoRecyclerView.addItemDecoration(new PackageGridItemDecoration(largePadding, smallPadding));
//    }

    @Override
    public void playItem(WebView webView, String title) {
//        mediaController = new MediaController(getContext());
//        mediaController.setAnchorView(videoView);
//        videoView.setMediaController(mediaController);
//        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                videoView.seekTo(pos);
//                mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
//                    @Override
//                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
//                        mediaController.setAnchorView(videoView);
//                    }
//                });
//            }
//        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
