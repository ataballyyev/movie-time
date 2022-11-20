package com.films.movietime.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.films.movietime.R
import com.films.movietime.databinding.FragmentSearchFilterBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SearchFilterFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentSearchFilterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_search_filter, container, false)
        binding = FragmentSearchFilterBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            applyButton.setOnClickListener {
                dismiss()
            }
        }
    }
}