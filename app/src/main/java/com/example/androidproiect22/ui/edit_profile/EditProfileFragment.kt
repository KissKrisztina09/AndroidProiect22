package com.example.androidproiect22.ui.edit_profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.androidproiect22.R
import com.example.androidproiect22.databinding.FragmentEditProfileBinding

class EditProfileFragment : Fragment() {

    private var _binding: FragmentEditProfileBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel = ViewModelProvider(this)[EditProfileViewModel::class.java]

        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)

        val root: View = binding.root

        val navController = findNavController()


        binding.updateButton.setOnClickListener {

            viewModel.updateProfile(
                binding.LastNameText.text.toString(),
                binding.firstNameText.text.toString(),
                binding.locationText.text.toString(),
                binding.phoneNumberText.text.toString(),
                binding.imageUrlText.text.toString()
            )
        }

        viewModel.editProfileResult.observe(viewLifecycleOwner) {
            if (it == EditProfileResult.LOADING) {
                binding.progressBarEditProfile.visibility = View.VISIBLE
                return@observe
            }
            else if (it == EditProfileResult.SUCCESS) {
                binding.progressBarEditProfile.visibility = View.INVISIBLE

                binding.LastNameText.setText(viewModel.userData.lastName)
                binding.firstNameText.setText(viewModel.userData.firstName)
                binding.locationText.setText(viewModel.userData.location)
                binding.phoneNumberText.setText(viewModel.userData.phoneNumber)

            }
            else if (it == EditProfileResult.EDIT_SUCCESS) {
                binding.progressBarEditProfile.visibility = View.INVISIBLE
                Toast.makeText(activity, "Profile updated!", Toast.LENGTH_LONG).show()
                navController.popBackStack()
            }
            else {
                binding.progressBarEditProfile.visibility = View.INVISIBLE
                Toast.makeText(activity, it.toString(), Toast.LENGTH_LONG).show()

            }
        }

        return root
    }


}