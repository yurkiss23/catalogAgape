package com.agape.datacatalog.utility;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.agape.datacatalog.NavigationHost;
import com.agape.datacatalog.R;
import com.agape.datacatalog.application.CatalogAgape;
import com.agape.datacatalog.lessonsView.LessonCardRecyclerViewAdapter;
import com.agape.datacatalog.lessonsView.LessonsGridFragment;
import com.agape.datacatalog.network.entries.LessonEntry;
import com.agape.datacatalog.packageView.PackageGridFragment;
import com.agape.datacatalog.packageView.PackageGridItemDecoration;

import java.util.concurrent.TimeUnit;

public final class CommonUtils {

    private static final String TAG = "CommonUtils";

    private static int cSpanCount;
    private static PopupMenu popup;
//    static ProgressDialog progressDialog;
//    static ProgressBar progressBar;

    private CommonUtils() {
        // This utility class is not publicly instantiable
    }

    public static void setProgressBar(ProgressBar progressBar){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.MILLISECONDS.sleep(2000);
//                    ((NavigationHost)activity).navigateTo(fragment, false);
                    progressBar.setVisibility(ProgressBar.INVISIBLE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        t.start();
    }

    public static void setUpToolBar(Toolbar toolbar, Activity activity){
        AppCompatActivity lessonActivity = (AppCompatActivity) activity;
        if (lessonActivity != null){
            lessonActivity.setSupportActionBar(toolbar);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ((NavigationHost)activity).navigateTo(new PackageGridFragment(), false);
                ((NavigationHost)activity).backTo();
            }
        });
    }

    @SuppressLint("ResourceType")
    public static void setRecyclerView(RecyclerView recyclerView, RecyclerView.Adapter adapter,
                                        Activity activity, Resources resources, String type){
        recyclerView.setHasFixedSize(true);
        if (activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            if (type != null){
                cSpanCount = 1;
            }else {
                cSpanCount = 2;
            }
        }else {
            if (type != null){
                cSpanCount = 2;
            }else {
                cSpanCount = 3;
            }
        }
        recyclerView.setLayoutManager(new GridLayoutManager(CatalogAgape.getAppContext(),
                cSpanCount, GridLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        int largePadding = resources.getDimensionPixelSize(R.dimen.package_grid_spacing);
        int smallPadding = resources.getDimensionPixelSize(R.dimen.package_grid_spacing_small);
        recyclerView.addItemDecoration(new PackageGridItemDecoration(largePadding, smallPadding));
    }

    public static void setPopup(View view, String[] itemList, Activity activity, Context context){
        popup = new PopupMenu(context, view);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                ((NavigationHost)activity).navigateTo(new LessonsGridFragment(), true);
                return false;
            }
        });
        if (itemList != null){
            for (String item:itemList){
                popup.getMenu().add(item);
            }
        }
        popup.show();
    }

//    public static ProgressDialog showLoading(Context context) {
//        progressDialog = new ProgressDialog(context);
//        progressDialog.show();
//        if (progressDialog.getWindow() != null) {
//            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        }
//        progressDialog.setContentView(R.layout.progress_dialog);
//        progressDialog.setIndeterminate(true);
//        progressDialog.setCancelable(false);
//        progressDialog.setCanceledOnTouchOutside(false);
//        return progressDialog;
//    }
//
//    public static void hideLoading() {
//        if (progressDialog == null) {
//            return;
//        }
//        progressDialog.dismiss();
//    }
}
