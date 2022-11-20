package com.films.movietime.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.films.movietime.R
import com.films.movietime.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private lateinit var binding: FragmentDetailsBinding
    private val args: DetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.bind(view)

        binding.apply {
            Glide.with(posterImage)
                .load(args.movie.Poster)
                .into(posterImage)
            posterTitle.text = args.movie.Title
            movieYear.text = args.movie.Year
            movieCategory.text = args.movie.Type

            backButton.setOnClickListener {
                findNavController().popBackStack()
            }

            leaveCommentButton.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }
}