package com.example.profnotes.presentation.fragment

import android.os.Bundle
import android.view.*
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.profnotes.R
import com.example.profnotes.databinding.FragmentHomeBinding
import com.example.profnotes.domain.model.Course
import com.example.profnotes.presentation.viewmodel.HomeViewModel
import com.example.profnotes.presentation.adapter.ViewPagerAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import me.relex.circleindicator.CircleIndicator3

class HomeFragment : Fragment() {

    private val viewModel by viewModels<HomeViewModel>()

    private lateinit var viewPager2: ViewPager2
    private lateinit var transformer: CompositePageTransformer
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var indicator: CircleIndicator3
    private lateinit var indicatorScrollView: HorizontalScrollView

    private lateinit var rootLayout: ConstraintLayout
    private lateinit var searchButton: ImageView
    private lateinit var searchLinearLayout: LinearLayout
    private lateinit var lessonConstraintLayout: ConstraintLayout

    lateinit var homeFragment: HomeFragment
    private lateinit var addNoteFragment: AddNoteFragment
    private lateinit var bookmarksFragment: BookmarksFragment
    private lateinit var profileFragment: ProfileFragment

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

        binding.bottomNavigationView.setupWithNavController(findNavController())

        val bnvNavigation = binding.bottomNavigationView

        homeFragment = HomeFragment()
        addNoteFragment = AddNoteFragment()
        bookmarksFragment = BookmarksFragment()
        profileFragment = ProfileFragment()

        bnvNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {

            }
            true
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setViewPagerSettings()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                loading()
                viewModel.coursesItemFlow.collectLatest {
                    postToViewPager(it)
                    endLoading()
                }
            }
        }
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            val selectedFragment = when (item.itemId) {
                //R.id.bottom_home -> HomeFragment()
                R.id.bottom_save -> BookmarksFragment()
                R.id.bottom_add -> AddNoteFragment()
                R.id.bottom_message -> MessageFragment()
                R.id.bottom_profile -> ProfileFragment()
                else -> HomeFragment()
            }
            requireActivity().supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.homeFragment, selectedFragment).commit()
            true
        }
    }






    private fun loading() {
        binding.uiScreen.visibility = View.GONE
        binding.circularProgressIndicator.visibility = View.VISIBLE
    }

    private fun endLoading() {
        binding.uiScreen.visibility = View.VISIBLE
        binding.circularProgressIndicator.visibility = View.GONE
    }

    private fun addToViewPager(title: String, number: String) {
        titleCard.add(title)
        numberCard.add(number)
    }

    private fun addToTagRecycler(names: MutableList<String>) {
        tagName.add(names)
    }

    private fun postToViewPager(items: List<Course>) {
        for (i in 1..items.size) {
            addToViewPager(items[i].title, "$i")
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

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val scrollTo = indicator.getChildAt(position).left
                indicatorScrollView.smoothScrollTo(scrollTo, 0)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.bottom_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

}