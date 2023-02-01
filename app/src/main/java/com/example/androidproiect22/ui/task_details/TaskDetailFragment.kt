package com.example.androidproiect22.ui.task_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.androidproiect22.databinding.FragmentTaskDetailBinding
import com.example.androidproiect22.model.Task

class TaskDetailFragment : Fragment() {

    private val sharedViewModel : TaskDetailViewModel by activityViewModels()

    lateinit var task : Task

    private var _binding : FragmentTaskDetailBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       task = sharedViewModel.task

        _binding = FragmentTaskDetailBinding.inflate(inflater, container, false)

        val root: View = binding.root

        binding.titleTextView.text = task.title
        binding.departmentText.text = task.department?.name ?: "Unknown"
        binding.creatorName.text = task.creator?.firstName + " " + task.creator?.lastName
        binding.assigneeName.text = task.assignee?.firstName + " " + task.assignee?.lastName
        if (task.priority == 1) {
            binding.priorityText.text = "High"
        }
        else {
            binding.priorityText.text = "Normal"
        }
        binding.description.text = task.description

        val sdf = java.text.SimpleDateFormat("MMMM dd yyyy")
        val assignedDateFormat = java.text.SimpleDateFormat("hh:mm MMMM dd yyyy")
        val date = java.util.Date(task.createdTime)
        binding.createdDate.text = assignedDateFormat.format(date)
        binding.deadlineText.text = sdf.format(date)

        return root
    }

}