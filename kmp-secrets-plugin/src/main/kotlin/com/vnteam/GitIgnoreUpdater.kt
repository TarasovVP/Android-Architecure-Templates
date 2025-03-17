package com.vnteam

import org.gradle.api.Project
import java.io.File

class GitIgnoreUpdater(private val project: Project) {

    fun addToGitIgnore(configFile: File) {
        val gitIgnoreFile = project.file(Constants.GITIGNORE_FILE)
        val relativePath = configFile.relativeTo(project.projectDir).path.replace("\\", "/")
        if (!gitIgnoreFile.exists()) {
            gitIgnoreFile.writeText("${Constants.GITIGNORE_AUTO_GENERATED}\n$relativePath\n")
        } else {
            val gitIgnoreContent = gitIgnoreFile.readText()
            if (!gitIgnoreContent.contains(relativePath)) {
                gitIgnoreFile.appendText("\n$relativePath\n")
            }
        }
    }
}
