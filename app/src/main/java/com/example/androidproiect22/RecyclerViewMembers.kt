package com.example.androidproiect22

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproiect22.model.Department
import com.example.androidproiect22.model.Task
import com.example.androidproiect22.model.User
import com.example.androidproiect22.service.DepartmentService
import com.example.androidproiect22.ui.members.MembersFragment
import com.example.androidproiect22.ui.tasks.TaskListFragment


class RecyclerViewMembers : RecyclerView.Adapter<RecyclerViewMembers.RecyclerViewViewHolder>()  {

    inner class RecyclerViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val username : TextView = itemView.findViewById(R.id.userName)
        val department: TextView = itemView.findViewById(R.id.departmentText)
        val depUser: TextView = itemView.findViewById(R.id.usersDepartment)    }

    private var userList : MutableList<User> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(userList: MutableList<User>) {
        this.userList.clear()
        this.userList.addAll(userList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.member, parent, false)

        return RecyclerViewViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return userList.size
    }


    override fun onBindViewHolder(holder: RecyclerViewViewHolder, position: Int) {
        val currentItem = userList[position]

        holder.username.text = currentItem.firstName
        holder.department.text = currentItem.lastName

    }

}