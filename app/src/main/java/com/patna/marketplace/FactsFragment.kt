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
import com.patna.marketplace.model.Constants
import org.json.JSONArray
import org.json.JSONObject
import java.lang.StringBuilder

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
        binding.factsDetailTv.text = extractData()
        return binding.root

    }
    fun extractData():String{
        val jsonObject = JSONObject(Constants.facts_json)
        val facts = jsonObject.getJSONObject(Constants.facts)
        val political:JSONArray = facts.getJSONArray(Constants.political)
        val stringBuilder = StringBuilder()
        for (i in 0 until political.length()){
            val element:JSONObject = political.get(i) as JSONObject
            stringBuilder.append(element.get(Constants.heading))
            stringBuilder.append("\n")
            stringBuilder.append(element.get(Constants.body))
        }
        return stringBuilder.toString()
    }

}