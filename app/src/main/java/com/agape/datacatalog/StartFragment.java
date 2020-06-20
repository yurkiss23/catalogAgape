package com.agape.datacatalog;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.agape.datacatalog.application.CatalogAgape;
import com.agape.datacatalog.packageView.PackageGridFragment;
import com.agape.datacatalog.utility.CommonUtils;
import com.agape.datacatalog.utility.ListUtils;

import java.util.concurrent.TimeUnit;

public class StartFragment extends Fragment {

    private final String TAG = "MyLOG";

    private ImageView imgLogoStart;
    private ProgressBar progressBar;
    private ToggleButton themeToggleButton;
    private int flag;// = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

//    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        Toast.makeText(getContext(), Integer.toString(flag), Toast.LENGTH_LONG).show();
        View view = inflater.inflate(R.layout.fragment_start, container, false);

        setupViews(view);
        initToggleButton();
        initDataLists();

        setThemeToggleButton(view);
        fadeStartLogo(imgLogoStart);
        setLogoClick(view);

        return view;
    }

    private void setupViews(View view){
        imgLogoStart = view.findViewById(R.id.start_image_small);
        progressBar = view.findViewById(R.id.pb_loading);
        themeToggleButton = view.findViewById(R.id.toggle_theme);
    }

    private void initToggleButton(){
//        Toast.makeText(getContext(), Integer.toString(flag), Toast.LENGTH_LONG).show();
        if (flag > 0){
            getActivity().setTheme(R.style.TurquoiseTheme);
            themeToggleButton.setBackground(getResources().getDrawable(R.drawable.toggle_turquoise, null));
            themeToggleButton.setBackgroundTintList(ColorStateList
                    .valueOf(getResources().getColor(R.color.tu_colorPrimaryDark, null)));
            flag = 1;
            return;
        }
//        getActivity().getTheme()
//        int name = getActivity().getTheme().getResources().hashCode();
//        Toast.makeText(getContext(), name, Toast.LENGTH_LONG).show();
//        if (getActivity().getTheme()){
//            themeToggleButton.setBackground(getResources().getDrawable(R.drawable.toggle_default, null));
//            themeToggleButton.setBackgroundTintList(ColorStateList
//                    .valueOf(getResources().getColor(R.color.colorPrimaryDark)));
//            return;
//        }
//        themeToggleButton.setBackground(getResources().getDrawable(R.drawable.toggle_turquoise, null));
//        themeToggleButton.setBackgroundTintList(ColorStateList
//                .valueOf(getResources().getColor(R.color.tu_colorPrimaryDark)));
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

    private void setLogoClick(View view){
        imgLogoStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "---logoClick_onClick---");
                progressBar.setVisibility(ProgressBar.VISIBLE);
                CommonUtils.setProgressBar(progressBar);
                ((NavigationHost)getActivity()).navigateTo(new PackageGridFragment(), true);
            }
        });
    }

    private void initDataLists(){
        ListUtils.initLists();
    }

    private void setThemeToggleButton(View view){
//        progressBar = view.findViewById(R.id.pb_loading);
        themeToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(), "---clickToggle---", Toast.LENGTH_SHORT).show();
                if (themeToggleButton.getBackgroundTintList() == ColorStateList.valueOf(getResources()
                        .getColor(R.color.colorPrimaryDark, null))){
                    themeToggleButton.setBackgroundTintList(ColorStateList
                            .valueOf(getResources().getColor(R.color.tu_colorPrimaryDark, null)));
                    getActivity().setTheme(R.style.TurquoiseTheme);
                    progressBar.setVisibility(ProgressBar.VISIBLE);
                    flag = 1;
                }else {
                    themeToggleButton.setBackgroundTintList(ColorStateList
                            .valueOf(getResources().getColor(R.color.colorPrimaryDark, null)));
                    getActivity().setTheme(R.style.AppTheme);
                    progressBar.setVisibility(ProgressBar.VISIBLE);
                    flag = 0;
                }
                CommonUtils.setProgressBar(progressBar);
//                Toast.makeText(getContext(), Integer.toString(flag), Toast.LENGTH_LONG).show();
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
