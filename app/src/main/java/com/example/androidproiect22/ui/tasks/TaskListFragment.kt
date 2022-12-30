package com.example.androidproiect22.ui.tasks

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproiect22.OnTaskClickListener
import com.example.androidproiect22.R
import com.example.androidproiect22.RecyclerViewAdapter
import com.example.androidproiect22.databinding.FragmentTaskListBinding
import com.example.androidproiect22.model.Task
import com.example.androidproiect22.ui.task_details.TaskDetailViewModel

class TaskListFragment : Fragment(), OnTaskClickListener {

    private var _binding: FragmentTaskListBinding? = null

    private val binding get() = _binding!!

    private lateinit var mTaskListAdapter: RecyclerViewAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val taskViewModel = ViewModelProvider(this)[TaskListViewModel::class.java]

        _binding = FragmentTaskListBinding.inflate(inflater, container, false)

        val root: View = binding.root

        val navController = findNavController()

        binding.fab.setOnClickListener{
            navController.navigate(R.id.navigation_create_task)
        }


        taskViewModel.taskResult.observe(viewLifecycleOwner) {
            if (it == TaskResult.LOADING) {
                binding.progressBarCyclic.visibility = View.VISIBLE
                return@observe
            }
            else if (it == TaskResult.SUCCESS) {
                binding.progressBarCyclic.visibility = View.INVISIBLE
                //call example item
                val recycler_view = root.findViewById<RecyclerView>(R.id.recycler_view)
                recycler_view.layoutManager = LinearLayoutManager(context)
                recycler_view.setHasFixedSize(true)

                mTaskListAdapter = RecyclerViewAdapter(this)

                recycler_view.adapter = mTaskListAdapter
                mTaskListAdapter.setData(taskViewModel.taskList.toMutableList())

            }
            else if (it == TaskResult.UNKNOWN_ERROR || it == TaskResult.INVALID_TOKEN) {
                binding.progressBarCyclic.visibility = View.INVISIBLE
                Toast.makeText(activity, it.toString(), Toast.LENGTH_LONG).show()
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDetails(model: Task) {
        val sharedViewModel : TaskDetailViewModel by activityViewModels()
        sharedViewModel.task = model
        val navController = findNavController()

        navController.navigate(R.id.navigation_task_detail)
    }

}