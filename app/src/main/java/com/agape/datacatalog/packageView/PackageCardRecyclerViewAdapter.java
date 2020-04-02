package com.agape.datacatalog.packageView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.agape.datacatalog.R;
import com.agape.datacatalog.network.ImageRequester;
import com.agape.datacatalog.network.PackageEntry;

import java.util.List;

public class PackageCardRecyclerViewAdapter extends RecyclerView.Adapter<PackageCardViewHolder> {

    private List<PackageEntry> packageList;
    private ImageRequester imageRequester;

    PackageCardRecyclerViewAdapter(List<PackageEntry> packageList) {
        this.packageList = packageList;
        imageRequester = ImageRequester.getInstance();
    }

    @NonNull
    @Override
    public PackageCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.package_card, parent, false);
        return new PackageCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PackageCardViewHolder holder, int position) {
        // TODO: Put ViewHolder binding code here in MDC-102
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
