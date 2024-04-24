package com.example.kidsnavigation.ui.fragment.medical

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import androidx.fragment.app.Fragment
import com.example.kidsnavigation.databinding.FragmentMedikationBinding
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class MedikationFragment : Fragment(), TimePickerDialog.OnTimeSetListener,
    DatePickerDialog.OnDateSetListener {

    private lateinit var binding: FragmentMedikationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentMedikationBinding.inflate(inflater, container, false)


        binding.etTime.setOnClickListener {
//            TimePickerFragment().show(requireActivity().supportFragmentManager,TimePickerFragment.TAG)
            val calendar = Calendar.getInstance()
            val hour = calendar[Calendar.HOUR_OF_DAY]
            val minute = calendar[Calendar.MINUTE]

            val timePickerDialog = TimePickerDialog(this.context, this, hour, minute, true)
            timePickerDialog.setMessage("Zeit festlegen")
            timePickerDialog.create()
            timePickerDialog.show()
        }

        binding.etDateStart.setOnClickListener {
            onDateClick(it)
        }

        binding.etDateEnd.setOnClickListener {
            onDateClick(it)
        }


        // Inflate the layout for this fragment
        return binding.root
    }

    private fun onDateClick(it: View?) {
        val calendar= Calendar.getInstance()
        val year=calendar[Calendar.YEAR]
        val month=calendar[Calendar.MONTH]
        val day=calendar[Calendar.DAY_OF_MONTH]
        val datePickerDialog = DatePickerDialog(this.requireContext(), this, year, month, day)
        datePickerDialog.setMessage("Datum festlegen")
        datePickerDialog.datePicker.tag= it
        datePickerDialog.create()
        datePickerDialog.show()
    }

    override fun onTimeSet(view: TimePicker?, hour: Int, minute: Int) {
        binding.etTime.setText("$hour:$minute")
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {
       val tv = view?.tag as TextView

        // mit Date Formatierung auch für SDK < 26
        val date =
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                LocalDate.of(year, month + 1, day).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
            }else{
                val tmpdate = SimpleDateFormat("yyyy-MM-dd").parse("$year-${month-1}-$day")
                SimpleDateFormat("dd.MM.yyyy", Locale.GERMANY).format(tmpdate)
            }



        tv.text= date //"$day.${month+1}.$year"
    }


}