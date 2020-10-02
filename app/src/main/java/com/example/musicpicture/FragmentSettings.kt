package com.example.musicpicture

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_settings.*
import org.koin.android.ext.android.inject

class FragmentSettings : Fragment() {

    private val settingsViewModel: SettingsViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_settings, container, false)

        settingsViewModel.text.observe(viewLifecycleOwner, Observer {
            text_settings.text = it
        })
        settingsViewModel.filledSpinner(root,activity,context)

        return root
    }
}