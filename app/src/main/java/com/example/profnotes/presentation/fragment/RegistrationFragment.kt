package com.example.profnotes.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.profnotes.R
import com.example.profnotes.data.api.Token
import com.example.profnotes.databinding.FragmentRegistrationBinding
import com.example.profnotes.presentation.viewmodel.RegistrationViewModel

class RegistrationFragment : Fragment() {

    private var _binding: FragmentRegistrationBinding? = null
    private val binding: FragmentRegistrationBinding
        get() = _binding ?: throw java.lang.RuntimeException("FragmentRegistrationBinding == null")

    private val viewModel: RegistrationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)

        binding.btnEnter.setOnClickListener {
            navigateToEnterScreen()
        }
        binding.btnRegistration.setOnClickListener {
            performRegistration()
        }

        getResultRegistration()

        return binding.root
    }

    private fun getResultRegistration() {
        viewModel.registerResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is RegistrationViewModel.RegisterResult.Success -> {
                    navigateToEnterScreen()
                }
                is RegistrationViewModel.RegisterResult.Error -> {
                    val message = result.message
                    showError(message)
                }
            }
        }
    }

    private fun performRegistration() {
        val name = binding.nameRegistration.text.toString()
        val lastName = binding.lastnameRegistration.text.toString()
        val phone = binding.phoneRegistration.text.toString()
        val password = binding.passwordRegistration.text.toString()

        if (validation(name, lastName, phone, password)) return

        viewModel.register(name, lastName, phone, password)
    }

    private fun validation(name: String, lastName: String, phone: String, password: String): Boolean {
        if (name.isBlank()) {
            Toast.makeText(context, "Введите имя", Toast.LENGTH_SHORT).show()
            return true
        }

        if (lastName.isBlank()) {
            Toast.makeText(context, "Введите фамилию", Toast.LENGTH_SHORT).show()
            return true
        }

        if (phone.isBlank()) {
            Toast.makeText(context, "Введите номер телефона", Toast.LENGTH_SHORT).show()
            return true
        }

        if (password.isBlank()) {
            Toast.makeText(context, "Введите пароль", Toast.LENGTH_SHORT).show()
            return true
        }
        return false
    }

    private fun navigateToEnterScreen() {
        findNavController().navigate(R.id.action_registrationFragment_to_enterFragment)
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(),message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}