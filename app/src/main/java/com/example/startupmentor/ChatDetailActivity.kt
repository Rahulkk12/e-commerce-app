package com.example.startupmentor

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.startupmentor.data.Message
import com.example.startupmentor.databinding.ActivityChatDetailBinding
import com.example.startupmentor.ui.chat.MessageAdapter
import java.util.UUID

class ChatDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatDetailBinding
    private lateinit var adapter: MessageAdapter
    private val messages = mutableListOf<Message>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("mentor_name") ?: "Mentor"
        val initials = intent.getStringExtra("mentor_initials") ?: ""
        val color = intent.getIntExtra("avatar_color", 0)

        setupToolbar(name, initials, color)
        setupRecyclerView()

        binding.btnSend.setOnClickListener {
            val content = binding.etMessage.text.toString()
            if (content.isNotEmpty()) {
                sendMessage(content)
                binding.etMessage.text.clear()
            }
        }

        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setupToolbar(name: String, initials: String, color: Int) {
        binding.tvToolbarName.text = name
        binding.ivToolbarAvatar.backgroundTintList = ColorStateList.valueOf(color)
        // Note: For simplicity, using same initials approach if needed or just color
    }

    private fun setupRecyclerView() {
        adapter = MessageAdapter()
        binding.rvMessages.layoutManager = LinearLayoutManager(this).apply {
            stackFromEnd = true
        }
        binding.rvMessages.adapter = adapter

        // Mock messages
        messages.add(Message("1", "Hello! How can I help you today?", "mentor", System.currentTimeMillis() - 100000, false))
        adapter.submitList(messages.toList())
    }

    private fun sendMessage(content: String) {
        val newMessage = Message(UUID.randomUUID().toString(), content, "user", System.currentTimeMillis(), true)
        messages.add(newMessage)
        adapter.submitList(messages.toList()) {
            binding.rvMessages.scrollToPosition(messages.size - 1)
            
            // Animation for new message
            val lastView = binding.rvMessages.layoutManager?.findViewByPosition(messages.size - 1)
            lastView?.startAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left))
        }
    }
}