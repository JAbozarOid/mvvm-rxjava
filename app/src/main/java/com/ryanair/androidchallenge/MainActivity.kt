package com.ryanair.androidchallenge

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ryanair.androidchallenge.databinding.MainScreenBinding
import com.ryanair.androidchallenge.ui.fragments.SearchScreenFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: MainScreenBinding? = null
    val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = MainScreenBinding.inflate(layoutInflater)

        setContentView(binding?.root)

        supportFragmentManager.beginTransaction()
            .add(R.id.container, SearchScreenFragment()).commit()
    }
}