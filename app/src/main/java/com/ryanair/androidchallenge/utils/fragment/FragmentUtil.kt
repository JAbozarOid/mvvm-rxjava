package com.ryanair.androidchallenge.utils.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

object FragmentUtil {

    fun FragmentManager.openFragment(
        fragmentClass: Class<out Fragment?>,
        bundle: Bundle? = null
    ) {
        this.beginTransaction()
            .add(fragmentClass, bundle, null)
            .commit()
    }
}