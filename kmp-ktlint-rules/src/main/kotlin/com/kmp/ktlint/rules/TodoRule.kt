package com.kmp.ktlint.rules

import com.pinterest.ktlint.core.Rule
import com.pinterest.ktlint.core.ast.ElementType
import org.jetbrains.kotlin.com.intellij.lang.ASTNode

class TodoRule : Rule("todo-rule") {
    override fun beforeVisitChildNodes(
        node: ASTNode,
        autoCorrect: Boolean,
        emit: (offset: Int, errorMessage: String, canBeAutoCorrected: Boolean) -> Unit
    ) {
        // We check COMMENT nodes
        if (node.elementType == ElementType.EOL_COMMENT) {
            val text = node.text
            if (text.contains("TODO")) {
                // If we find "TODO", we report it
                emit(
                    node.startOffset,
                    "Found a TODO comment. Please remove or fix it.",
                    false
                )
            }
        }
    }
}