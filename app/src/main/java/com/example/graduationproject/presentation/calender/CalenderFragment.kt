package com.example.graduationproject.presentation.calender

import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.graduationproject.databinding.DialogEventBinding
import com.example.graduationproject.databinding.FragmentCalenderBinding
import com.github.sundeepk.compactcalendarview.CompactCalendarView
import com.github.sundeepk.compactcalendarview.CompactCalendarView.CompactCalendarViewListener
import com.github.sundeepk.compactcalendarview.domain.Event
import java.text.SimpleDateFormat
import java.util.*


class CalenderFragment : Fragment(), DialogeEvent.OnEventSelected {
    private lateinit var binding: FragmentCalenderBinding
    private lateinit var compactCalendarView: CompactCalendarView
    private lateinit var date: Date

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCalenderBinding.inflate(inflater, container, false)
        compactCalendarView = binding.compactcalendarView
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        compactCalendarView.setFirstDayOfWeek(Calendar.SATURDAY)
        var mydate: String = "2022/3/3 18:20:45"
        var simpleDateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
        date = simpleDateFormat.parse(mydate)
        var dateInMillis: Long = date.time
        val ev1 = Event(Color.BLACK, dateInMillis, "Some extra data that I want to store.")
        val ev2 = Event(Color.BLACK, dateInMillis, "Some extra data that I want to store.")
        val ev3 = Event(Color.BLACK, dateInMillis, "Some extra data that I want to store.")
        var timeZone = TimeZone.getTimeZone("UTC+02:00")
        compactCalendarView.setUseThreeLetterAbbreviation(true)
        //compactCalendarView.setIsRtl(true);
        compactCalendarView.setEventIndicatorStyle(3)
        compactCalendarView.addEvent(ev1)
        compactCalendarView.addEvent(ev2)
        compactCalendarView.addEvent(ev3)
        compactCalendarView.setIsRtl(true)
        val dateFormatForMonth = SimpleDateFormat("MMM - yyyy", Locale.getDefault())
        binding.monthYear.setText(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()))
        // uncomment below to show indicators above small indicator events
        compactCalendarView.shouldDrawIndicatorsBelowSelectedDays(true);
        compactCalendarView.shouldScrollMonth(true);
        compactCalendarView.displayOtherMonthDays(false)


        //set title on calendar scroll
        compactCalendarView.setListener(object : CompactCalendarViewListener {
            override fun onDayClick(dateClicked: Date) {
                Log.d(
                    "calender",
                    "onViewCreated:" + compactCalendarView.getEvents(dateClicked.time)
                )
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

    override fun sendEvent(startEvent: String, endEvent: String) {
        Log.d(TAG, "sendEvent: " + startEvent + endEvent)
    }


}