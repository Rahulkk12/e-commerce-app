package com.example.startupmentor.ui.mentors

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.startupmentor.data.Mentor

class MentorListViewModel : ViewModel() {

    private val allMentors = listOf(
        Mentor("1", "Arjun Sharma", "CTO", "TechVentures", "Expert in Android and Backend scaling.", listOf("Android", "Backend", "Scaling"), 4.9f, 120, "$50", "$90", true, "AS", Color.parseColor("#FF6B35")),
        Mentor("2", "Priya Mehta", "Growth Lead", "GrowthX", "Growth marketing specialist with 10+ years experience.", listOf("Marketing", "SEO"), 4.8f, 85, "$40", "$70", true, "PM", Color.parseColor("#00D4AA")),
        Mentor("3", "Rahul Gupta", "VC Partner", "Sequoia", "Venture capitalist focusing on early-stage startups.", listOf("Fundraising", "Finance"), 4.7f, 60, "$100", "$180", false, "RG", Color.parseColor("#9C27B0")),
        Mentor("4", "Sneha Iyer", "Product Director", "Zomato", "Product management and UX expert for consumer apps.", listOf("Product", "UX"), 4.9f, 150, "$60", "$110", true, "SI", Color.parseColor("#2196F3")),
        Mentor("5", "Vikram Nair", "Founder", "SaaSify", "Serial entrepreneur with multiple exits in SaaS.", listOf("SaaS", "Sales"), 4.6f, 45, "$45", "$80", true, "VN", Color.parseColor("#4CAF50")),
        Mentor("6", "Ananya Das", "CMO", "Meesho", "Specialist in D2C branding and social commerce.", listOf("D2C", "Branding"), 4.8f, 95, "$55", "$100", true, "AD", Color.parseColor("#E91E63"))
    )

    private val _mentors = MutableLiveData<List<Mentor>>()
    val mentors: LiveData<List<Mentor>> = _mentors

    init {
        _mentors.value = allMentors
    }

    fun filterMentors(category: String) {
        if (category == "All") {
            _mentors.value = allMentors
        } else {
            _mentors.value = allMentors.filter { mentor ->
                mentor.expertise.any { it.contains(category, ignoreCase = true) } ||
                mentor.title.contains(category, ignoreCase = true)
            }
        }
    }
}