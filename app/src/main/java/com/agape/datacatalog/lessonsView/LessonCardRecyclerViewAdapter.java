package com.agape.datacatalog.lessonsView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.agape.datacatalog.R;
import com.agape.datacatalog.network.entries.LessonEntry;
import com.agape.datacatalog.network.requester.ImageRequester;

import java.util.List;

public class LessonCardRecyclerViewAdapter extends RecyclerView.Adapter<LessonCardViewHolder> {
    private final String TAG = "MyLOG_LCRVA";

    private List<LessonEntry> lessonsList;
    private ImageRequester imageRequester;

    public LessonCardRecyclerViewAdapter(List<LessonEntry> lessonsList) {
        this.lessonsList = lessonsList;
        imageRequester = ImageRequester.getInstance();
    }

    @NonNull
    @Override
    public LessonCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lesson_card, parent, false);
        return new LessonCardViewHolder(view, view);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonCardViewHolder holder, int position) {
        Log.d(TAG, "---onBind_lessonCard---");

        if (lessonsList != null && position < lessonsList.size()){
            LessonEntry lesson = lessonsList.get(position);
            holder.lessonTitle.setText(lesson.title);
            holder.lessonText.setText(lesson.text);
            imageRequester.setImageFromUrl(holder.lessonImage, lesson.image);
        }
    }

    @Override
    public int getItemCount() {
        if (lessonsList != null && lessonsList.size() > 0){
            return lessonsList.size();
        }else {
            return 1;
        }
    }
}
