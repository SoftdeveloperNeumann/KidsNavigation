package com.example.kidsnavigation.ui.fragment.medical

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kidsnavigation.database.entity.Einnahmezeit
import com.example.kidsnavigation.database.entity.Medikament
import com.example.kidsnavigation.database.entity.MedikamentMitEinnahmeZeit
import com.example.kidsnavigation.databinding.DialogEditMedicationBinding
import com.example.kidsnavigation.databinding.FragmentMedikationBinding
import com.example.kidsnavigation.model.KidsNavigationViewModel
import com.example.kidsnavigation.util.adapter.MediListAdapter
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class MedikationFragment : Fragment(), TimePickerDialog.OnTimeSetListener,
    DatePickerDialog.OnDateSetListener {

    private lateinit var binding: FragmentMedikationBinding
    private lateinit var model: KidsNavigationViewModel
    private val mediAdapter = MediListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMedikationBinding.inflate(inflater, container, false)


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


        var liste = arrayListOf<String>()
        model = KidsNavigationViewModel(requireActivity().application)

        val adapter = ArrayAdapter(requireActivity(), android.R.layout.simple_list_item_1, liste)
        binding.etName.setAdapter(adapter)

        model.getAllMedics()?.observe(requireActivity()) {
            adapter.clear()
            it.forEach { value ->
                adapter.add(value.name)
            }

        }

        binding.rvMediList.layoutManager = LinearLayoutManager(requireActivity())
        binding.rvMediList.adapter = mediAdapter

        model.getAllMedikamenteMitEinnahmeZeit()?.observe(requireActivity()) {
            mediAdapter.setMediList(it)
        }


        binding.etDateStart.setOnClickListener {
            onDateClick(it)
        }

        binding.etDateEnd.setOnClickListener {
            onDateClick(it)
        }

        binding.btnInsertMedication.setOnClickListener {
            val medikamentName = binding.etName.text.toString()
            if (binding.etName.text.isNotBlank() && !liste.contains(medikamentName)) {
                model.insert(Medikament(medikamentName))
                binding.etName.text.clear()
                binding.etName.requestFocus()
            }

        }

        binding.btnInsertDose.setOnClickListener {
            MainScope().launch {
                val medikament = model.getMedikament(binding.etName.text.toString())
                if (medikament == null) {
                    binding.etName.text.clear()
                    binding.etName.error = "Bitte ein Medikament eintragen"
                    binding.etName.requestFocus()
                    return@launch
                }

                if (binding.etAmount.text.isBlank()) {
                    binding.etAmount.error = "Bitte Dosierung eintragen"
                    binding.etAmount.requestFocus()
                    return@launch
                }

                if (binding.etTime.text.isBlank()) {
                    binding.etTime.performClick()
                    return@launch
                }

                val einnahmezeit = Einnahmezeit(
                    dosis = binding.etAmount.text.toString().toDouble(),
                    zeit = binding.etTime.text.toString(),
                    startDatum = if (binding.etDateStart.text.isNullOrBlank()) null else binding.etDateStart.text.toString(),
                    endDatum = if (binding.etDateEnd.text.isNullOrBlank()) null else binding.etDateEnd.text.toString(),
                    medikamentId = medikament.id
                )
                model.insert(einnahmezeit)
            }

        }

        mediAdapter.setOnItemClickListener(object : MediListAdapter.OnItemClickListener {
            override fun onItemClick(medikament: MedikamentMitEinnahmeZeit) {
                val dialogBinding = DialogEditMedicationBinding.inflate(inflater,container,false)
                val dialogView = dialogBinding.root
                val builder = AlertDialog.Builder(requireActivity())
                with(dialogBinding){
                    etTime.text = medikament.zeit
                    etName.setText(medikament.medikament.name)
                    etAmount.setText(medikament.dosis.toString())
                    if(medikament.startDatum != null){
                        etDateStart.text = medikament.startDatum
                    }
                     if(medikament.endDatum != null){
                        etDateEnd.text = medikament.endDatum
                    }
                }
                if (dialogView.parent != null){
                    (dialogView.parent as ViewGroup).removeView(dialogView)
                }
                builder.setView(dialogView)
                    .setTitle("Medikation ändern")
                    .setPositiveButton("Ändern"){dialog,_ ->}
                    .setNegativeButton("Abbrechen"){dialog,_ ->
                        dialog.cancel()
                    }
                    .setNeutralButton("Löschen"){dialog,_ ->}
                    .create()
                    .show()
            }
        })

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun onDateClick(it: View?) {
        val calendar = Calendar.getInstance()
        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH]
        val day = calendar[Calendar.DAY_OF_MONTH]
        val datePickerDialog = DatePickerDialog(this.requireContext(), this, year, month, day)
        datePickerDialog.setMessage("Datum festlegen")
        datePickerDialog.datePicker.tag = it
        datePickerDialog.create()
        datePickerDialog.show()
    }

    override fun onTimeSet(view: TimePicker?, hour: Int, minute: Int) {

        val time = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalTime.of(hour, minute).format(DateTimeFormatter.ofPattern("hh:mm"))

        } else {
            val tmptime = SimpleDateFormat("hh:mm").parse("$hour:$minute")
            SimpleDateFormat("hh:mm",Locale.GERMANY).format(tmptime)
        }

        binding.etTime.text = time
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {
        val tv = view?.tag as TextView

        // mit Date Formatierung auch für SDK < 26
        val date =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                LocalDate.of(year, month + 1, day).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
            } else {
                val tmpdate = SimpleDateFormat("yyyy-MM-dd").parse("$year-${month - 1}-$day")
                SimpleDateFormat("dd.MM.yyyy", Locale.GERMANY).format(tmpdate)
            }

        tv.text = date //"$day.${month+1}.$year"
    }


}