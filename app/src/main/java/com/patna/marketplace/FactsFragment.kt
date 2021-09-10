package com.patna.marketplace

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.patna.marketplace.databinding.FragmentFactsBinding
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

        factsViewModel.facts.observe(viewLifecycleOwner,{
            binding.factsDetailTv.setText(it.toString())
        })

        Toast.makeText(context,args.categoryType.name,Toast.LENGTH_SHORT).show()

        return binding.root

    }

}