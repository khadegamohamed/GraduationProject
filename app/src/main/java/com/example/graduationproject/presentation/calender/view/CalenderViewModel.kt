package com.example.graduationproject.presentation.calender.view

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graduationproject.application.CalenderRepository
import com.example.graduationproject.domain.networking.RetrofitBuilder
import com.example.graduationproject.presentation.calender.model.DateEventRequest
import com.example.graduationproject.presentation.calender.model.EventPojo
import com.example.graduationproject.presentation.calender.model.EventRequest
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Response
import java.util.*

class CalenderViewModel(context: Context) :ViewModel(){

    var mutableEventsResponse: MutableLiveData<Response<List<EventPojo>>> = MutableLiveData()
    var mutableDateEventsResponse: MutableLiveData<Response<List<EventPojo>>> = MutableLiveData()
    var mutableAddEventsResponse: MutableLiveData<Response<EventRequest>> = MutableLiveData()
    var serviceInst = RetrofitBuilder.getAPIService(context)
    private var calenderRepo = CalenderRepository(serviceInst)

    fun getAllEvents() {
        viewModelScope.launch {
            calenderRepo.getAllEvents().catch {e->
                Log.d("CalenderViewModel", "getAllEvents: ${e.message}")
            }.collect {response ->
               mutableEventsResponse.value=response
            }

        }
    }


    fun addEvent(eventRequest: EventRequest) {
        viewModelScope.launch {
            var addEvent=calenderRepo.addEvent(eventRequest)
           mutableAddEventsResponse.value = addEvent
        }
    }

    fun getDateEvents(dateEventRequest: DateEventRequest) {
        viewModelScope.launch {
            calenderRepo.getDateEvents(dateEventRequest).catch {e->
                Log.d("CalenderViewModel", "getDateEvents: ${e.message}")
            }.collect {response ->
             mutableDateEventsResponse.value=response
            }

        }
    }





}