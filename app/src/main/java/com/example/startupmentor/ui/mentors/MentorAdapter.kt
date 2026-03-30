package com.example.startupmentor.ui.mentors

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.startupmentor.data.Mentor
import com.example.startupmentor.databinding.ItemMentorBinding
import com.google.android.material.chip.Chip

class MentorAdapter(private val onViewProfile: (Mentor) -> Unit) :
    ListAdapter<Mentor, MentorAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMentorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemMentorBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(mentor: Mentor) {
            binding.tvName.text = mentor.name
            binding.tvTitle.text = "${mentor.title} @ ${mentor.company}"
            binding.tvRating.text = mentor.rating.toString()
            binding.tvInitials.text = mentor.initials
            binding.vAvatarBg.backgroundTintList = ColorStateList.valueOf(mentor.avatarColor)
            
            binding.cgExpertise.removeAllViews()
            mentor.expertise.forEach { skill ->
                val chip = Chip(binding.root.context).apply {
                    text = skill
                    // Setting a standard height manually to avoid unresolved resource issues
                    chipMinHeight = 32f * resources.displayMetrics.density
                    textSize = 10f
                }
                binding.cgExpertise.addView(chip)
            }

            binding.btnViewProfile.setOnClickListener { onViewProfile(mentor) }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Mentor>() {
        override fun areItemsTheSame(oldItem: Mentor, newItem: Mentor): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Mentor, newItem: Mentor): Boolean = oldItem == newItem
    }
}