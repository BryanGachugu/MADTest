package com.ics.mad.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.ics.mad.R
import com.ics.mad.databinding.ActivityMainBinding
import com.ics.mad.view.adapter.ResourcesAdapter
import com.ics.mad.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.viewmodel = mainViewModel
        binding.adapter = mainViewModel.codingResources.value?.let { ResourcesAdapter(it) }


    }
}