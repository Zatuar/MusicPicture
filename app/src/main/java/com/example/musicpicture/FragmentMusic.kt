package com.example.musicpicture

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_music.*
import org.koin.android.ext.android.inject


class FragmentMusic : Fragment() {

    private val musicViewModel: MusicViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_music, container, false)

        musicViewModel.text.observe(viewLifecycleOwner, Observer {
            text_music.text = it
        })
        musicViewModel.setButtonPlayer(root,context)
        musicViewModel.callAPI(root,context)
        return root
    }
}