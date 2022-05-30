package com.example.android.trackmysleepquality.diarytracker

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R

class DiaryNoteViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
    val lengthTextView : TextView = itemView.findViewById(R.id.stopwatch_timer)
    val description : TextView = itemView.findViewById(R.id.description)

}