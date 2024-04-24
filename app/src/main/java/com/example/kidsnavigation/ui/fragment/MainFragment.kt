package com.example.kidsnavigation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.kidsnavigation.R
import com.example.kidsnavigation.databinding.FragmentMainBinding



class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMainBinding.inflate(inflater,container,false)
        navController = findNavController()

        binding.btnToMedicalFragment.setOnClickListener{
            navController.navigate(R.id.action_mainFragment_to_medicalFragment)
        }
        binding.btnCalendar.setOnClickListener{
            navController.navigate(R.id.action_mainFragment_to_kalenderFragment)
        }

        // Inflate the layout for this fragment
        return binding.root
    }


}