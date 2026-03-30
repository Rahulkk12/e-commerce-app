package com.example.startupmentor.ui.chat

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.startupmentor.ChatDetailActivity
import com.example.startupmentor.data.ChatConversation
import com.example.startupmentor.databinding.FragmentChatBinding

class ChatFragment : Fragment() {

    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val conversations = listOf(
            ChatConversation("1", "Arjun Sharma", "AS", "Let's discuss the backend architecture.", "10:30 AM", 2, Color.parseColor("#FF6B35")),
            ChatConversation("2", "Priya Mehta", "PM", "The SEO report looks good.", "Yesterday", 0, Color.parseColor("#00D4AA")),
            ChatConversation("3", "Sneha Iyer", "SI", "When can we review the wireframes?", "Monday", 0, Color.parseColor("#2196F3"))
        )

        val adapter = ConversationAdapter { conversation ->
            val intent = Intent(requireContext(), ChatDetailActivity::class.java)
            intent.putExtra("mentor_name", conversation.mentorName)
            intent.putExtra("mentor_initials", conversation.mentorInitials)
            intent.putExtra("avatar_color", conversation.avatarColor)
            startActivity(intent)
        }

        binding.rvConversations.layoutManager = LinearLayoutManager(requireContext())
        binding.rvConversations.adapter = adapter
        adapter.submitList(conversations)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}