package com.hdd.pakwan.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.hdd.pakwan.R

class ProductOtherDetailsFragment() : Fragment() {
    private lateinit var otherDetails:TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_product_other_details, container, false)
        otherDetails = view.findViewById(R.id.fpd_product_other_details)
//        otherDetails.text = product.productOtherDetails
        return view
    }

}