package com.films.movietime.presentation.view

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.films.domain.common.Constants
import com.films.movietime.MovieApp
import com.films.movietime.R
import com.films.movietime.databinding.FragmentSearchFilterBinding
import com.films.movietime.presentation.viewmodel.MainViewModel
import com.films.movietime.presentation.viewmodel.MainViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import javax.inject.Inject

class SearchFilterFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentSearchFilterBinding
    @Inject lateinit var viewModelFactory: MainViewModelFactory
    private lateinit var onBottomSheetCloseListener: OnBottomSheetCloseListener
    private lateinit var viewModel: MainViewModel

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

        (requireActivity().applicationContext as MovieApp).appComponent.inject(this)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory)[MainViewModel::class.java]

        binding.apply {
            applyButton.setOnClickListener {
                viewModel.getSearchResults(
                    title = editTextTitle.editableText.toString(),
                    page = editTextQuantity.editableText.toString().toInt(),
                    apiKey = Constants.API_KEY
                )
                dismiss()
            }
        }
    }

    interface OnBottomSheetCloseListener{
        fun onBottomSheetClose(title: String)
    }

    fun setOnBottomSheetCloseListener(listener:OnBottomSheetCloseListener) {
        onBottomSheetCloseListener = listener
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onBottomSheetCloseListener.onBottomSheetClose(title = binding.editTextTitle.editableText.toString())
    }
}