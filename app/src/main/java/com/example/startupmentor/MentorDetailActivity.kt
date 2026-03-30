package com.example.startupmentor

import android.content.res.ColorStateList
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.startupmentor.data.Mentor
import com.example.startupmentor.data.Session
import com.example.startupmentor.data.SessionRepository
import com.example.startupmentor.databinding.ActivityMentorDetailBinding
import com.google.android.material.chip.Chip
import java.util.UUID

class MentorDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMentorDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMentorDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mentor = intent.getParcelableExtra<Mentor>("mentor") ?: return

        setupUI(mentor)

        binding.btnBookSession.setOnClickListener {
            showBookingConfirmation(mentor)
        }

        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setupUI(mentor: Mentor) {
        binding.tvName.text = mentor.name
        binding.tvTitle.text = "${mentor.title} @ ${mentor.company}"
        binding.tvBio.text = mentor.bio
        binding.tvInitials.text = mentor.initials
        binding.vAvatarBg.backgroundTintList = ColorStateList.valueOf(mentor.avatarColor)

        mentor.expertise.forEach { skill ->
            val chip = Chip(this).apply {
                text = skill
                isClickable = false
            }
            binding.cgExpertise.addView(chip)
        }

        binding.chip30min.text = "${getString(R.string.min_30)} (${mentor.sessionPrice30})"
        binding.chip60min.text = "${getString(R.string.min_60)} (${mentor.sessionPrice60})"
    }

    private fun showBookingConfirmation(mentor: Mentor) {
        val duration = if (binding.chip30min.isChecked) "30 min" else "60 min"
        val date = "Nov 15" // Simplified for demo
        val time = "10:00 AM" // Simplified for demo

        AlertDialog.Builder(this)
            .setTitle(R.string.booking_confirmation)
            .setMessage(getString(R.string.booking_message, duration, mentor.name, date, time))
            .setPositiveButton(R.string.yes) { _, _ ->
                val newSession = Session(
                    id = UUID.randomUUID().toString(),
                    mentorName = mentor.name,
                    mentorInitials = mentor.initials,
                    date = date,
                    time = time,
                    duration = duration,
                    status = "Confirmed"
                )
                SessionRepository.addSession(newSession)
                Toast.makeText(this, "Session booked successfully!", Toast.LENGTH_SHORT).show()
                finish()
            }
            .setNegativeButton(R.string.no, null)
            .show()
    }
}