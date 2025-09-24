package com.github.geletinousamigo.jetpacktemplatesplugin


import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider
import com.github.geletinousamigo.jetpacktemplatesplugin.activities.composeWebViewActivity.webViewTemplate

class MyProjectTemplatesProvider : WizardTemplateProvider() {

    override fun getTemplates(): List<Template> {
        return listOf(webViewTemplate)
    }
}