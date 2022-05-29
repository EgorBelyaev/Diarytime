package com.example.android.trackmysleepquality.diarytimer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Dao
import com.example.android.trackmysleepquality.database.DiaryDatabaseDao
import java.lang.IllegalArgumentException

class DiaryTimerModelFactory(
    private val diaryId: Long,
    private val dao: DiaryDatabaseDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DiaryTimerViewModel::class.java)){
            return DiaryTimerViewModel(diaryId,dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
