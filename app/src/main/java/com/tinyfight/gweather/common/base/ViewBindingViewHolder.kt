package com.tinyfight.gweather.common.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * Create at 2022/5/31
 * @author Yao
 * Name com.tinyfight.gweather.common.base.ViewBindingViewHolder
 */

class ViewBindingViewHolder<VB : ViewBinding> private constructor(val binding: VB) :
    RecyclerView.ViewHolder(binding.root) {

    constructor(
        parent: ViewGroup,
        creator: (inflater: LayoutInflater, root: ViewGroup, attachToRoot: Boolean) -> VB,
    ) : this(creator(LayoutInflater.from(parent.context), parent, false))

}

fun <T : ViewBinding> ViewGroup.getViewHolder(
    creator: (inflater: LayoutInflater, root: ViewGroup, attachToRoot: Boolean) -> T,
): ViewBindingViewHolder<T> = ViewBindingViewHolder(this, creator)