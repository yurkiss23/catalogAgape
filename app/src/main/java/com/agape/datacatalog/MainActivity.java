package com.agape.datacatalog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.agape.datacatalog.newsView.NewsGridFragment;
import com.agape.datacatalog.packageView.PackageGridFragment;
import com.agape.datacatalog.videoView.VideosGridFragment;

public class MainActivity extends AppCompatActivity implements NavigationHost{

    private final String TAG = "MyLOG_MA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new NewsGridFragment())
                    .commit();
        }
    }

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
}
