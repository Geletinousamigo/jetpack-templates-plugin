package com.github.geletinousamigo.jetpacktemplatesplugin.activities.composeWebViewActivity.res.values


fun themesXml(themeName: String): String = """
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <style name="$themeName" parent="android:Theme.Material.Light.NoActionBar" />
</resources>
"""