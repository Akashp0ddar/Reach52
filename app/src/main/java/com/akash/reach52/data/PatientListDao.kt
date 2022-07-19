package com.akash.reach52.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.akash.reach52.model.PatientList

@Dao
interface PatientListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPatient(patientList: PatientList)

    @Query("SELECT*FROM patient_list")
    fun readAllData(): LiveData<List<PatientList>>
}