package com.patna.marketplace

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.patna.marketplace.databinding.FragmentFactsBinding

class FactsFragment : Fragment() {

    private lateinit var binding:FragmentFactsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         binding  = FragmentFactsBinding.inflate(inflater)
        binding.factsHeadingTv.setOnClickListener {
            it.findNavController().navigate(FactsFragmentDirections.actionFactsFragmentToFactCategoryFragment())
        }
        val args = FactsFragmentArgs.fromBundle(requireArguments())

        Toast.makeText(context,args.categoryType.name,Toast.LENGTH_SHORT).show()

        setHasOptionsMenu(true)
        return binding.root

    }

    private fun getShareIntent(): Intent {
        val args = FactsFragmentArgs.fromBundle(requireArguments())
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain")
            .putExtra(Intent.EXTRA_TEXT,"${args.categoryType.name} : ${binding.factsDetailTv.text}")
        return shareIntent

    }

    private fun shareFact(){
        startActivity(getShareIntent())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fact_sharing_menu,menu)
        if (getShareIntent().resolveActivity(requireActivity().packageManager)==null){
            menu.findItem(R.id.share).isVisible = false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.share ->{
                shareFact()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}