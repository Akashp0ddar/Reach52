package com.akash.reach52.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.akash.reach52.data.PatientDataBase
import com.akash.reach52.model.PatientList
import com.akash.reach52.repository.PatientRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PatientViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<PatientList>>
    private val repository: PatientRepository

    init {
        val patientListViewModelDao = PatientDataBase.getDataBase(application).patientListDao()
        repository = PatientRepository(patientListViewModelDao)
        readAllData = repository.readAllData
    }

    fun addPatient(patientList: PatientList) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPatient(patientList)
        }
    }

    fun updatePatient(patientList: PatientList){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updatePatient(patientList)
        }
    }

    fun deletePatient(patientList: PatientList){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deletePatient(patientList)
        }
    }

    fun deleteAllPatient(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }
}