package com.example.weatheravito.features.cities.presentation

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.weatheravito.R
import com.example.weatheravito.databinding.FragmentTopCitiesBinding
import com.example.weatheravito.features.cities.presentation.adapter.CitiesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopCitiesFragment : Fragment(R.layout.fragment_top_cities) {

    private val cityViewModel: TopCitiesViewModel by viewModels()
    private val viewBinding: FragmentTopCitiesBinding by viewBinding(FragmentTopCitiesBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getTopCities()
        init()

    }

    private fun init() {
        viewBinding.searchIV.setOnClickListener {
            if (viewBinding.cityToolbar.visibility == View.VISIBLE) {
                viewBinding.cityToolbar.visibility = View.GONE
                viewBinding.searchToolBar.visibility = View.VISIBLE
            } else {
                viewBinding.cityToolbar.visibility = View.VISIBLE
                viewBinding.searchToolBar.visibility = View.GONE
                viewBinding.cityToolbar.visibility = View.VISIBLE
            }
        }

        viewBinding.etSearch.doAfterTextChanged {
            if (viewBinding.etSearch.text.isNotEmpty()) {
                cityViewModel.onSearchQueryChanged(it.toString())
                getSearchList()
            }

        }

        viewBinding.searchToolBar.setOnClickListener {
            viewBinding.searchToolBar.visibility = View.GONE
            viewBinding.cityToolbar.visibility = View.VISIBLE
            getTopCities()
        }
    }

    private fun getTopCities() {
        val topRecyclerView = viewBinding.topCitiesRV
        topRecyclerView.layoutManager = LinearLayoutManager(activity)

        cityViewModel.cities.observe(viewLifecycleOwner, {
            val cityAdapter = CitiesAdapter(it) {
                findNavController().navigate(
                    R.id.action_topCitiesFragment_to_temperatureFragment, bundleOf(
                        "key" to it
                    )
                )
            }
            topRecyclerView.adapter = cityAdapter
        })
    }

    private fun getSearchList() {
        val searchRecyclerView = viewBinding.topCitiesRV
        searchRecyclerView.layoutManager = LinearLayoutManager(activity)

        cityViewModel.search.observe(viewLifecycleOwner, {
            val searchAdapter = CitiesAdapter(it) {
                findNavController().navigate(
                    R.id.action_topCitiesFragment_to_temperatureFragment, bundleOf(
                        "key" to it
                    )
                )
            }
            searchRecyclerView.adapter = searchAdapter
        })
    }
}