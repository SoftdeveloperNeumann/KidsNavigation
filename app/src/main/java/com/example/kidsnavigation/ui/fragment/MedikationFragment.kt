package com.example.kidsnavigation.ui.fragment

import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import androidx.fragment.app.Fragment
import com.example.kidsnavigation.databinding.FragmentMedikationBinding


class MedikationFragment : Fragment(),TimePickerDialog.OnTimeSetListener{

    private lateinit var binding: FragmentMedikationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentMedikationBinding.inflate(inflater,container,false)


        binding.etTime.setOnClickListener {
//            TimePickerFragment().show(requireActivity().supportFragmentManager,TimePickerFragment.TAG)
            val calendar = Calendar.getInstance()
            val hour = calendar[Calendar.HOUR_OF_DAY]
            val minute = calendar[Calendar.MINUTE]

            val timePickerDialog = TimePickerDialog(this.context,this,hour,minute,true)
            timePickerDialog.setMessage("Zeit einstellen")
            timePickerDialog.create()
            timePickerDialog.show()
        }


        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onTimeSet(view: TimePicker?, hour: Int, minute: Int) {
        binding.etTime.setText("$hour:$minute")
    }


}