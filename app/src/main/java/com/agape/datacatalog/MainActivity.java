package com.agape.datacatalog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.agape.datacatalog.lessonsView.lessonsPack.LessonsPackFragment;
import com.agape.datacatalog.network.entries.LessonEntry;
import com.agape.datacatalog.network.entries.LessonPackEntry;
import com.agape.datacatalog.network.entries.NewsEntry;
import com.agape.datacatalog.newsView.NewsGridFragment;
import com.agape.datacatalog.packageView.PackageGridFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationHost{

    private final String TAG = "MyLOG_MA";

//    private static List<LessonPackEntry> lessonPackEntryList;
//    private static List<LessonEntry> lessonEntryList;
//    private static List<NewsEntry> newsEntryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        initLists();

        if (savedInstanceState == null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new PackageGridFragment())
                    .commit();
        }
    }

//    private void initLists(){
//        lessonPackEntryList = new ArrayList<>();
//        lessonEntryList = new ArrayList<>();
//        newsEntryList = new ArrayList<>();
//    }

    @Override
    public void navigateTo(Fragment fragment, boolean addToBackstack) {
        FragmentTransaction transaction =
                getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment);
        if (addToBackstack){
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    @Override
    public void backTo() {
        FragmentManager manager = getSupportFragmentManager();
        manager.popBackStack();
    }
}
