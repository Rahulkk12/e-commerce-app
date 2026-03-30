package com.example.startupmentor.ui.chat

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.startupmentor.data.ChatConversation
import com.example.startupmentor.databinding.ItemConversationBinding

class ConversationAdapter(private val onClick: (ChatConversation) -> Unit) :
    ListAdapter<ChatConversation, ConversationAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemConversationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemConversationBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(conversation: ChatConversation) {
            binding.tvName.text = conversation.mentorName
            binding.tvLastMessage.text = conversation.lastMessage
            binding.tvTime.text = conversation.timestamp
            binding.tvInitials.text = conversation.mentorInitials
            binding.vAvatarBg.backgroundTintList = ColorStateList.valueOf(conversation.avatarColor)
            
            if (conversation.unreadCount > 0) {
                binding.tvUnread.visibility = View.VISIBLE
                binding.tvUnread.text = conversation.unreadCount.toString()
            } else {
                binding.tvUnread.visibility = View.GONE
            }

            binding.root.setOnClickListener { onClick(conversation) }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<ChatConversation>() {
        override fun areItemsTheSame(oldItem: ChatConversation, newItem: ChatConversation): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: ChatConversation, newItem: ChatConversation): Boolean = oldItem == newItem
    }
}