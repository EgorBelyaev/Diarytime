package com.example.android.trackmysleepquality.diarytimer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.trackmysleepquality.database.DiaryDatabaseDao
import com.example.android.trackmysleepquality.database.DiaryNote
import kotlinx.coroutines.*

class DiaryTimerViewModel(
    private val diaryId: Long = 0L,
    private val dao: DiaryDatabaseDao):ViewModel(){

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _navigateToDiaryTracker = MutableLiveData<DiaryNote?>()
    val navigateToDiaryTracker: LiveData<DiaryNote?>
        get() = _navigateToDiaryTracker

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun onSetDiarytimer(time: Long,description:String){
        uiScope.launch {
            withContext(Dispatchers.IO){
                val note = dao.get(diaryId)?: return@withContext
                note.diarytime = time
                note.description = description
                dao.update(note)
                _navigateToDiaryTracker.value = note
            }

        }
    }

    fun doneNavigating(){
        _navigateToDiaryTracker.value = null
    }
}
