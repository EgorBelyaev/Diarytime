package com.example.android.trackmysleepquality.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DiaryDatabaseDao{
    @Insert
    fun insert(note: DiaryNote)

    @Update
    fun update(note: DiaryNote)

    @Query("SELECT * FROM diary_table WHERE id = :key")
    fun get(key: Long): DiaryNote?

    @Query("DELETE FROM diary_table")
    fun clear()

    @Query("SELECT * FROM diary_table ORDER BY id DESC")
    fun getAllNote(): LiveData<List<DiaryNote>>

    @Query("SELECT * FROM diary_table ORDER BY id DESC LIMIT 1")
    fun getOne(): DiaryNote?
}
