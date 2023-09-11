package com.example.profnotes.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.profnotes.databinding.FragmentAddNoteSheetBinding

class AddNoteSheetFragment : Fragment() {

    private var _binding: FragmentAddNoteSheetBinding? = null
    private val binding: FragmentAddNoteSheetBinding
        get() = _binding ?: throw java.lang.RuntimeException("FragmentAddNoteSheetBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

}