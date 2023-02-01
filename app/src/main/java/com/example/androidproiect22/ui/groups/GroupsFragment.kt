package com.example.androidproiect22.ui.groups

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproiect22.R
import com.example.androidproiect22.RecyclerViewDepartments
import com.example.androidproiect22.databinding.FragmentGroupsBinding
import com.example.androidproiect22.ui.groups.GroupResult
import com.example.androidproiect22.ui.groups.GroupsViewModel

class GroupsFragment : Fragment() {

    private var _binding : FragmentGroupsBinding? = null

    private val binding get() = _binding!!

    private lateinit var mDepartmentListAdapter : RecyclerViewDepartments


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel = ViewModelProvider(this)[GroupsViewModel::class.java]

        _binding = FragmentGroupsBinding.inflate(inflater, container, false)

        val root: View = binding.root

        viewModel.departmentResult.observe(viewLifecycleOwner) {
            if (it == GroupResult.LOADING) {
                binding.progressBarLoading.visibility = View.VISIBLE
                return@observe
            }
            else if ( it == GroupResult.SUCCESS) {
                binding.progressBarLoading.visibility = View.INVISIBLE

                val recyclerView = root.findViewById<RecyclerView>(R.id.recycler_view)
                recyclerView.layoutManager = LinearLayoutManager(context)
                recyclerView.setHasFixedSize(true)
                mDepartmentListAdapter = RecyclerViewDepartments()
                recyclerView.adapter = mDepartmentListAdapter
                mDepartmentListAdapter.setData(viewModel.departmentList.toMutableList())

            }
            else {
                binding.progressBarLoading.visibility = View.INVISIBLE
                Toast.makeText(activity, it.toString(), Toast.LENGTH_LONG).show()
            }
        }

        return root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}