package com.tinyfight.gweather.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * Create at 2022/5/30
 * @author Yao
 * Name com.tinyfight.gweather.common.base.BaseFragment
 */

abstract class ViewBindingFragment<VB : ViewBinding> : Fragment() {
    private lateinit var _binding: ViewBinding

    @Suppress("UNCHECKED_CAST")
    protected val binding: VB
        get() = _binding as VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = bindingInflate(inflater, container, false)
        return _binding.root
    }

    abstract fun bindingInflate(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean,
    ): VB
}