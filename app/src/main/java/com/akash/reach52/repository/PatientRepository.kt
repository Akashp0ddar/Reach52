package com.akash.reach52.repository

import androidx.lifecycle.LiveData
import com.akash.reach52.data.PatientListDao
import com.akash.reach52.model.PatientList

class PatientRepository(private val patientListDao: PatientListDao) {
    val readAllData: LiveData<List<PatientList>> = patientListDao.readAllData()

     fun addPatient(patientList: PatientList) {
        patientListDao.addPatient(patientList)
    }

     fun updatePatient(patientList: PatientList){
        patientListDao.updateUser(patientList)
    }

    fun deletePatient(patientList: PatientList){
         patientListDao.deletePatient(patientList)
    }

    fun deleteAll(){
        patientListDao.deleteAllPatient()
    }

}