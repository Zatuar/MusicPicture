package com.example.musicpicture.injection

import com.example.musicpicture.HomeViewModel
import com.example.musicpicture.LoginViewModel
import com.example.musicpicture.MusicViewModel
import com.example.musicpicture.SettingsViewModel
import org.koin.dsl.module

val presentationKoin = module {
    factory { LoginViewModel() }
    factory { HomeViewModel() }
    factory { MusicViewModel() }
    factory { SettingsViewModel() }
}