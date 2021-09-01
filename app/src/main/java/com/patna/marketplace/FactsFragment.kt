package com.patna.marketplace

import android.app.Service
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.text.format.DateUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ServiceLifecycleDispatcher
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

    val GAME_OVER_BUZZ_PATTERN = longArrayOf(0,2000)

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

        binding.factsViewModel = factsViewModel
        binding.setLifecycleOwner(viewLifecycleOwner)

        Toast.makeText(context,args.categoryType.name,Toast.LENGTH_SHORT).show()
        factsViewModel.timerFinished.observe(viewLifecycleOwner, Observer {
            if (it==true){
                buzz(GAME_OVER_BUZZ_PATTERN)
                Toast.makeText(context,"Timer has finished !!",Toast.LENGTH_SHORT).show()
                factsViewModel.onGameFinishComplete()
            }

        })
        return binding.root

    }

private fun buzz(pattern:LongArray){
    val buzzer = activity?.getSystemService(Service.VIBRATOR_SERVICE) as Vibrator

    buzzer?.let {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            buzzer.vibrate(VibrationEffect.createWaveform(pattern,-1))
        }
        else{
            buzzer.vibrate(pattern,-1)
        }
    }
}


}