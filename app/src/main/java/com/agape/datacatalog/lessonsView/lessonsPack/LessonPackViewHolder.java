package com.agape.datacatalog.lessonsView.lessonsPack;

import android.view.View;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.agape.datacatalog.R;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

public class LessonPackViewHolder extends RecyclerView.ViewHolder {
    private View view;

    public TextView lessonPackTitle;
    public TextView lessonPackDescription;
    public NetworkImageView lessonPackImage;
    public LessonPackViewHolder(View view, @NonNull View itemView) {
        super(itemView);
        this.view = itemView;
        lessonPackTitle = itemView.findViewById(R.id.lessonPack_title);
        lessonPackDescription = itemView.findViewById(R.id.lessonPack_description);
        lessonPackImage = itemView.findViewById(R.id.lessonPack_image);
    }

    public View getView() { return view; }
}
