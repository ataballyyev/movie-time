package com.films.movietime.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.films.movietime.R
import com.films.movietime.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        binding.apply {
            searchButton.setOnClickListener {
                val bottomSheetDialog = SearchFilterFragment()
                bottomSheetDialog.show(childFragmentManager, bottomSheetDialog.tag)
            }
        }

    }

}