package com.kmp.ktlint

import com.github.shyiko.ktlint.core.Rule
import org.jetbrains.kotlin.com.intellij.lang.ASTNode

class NoTodoCommentsRule : Rule("no-todo-comments") {
    override fun visit(
        node: ASTNode,
        autoCorrect: Boolean,
        emit: (offset: Int, errorMessage: String, canBeAutoCorrected: Boolean) -> Unit
    ) {
        if (node.elementType.toString() == "LINE_COMMENT" || node.elementType.toString() == "BLOCK_COMMENT") {
            if (node.text.contains("TODO")) {
                emit(node.startOffset, "TODO comments are not allowed.", false)
            }
        }
    }
}