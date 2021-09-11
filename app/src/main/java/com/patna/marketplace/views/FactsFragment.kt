package com.patna.marketplace.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.patna.marketplace.viewmodel.FactsViewModel
import com.patna.marketplace.viewmodel.FactsViewModelFactory
import com.patna.marketplace.databinding.FragmentFactsBinding
import com.patna.marketplace.model.FactAdapter
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

        val args = FactsFragmentArgs.fromBundle(requireArguments())

        val application = requireNotNull(this.activity).application
        val factDao = MarketPlaceDatabase.getInstance(application).factDao

        factsViewModelFactory = FactsViewModelFactory(factDao,application)
        factsViewModel = ViewModelProvider(this,factsViewModelFactory).get(FactsViewModel::class.java)

        binding.factsViewModel = factsViewModel
        binding.setLifecycleOwner(viewLifecycleOwner)

        val adapter = FactAdapter()
        binding.factList.adapter = adapter

        val layoutManager = GridLayoutManager(requireContext(),3)
        binding.factList.layoutManager = layoutManager

        factsViewModel.facts.observe(viewLifecycleOwner,{
           it?.let {
               adapter.submitList(it)
           }
        })

        Toast.makeText(context,args.categoryType.name,Toast.LENGTH_SHORT).show()

        return binding.root

    }

}