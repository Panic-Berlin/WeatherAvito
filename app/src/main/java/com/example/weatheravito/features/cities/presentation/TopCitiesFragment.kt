package com.example.weatheravito.features.cities.presentation

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.weatheravito.R
import com.example.weatheravito.databinding.FragmentTopCitiesBinding
import com.example.weatheravito.features.cities.presentation.adapter.CitiesAdapter
import com.example.weatheravito.utils.ViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopCitiesFragment : Fragment(R.layout.fragment_top_cities) {

    private val cityViewModel: TopCitiesViewModel by viewModels()
    private val viewBinding: FragmentTopCitiesBinding by viewBinding(FragmentTopCitiesBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeTopCities()
        init()
        viewBinding.topCitiesRV.addItemDecoration(
            TopCityItemDecoration(
                requireContext(),
                R.dimen.city_left_and_right_margin,
                R.dimen.city_top_and_bottom_margin
            )
        )

    }

    private fun init() {
        viewBinding.srlTopCities.setOnRefreshListener { cityViewModel.onTopCitiesRefresh() }
        cityViewModel.isRefresh.observe(viewLifecycleOwner) {
            viewBinding.srlTopCities.isRefreshing = it
        }
        viewBinding.searchIV.setOnClickListener {
            when (viewBinding.cityToolbar.isVisible) {
                true -> {
                    viewBinding.cityToolbar.isVisible = false
                    viewBinding.searchToolBar.isVisible = true
                }
                false -> {
                    viewBinding.cityToolbar.isVisible = true
                    viewBinding.searchToolBar.isVisible = false
                }
            }
        }

        viewBinding.etSearch.doAfterTextChanged {
            if (viewBinding.etSearch.text.isNotEmpty()) {
                cityViewModel.onSearchQueryChanged(it.toString())
                observeSearchCity()
            }
        }

        viewBinding.searchToolBar.setOnClickListener {
            viewBinding.etSearch.text.clear()
            viewBinding.searchToolBar.isVisible = false
            viewBinding.cityToolbar.isVisible = true
            observeTopCities()
        }
    }

    private fun observeTopCities() {
        val topRecyclerView = viewBinding.topCitiesRV
        topRecyclerView.layoutManager = LinearLayoutManager(activity)
        cityViewModel.isLoading.observe(viewLifecycleOwner) {
            viewBinding.cityLoading.isVisible = it
        }
        cityViewModel.cities.observe(viewLifecycleOwner, { viewState ->
            viewBinding.tvCityError.isVisible = viewState !is ViewState.Show
            if (viewState is ViewState.Show) {
                val cityAdapter = CitiesAdapter(viewState.data) {
                    findNavController().navigate(
                        R.id.action_topCitiesFragment_to_temperatureFragment, bundleOf(
                            "key" to it
                        )
                    )
                }
                topRecyclerView.adapter = cityAdapter
            }
        })
    }

    private fun observeSearchCity() {
        val searchRecyclerView = viewBinding.topCitiesRV
        searchRecyclerView.layoutManager = LinearLayoutManager(activity)
        cityViewModel.isLoading.observe(viewLifecycleOwner) {
            viewBinding.cityLoading.isVisible = it
        }
        cityViewModel.search.observe(viewLifecycleOwner, { viewState ->
            viewBinding.tvCityError.isVisible = viewState !is ViewState.Show
            if (viewState is ViewState.Show) {
                val searchAdapter = CitiesAdapter(viewState.data) {
                    findNavController().navigate(
                        R.id.action_topCitiesFragment_to_temperatureFragment, bundleOf(
                            "key" to it
                        )
                    )
                }
                searchRecyclerView.adapter = searchAdapter
            }
        })
    }
}