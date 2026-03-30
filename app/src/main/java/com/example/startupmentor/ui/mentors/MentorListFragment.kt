package com.example.startupmentor.ui.mentors

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.startupmentor.MentorDetailActivity
import com.example.startupmentor.databinding.FragmentMentorListBinding
import com.google.android.material.chip.Chip

class MentorListFragment : Fragment() {

    private var _binding: FragmentMentorListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MentorListViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMentorListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = MentorAdapter { mentor ->
            val intent = Intent(requireContext(), MentorDetailActivity::class.java)
            intent.putExtra("mentor", mentor)
            startActivity(intent)
        }
        binding.rvMentors.layoutManager = LinearLayoutManager(requireContext())
        binding.rvMentors.adapter = adapter

        viewModel.mentors.observe(viewLifecycleOwner) { mentors ->
            adapter.submitList(mentors)
        }

        binding.chipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
            val chip = group.findViewById<Chip>(checkedIds.firstOrNull() ?: -1)
            val category = chip?.text?.toString() ?: "All"
            viewModel.filterMentors(category)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}