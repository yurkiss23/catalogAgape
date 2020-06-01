package com.agape.datacatalog;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public interface NavigationHost {
    void navigateTo(Fragment fragment, boolean addToBackstack);
    void backTo();
}
