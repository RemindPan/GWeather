package com.tinyfight.gweather.common.widget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.tinyfight.gweather.common.Callback
import com.tinyfight.gweather.databinding.LayoutLocationDialogBinding

/**
 * Create at 2022/5/31
 * @author Yao
 * Name com.tinyfight.gweather.common.base.CustomizeDialog
 */
class LocationDialogFragment : DialogFragment() {
    private var dialogBinding: LayoutLocationDialogBinding? = null

    private var title: String? = null
    private var subTitle: String? = null
    private var confirmCallback: Callback? = null
    private var cancelCallback: Callback? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        dialogBinding = LayoutLocationDialogBinding.inflate(layoutInflater, container, false)
        return dialogBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialogBinding?.title?.text = title
        subTitle?.let {
            dialogBinding?.subTitle?.visibility = View.VISIBLE
            dialogBinding?.subTitle?.text = it
        } ?: kotlin.run {
            dialogBinding?.subTitle?.visibility = View.GONE
        }

        dialogBinding?.cancelButton?.setOnClickListener {
            dismiss()
            cancelCallback?.invoke()
        }

        dialogBinding?.confirmButton?.setOnClickListener {
            dismiss()
            confirmCallback?.invoke()
        }
    }

    override fun onStart() {
        super.onStart()
        val window = dialog?.window ?: return
        window.setBackgroundDrawableResource(android.R.color.transparent)
    }

    companion object {
        const val TAG = "LocationDialogFragment"
    }

    class Builder private constructor() {
        private var title: String? = null
        private var subTitle: String? = null
        private var confirmCallback: Callback? = null
        private var cancelCallback: Callback? = null

        fun title(title: String) = apply {
            this.title = title
        }

        fun subTitle(subTitle: String) = apply {
            this.subTitle = subTitle
        }

        fun cancelCallback(callback: Callback) = apply {
            this.cancelCallback = callback
        }

        fun confirmCallback(callback: Callback) = apply {
            this.confirmCallback = callback
        }

        fun build(): LocationDialogFragment {
            val fragment = LocationDialogFragment()
            fragment.title = title
            fragment.subTitle = subTitle
            fragment.confirmCallback = confirmCallback
            fragment.cancelCallback = cancelCallback
            return fragment
        }

        companion object {
            fun newBuilder(): Builder {
                return Builder()
            }
        }
    }
}