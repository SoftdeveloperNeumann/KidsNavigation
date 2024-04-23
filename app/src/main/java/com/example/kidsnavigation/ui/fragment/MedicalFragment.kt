package com.example.kidsnavigation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.kidsnavigation.R
import com.example.kidsnavigation.databinding.FragmentMedicalBinding


class MedicalFragment : Fragment() {

    private lateinit var binding: FragmentMedicalBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMedicalBinding.inflate(inflater,container,false)
        navController= findNavController()
        binding.button.setOnClickListener {
            navController.navigate(R.id.action_medicalFragment_to_medikationFragment)
        }

        // Inflate the layout for this fragment
        return binding.root
    }

}