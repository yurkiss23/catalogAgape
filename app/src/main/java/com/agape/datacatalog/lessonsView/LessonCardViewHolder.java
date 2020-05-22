package com.agape.datacatalog.lessonsView;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.agape.datacatalog.R;
import com.android.volley.toolbox.NetworkImageView;

public class LessonCardViewHolder extends RecyclerView.ViewHolder {
    public TextView lessonTitle;
    public TextView lessonText;
    public NetworkImageView lessonImage;
    public LessonCardViewHolder(View view, @NonNull View itemView) {
        super(itemView);
        lessonTitle = itemView.findViewById(R.id.lesson_title);
        lessonText = itemView.findViewById(R.id.lesson_text);
        lessonImage = itemView.findViewById(R.id.lesson_image);
    }
}
