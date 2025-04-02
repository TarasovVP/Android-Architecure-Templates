package com.kmp.ktlint.rules

import com.github.shyiko.ktlint.core.Rule
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.lexer.KtTokens

class NoTodoCommentsRule : Rule("no-todo-comments") {
    override fun visit(
        node: ASTNode,
        autoCorrect: Boolean,
        emit: (offset: Int, errorMessage: String, canBeAutoCorrected: Boolean) -> Unit,
    ) {
        if (node.elementType == KtTokens.EOL_COMMENT || node.elementType == KtTokens.BLOCK_COMMENT) {
            if (node.text.contains("TODO")) {
                emit(node.startOffset, "TODO comments are not allowed.", false)
            }
        }
    }
}
