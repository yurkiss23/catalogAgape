package com.agape.datacatalog;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.transition.Scene;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.agape.datacatalog.packageView.PackageGridFragment;
import com.agape.datacatalog.utility.CommonUtils;

import java.util.concurrent.TimeUnit;

public class StartFragment extends Fragment {

    private final String TAG = "MyLOG";

    private ImageView imgLogoStart;
    private Button btnContinue;
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

        fadeStartLogo(imgLogoStart, btnContinue);
        setBtnContinue(view);

        return view;
    }

    private void setupViews(View view){
        imgLogoStart = view.findViewById(R.id.start_image_small);
        btnContinue = view.findViewById(R.id.btn_continue);
        progressBar = view.findViewById(R.id.pb_loading);
    }

    private void fadeStartLogo(ImageView view, Button btnView){
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

        Animation btnStart, btnEnd;
        btnStart = AnimationUtils.loadAnimation(getContext(), R.anim.alpha);
        btnEnd = AnimationUtils.loadAnimation(getContext(), R.anim.alpha);
        btnContinue.startAnimation(btnStart);
        btnContinue.startAnimation(btnEnd);
    }

    private void setBtnContinue(View view){
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "---btnContinue_onClick---");
                progressBar.setVisibility(ProgressBar.VISIBLE);
                setProgressBar();
            }
        });
    }

    private void setProgressBar(){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.MILLISECONDS.sleep(2000);
                    ((NavigationHost)getActivity()).navigateTo(new PackageGridFragment(), true);
                    progressBar.setVisibility(ProgressBar.INVISIBLE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        t.start();
    }

//    public String uploadData(){
//        Thread t = new Thread(new Runnable() {
//            public void run() {
//                try {
//                    CommonUtils.showLoading(getActivity());
//                    TimeUnit.MILLISECONDS.sleep(1000);
////                    CommonUtils.hideLoading();
////                    ((NavigationHost)getActivity()).navigateTo(new SportNewsFragment(), false);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        t.start();
//        return null;
//    }
}
