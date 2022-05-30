package com.example.android.trackmysleepquality.diarytracker

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.database.DiaryNote

class DiaryNoteAdapter : RecyclerView.Adapter<DiaryNoteViewHolder>() {

    var data = listOf<DiaryNote>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryNoteViewHolder {
       val view =  LayoutInflater.from(parent.context).inflate(R.layout.stopwatch_item,parent,false)
        return DiaryNoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: DiaryNoteViewHolder, position: Int) {
        val item = data[position]
        val res = holder.itemView.resources
        holder.description.text = "test description"
        holder.lengthTextView.text = "test timer 00:00:1"
    }

    override fun getItemCount(): Int {
        return data.size
    }

}