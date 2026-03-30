package com.example.startupmentor.ui.sessions

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.startupmentor.R
import com.example.startupmentor.data.Session
import com.example.startupmentor.databinding.ItemSessionBinding

class SessionAdapter(private val onActionClick: (Session) -> Unit) :
    ListAdapter<Session, SessionAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSessionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemSessionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(session: Session) {
            binding.tvMentorName.text = session.mentorName
            binding.tvDateTime.text = "${session.date} | ${session.time} (${session.duration})"
            binding.tvInitials.text = session.mentorInitials
            binding.tvStatus.text = session.status

            if (session.status == "Confirmed") {
                binding.tvStatus.setTextColor(ContextCompat.getColor(binding.root.context, R.color.status_confirmed))
                binding.btnAction.text = binding.root.context.getString(R.string.join_meeting)
            } else {
                binding.tvStatus.setTextColor(ContextCompat.getColor(binding.root.context, R.color.status_completed))
                binding.btnAction.text = binding.root.context.getString(R.string.leave_review)
            }

            binding.btnAction.setOnClickListener { onActionClick(session) }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Session>() {
        override fun areItemsTheSame(oldItem: Session, newItem: Session): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Session, newItem: Session): Boolean = oldItem == newItem
    }
}