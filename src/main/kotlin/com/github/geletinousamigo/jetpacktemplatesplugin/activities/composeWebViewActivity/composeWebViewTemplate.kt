package com.github.geletinousamigo.jetpacktemplatesplugin.activities.composeWebViewActivity

import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.defaultPackageNameParameter
import com.github.geletinousamigo.jetpacktemplatesplugin.MyProjectTemplatesProvider
import java.io.File

context(myProjectTemplateProviderScope: MyProjectTemplatesProvider)
val webViewTemplate
    get() = template {
        name = "Empty Compose WebView Activity"
        description = "Create a new empty WebView activity with Jetpack Compose"
        minApi = 21
        constraints = listOf(
            TemplateConstraint.AndroidX,
            TemplateConstraint.Kotlin,
            TemplateConstraint.Material3,
            TemplateConstraint.Compose
        )
        category = Category.Compose
        formFactor = FormFactor.Mobile
        screens = listOf(WizardUiContext.NewProject, WizardUiContext.NewProjectExtraDetail)

        val activityClass = stringParameter {
            name = "Activity Name"
            default = "MainActivity"
            help = "The name of the activity class to create"
            constraints = listOf(
                Constraint.CLASS,
                Constraint.UNIQUE,
                Constraint.NONEMPTY
            )
            loggable = true
            visible = { false }
        }

        val debugUrlName = stringParameter {
            name = "Debug WebView Url"
            default = "www.example.com"
            help = "Url for debug build type"
            constraints = listOf(
                Constraint.URI_AUTHORITY,
                Constraint.NONEMPTY
            )
            loggable = true
            visible = { true }
        }

        val releaseUrlName = stringParameter {
            name = "Release WebView Url"
            default = "www.example.com"
            help = "Url for release build type"
            constraints = listOf(
                Constraint.URI_AUTHORITY,
                Constraint.NONEMPTY
            )
            loggable = true
            visible = { true }
        }

        val packageName = defaultPackageNameParameter

        val isLauncher = booleanParameter {
            name = "Launcher Activity"
            default = false
            help =
                "If true, this activity will have a CATEGORY_LAUNCHER intent filter, making it visible in the launcher"
            visible = { false }
        }

        widgets = listOf(
            TextFieldWidget(activityClass),
            PackageNameWidget(packageName),
            CheckBoxWidget(isLauncher),
            // Invisible widgets to pass data
            TextFieldWidget(debugUrlName),
            TextFieldWidget(releaseUrlName),
//            LanguageWidget()
        )

        // I am reusing the thumbnail provided by Android Studio, but
        // replace it with your own
        val file = File("thumbnails").resolve("webview_compose.png")
        thumb = { Thumb { findResource(myProjectTemplateProviderScope.javaClass, file) } }
        println(thumb.invoke().path)

//        val file = File("compose-activity-material3").resolve("template_compose_empty_activity_material3.png")
//        thumb {
//            file
//        }

//        val projectRoot = File("").absoluteFile
//        val file = File(projectRoot, "src/main/resources/thumbnails/webview_compose.png")

//        thumb { file }


        recipe = { data: TemplateData ->
            composeWebViewRecipe(
                moduleData = data as ModuleTemplateData,
                activityClass = activityClass.value,
                packageName = packageName.value,
                isLauncher = isLauncher.value
            )
        }
    }