package com.example.apicartoes.di

import com.example.apicartoes.di.cards.cardComponent
import com.example.apicartoes.di.home.homeViewModelModule
import com.example.apicartoes.di.login.loginRepositoryModule
import com.example.apicartoes.di.login.loginUseCaseModule
import com.example.apicartoes.di.login.loginViewModelModule
import com.example.apicartoes.di.network.networkComponent
import com.example.apicartoes.di.splashScreen.splashScreenViewModelModule
import com.example.apicartoes.di.utils.statusUserFireBaseModule

val appModule = listOf(
    loginUseCaseModule,
    loginViewModelModule,
    dispatchersModule,
    loginRepositoryModule,
    statusUserFireBaseModule,
    homeViewModelModule,
    splashScreenViewModelModule
) + networkComponent + cardComponent