package com.anthony.net.sample.github

import androidx.multidex.MultiDexApplication
import com.anthony.net.sample.github.di.networkModule
import com.anthony.net.sample.github.di.repositoryModule
import com.anthony.net.sample.github.di.serviceModule
import com.anthony.net.sample.github.di.useCaseModule
import com.anthony.net.sample.github.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GithubClientApplication : MultiDexApplication() {

    companion object {

        lateinit var context: GithubClientApplication
            private set

    }

    override fun onCreate() {
        super.onCreate()

        context = this

        startKoin {

            androidContext(this@GithubClientApplication)

            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    serviceModule,
                    viewModelModule
                )
            )

        }

    }

}