package com.patna.marketplace.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.patna.marketplace.viewmodel.FactsViewModel
import com.patna.marketplace.viewmodel.FactsViewModelFactory
import com.patna.marketplace.databinding.FragmentFactsBinding
import com.patna.marketplace.model.FactAdapter
import com.patna.marketplace.model.FactListItemListener
import com.patna.marketplace.model.MarketPlaceDatabase

class FactsFragment : Fragment() {


    lateinit var factsViewModel: FactsViewModel
    lateinit var factsViewModelFactory: FactsViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding  = FragmentFactsBinding.inflate(inflater)

        val application = requireNotNull(this.activity).application
        val factDao = MarketPlaceDatabase.getInstance(application).factDao

        factsViewModelFactory = FactsViewModelFactory(factDao,application)
        factsViewModel = ViewModelProvider(this,factsViewModelFactory).get(FactsViewModel::class.java)

        binding.factsViewModel = factsViewModel
        binding.setLifecycleOwner(viewLifecycleOwner)

        val adapter = FactAdapter(FactListItemListener { factId ->
            factsViewModel.onFactItemClicked(factId)
        })
        binding.factList.adapter = adapter

        val layoutManager = LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
        binding.factList.layoutManager = layoutManager

        factsViewModel.navigateToFactDetail.observe(viewLifecycleOwner,{ nightId ->
            nightId?.let {
               this.findNavController().navigate(FactsFragmentDirections.factsFragmentToFactDetailFragment(nightId))
                factsViewModel.onFactItemNavigated()
           }

        })
        factsViewModel.facts.observe(viewLifecycleOwner,{
           it?.let {
               adapter.submitList(it)
           }
        })

        return binding.root

    }

}