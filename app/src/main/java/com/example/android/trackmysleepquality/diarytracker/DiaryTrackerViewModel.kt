package com.example.android.trackmysleepquality.diarytracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.android.trackmysleepquality.database.DiaryDatabaseDao
import com.example.android.trackmysleepquality.database.DiaryNote
import com.example.android.trackmysleepquality.formatDiary
import kotlinx.coroutines.*

class DiaryTrackerViewModel(
    val dao: DiaryDatabaseDao,
    application: Application) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val note = dao.getAllNote()
    //1
    private val onenote = MutableLiveData<DiaryNote?>()

    val noteString = Transformations.map(note){ note ->
        formatDiary(note,application.resources)
    }

    private val _navigateToDiaryTimer = MutableLiveData<DiaryNote?>()
    val navigateToDiaryTimer: LiveData<DiaryNote?>
        get() = _navigateToDiaryTimer

    init {
        initOneNote()
    }

    private fun initOneNote() {
        uiScope.launch {
            onenote.value = getOneNoteFromDao()
        }
    }

    private suspend fun getOneNoteFromDao(): DiaryNote? {
        return withContext(Dispatchers.IO){
            var diary = dao.getOne()
            //2

            return@withContext diary
        }

    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun onStartTracking(){
        uiScope.launch {
            val newDiary = DiaryNote()
            insert(newDiary)
            onenote.value = getOneNoteFromDao()
            _navigateToDiaryTimer.value = newDiary
        }
    }

    fun onClear(){
        uiScope.launch {
            clear()
            onenote.value = null
        }
    }

    private suspend fun insert(newDiary: DiaryNote) {
        withContext(Dispatchers.IO) {
            dao.insert(newDiary)
        }
    }
    private suspend fun update(oldDiary: DiaryNote) {
        withContext(Dispatchers.IO) {
            dao.update(oldDiary)
        }
    }
    private suspend fun clear() {
        withContext(Dispatchers.IO) {
            dao.clear()
        }
    }

    fun doneNavigating(){
        _navigateToDiaryTimer.value = null
    }

}