package com.example.androidproiect22

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproiect22.model.Department

class RecyclerViewDepartments : RecyclerView.Adapter<RecyclerViewDepartments.RecyclerViewViewHolder>()  {

    inner class RecyclerViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title : TextView = itemView.findViewById(R.id.departmentName)
    }

    private var departmentList : MutableList<Department> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(departmentList: MutableList<Department>) {
        this.departmentList.clear()
        this.departmentList.addAll(departmentList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.department_example_item, parent, false)

        return RecyclerViewViewHolder(itemView)
    }

    override fun getItemCount(): Int {
       return departmentList.size
    }

    override fun onBindViewHolder(holder: RecyclerViewViewHolder, position: Int) {
        val currentItem = departmentList[position]

        holder.title.text = currentItem.name

    }

}