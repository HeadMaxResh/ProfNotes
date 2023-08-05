package com.example.profnotes.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.profnotes.databinding.FragmentHomeBinding
import com.example.profnotes.presentation.viewpager.ViewPagerAdapter
import me.relex.circleindicator.CircleIndicator3


class HomeFragment : Fragment() {

    private lateinit var viewPager2: ViewPager2
    private lateinit var transformer: CompositePageTransformer
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var indicator: CircleIndicator3
    private lateinit var indicatorScrollView: HorizontalScrollView

    private lateinit var rootLayout: ConstraintLayout
    private lateinit var searchButton: ImageView
    private lateinit var searchLinearLayout: LinearLayout
    private lateinit var lessonConstraintLayout: ConstraintLayout

    private var titleCard = mutableListOf<String>()
    private var numberCard = mutableListOf<String>()
    private var tagName = mutableListOf<MutableList<String>>()
    private var isSearchOpen = false

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding ?: throw java.lang.RuntimeException("FragmentHomeBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        viewPager2 = binding.viewPager2
        indicator = binding.indicator
        viewPagerAdapter = ViewPagerAdapter(titleCard, numberCard, tagName)
        indicatorScrollView = binding.indicatorScrollView

        rootLayout = binding.rootLayout
        searchButton = binding.searchButton
        searchLinearLayout = binding.llSearch
        lessonConstraintLayout = binding.clLesson

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*button.setOnClickListener {
            findNavController().navigate(
                FirstFragmentDirections.actionFirstFragmentToSecondFragment(
                    etTextForNavigation.text.toString()
                )
            )
        }*/
        postToViewPager()
        setViewPagerSettings()
    }

    private fun addToViewPager(title: String, number: String) {
        titleCard.add(title)
        numberCard.add(number)
    }

    private fun addToTagRecycler(names: MutableList<String>) {
        tagName.add(names)
    }

    private fun postToViewPager() {
        for (i in 1..9) {
            addToViewPager("Title $i", "$i")
            addToTagRecycler(
                mutableListOf(
                    "Title $i",
                    "Title ${i + 1}",
                    "Title ${i + 1}",
                    "Title ${i + 1}",
                    "Title ${i + 1}"
                )
            )
        }
    }

    private fun setViewPagerSettings() {
        viewPager2.adapter = viewPagerAdapter
        viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.offscreenPageLimit = 3
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(8))
        transformer.addTransformer { page, position ->
            val r = 1 - kotlin.math.abs(position)
            page.scaleY = 0.95f + r * 0.07f
        }
        viewPager2.setPageTransformer(transformer)

        indicator.setViewPager(viewPager2)

        viewPager2.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val scrollTo = indicator.getChildAt(position).left
                indicatorScrollView.smoothScrollTo(scrollTo, 0)
            }
        })
    }


}