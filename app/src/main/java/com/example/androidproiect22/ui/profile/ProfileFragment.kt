package com.example.androidproiect22.ui.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.androidproiect22.R
import com.example.androidproiect22.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val profileViewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        val root: View = binding.root

        val navController = findNavController()

        profileViewModel.profileRequestResult.observe(viewLifecycleOwner) {
            if (it == ProfileResult.LOADING) {
                binding.progressBarCyclic.visibility = View.VISIBLE
                return@observe
            }
            else if (it == ProfileResult.SUCCESS) {
                binding.progressBarCyclic.visibility = View.INVISIBLE
                binding.emailAddress = profileViewModel.userData.email
                binding.location = profileViewModel.userData.location
                binding.fullName = profileViewModel.userData.firstName + " " + profileViewModel.userData.lastName
                binding.phoneNumber = profileViewModel.userData.phoneNumber
                if (profileViewModel.userData.imageUrl != null) {
                    Glide.with(this)
                        .load(profileViewModel.userData.imageUrl)
                        .apply(RequestOptions().override(600, 200))
                        .circleCrop()
                        .into(binding.profileImage)
                }

            }
            else if (it == ProfileResult.UNKNOWN_ERROR || it == ProfileResult.INVALID_TOKEN) {
                binding.progressBarCyclic.visibility = View.INVISIBLE
                Toast.makeText(activity, it.toString(), Toast.LENGTH_LONG).show()
            }

        }

        binding.editProfileButton.setOnClickListener {
            navController.navigate(R.id.navigation_edit_profile)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        val profileViewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        profileViewModel.getProfile()
    }

}
