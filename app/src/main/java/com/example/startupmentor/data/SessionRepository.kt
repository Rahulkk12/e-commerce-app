package com.example.startupmentor.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object SessionRepository {
    private val _sessions = MutableLiveData<MutableList<Session>>(mutableListOf(
        Session("1", "Arjun Sharma", "AS", "25 Oct", "10:00 AM", "30 min", "Confirmed"),
        Session("2", "Priya Mehta", "PM", "20 Oct", "02:00 PM", "60 min", "Completed")
    ))
    val sessions: LiveData<MutableList<Session>> = _sessions

    fun addSession(session: Session) {
        val currentList = _sessions.value ?: mutableListOf()
        currentList.add(0, session)
        _sessions.value = currentList
    }
}