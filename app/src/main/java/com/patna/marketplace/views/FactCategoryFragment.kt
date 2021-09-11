package com.patna.marketplace.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.patna.marketplace.databinding.FragmentFactCategoryBinding
import com.patna.marketplace.model.FactCategory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBERf
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FactCategoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FactCategoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentFactCategoryBinding.inflate(inflater)

        binding.animalBt.setOnClickListener{
            it.findNavController().navigate(FactCategoryFragmentDirections.actionFactCategoryToFactsFragment(FactCategory.ANIMAL))
        }
        binding.historyBt.setOnClickListener{
            it.findNavController().navigate(FactCategoryFragmentDirections.actionFactCategoryToFactsFragment(FactCategory.HISTORY))
        }
        binding.scienceBt.setOnClickListener{
            it.findNavController().navigate(FactCategoryFragmentDirections.actionFactCategoryToFactsFragment(FactCategory.SCIENCE))
        }
        binding.natureBt.setOnClickListener{
            it.findNavController().navigate(FactCategoryFragmentDirections.actionFactCategoryToFactsFragment(FactCategory.NATURE))
        }
        binding.businessBt.setOnClickListener{
            it.findNavController().navigate(FactCategoryFragmentDirections.actionFactCategoryToFactsFragment(FactCategory.BUSINESS))
        }
        return binding.root
    }
}