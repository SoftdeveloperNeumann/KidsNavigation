package com.example.kidsnavigation.ui.fragment.learning

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.example.kidsnavigation.R

/**
 * A simple [Fragment] subclass.
 * Use the [EvaluationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EvaluationFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_evaluation, container, false)
    }


}