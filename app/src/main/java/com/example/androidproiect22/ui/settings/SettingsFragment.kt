package com.example.androidproiect22.ui.settings

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.androidproiect22.R
import com.example.androidproiect22.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val settingViewModel = ViewModelProvider(this)[SettingsViewModel::class.java]
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)

        val root: View = binding.root

        val navController = findNavController()


        binding.viewProfileButton.setOnClickListener {
            navController.navigate(R.id.navigation_profile)
        }

        return root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

