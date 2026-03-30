package com.example.startupmentor

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.startupmentor.databinding.ActivityOnboardingBinding
import com.example.startupmentor.databinding.FragmentOnboardingPageBinding
import com.google.android.material.tabs.TabLayoutMediator

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = OnboardingAdapter(this)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { _, _ -> }.attach()

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position == 2) {
                    binding.btnNext.text = getString(R.string.get_started)
                } else {
                    binding.btnNext.text = getString(R.string.next)
                }
            }
        })

        binding.btnNext.setOnClickListener {
            if (binding.viewPager.currentItem < 2) {
                binding.viewPager.currentItem += 1
            } else {
                navigateToMain()
            }
        }

        binding.btnSkip.setOnClickListener {
            navigateToMain()
        }
    }

    private fun navigateToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    class OnboardingAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
        override fun getItemCount(): Int = 3
        override fun createFragment(position: Int): Fragment {
            return OnboardingFragment.newInstance(position)
        }
    }

    class OnboardingFragment : Fragment() {
        private var _binding: FragmentOnboardingPageBinding? = null
        private val binding get() = _binding!!

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
            _binding = FragmentOnboardingPageBinding.inflate(inflater, container, false)
            return binding.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            val position = arguments?.getInt("position") ?: 0
            when (position) {
                0 -> {
                    binding.tvTitle.text = getString(R.string.find_mentors)
                    binding.tvDescription.text = getString(R.string.find_mentors_desc)
                    binding.ivOnboarding.setImageResource(android.R.drawable.ic_menu_search)
                }
                1 -> {
                    binding.tvTitle.text = getString(R.string.book_sessions)
                    binding.tvDescription.text = getString(R.string.book_sessions_desc)
                    binding.ivOnboarding.setImageResource(android.R.drawable.ic_menu_today)
                }
                2 -> {
                    binding.tvTitle.text = getString(R.string.grow_startup)
                    binding.tvDescription.text = getString(R.string.grow_startup_desc)
                    binding.ivOnboarding.setImageResource(android.R.drawable.stat_sys_upload)
                }
            }
        }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }

        companion object {
            fun newInstance(position: Int) = OnboardingFragment().apply {
                arguments = Bundle().apply { putInt("position", position) }
            }
        }
    }
}