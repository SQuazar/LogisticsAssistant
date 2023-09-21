package net.quazar.logisticsassistant

import android.os.Build
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import net.quazar.logisticsassistant.data.model.Task

import net.quazar.logisticsassistant.placeholder.PlaceholderContent.PlaceholderItem
import net.quazar.logisticsassistant.databinding.FragmentTaskBinding
import java.time.format.DateTimeFormatter

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */


class MyTasksRecyclerViewAdapter(
    private var values: List<Task>
) : RecyclerView.Adapter<MyTasksRecyclerViewAdapter.ViewHolder>() {

    @RequiresApi(Build.VERSION_CODES.O)
    val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    @RequiresApi(Build.VERSION_CODES.O)
    val timeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        values = values.sortedByDescending { task -> task.description.orderDate; task.isCurrentTask }
        return ViewHolder(
            FragmentTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.cargoType.text = item.description.cargoType
        if (item.isCurrentTask)
            holder.isCurrentTask.visibility = View.VISIBLE

        holder.orderDate.text = dateFormatter.format(item.description.orderDate)
        holder.arrivalTime.text = timeFormatter.format(item.description.arrivalTime)
        holder.routeStart.text = item.description.routeStart
        holder.routeEnd.text = item.description.routeEnd
        holder.orderDetails.text = item.description.orderDetails
        holder.paymentOptions.text = item.description.paymentOptions
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentTaskBinding) : RecyclerView.ViewHolder(binding.root) {
        val cargoType: TextView = binding.taskName
        val isCurrentTask: TextView = binding.isCurrentTask
        val orderDate: TextView = binding.orderDate
        val arrivalTime: TextView = binding.arrivalTime
        val routeStart: TextView = binding.routeStart
        val routeEnd: TextView = binding.routeEnd
        val orderDetails: TextView = binding.orderDetails
        val paymentOptions: TextView = binding.paymentOptions


        override fun toString(): String {

            return super.toString() + " '" + cargoType.text + "'"
        }
    }

}