package com.example.graduationproject.presentation.calender.view
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.graduationproject.databinding.DialogEventBinding
import com.example.graduationproject.presentation.calender.model.EventRequest
import java.lang.ClassCastException
import java.text.SimpleDateFormat
import java.util.*

class DialogeEvent: DialogFragment(){

    private lateinit var builder: AlertDialog.Builder
    private lateinit var bindingDialog: DialogEventBinding
    private lateinit var dialog: AlertDialog
    private lateinit var timePicker: TimePickerDialog
   private lateinit var onEventSelected: OnEventSelected
    private lateinit var  date:Date
    private val datePicker: DatePickerDialog? = null
    private var startEvent: Long = 0L
    private var endEvent: Long = 0L
    private var startDataFormat:String? = null
    private var endDataFormat:String? = null
    private var startTimeFormat:String? = null
    private var endTimeFormat:String? = null
    private lateinit var cancelEvent:Button
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingDialog = DialogEventBinding.inflate(LayoutInflater.from(context), container, false)

        return bindingDialog.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
     /*   dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCancelable(false)*/
        cancelEvent = bindingDialog.cancelEvent
        cancelEvent.setOnClickListener {
         getDialog()?.dismiss()
        }
        bindingDialog.tvStartDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(
            requireContext(),
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    var realMonth = monthOfYear+1
                    if(realMonth<10){
                        bindingDialog.tvStartDate.text = "$dayOfMonth, 0$monthOfYear, $year"
                        startDataFormat = "$year/0$monthOfYear/$dayOfMonth"
                    }else{
                    bindingDialog.tvStartDate.text = "$dayOfMonth, $monthOfYear, $year"
                    startDataFormat = "$year/$monthOfYear/$dayOfMonth"
                }},
                year,
                month,
                day
            )

            datePickerDialog.show()
        }
        bindingDialog.tvEndDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(
                requireContext(),
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    var realMonth = monthOfYear+1
                    if(realMonth<10){
                        bindingDialog.tvEndDate.text = "$dayOfMonth, 0$monthOfYear, $year"
                        endDataFormat = "$year/0$monthOfYear/$dayOfMonth"
                    }else{
                        bindingDialog.tvEndDate.text = "$dayOfMonth, $monthOfYear, $year"
                        endDataFormat = "$year/$monthOfYear/$dayOfMonth"
                    }
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }
        bindingDialog.tvStartTime.setOnClickListener {
            val (hour, minute) = showTimePicker()
            timePicker = TimePickerDialog(
                requireContext(),
                { view, hourOfDay, minute ->
                    var realHour =""
                    var realMinute=""
                    if (hourOfDay<10){
                        realHour = "0$hourOfDay"
                    }else{
                        realHour="$hourOfDay"
                    }
                    if (minute<10){
                        realMinute= "0$minute"
                    }else{
                        realMinute="$minute"
                    }
                    bindingDialog.tvStartTime.text = String.format("%d : %d", realHour, realMinute)
                    startTimeFormat = "$startDataFormat $realHour:$realMinute:00"
                    var simpleDateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
                    date = simpleDateFormat.parse(startTimeFormat)
                    var startEvent:Long = date.time
                }, hour, minute, false)
            timePicker.show()

        }
        bindingDialog.tvEndTime.setOnClickListener {
            val (hour, minute) = showTimePicker()
            timePicker = TimePickerDialog(
                requireContext(),
                { view, hourOfDay, minute ->
                    var realHour =""
                    var realMinute=""
                    if (hourOfDay<10){
                        realHour = "0$hourOfDay"
                    }else{
                        realHour="$hourOfDay"
                    }
                    if (minute<10){
                        realMinute= "0$minute"
                    }else{
                        realMinute="$minute"
                    }
                    bindingDialog.tvEndTime.text = String.format("%d : %d", realHour, realMinute)
                    endTimeFormat ="$endDataFormat $realHour:$realMinute:00"
                    var simpleDateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
                    date = simpleDateFormat.parse(endTimeFormat)
                    var endEvent:Long = date.time
                }, hour, minute, false)

            timePicker.show()

        }

        bindingDialog.addEvent.setOnClickListener {
            var nameEvent= bindingDialog.tvDescription.text.toString()
            onEventSelected.sendEvent(nameEvent,startTimeFormat!!,endTimeFormat!!,requireContext())
            var eventRequest = EventRequest(nameEvent,startTimeFormat!!,endTimeFormat!!)
            var calender1ViewModel = CalenderViewModel(requireContext())
            Log.i("DIALOGE", "onViewCreated:RRRRRRR ")
            calender1ViewModel.addEvent(eventRequest)
            Log.i("DIALOGE", "onViewCreated: oooooooooo")
            calender1ViewModel.mutableAddEventsResponse.observe(viewLifecycleOwner,{
                if (it.isSuccessful){
                    Log.d("DIALOGE", "onViewCreated: " + it.body())
                    Toast.makeText(requireContext(),"you add Event", Toast.LENGTH_SHORT).show()
                }else{
                    Log.d("DIALOGE", "onViewCreated: " + it.body())
                    Toast.makeText(requireContext(),"you cannot add Event", Toast.LENGTH_SHORT).show()
                }
            })
            getDialog()?.dismiss()
        }


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            onEventSelected = CalenderFragment()
        }catch (e: ClassCastException){
            Log.d(TAG, "onAttach:ClassCastException "+e.message)
        }
    }
    private fun showTimePicker(): Pair<Int, Int> {
        val mcurrentTime = Calendar.getInstance()
        val hour = mcurrentTime.get(Calendar.HOUR_OF_DAY)
        val minute = mcurrentTime.get(Calendar.MINUTE)
        return Pair(hour, minute)
    }
    public interface OnEventSelected{
        fun  sendEvent( nameEvent:String ,startEvent: String ,endEvent: String,context: Context)
    }


}