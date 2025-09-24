package com.github.geletinousamigo.jetpacktemplatesplugin.activities.composeWebViewActivity

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.addAllKotlinDependencies
import com.android.tools.idea.wizard.template.impl.activities.common.addComposeDependencies
import com.android.tools.idea.wizard.template.impl.activities.common.generateManifest
import com.github.geletinousamigo.jetpacktemplatesplugin.activities.composeWebViewActivity.res.values.themesXml
import com.github.geletinousamigo.jetpacktemplatesplugin.activities.composeWebViewActivity.src.app_package.mainActivityKt
import com.github.geletinousamigo.jetpacktemplatesplugin.activities.composeWebViewActivity.src.app_package.ui.colorKt
import com.github.geletinousamigo.jetpacktemplatesplugin.activities.composeWebViewActivity.src.app_package.ui.themeKt
import com.github.geletinousamigo.jetpacktemplatesplugin.activities.composeWebViewActivity.src.app_package.ui.typeKt


fun RecipeExecutor.composeWebViewRecipe(
    moduleData: ModuleTemplateData,
    activityClass: String,
    packageName: String,
    isLauncher: Boolean
) {
    val (_, srcOut, resOut, _) = moduleData
    addAllKotlinDependencies(moduleData)

    addDependency(mavenCoordinate = "androidx.lifecycle:lifecycle-runtime-ktx:+")
    addDependency(mavenCoordinate = "androidx.activity:activity-compose:+")

    // Add Compose dependencies, using the BOM to set versions
    addComposeDependencies(moduleData)

    addDependency(mavenCoordinate = "androidx.compose.material3:material3")

    generateManifest(
        moduleData = moduleData,
        activityClass = activityClass,
        activityThemeName = moduleData.themesData.main.name,
        packageName = packageName,
        isLauncher = isLauncher,
        hasNoActionBar = true,
        generateActivityTitle = true
    )
    // It doesn't have to create separate themes.xml for light and night because the default
    // status bar color is same between them at this moment
    // TODO remove themes.xml once Compose library supports setting the status bar color in Composable
    // this themes.xml exists just for settings the status bar color.
    // Thus, themeName follows the non-Compose project convention.
    // (E.g. Theme.MyApplication) as opposed to the themeName variable below (E.g. MyApplicationTheme)
    mergeXml(themesXml(themeName = moduleData.themesData.main.name), resOut.resolve("values/themes.xml"))

    val themeName = "${moduleData.themesData.appName}Theme"
    save(
        mainActivityKt(activityClass, "${moduleData.themesData.appName}Preview", packageName, themeName),
        srcOut.resolve("${activityClass}.kt")
    )
    val uiThemeFolder = "ui/theme"
    save(colorKt(packageName), srcOut.resolve("$uiThemeFolder/Color.kt"))
    save(themeKt(packageName, themeName), srcOut.resolve("$uiThemeFolder/Theme.kt"))
    save(typeKt(packageName), srcOut.resolve("$uiThemeFolder/Type.kt"))

    setJavaKotlinCompileOptions(true)
    setBuildFeature("compose", true)

    open(srcOut.resolve("${activityClass}.kt"))
}