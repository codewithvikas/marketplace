package com.patna.marketplace

import android.os.Bundle
import android.text.format.DateUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.patna.marketplace.databinding.FragmentFactsBinding
import com.patna.marketplace.model.Constants
import org.json.JSONArray
import org.json.JSONObject
import java.lang.StringBuilder

class FactsFragment : Fragment() {


    lateinit var factsViewModel: FactsViewModel
    lateinit var factsViewModelFactory: FactsViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding  = FragmentFactsBinding.inflate(inflater)
        binding.factsHeadingTv.setOnClickListener {
            it.findNavController().navigate(FactsFragmentDirections.actionFactsFragmentToFactCategoryFragment())
        }
        val args = FactsFragmentArgs.fromBundle(requireArguments())

        factsViewModelFactory = FactsViewModelFactory(args.categoryType)
        factsViewModel = ViewModelProvider(this,factsViewModelFactory).get(FactsViewModel::class.java)


        Toast.makeText(context,args.categoryType.name,Toast.LENGTH_SHORT).show()

        factsViewModel.facts.observe(viewLifecycleOwner,{
            binding.factsDetailTv.text = it
        })

        factsViewModel.currentTime.observe(viewLifecycleOwner, Observer {

            binding.timerTv.setText(DateUtils.formatElapsedTime(it))
        })
        factsViewModel.timerFinished.observe(viewLifecycleOwner, Observer {
            if (it==true){
                Toast.makeText(context,"Timer has finished !!",Toast.LENGTH_SHORT).show()
                factsViewModel.onGameFinishComplete()
            }

        })
        return binding.root

    }


}