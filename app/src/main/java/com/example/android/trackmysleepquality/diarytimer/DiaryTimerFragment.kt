package com.example.android.trackmysleepquality.diarytimer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.database.DiaryDatabase
import com.example.android.trackmysleepquality.databinding.FragmentDiaryTimerBinding



class DiaryTimerFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentDiaryTimerBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_diary_timer, container, false)

        val application = requireNotNull(this.activity).application

        val args = DiaryTimerFragmentArgs.fromBundle(requireArguments())
        val dao = DiaryDatabase.getInstance(application).getDiaryDatabaseDao()
        val viewModelFactory = DiaryTimerModelFactory(args.diaryNoteKey,dao)
        val viewModel = ViewModelProvider(this,viewModelFactory).get(DiaryTimerViewModel::class.java)
        return binding.root
    }

    //noteString?

}