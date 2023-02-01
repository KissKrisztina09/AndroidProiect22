package com.example.androidproiect22

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproiect22.model.Task
import com.example.androidproiect22.ui.tasks.TaskListFragment
import com.example.androidproiect22.ui.tasks.TaskListViewModel
import com.example.androidproiect22.ui.tasks.TaskResult

class RecyclerViewAdapter(private val mOnProductClickListener: TaskListFragment) : RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewViewHolder>() {

    inner class RecyclerViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title : TextView = itemView.findViewById(R.id.titleText)
        val department : TextView = itemView.findViewById(R.id.departmentText)
        val creator : TextView = itemView.findViewById(R.id.creatorText)
        val assignee : TextView = itemView.findViewById(R.id.assigneeNameText)
        val deadline : TextView = itemView.findViewById(R.id.deadlineText)
        val description : TextView = itemView.findViewById(R.id.descriptionText)
        val priority : TextView = itemView.findViewById(R.id.priorityText)


        val detailsButton : Button = itemView.findViewById(R.id.detailsButton)

    }
    private var taskList : MutableList<Task> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(taskList: MutableList<Task>) {
        this.taskList.clear()
        this.taskList.addAll(taskList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_example_item,parent,false)
        val holder = RecyclerViewViewHolder(itemView)

        // to see details
        holder.detailsButton.setOnClickListener {
            val position = holder.adapterPosition
            val model = taskList[position]
            mOnProductClickListener.onDetails(model)
        }

        return holder
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerViewViewHolder, position: Int) {
        val currentItem = taskList[position]

        val hmDateFormat = java.text.SimpleDateFormat("HH:mm")
        val sdf = java.text.SimpleDateFormat("MMMM-dd-yyyy")
        val date = java.util.Date(currentItem.createdTime)

        holder.title.text = currentItem.title


        if (currentItem.assignee != null) {
            holder.assignee.text = "${currentItem.assignee!!.firstName} ${currentItem.assignee!!.lastName}"
        }
        else {
            holder.assignee.text = "Unknown"
        }

        if (currentItem.department != null) {
            holder.department.text = currentItem.department!!.name
        }
        else {
            holder.department.text = "Unknown dep."
        }


        holder.creator.text = "${currentItem.creatorId} ${hmDateFormat.format(date)}"

        holder.description.text = currentItem.description

        holder.deadline.text = sdf.format(date)

        if (currentItem.priority == 1) {
            holder.priority.text = "High prio"
        }
        else {
            holder.priority.text = "Normal prio"
        }

    }

    override fun getItemCount(): Int = taskList.size

}

interface OnTaskClickListener {

    fun onDetails(model: Task)
}
