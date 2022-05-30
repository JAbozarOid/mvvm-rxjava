package com.ryanair.androidchallenge.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.ryanair.androidchallenge.R
import com.ryanair.androidchallenge.databinding.FragmentSearchScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentSearchScreenBinding.inflate(inflater, container, false)
            .apply {
                // TODO("Good luck!")
                destinationView.title.text = getString(R.string.to)
                departureDateView.title.text = getString(R.string.departure_date)
                passengersView.title.text = getString(R.string.passengers)

            }.root
    }

    private fun showDatePicker(time: Long?) {
        if (parentFragmentManager.findFragmentByTag(tag) != null) return

        MaterialDatePicker.Builder.datePicker()
            .setTitleText(getString(R.string.select_departure_date))
            .setCalendarConstraints(
                CalendarConstraints.Builder()
                    .setValidator(DateValidatorPointForward.now())
                    .build()
            )
            .setSelection(time).build().also { datePicker ->
                datePicker.addOnPositiveButtonClickListener { updatedTime ->
                    // TODO("Good luck!")
                }
                datePicker.show(parentFragmentManager, DATE_PICKER_TAG)
            }
    }

    companion object {
        private const val DATE_PICKER_TAG = "MaterialDatePicker"
    }

}