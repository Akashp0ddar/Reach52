package com.akash.reach52.fragments.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akash.reach52.databinding.RvListSingleItemBinding
import com.akash.reach52.model.PatientList

class MyAdapter() : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    var rvPatientList = ArrayList<PatientList>()

    inner class ViewHolder(val binding: RvListSingleItemBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            RvListSingleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        /**
         * Setting Data in RecyclerView
         */
        holder.binding.tvPatientFirstName.text = rvPatientList[position].FirstName
        holder.binding.tvPatientMiddleName.text = rvPatientList[position].MiddleName
        holder.binding.tvPatientLastName.text = rvPatientList[position].LastName
        holder.binding.tvPatientSex.text = rvPatientList[position].Sex
        holder.binding.tvPatientBirthdate.text = rvPatientList[position].BirthDate
        holder.binding.tvDFirstName.text = rvPatientList[position].DoctorFirstName
        holder.binding.tvDMiddleName.text = rvPatientList[position].DoctorMiddleName
        holder.binding.tvDLastName.text = rvPatientList[position].DoctorLastName
        holder.binding.ivPatientPicture.setImageBitmap(rvPatientList[position].patientImage)

    }

    fun setData(patient: ArrayList<PatientList>) {
        this.rvPatientList = patient
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return rvPatientList.size
    }

}
