package com.agape.datacatalog;

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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.agape.datacatalog.packageView.PackageGridFragment;
import com.agape.datacatalog.utility.CommonUtils;

import java.util.concurrent.TimeUnit;

public class StartFragment extends Fragment implements TransitionListener{

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_start, container, false);

        ViewGroup sceneRoot = (ViewGroup) view.findViewById(R.id.scene_root);

        Scene sceneEnd = Scene.getSceneForLayout(sceneRoot, R.layout.scene2, this.getContext());
        TransitionSet set = new TransitionSet();
//        set.addTransition(new Fade());
//                .setDuration(5000);
//        TransitionManager.go(sceneEnd, set);

//        ImageView logo = sceneRoot.findViewById(R.id.start_image);
        ImageView imageView = (ImageView) view.findViewById(R.id.start_image);
        Fade f = new Fade();
        set.addTransition(f)
//                .addTransition(new ChangeBounds())
//                .setOrdering(TransitionSet.ORDERING_TOGETHER)
                .setDuration(2000);
//                .setInterpolator(new AccelerateInterpolator());

        int newSize = getResources().getDimensionPixelSize(R.dimen.logo_size_expand);
//        TransitionManager.beginDelayedTransition(sceneRoot, set);
//        sceneEnd.enter();
        TransitionManager.go(sceneEnd, set);
//
//        ViewGroup.LayoutParams params = logo.getLayoutParams();
//        params.width = newSize;
//        params.height = newSize;
//        logo.setLayoutParams(params);

//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (v.equals(imageView)){
//                    Log.d("TAG", "click");
//                }
////                CommonUtils.showLoading(getActivity());
////                uploadData();
//                Log.d("TAG", "click");
//                ((NavigationHost)getActivity()).navigateTo(new PackageGridFragment(), false);
//            }
//        });
//        imageView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
////                CommonUtils.showLoading(getActivity());
////                uploadData();
//                Log.d("TAG", "click");
//                ((NavigationHost)getActivity()).navigateTo(new PackageGridFragment(), false);
//                return false;
//            }
//        });

        goToFragment(f, new PackageGridFragment());

//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        ((NavigationHost)getActivity()).navigateTo(new PackageGridFragment(), false);

        return view;
    }

    public void goToFragment(Transition transition, Fragment fragment){
        Thread thread =new Thread(new Runnable() {
            @Override
            public void run() {
                onTransitionEnd(transition);
                ((NavigationHost)getActivity()).navigateTo(fragment, false);
            }
        });
        thread.start();
    }
    public String uploadData(){
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                    CommonUtils.hideLoading();
//                    ((NavigationHost)getActivity()).navigateTo(new SportNewsFragment(), false);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        return null;
    }

    @Override
    public void onTransitionStart(Transition transition) {

    }

    @Override
    public void onTransitionEnd(Transition transition) {
        try {
            Log.d("TAG", "--------______--------");
            TimeUnit.MILLISECONDS.sleep(2500);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("TAG", "--------!!!!!--------");
    }

    @Override
    public void onTransitionCancel(Transition transition) {

    }

    @Override
    public void onTransitionPause(Transition transition) {

    }

    @Override
    public void onTransitionResume(Transition transition) {

    }
}
