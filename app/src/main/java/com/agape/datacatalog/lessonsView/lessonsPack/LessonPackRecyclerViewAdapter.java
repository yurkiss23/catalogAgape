package com.agape.datacatalog.lessonsView.lessonsPack;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.agape.datacatalog.R;
import com.agape.datacatalog.lessonsView.lessonsPack.click_listener.OnClickListener;
import com.agape.datacatalog.network.entries.LessonPackEntry;
import com.agape.datacatalog.network.requester.ImageRequester;

import java.util.List;

public class LessonPackRecyclerViewAdapter extends RecyclerView.Adapter<LessonPackViewHolder> {
    private final String TAG = "MyLOG_LCRVA";

    private List<LessonPackEntry> lessonsPackList;
    private ImageRequester imageRequester;
    private OnClickListener clickListener;

    public LessonPackRecyclerViewAdapter(List<LessonPackEntry> lessonsPackList, OnClickListener clickListener) {
        this.lessonsPackList = lessonsPackList;
        this.clickListener = clickListener;
        imageRequester = ImageRequester.getInstance();
    }

    @NonNull
    @Override
    public LessonPackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lesson_pack_card, parent, false);
        return new LessonPackViewHolder(view, view);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonPackViewHolder holder, int position) {
        Log.d(TAG, "---onBind_lessonPack---");

        if (lessonsPackList != null && position < lessonsPackList.size()){
            LessonPackEntry lessonPack = lessonsPackList.get(position);
            holder.lessonPackTitle.setText(lessonPack.title);
            holder.lessonPackDescription.setText(lessonPack.description);
            imageRequester.setImageFromUrl(holder.lessonPackImage, lessonPack.image);

            holder.getView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.setOnClick(lessonsPackList.get(position), v);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (lessonsPackList != null && lessonsPackList.size() > 0){
            return lessonsPackList.size();
        }else {
            return 1;
        }
    }
}
