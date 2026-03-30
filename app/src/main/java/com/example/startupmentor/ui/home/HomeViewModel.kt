package com.example.startupmentor.ui.home

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.startupmentor.data.Mentor

class HomeViewModel : ViewModel() {

    private val _featuredMentors = MutableLiveData<List<Mentor>>()
    val featuredMentors: LiveData<List<Mentor>> = _featuredMentors

    init {
        loadFeaturedMentors()
    }

    private fun loadFeaturedMentors() {
        val list = listOf(
            Mentor("1", "Arjun Sharma", "CTO", "TechVentures", "Expert in Android and Backend scaling.", listOf("Android", "Backend", "Scaling"), 4.9f, 120, "$50", "$90", true, "AS", Color.parseColor("#FF6B35")),
            Mentor("2", "Priya Mehta", "Growth Lead", "GrowthX", "Growth marketing specialist.", listOf("Marketing", "SEO"), 4.8f, 85, "$40", "$70", true, "PM", Color.parseColor("#00D4AA")),
            Mentor("4", "Sneha Iyer", "Product Director", "Zomato", "Product management and UX expert.", listOf("Product", "UX"), 4.9f, 150, "$60", "$110", true, "SI", Color.parseColor("#2196F3"))
        )
        _featuredMentors.value = list
    }
}