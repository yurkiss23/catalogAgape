package com.agape.datacatalog.resourcesView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.agape.datacatalog.R;
import com.agape.datacatalog.network.entries.ResourceEntry;

import java.util.List;

public class ResourceCardRecyclerViewAdapter extends RecyclerView.Adapter<ResourceCardViewHolder> {

    private List<ResourceEntry> resourceList;

    ResourceCardRecyclerViewAdapter(List<ResourceEntry>resourceList){
        this.resourceList = resourceList;
    }

    @NonNull
    @Override
    public ResourceCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.resource_card, parent, false);
        return new ResourceCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResourceCardViewHolder holder, int position) {
        if (resourceList != null && position < resourceList.size()) {
            ResourceEntry product = resourceList.get(position);
            holder.resourceTitle.setText(product.title);
            holder.resourceDescription.setText(product.description);
        }
    }

    @Override
    public int getItemCount() {
        if (resourceList != null && resourceList.size() > 0){
            return  resourceList.size();
        }else {
            return 1;
        }
    }
}
