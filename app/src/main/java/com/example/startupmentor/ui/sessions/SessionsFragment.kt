package com.example.startupmentor.ui.sessions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.startupmentor.R
import com.example.startupmentor.data.Session
import com.example.startupmentor.data.SessionRepository
import com.example.startupmentor.databinding.FragmentSessionsBinding
import com.google.android.material.tabs.TabLayout

class SessionsFragment : Fragment() {

    private var _binding: FragmentSessionsBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: SessionAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSessionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = SessionAdapter { session ->
            if (session.status == "Confirmed") {
                Toast.makeText(requireContext(), "Joining meeting with ${session.mentorName}...", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Opening review for ${session.mentorName}...", Toast.LENGTH_SHORT).show()
            }
        }
        binding.rvSessions.layoutManager = LinearLayoutManager(requireContext())
        binding.rvSessions.adapter = adapter

        SessionRepository.sessions.observe(viewLifecycleOwner) { sessions ->
            filterSessions(binding.tabLayout.selectedTabPosition, sessions)
        }

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                SessionRepository.sessions.value?.let { filterSessions(tab?.position ?: 0, it) }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun filterSessions(position: Int, allSessions: List<Session>) {
        val filtered = if (position == 0) {
            allSessions.filter { it.status == "Confirmed" }
        } else {
            allSessions.filter { it.status == "Completed" }
        }

        if (filtered.isEmpty()) {
            binding.rvSessions.visibility = View.GONE
            binding.emptyState.visibility = View.VISIBLE
        } else {
            binding.rvSessions.visibility = View.VISIBLE
            binding.emptyState.visibility = View.GONE
            adapter.submitList(filtered)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}