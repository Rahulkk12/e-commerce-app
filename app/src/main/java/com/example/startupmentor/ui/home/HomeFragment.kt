package com.example.startupmentor.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.startupmentor.MentorDetailActivity
import com.example.startupmentor.R
import com.example.startupmentor.data.PreferenceManager
import com.example.startupmentor.data.Session
import com.example.startupmentor.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val prefManager = PreferenceManager(requireContext())
        binding.tvWelcome.text = getString(R.string.good_morning, prefManager.getUserName())

        binding.btnChat.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_chat)
        }

        setupFeaturedMentors()
        setupUpcomingSessions()
        setupCategories()
    }

    private fun setupFeaturedMentors() {
        val adapter = FeaturedMentorAdapter { mentor ->
            val intent = Intent(requireContext(), MentorDetailActivity::class.java)
            intent.putExtra("mentor", mentor)
            startActivity(intent)
        }
        binding.rvFeaturedMentors.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvFeaturedMentors.adapter = adapter

        viewModel.featuredMentors.observe(viewLifecycleOwner) { mentors ->
            adapter.submitList(mentors)
        }
    }

    private fun setupUpcomingSessions() {
        val adapter = UpcomingSessionAdapter()
        binding.rvUpcomingSessions.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvUpcomingSessions.adapter = adapter

        val mockSessions = listOf(
            Session("1", "Arjun Sharma", "AS", "25 Oct", "10:00 AM", "30 min", "Confirmed"),
            Session("2", "Priya Mehta", "PM", "27 Oct", "02:00 PM", "60 min", "Confirmed")
        )
        adapter.submitList(mockSessions)
    }

    private fun setupCategories() {
        val categories = listOf(
            Category(getString(R.string.tech), android.R.drawable.ic_menu_preferences),
            Category(getString(R.string.marketing), android.R.drawable.ic_menu_share),
            Category(getString(R.string.finance), android.R.drawable.ic_menu_save),
            Category(getString(R.string.legal), android.R.drawable.ic_menu_info_details),
            Category(getString(R.string.product), android.R.drawable.ic_menu_view),
            Category(getString(R.string.sales), android.R.drawable.ic_menu_send)
        )
        binding.rvCategories.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvCategories.adapter = CategoryAdapter(categories)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}