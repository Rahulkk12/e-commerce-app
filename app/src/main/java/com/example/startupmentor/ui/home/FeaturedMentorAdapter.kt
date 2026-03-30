package com.example.startupmentor.ui.home

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.startupmentor.data.Mentor
import com.example.startupmentor.databinding.ItemFeaturedMentorBinding

class FeaturedMentorAdapter(private val onBookClick: (Mentor) -> Unit) :
    ListAdapter<Mentor, FeaturedMentorAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFeaturedMentorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemFeaturedMentorBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(mentor: Mentor) {
            binding.tvName.text = mentor.name
            binding.tvRole.text = "${mentor.title} @ ${mentor.company}"
            binding.tvRating.text = mentor.rating.toString()
            binding.tvInitials.text = mentor.initials
            binding.vAvatarBg.backgroundTintList = ColorStateList.valueOf(mentor.avatarColor)
            
            binding.btnBook.setOnClickListener { onBookClick(mentor) }
            binding.root.setOnClickListener { /* Navigate to detail */ }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Mentor>() {
        override fun areItemsTheSame(oldItem: Mentor, newItem: Mentor): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Mentor, newItem: Mentor): Boolean = oldItem == newItem
    }
}