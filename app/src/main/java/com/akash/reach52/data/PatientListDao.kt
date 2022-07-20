package com.akash.reach52.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.akash.reach52.model.PatientList

@Dao
interface PatientListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPatient(patientList: PatientList)

    @Update
    fun updateUser(patientList: PatientList)

    @Delete
    fun deletePatient(patientList: PatientList)

    @Query("DELETE FROM patient_list")
    fun deleteAllPatient()

    @Query("SELECT*FROM patient_list")
    fun readAllData(): LiveData<List<PatientList>>


}