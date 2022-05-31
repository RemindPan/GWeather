package com.tinyfight.gweather.common.base

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * Create at 2022/5/31
 * @author Yao
 * Name com.tinyfight.gweather.common.base.ViewBindingActivity
 */
abstract class ViewBindingActivity<VB : ViewBinding> : AppCompatActivity() {
    private lateinit var _binding: ViewBinding

    @Suppress("UNCHECKED_CAST")
    protected val binding: VB
        get() = _binding as VB


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentView())
    }

    private fun getContentView(): View {
        _binding = bindingInflate(layoutInflater)
        return _binding.root
    }

    abstract fun bindingInflate(
        layoutInflater: LayoutInflater,
    ): VB
}