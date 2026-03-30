package com.example.startupmentor.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.startupmentor.LoginActivity
import com.example.startupmentor.R
import com.example.startupmentor.data.PreferenceManager
import com.example.startupmentor.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var prefManager: PreferenceManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prefManager = PreferenceManager(requireContext())

        setupUI()

        binding.btnLogout.setOnClickListener {
            prefManager.logout()
            startActivity(Intent(requireContext(), LoginActivity::class.java))
            requireActivity().finishAffinity()
        }
    }

    private fun setupUI() {
        binding.tvName.text = prefManager.getUserName()
        binding.tvEmail.text = prefManager.getUserEmail()
        binding.tvInitials.text = if (prefManager.getUserName().isNotEmpty()) {
            prefManager.getUserName().first().uppercase()
        } else "U"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}