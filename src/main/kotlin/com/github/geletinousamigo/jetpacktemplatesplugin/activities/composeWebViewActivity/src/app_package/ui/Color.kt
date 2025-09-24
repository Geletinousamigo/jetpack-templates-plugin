package com.github.geletinousamigo.jetpacktemplatesplugin.activities.composeWebViewActivity.src.app_package.ui


import com.android.tools.idea.wizard.template.MaterialColor
import com.android.tools.idea.wizard.template.escapeKotlinIdentifier

fun colorKt(
    packageName: String
) = """
package ${escapeKotlinIdentifier(packageName)}.ui.theme

import androidx.compose.ui.graphics.Color

${MaterialColor.PURPLE_80.kotlinComposeVal()}
${MaterialColor.PURPLE_GREY_80.kotlinComposeVal()}
${MaterialColor.PINK_80.kotlinComposeVal()}

${MaterialColor.PURPLE_40.kotlinComposeVal()}
${MaterialColor.PURPLE_GREY_40.kotlinComposeVal()}
${MaterialColor.PINK_40.kotlinComposeVal()}
"""