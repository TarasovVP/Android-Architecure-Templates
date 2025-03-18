package com.vnteam

import org.gradle.api.Project
import java.io.File
import java.util.Properties

class SecretsGenerator(private val project: Project, private val extension: SecretsPluginExtension) {

    fun generateSecrets(): File {
        val propertiesFile = findPropertiesFile()
        val properties = loadProperties(propertiesFile)

        val kotlinSrcDir = File(extension.outputDir)
        val configDir = File("$kotlinSrcDir/${Constants.SECRETS_PACKAGE_NAME}")
        val configFile = File("$configDir/${Constants.SECRETS_FILE_NAME}")

        if (!configDir.exists()) {
            configDir.mkdirs()
        }

        val configContent = generateSecretsContent(configDir, properties)
        configFile.writeText(configContent)
        return configFile
    }

    private fun findPropertiesFile(): File {
        val modulePropertiesFile = project.file(Constants.LOCAL_PROPERTIES_FILE)
        val globalPropertiesFile = project.rootProject.file(Constants.LOCAL_PROPERTIES_FILE)

        return when {
            modulePropertiesFile.exists() -> modulePropertiesFile
            globalPropertiesFile.exists() -> globalPropertiesFile
            else -> throw RuntimeException(Constants.ERROR_NO_PROPERTIES_FOUND + project.name)
        }
    }

    private fun loadProperties(propertiesFile: File): Properties {
        if (!propertiesFile.exists()) {
            throw RuntimeException(Constants.ERROR_LOCAL_PROPERTIES_NOT_FOUND + propertiesFile.absolutePath)
        }

        return Properties().apply {
            load(propertiesFile.inputStream())
        }
    }

    private fun generateSecretsContent(configDir: File, properties: Properties): String {
        val packagePath = configDir.relativeTo(File(extension.outputDir))
        val packageName = packagePath.toString().replace("/", ".").replace("\\", ".")

        return buildString {
            appendLine("${Constants.SECRETS_PACKAGE} $packageName")
            appendLine()
            appendLine("${Constants.SECRETS_OBJECT} ${Constants.SECRETS_OBJECT_NAME} {")

            properties.forEach { (keyAny, value) ->
                val key = keyAny.toString()
                if (isValidKey(key)) {
                    appendLine("    val $key = \"$value\"")
                }
            }

            appendLine("}")
        }
    }

    private fun isValidKey(key: String): Boolean {
        return key.matches(Regex("^[a-zA-Z_][a-zA-Z0-9_]*$"))
    }
}
