package com.patna.marketplace

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
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
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        homeBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)

        homeBinding.fact = Fact(FactCategory.ANIMAL,"Cow is a pet Animal","Cow Gives milk.\n Cow dung is a great source of organic fertilizer")

        homeBinding.factsBt.setOnClickListener (Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_factCategory))

        homeBinding.blogBt.setOnClickListener {
            Toast.makeText(context,"Blogs coming soon", Toast.LENGTH_SHORT).show()
        }
        homeBinding.dealsBt.setOnClickListener {
            Toast.makeText(context,"Offer and deals coming soon", Toast.LENGTH_SHORT).show()
        }

        return homeBinding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu,menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,requireView().findNavController()) || super.onOptionsItemSelected(item)
    }
}