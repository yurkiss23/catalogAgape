package com.agape.datacatalog.resourcesView;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.agape.datacatalog.R;

public class ResourceCardViewHolder extends RecyclerView.ViewHolder {

    public TextView resourceTitle;
    public TextView resourceDescription;
    public ResourceCardViewHolder(@NonNull View itemView) {
        super(itemView);
        resourceTitle = itemView.findViewById(R.id.resource_title);
        resourceDescription = itemView.findViewById(R.id.resource_description);
    }
}
