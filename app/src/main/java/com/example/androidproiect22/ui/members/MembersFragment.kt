package com.example.androidproiect22.ui.members

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproiect22.*
import com.example.androidproiect22.databinding.FragmentGroupsBinding
import com.example.androidproiect22.databinding.FragmentMembersBinding
import com.example.androidproiect22.ui.groups.GroupResult
import com.example.androidproiect22.ui.groups.GroupsViewModel

class MembersFragment : Fragment(){

    private var _binding : FragmentMembersBinding? =  null

    private val binding get() = _binding!!

    private lateinit var memberListAdapter : RecyclerViewMembers

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel = ViewModelProvider(this)[MemberViewModel::class.java]

        _binding = FragmentMembersBinding.inflate(inflater, container, false)

        val root: View = binding.root

        viewModel.memberResult.observe(viewLifecycleOwner) {
            if (it == MemberResult.LOADING) {
               binding.progressBarMembers.visibility = View.VISIBLE
                return@observe
            }
            else if ( it == MemberResult.SUCCESS) {
                binding.progressBarMembers.visibility = View.INVISIBLE

                val recyclerView = root.findViewById<RecyclerView>(R.id.recycler_view_members)
                recyclerView.layoutManager = LinearLayoutManager(context)
                recyclerView.setHasFixedSize(true)
                memberListAdapter = RecyclerViewMembers()
                recyclerView.adapter = memberListAdapter
                memberListAdapter.setData(viewModel.userList.toMutableList())

            }
            else {
                binding.progressBarMembers.visibility = View.INVISIBLE
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