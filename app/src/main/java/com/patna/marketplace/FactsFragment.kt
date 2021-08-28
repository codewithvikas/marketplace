package com.patna.marketplace

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.patna.marketplace.databinding.FragmentFactsBinding

class FactsFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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

        Toast.makeText(context,args.categoryType.name,Toast.LENGTH_SHORT).show()
        return binding.root

    }

}