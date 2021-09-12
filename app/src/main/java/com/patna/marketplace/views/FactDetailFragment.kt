package com.patna.marketplace.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.patna.marketplace.databinding.FragmentFactDetailBinding

class FactDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFactDetailBinding.inflate(inflater)
        val arguments = FactDetailFragmentArgs.fromBundle(requireArguments())

        binding.fact = arguments.factId


        return binding.root
    }
}