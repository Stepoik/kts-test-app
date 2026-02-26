package ru.stepan.reddit

import android.app.Application
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.stepan.reddit.umbrella.umbrellaModule

class RedditApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Napier.base(DebugAntilog())
        startKoin {
            androidContext(this@RedditApp)
            modules(umbrellaModule)
        }
    }
}