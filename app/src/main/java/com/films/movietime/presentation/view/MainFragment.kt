package com.films.movietime.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.films.domain.model.NetworkResult
import com.films.domain.model.Search
import com.films.movietime.MovieApp
import com.films.movietime.R
import com.films.movietime.databinding.FragmentMainBinding
import com.films.movietime.presentation.adapter.MainAdapter
import com.films.movietime.presentation.viewmodel.MainViewModel
import com.films.movietime.presentation.viewmodel.MainViewModelFactory
import javax.inject.Inject

class MainFragment : Fragment(R.layout.fragment_main), MainAdapter.OnItemClickListener {

    private lateinit var binding: FragmentMainBinding
    @Inject lateinit var adapter: MainAdapter
    @Inject lateinit var viewModelFactory: MainViewModelFactory
    private lateinit var viewModel: MainViewModel
    private lateinit var searchResultList: List<Search>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        (requireActivity().applicationContext as MovieApp).appComponent.inject(this)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory)[MainViewModel::class.java]

        getSearchResults()

        binding.apply {
            searchButton.setOnClickListener {
                val bottomSheetDialog = SearchFilterFragment()
                bottomSheetDialog.setOnBottomSheetCloseListener(object : SearchFilterFragment.OnBottomSheetCloseListener{
                    override fun onBottomSheetClose(title: String) {
                        titleText.text = title
                        progressBar.visibility = View.VISIBLE
                        textNothingToShow.visibility = View.GONE
                        getSearchResults()
                    }
                })
                bottomSheetDialog.show(childFragmentManager, bottomSheetDialog.tag)
            }
        }

    }

    private fun getSearchResults() {
        viewModel.liveSearchResults.observe(viewLifecycleOwner) { response ->
            when(response) {
                is NetworkResult.Success -> {
                    response.data?.let { model ->
                        searchResultList = model.Search
                        initializeUIElements()
                        Log.i("TAG", model.toString())
                    }
                }
                is NetworkResult.Error -> {
                    Log.i("TAG", "Error")
                }
                is NetworkResult.Loading -> {
                    Log.i("TAG", "Loading...")
                }
            }
        }
    }

    private fun initializeUIElements() {
        binding.progressBar.visibility = View.GONE
        binding.textNothingToShow.visibility = View.GONE
        binding.recyclerViewSearchResult.visibility = View.VISIBLE
        adapter.initializeItemClickListener(this)
        binding.recyclerViewSearchResult.adapter = adapter
        binding.recyclerViewSearchResult.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapter.initializeListSearchResults(list = searchResultList)
    }

    override fun onItemClick(position: Int) {
        findNavController().navigate(MainFragmentDirections.actionMainFragmentToDetailsFragment(searchResultList[position]))
    }

}