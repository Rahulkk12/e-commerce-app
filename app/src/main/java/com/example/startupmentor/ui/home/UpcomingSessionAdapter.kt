package com.example.startupmentor.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.startupmentor.data.Session
import com.example.startupmentor.databinding.ItemUpcomingSessionHomeBinding

class UpcomingSessionAdapter : ListAdapter<Session, UpcomingSessionAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUpcomingSessionHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemUpcomingSessionHomeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(session: Session) {
            binding.tvMentorName.text = "Session with ${session.mentorName}"
            binding.tvTime.text = "${session.date} at ${session.time}"
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Session>() {
        override fun areItemsTheSame(oldItem: Session, newItem: Session): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Session, newItem: Session): Boolean = oldItem == newItem
    }
}