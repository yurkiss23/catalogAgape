package com.agape.datacatalog.packageView;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.agape.datacatalog.R;
import com.android.volley.toolbox.NetworkImageView;

public class PackageCardViewHolder extends RecyclerView.ViewHolder {
    private View view;

    public TextView packageTitle;
    public TextView packageText;
    public NetworkImageView packageImage;
    public PackageCardViewHolder(View view, @NonNull View itemView) {
        super(itemView);
        this.view = itemView;
        packageTitle = itemView.findViewById(R.id.package_title);
        packageText = itemView.findViewById(R.id.package_description);
        packageImage = itemView.findViewById(R.id.package_image);
    }

    public View getView() { return view; }
}
