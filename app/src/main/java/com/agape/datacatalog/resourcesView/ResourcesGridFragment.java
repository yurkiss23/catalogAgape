package com.agape.datacatalog.resourcesView;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.agape.datacatalog.R;
import com.agape.datacatalog.network.entries.ResourceEntry;
import com.agape.datacatalog.packageView.PackageGridItemDecoration;

import java.util.List;

public class ResourcesGridFragment extends Fragment {

    private final String TAG = "MyLOG_RGF";

    private RecyclerView resRecyclerView;
    private List<ResourceEntry> listResourceEntry;
    private ResourceCardRecyclerViewAdapter resourceAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "---ResourcesGridFragment---onCreateView---");
        View view = inflater.inflate(R.layout.fragment_resources_grid, container, false);

        setView(view);
        setRecyclerView(view);

        return view;
    }

    private void setView(View view){
        resRecyclerView = view.findViewById(R.id.resources_recycler_view);
    }

    private void setRecyclerView(View view){
        resRecyclerView.setHasFixedSize(true);
        resRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));

        resourceAdapter = new ResourceCardRecyclerViewAdapter(ResourceEntry.initResourcesEntryList(getResources()));
        resRecyclerView.setAdapter(resourceAdapter);
        int largePadding = getResources().getDimensionPixelSize(R.dimen.package_grid_spacing);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.package_grid_spacing_small);
        resRecyclerView.addItemDecoration(new PackageGridItemDecoration(largePadding, smallPadding));
    }
}
