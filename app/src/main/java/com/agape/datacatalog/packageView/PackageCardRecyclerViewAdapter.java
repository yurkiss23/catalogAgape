package com.agape.datacatalog.packageView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.agape.datacatalog.R;
import com.agape.datacatalog.network.requester.ImageRequester;
import com.agape.datacatalog.network.entries.PackageEntry;
import com.agape.datacatalog.packageView.click_listener.OnShowListener;

import java.util.List;

public class PackageCardRecyclerViewAdapter extends RecyclerView.Adapter<PackageCardViewHolder> {

    private List<PackageEntry> packageList;
    private ImageRequester imageRequester;
    private OnShowListener showListener;
    private Context context;

    PackageCardRecyclerViewAdapter(List<PackageEntry> packageList,
                                   OnShowListener showListener,
                                   Context context) {
        this.packageList = packageList;
        this.showListener = showListener;
        this.context = context;
        imageRequester = ImageRequester.getInstance();
    }

    @NonNull
    @Override
    public PackageCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.package_card, parent, false);
        return new PackageCardViewHolder(view, view);
    }

    @Override
    public void onBindViewHolder(@NonNull PackageCardViewHolder holder, int position) {
        if (packageList != null && position < packageList.size()){
            PackageEntry pack = packageList.get(position);
            holder.packageTitle.setText(pack.title);
            holder.packageText.setText(pack.text);
            imageRequester.setImageFromUrl(holder.packageImage, pack.image);

            holder.getView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showListener.showItem(packageList.get(position).pk);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (packageList != null && packageList.size() > 0){
            return packageList.size();
        }else {
            return 1;
        }
    }
}
