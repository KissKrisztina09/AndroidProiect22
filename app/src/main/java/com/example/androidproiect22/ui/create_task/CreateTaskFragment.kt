package com.example.androidproiect22.ui.create_task

import android.app.DatePickerDialog
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.androidproiect22.R
import com.example.androidproiect22.databinding.FragmentCreateTaskBinding
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

class CreateTaskFragment : Fragment() {

    private var _binding : FragmentCreateTaskBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val viewModel = ViewModelProvider(this)[CreateTaskViewModel::class.java]

        _binding = FragmentCreateTaskBinding.inflate(inflater, container, false)

        val root: View = binding.root

        val navController = findNavController()

        binding.createTask.setOnClickListener{

            try {
                val priority : Int = if (binding.PrioritySelector.selectedItem.toString() == "High") {
                    0
                } else {
                    1
                }

                val assignee = viewModel.users.first{it.toString() == binding.SelectAssigneeSpinner.selectedItem.toString()}
                val department = viewModel.departments.first{it.toString() == binding.SelectDepartmentSpinner.selectedItem.toString()}
                val deadline =  SimpleDateFormat("MM/dd/yyyy").parse(binding.editTextDate.text.toString())

                viewModel.createTask(binding.TaskNameField.text.toString(), binding.descriptionTextField.text.toString(), assignee.userId, priority, deadline.time,department.id)
            }
            catch (exception: Exception) {
                val message : String
                if (binding.TaskNameField.text.toString().isEmpty()) {
                    message = "Enter a task name!"
                }
                else {
                    message = "Select a deadline!"
                }
                Snackbar.make(
                    root,
                    message,
                    Snackbar.LENGTH_SHORT
                ).show()
            }

        }

        viewModel.createTaskResult.observe(viewLifecycleOwner) {
            if (it == CreateTaskResult.LOADING) {
                binding.progressBarUpload.visibility = View.VISIBLE
                return@observe
            }
            else if (it == CreateTaskResult.SUCCESS) {
                binding.progressBarUpload.visibility = View.INVISIBLE
                binding.editTextDate.transformIntoDatePicker(requireContext(), "MM/dd/yyyy")


                val adapter= ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, viewModel.departments)
                binding.SelectDepartmentSpinner.adapter = adapter

                val priorities = listOf("High", "Normal")

                val priorityAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, priorities)
                binding.PrioritySelector.adapter = priorityAdapter

                val userAdapter = ArrayAdapter(requireContext(),  android.R.layout.simple_spinner_item, viewModel.users)
                binding.SelectAssigneeSpinner.adapter = userAdapter


            }
            else if (it == CreateTaskResult.UPLOAD_SUCCESS) {
                binding.progressBarUpload.visibility = View.INVISIBLE
                Toast.makeText(activity, "Task created!", Toast.LENGTH_LONG).show()
                navController.navigate(R.id.navigation_tasks)
                //navController.popBackStack()

            }
            else if (it == CreateTaskResult.UNKNOWN_ERROR || it == CreateTaskResult.INVALID_TOKEN) {
                binding.progressBarUpload.visibility = View.INVISIBLE
                Toast.makeText(activity, it.toString(), Toast.LENGTH_LONG).show()

            }
        }


        return root
    }


    private fun EditText.transformIntoDatePicker(context: Context, format: String, maxDate: Date? = null) {
        isFocusableInTouchMode = false
        isClickable = true
        isFocusable = false

        val myCalendar = Calendar.getInstance()
        val datePickerOnDataSetListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                myCalendar.set(Calendar.YEAR, year)
                myCalendar.set(Calendar.MONTH, monthOfYear)
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val sdf = SimpleDateFormat(format, Locale.UK)
                setText(sdf.format(myCalendar.time))
            }

        setOnClickListener {
            DatePickerDialog(
                context, datePickerOnDataSetListener, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).run {
                maxDate?.time?.also { datePicker.maxDate = it }
                show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}