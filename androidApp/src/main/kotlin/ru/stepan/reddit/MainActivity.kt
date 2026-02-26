package ru.stepan.reddit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arkivanov.decompose.defaultComponentContext
import com.arkivanov.decompose.retainedComponent
import org.koin.android.ext.android.getKoin
import ru.stepan.reddit.root.entry.RootEntryComponent

class MainActivity : ComponentActivity() {
    private val rootComponentFactory = getKoin().get<RootEntryComponent.Factory>()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val rootComponent = retainedComponent {
            rootComponentFactory.create(defaultComponentContext())
        }
        setContent {
            App(rootComponent)
        }
    }
}