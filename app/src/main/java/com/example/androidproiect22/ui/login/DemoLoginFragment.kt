package com.example.androidproiect22.ui.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.androidproiect22.R
import com.example.androidproiect22.databinding.FragmentDemoLoginBinding

class DemoLoginFragment : Fragment() {

    private var _binding: FragmentDemoLoginBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val demoLoginViewModel =
            ViewModelProvider(this)[DemoLoginViewModel::class.java]
        _binding = FragmentDemoLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val navController = findNavController()

        binding.projectDemoLoginButton.setOnClickListener {
            demoLoginViewModel.login(
                binding.projectDemoEmail.text.toString(),
                binding.projectDemoPassword.text.toString()
            )
            demoLoginViewModel.loginResult.observe(viewLifecycleOwner) {
                if (it == LoginResult.LOADING) {
                    return@observe
                }
                Toast.makeText(activity, it.toString(), Toast.LENGTH_LONG).show()
                if (it == LoginResult.SUCCESS) {
                    navController.navigate(R.id.navigation_settings)
                }
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}