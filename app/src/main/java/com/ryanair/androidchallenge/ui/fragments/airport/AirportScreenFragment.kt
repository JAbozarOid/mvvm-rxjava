package com.ryanair.androidchallenge.ui.fragments.airport

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ryanair.androidchallenge.R
import com.ryanair.androidchallenge.databinding.FragmentAirportScreenBinding
import com.ryanair.androidchallenge.ui.fragments.airport.adapter.AirportRecAdapter
import com.ryanair.androidchallenge.utils.ApiResponse
import com.ryanair.androidchallenge.viewModel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AirportScreenFragment : Fragment() {

    private var _binding: FragmentAirportScreenBinding? = null
    private val binding get() = _binding

    private val mSearchViewModel: SearchViewModel by activityViewModels()

    private val mAirportRecAdapter by lazy { AirportRecAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate<FragmentAirportScreenBinding?>(
            inflater,
            R.layout.fragment_airport_screen,
            container,
            false
        ).apply {
            this.lifecycleOwner = viewLifecycleOwner
            this.viewModel = mSearchViewModel
        }

        return _binding?.root!!
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        build()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun build() {
        setupAirportRecyclerView()
        requestGetAirports()
        responseGetAirports()
    }


    private fun setupAirportRecyclerView() {
        binding?.airports?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAirportRecAdapter
        }

    }

    private fun requestGetAirports() {
        mSearchViewModel.getAirports()
    }

    private fun responseGetAirports() {
        mSearchViewModel.airportRes.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResponse.Error -> {
                    mSearchViewModel.showToastErrorMsg(it.errorMessage)
                    mSearchViewModel.setProgressBarVisibility(false)
                }
                is ApiResponse.ErrorTryAgain -> {
                    mSearchViewModel.showToastErrorMsg(it.errorMessage)
                    mSearchViewModel.setProgressBarVisibility(false)
                }
                is ApiResponse.Loading -> {
                    mSearchViewModel.setProgressBarVisibility(true)
                }
                is ApiResponse.Success -> {
                    mSearchViewModel.setProgressBarVisibility(false)
                    if (it.data.isNotEmpty())
                        mAirportRecAdapter.setList(it.data)
                }
            }
        }
    }
}