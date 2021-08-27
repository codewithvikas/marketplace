package com.patna.marketplace

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.patna.marketplace.databinding.FragmentFactCategoryBinding
import com.patna.marketplace.model.FactCategory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
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

        binding.animalBt.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_factCategory_to_factsFragment))
        return binding.root
    }
}