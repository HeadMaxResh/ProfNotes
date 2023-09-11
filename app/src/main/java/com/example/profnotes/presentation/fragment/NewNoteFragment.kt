package com.example.profnotes.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.profnotes.R
import com.example.profnotes.databinding.FragmentNewNoteBinding
import com.example.profnotes.databinding.FragmentSecondBinding

class NewNoteFragment : Fragment() {

    private var _binding: FragmentNewNoteBinding? = null
    private val binding: FragmentNewNoteBinding
        get() = _binding ?: throw java.lang.RuntimeException("FragmentNewNoteBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }
}