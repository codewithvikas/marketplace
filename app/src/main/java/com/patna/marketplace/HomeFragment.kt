package com.patna.marketplace

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.patna.marketplace.databinding.FragmentHomeBinding
import com.patna.marketplace.model.Fact
import com.patna.marketplace.model.FactCategory


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {


    lateinit var homeBinding:FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        homeBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)

        homeBinding.fact = Fact(FactCategory.ANIMAL,"Cow is a pet Animal","Cow Gives milk.\n Cow dung is a great source of organic fertilizer")

        homeBinding.factsBt.setOnClickListener (Navigation.createNavigateOnClickListener(R.id.homeFragmentToFactsFragment))

        homeBinding.blogBt.setOnClickListener {
            Toast.makeText(context,"Blogs coming soon", Toast.LENGTH_SHORT).show()
        }
        homeBinding.dealsBt.setOnClickListener {
            Toast.makeText(context,"Offer and deals coming soon", Toast.LENGTH_SHORT).show()
        }

        return homeBinding.root
    }

}