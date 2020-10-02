package com.example.musicpicture

import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData

class SettingsViewModel {

    val text: MutableLiveData<String> = MutableLiveData()
    val langagues = arrayOf("Français", "Anglais", "Espagnol", "Italien", "Japonais", "Coréen")

    init {
        text.value = "This is Settings Fragment"
    }
    fun filledSpinner(root: View?, activity: FragmentActivity?, context: Context?) {
        val spinner = root!!.findViewById<Spinner>(R.id.spinner)
        spinner?.adapter = ArrayAdapter(context!!, R.layout.support_simple_spinner_dropdown_item, langagues)
        spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("erreur")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val type = parent?.getItemAtPosition(position).toString()
                Toast.makeText(activity, "Selected: $type", Toast.LENGTH_LONG).show()
                println(type)
            }

        }
    }
}