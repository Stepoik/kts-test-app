package ru.stepan.reddit

import android.app.Application
import dev.lokksmith.Lokksmith
import dev.lokksmith.SingletonLokksmithProvider
import dev.lokksmith.createLokksmith
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.stepan.reddit.umbrella.umbrellaModule

class StepikApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Napier.base(DebugAntilog())

        SingletonLokksmithProvider.set(createLokksmith(this))

        startKoin {
            androidContext(this@StepikApp)
            modules(umbrellaModule)
        }
    }
}