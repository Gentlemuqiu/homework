package com.example.midtest.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.midtest.R
import com.example.midtest.databinding.FragmentBeforeBinding

class BeforeFragment : Fragment() {
    private val mBinding :FragmentBeforeBinding by lazy{
        FragmentBeforeBinding.inflate(layoutInflater)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return mBinding.root
    }

}