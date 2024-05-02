package com.example.kidsnavigation.ui.fragment.social

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.kidsnavigation.R
import com.example.kidsnavigation.databinding.FragmentSocialBinding


class SocialFragment : Fragment() {

    private lateinit var binding: FragmentSocialBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSocialBinding.inflate(inflater,container,false)
        navController = findNavController()

        binding.btnToChat.setOnClickListener {
            navController.navigate(R.id.action_socialFragment_to_chatFragment)
        }

        // Inflate the layout for this fragment
        return binding.root
    }


}