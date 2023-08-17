package com.example.profnotes.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.profnotes.R
import com.example.profnotes.databinding.FragmentEnterBinding
import com.example.profnotes.presentation.viewmodel.EnterViewModel

class EnterFragment : Fragment() {

    private var _binding: FragmentEnterBinding? = null
    private val binding: FragmentEnterBinding
        get() = _binding ?: throw java.lang.RuntimeException("FragmentEnterBinding == null")

    private val viewModel: EnterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEnterBinding.inflate(inflater, container, false)

        binding.btnRegistration.setOnClickListener {
            findNavController().navigate(R.id.action_enterFragment_to_registrationFragment)
        }
        binding.btnEnter.setOnClickListener {
            performAuthentication()
        }

        getResultEnter()

        return binding.root
    }

    private fun performAuthentication() {
        val phone = binding.phoneEnter.text.toString()
        val password = binding.passwordEnter.text.toString()

        if (validation(phone, password)) return

        viewModel.auth(phone, password)
    }

    private fun validation(phone: String, password: String): Boolean {
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

    private fun getResultEnter() {
        viewModel.enterResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is EnterViewModel.EnterResult.Success -> {
                    navigateToHomeScreen()
                }
                is EnterViewModel.EnterResult.Error -> {
                    val message = result.message
                    showError(message)
                }
            }
        }
    }

    private fun navigateToHomeScreen() {
        findNavController().navigate(R.id.action_enterFragment_to_homeFragment)
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(),message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}