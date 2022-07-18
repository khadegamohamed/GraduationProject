package com.example.graduationproject.presentation.calender.view

import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.graduationproject.databinding.FragmentCalenderBinding
import com.example.graduationproject.presentation.calender.model.EventRequest
import com.github.sundeepk.compactcalendarview.CompactCalendarView
import com.github.sundeepk.compactcalendarview.CompactCalendarView.CompactCalendarViewListener
import com.github.sundeepk.compactcalendarview.domain.Event
import java.text.SimpleDateFormat
import java.util.*
import android.util.StatsLog.logEvent
import androidx.navigation.fragment.findNavController
import java.text.DateFormat


class CalenderFragment : Fragment(), DialogeEvent.OnEventSelected {
    private lateinit var binding: FragmentCalenderBinding
    private lateinit var compactCalendarView: CompactCalendarView
    private lateinit var date: Date
    private lateinit var  calenderViewModel: CalenderViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCalenderBinding.inflate(inflater, container, false)
        compactCalendarView = binding.compactcalendarView
        calenderViewModel = CalenderViewModel(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //attribute To Calender
        compactCalendarView.setFirstDayOfWeek(Calendar.SATURDAY)
        compactCalendarView.setUseThreeLetterAbbreviation(true)
        //compactCalendarView.setIsRtl(true);
        compactCalendarView.setEventIndicatorStyle(3)
        compactCalendarView.setIsRtl(true)
        val dateFormatForMonth = SimpleDateFormat("MMM - yyyy", Locale.getDefault())
        binding.monthYear.setText(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()))
        // uncomment below to show indicators above small indicator events
        compactCalendarView.shouldDrawIndicatorsBelowSelectedDays(true);
        compactCalendarView.shouldScrollMonth(true);
        compactCalendarView.displayOtherMonthDays(false)
        var timeZone = TimeZone.getTimeZone("UTC+02:00")
        //addEvents
       calenderViewModel.getAllEvents()
        calenderViewModel.mutableEventsResponse.observe(viewLifecycleOwner,{
            if (it.isSuccessful){
                for (i in it.body()!!){
                    createEvent("${i.fromDate} ${i.fromTime}",i.event_name)
                    createEvent("${i.toDate} ${i.toTime}",i.event_name)
                }
            }else{
                Log.d("Errorincalender", it.message().toString())
            }
        }
        )
        //set title on calendar scroll
        compactCalendarView.setListener(object : CompactCalendarViewListener {
            override fun onDayClick(dateClicked: Date) {
               /* Log.d(
                    "calender",
                    "onViewCreated:" + compactCalendarView.getEvents(dateClicked.time)
                )*/
                val formatter: DateFormat = SimpleDateFormat("yyyy/MM/dd")
                val calendar = Calendar.getInstance()
                calendar.timeInMillis = dateClicked.time
                var newdate:String =formatter.format(calendar.getTime());

     findNavController().navigate(CalenderFragmentDirections.actionCalenderFragmentToShowEvents(newdate))
            }

            override fun onMonthScroll(firstDayOfNewMonth: Date) {
                binding.monthYear.setText(dateFormatForMonth.format(firstDayOfNewMonth))
            }
        })
        binding.fab.setOnClickListener {

            var dialogeEvent: DialogeEvent = DialogeEvent()
            dialogeEvent.show(parentFragmentManager, "DialogEvent")
            Log.d(TAG, "onViewCreated: " + "hello")
        }

    }
//addEvent
    override fun sendEvent(nameEvent:String,startEvent: String, endEvent: String,context: Context) {
        Log.d(TAG, "sendEvent: " + startEvent + endEvent)
    }

fun createEvent(time:String,name:String){
    var simpleDateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
    date = simpleDateFormat.parse(time)
    var dateInMillis: Long = date.time
    val theEvent= Event(Color.BLACK, dateInMillis, name)
    compactCalendarView.addEvent(theEvent)
}
}
/*  var mydate: String = "2022/3/3 18:20:45"
  var simpleDateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
  date = simpleDateFormat.parse(mydate)
  var dateInMillis: Long = date.time
  val ev1 = Event(Color.BLACK, dateInMillis, "Some extra data that I want to store.")
  val ev2 = Event(Color.BLACK, dateInMillis, "Some extra data that I want to store.")
  val ev3 = Event(Color.BLACK, dateInMillis, "Some extra data that I want to store.")

  compactCalendarView.addEvent(ev1)
  compactCalendarView.addEvent(ev2)
  compactCalendarView.addEvent(ev3)*/
