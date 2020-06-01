package com.agape.datacatalog;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.agape.datacatalog.packageView.PackageGridFragment;
import com.agape.datacatalog.utility.CommonUtils;

import java.util.concurrent.TimeUnit;

public class StartFragment extends Fragment {

    private final String TAG = "MyLOG";

    private ImageView imgLogoStart;
    private ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_start, container, false);

        setupViews(view);

        fadeStartLogo(imgLogoStart);
        setLogoClick();

        return view;
    }

    private void setupViews(View view){
        imgLogoStart = view.findViewById(R.id.start_image_small);
        progressBar = view.findViewById(R.id.pb_loading);
    }

    private void fadeStartLogo(ImageView view){
        Log.d(TAG, "---fadeStartLogo---");

        AnimatorSet start, end, total;
        start = new AnimatorSet();
        start.setDuration(750).playTogether(
                ObjectAnimator.ofFloat(view, View.ALPHA, 0, 1),
                ObjectAnimator.ofFloat(view, View.SCALE_X, 1f, 1.5f),
                ObjectAnimator.ofFloat(view, View.SCALE_Y, 1f, 1.5f)
        );
        end = new AnimatorSet();
        end.setDuration(3500).playTogether(
                ObjectAnimator.ofFloat(view, View.SCALE_X, 1.5f, 1.6f),
                ObjectAnimator.ofFloat(view, View.SCALE_Y, 1.5f, 1.6f)
        );
        total = new AnimatorSet();
        total.playSequentially(
                start,
                end
        );
        total.start();
    }

    private void setLogoClick(){
        imgLogoStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "---logoClick_onClick---");
                progressBar.setVisibility(ProgressBar.VISIBLE);
                CommonUtils.setProgressBar(progressBar);
                ((NavigationHost)getActivity()).navigateTo(new PackageGridFragment(), false);
            }
        });
    }

//    private void setProgressBar(){
//        Thread t = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    TimeUnit.MILLISECONDS.sleep(2000);
//                    ((NavigationHost)getActivity()).navigateTo(new PackageGridFragment(), false);
//                    progressBar.setVisibility(ProgressBar.INVISIBLE);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });
//        t.start();
//    }
}
