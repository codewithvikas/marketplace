package com.patna.marketplace.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.patna.marketplace.R
import com.patna.marketplace.databinding.FragmentBloggerBinding
import com.patna.marketplace.viewmodel.BloggerViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BloggerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BloggerFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentBloggerBinding.inflate(inflater,container,false)

        val bloggerViewModel = ViewModelProvider(this).get(BloggerViewModel::class.java)
        bloggerViewModel.response.observe(viewLifecycleOwner,{data ->
            data.let {
                binding.tempBlogTv.text = data
            }

        })

        return binding.root
    }

}