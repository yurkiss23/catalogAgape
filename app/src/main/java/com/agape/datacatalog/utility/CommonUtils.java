package com.agape.datacatalog.utility;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.agape.datacatalog.NavigationHost;
import com.agape.datacatalog.R;
import com.agape.datacatalog.application.CatalogAgape;
import com.agape.datacatalog.lessonsView.LessonsGridFragment;
import com.agape.datacatalog.lessonsView.lessonsPack.LessonsPackFragment;
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
                                        Activity activity, Resources resources, String type, String padding){
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
        if (padding != null){
            largePadding = 0;
            smallPadding = 0;
        }
        recyclerView.addItemDecoration(new PackageGridItemDecoration(largePadding, smallPadding));
    }

    public static void setPopup(View view, Activity activity, Context context, String title){
        popup = new PopupMenu(context, view);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                ((NavigationHost)activity).navigateTo(new LessonsGridFragment(item.getItemId(), title),true);
                return false;
            }
        });
        if (LessonsPackFragment.subPackList != null){
            for (int[] item : LessonsPackFragment.subPackList){
                popup.getMenu().add(item[0] + "-й рік навчання");
            }
            popup.show();
        }else {
            Toast.makeText(context, "Уроки в розробці", Toast.LENGTH_SHORT).show();
        }
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
