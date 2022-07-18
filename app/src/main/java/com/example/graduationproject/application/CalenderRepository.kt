package com.example.graduationproject.application

import android.util.Log
import com.example.graduationproject.domain.networking.ServiceAPI
import com.example.graduationproject.presentation.calender.model.DateEventRequest
import com.example.graduationproject.presentation.calender.model.EventPojo
import com.example.graduationproject.presentation.calender.model.EventRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import retrofit2.Response

class CalenderRepository (private val api: ServiceAPI){

    suspend fun getAllEvents(): Flow<Response<List<EventPojo>>> = flow {
        var responseOfAllEvents:Response<List<EventPojo>> = api.getAllEvents()
        emit(responseOfAllEvents)
         }.flowOn(Dispatchers.IO)


    suspend fun addEvent(eventRequest: EventRequest):Response<EventRequest> =
        withContext(Dispatchers.IO) {
            var respones =  api.addEvent(eventRequest)
            Log.i("DIALOGE", "addEvent:${respones.code()} " )
            return@withContext respones
        }

    suspend fun getDateEvents(dateEventRequest: DateEventRequest): Flow<Response<List<EventPojo>>> = flow {
        var responseOfDateEvents:Response<List<EventPojo>> = api.getDateEvent(dateEventRequest)
        emit(responseOfDateEvents)
        Log.i("SHOWEVENTS", "${responseOfDateEvents.code()}")
    }.flowOn(Dispatchers.IO)



}