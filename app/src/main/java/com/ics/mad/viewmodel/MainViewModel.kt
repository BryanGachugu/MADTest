package com.ics.mad.viewmodel

import androidx.lifecycle.ViewModel
import com.ics.mad.model.repository.CodingResourcesRepo

class MainViewModel(codingResourcesRepo: CodingResourcesRepo) : ViewModel() {

   val codingResources = codingResourcesRepo.getAllResources()

}