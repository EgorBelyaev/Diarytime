package com.example.android.trackmysleepquality.diarytracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.database.DiaryDatabase
import com.example.android.trackmysleepquality.databinding.FragmentSleepTrackerBinding


class DiaryTrackerFragment : Fragment() {

    private lateinit var viewModel: DiaryTrackerViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentSleepTrackerBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_sleep_tracker, container, false)


        val adapter = DiaryNoteAdapter()
        binding.scrollView2.adapter = adapter

        val application = requireActivity().application
        val dao = DiaryDatabase.getInstance(application).getDiaryDatabaseDao()
        val viewModelFactory = DiaryTrackerViewModelFactory(dao,application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DiaryTrackerViewModel::class.java)

        viewModel.note.observe(viewLifecycleOwner, Observer { note ->
            if(note !=  null)
                adapter.data = note
       })


        viewModel.navigateToDiaryTimer.observe(viewLifecycleOwner, Observer { note ->
            if(note != null ){
                findNavController().navigate(DiaryTrackerFragmentDirections.actionSleepTrackerFragmentToSleepQualityFragment(note.diaryid))
                viewModel.doneNavigating()
            }
        })


        binding.addButton.setOnClickListener {
            viewModel.onStartTracking()
        }
        binding.clearButton.setOnClickListener{
            viewModel.onClear()
        }

        return binding.root
    }
}