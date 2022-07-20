package com.akash.reach52.fragments.list

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.akash.reach52.R
import com.akash.reach52.databinding.FragmentListBinding
import com.akash.reach52.model.PatientList
import com.akash.reach52.viewmodel.PatientViewModel


class ListFragment : Fragment(R.layout.fragment_list), MenuProvider {
    private var binding: FragmentListBinding? = null

    private val viewModel by viewModels<PatientViewModel>()
    private var adapter = MyAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListBinding.bind(view)
        binding?.rvList?.adapter = adapter

        viewModel.readAllData.observe(viewLifecycleOwner) { patient ->
            adapter.setData(patient as ArrayList<PatientList>)
        }
        onClick()
        requireActivity().addMenuProvider(this, viewLifecycleOwner)
    }

    private fun onClick() {
        binding?.floatingButton?.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
    }

    private fun deleteAllPatient() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            viewModel.deleteAllPatient()
            Toast.makeText(
                requireContext(),
                "Successfully removed everyThing",
                Toast.LENGTH_SHORT
            ).show()
        }
        builder.setNegativeButton("No") { _, _ ->

        }
        builder.setTitle("Delete everything")
        builder.setMessage("Are you sure you want to delete everything")
        builder.create().show()
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.itemId == R.id.menu_delete) {
            deleteAllPatient()
        }
        return super.onContextItemSelected(menuItem)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}