package com.akash.reach52.fragments.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.akash.reach52.R
import com.akash.reach52.databinding.FragmentListBinding
import com.akash.reach52.model.PatientList
import com.akash.reach52.viewmodel.PatientViewModel


class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    lateinit var mPatientViewModel: PatientViewModel
    private var adapter = MyAdapter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvList.adapter = adapter

        mPatientViewModel = ViewModelProvider(requireActivity()).get(PatientViewModel::class.java)
        mPatientViewModel.readAllData.observe(viewLifecycleOwner, Observer { patient ->
            adapter.setData(patient as ArrayList<PatientList> /* = java.util.ArrayList<com.akash.reach52.model.PatientList> */)
        })
        onClick()
    }

    private fun onClick() {
        binding.floatingButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
    }


}