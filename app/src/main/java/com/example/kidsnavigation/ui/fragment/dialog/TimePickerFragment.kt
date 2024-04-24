package com.example.kidsnavigation.ui.fragment.dialog

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment

/*
Für die moderne Variante einen Dialog zu verwenden.
Beim Arbeiten aus Fragmenten heraus ist die Implementierung jedoch sehr aufwendig, für unseren Anwendungsfall
wurde nach dem herkömmlichen Prinzip vorgegangen
 */
class TimePickerFragment : DialogFragment() {

    private var listener :TimePickerDialog.OnTimeSetListener? = null
    lateinit var timePickerDialog: TimePickerDialog

    companion object{
        val TAG=TimePickerFragment::class.java.simpleName
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach: ${context.javaClass.simpleName}")
        if(context is TimePickerDialog.OnTimeSetListener)
            listener = context
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val hour = calendar[Calendar.HOUR_OF_DAY]
        val minute = calendar[Calendar.MINUTE]

        timePickerDialog = TimePickerDialog(requireActivity(),listener,hour,minute,true)
        timePickerDialog.setMessage("Zeit einstellen")

        return timePickerDialog

    }
}