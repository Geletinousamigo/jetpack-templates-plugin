package com.github.geletinousamigo.jetpacktemplatesplugin.activities.composeWebViewActivity.src.app_package

import com.android.tools.idea.wizard.template.escapeKotlinIdentifier

fun mainActivityKt(
    activityClass: String,
    defaultPreview: String,
    packageName: String,
    themeName: String
) = """
package ${escapeKotlinIdentifier(packageName)}

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ${escapeKotlinIdentifier(packageName)}.ui.theme.${themeName}

class $activityClass : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            $themeName {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    App(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun App(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello ${"$"}name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun ${defaultPreview}() {
    $themeName {
        App("Android")
    }
}
"""