package com.ics.mad.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.ics.mad.R
import com.ics.mad.databinding.ActivityMainBinding
import com.ics.mad.model.repository.CodingResourcesRepo
import com.ics.mad.view.adapter.ResourcesAdapter
import com.ics.mad.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val lifecycleOwner = this

        mainViewModel = MainViewModel(CodingResourcesRepo())

        binding.apply {
            mainViewModel.codingResources.observe(lifecycleOwner, Observer { list ->
                adapter = ResourcesAdapter(list)
            })
        }


    }
}