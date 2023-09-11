package com.example.profnotes.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.profnotes.databinding.FragmentSecondBinding
import com.example.profnotes.presentation.adapter.TagAdapter

class SecondFragment : Fragment() {

    private lateinit var tvTextArgument: TextView
    /*private val args by navArgs<SecondFragmentArgs>()*/

    var recyclerView: RecyclerView? = null

    private var _binding: FragmentSecondBinding? = null
    private val binding: FragmentSecondBinding
        get() = _binding ?: throw java.lang.RuntimeException("FragmentSecondBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(layoutInflater, container, false)
        tvTextArgument = binding.tvTextArgument
        recyclerView = binding.rv
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTextView()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        recyclerView?.layoutManager = LinearLayoutManager(context)
        val items = listOf("item", "item2", "item3")
        recyclerView?.adapter = TagAdapter(items)
    }

    private fun setupTextView() {
        /*val textFromArgument = args.text
        tvTextArgument.text = textFromArgument*/
    }
}