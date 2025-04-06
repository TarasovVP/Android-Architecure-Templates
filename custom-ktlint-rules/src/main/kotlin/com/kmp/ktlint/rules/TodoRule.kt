package com.kmp.ktlint.rules

import com.pinterest.ktlint.rule.engine.core.api.ElementType
import com.pinterest.ktlint.rule.engine.core.api.Rule
import com.pinterest.ktlint.rule.engine.core.api.RuleId
import org.jetbrains.kotlin.com.intellij.lang.ASTNode

class TodoRule : Rule(RuleId("custom:todo-rule"), About()) {
    override fun beforeVisitChildNodes(
        node: ASTNode,
        autoCorrect: Boolean,
        emit: (offset: Int, errorMessage: String, canBeAutoCorrected: Boolean) -> Unit
    ) {
        if (node.elementType == ElementType.EOL_COMMENT) {
            val text = node.text
            if (text.contains("TODO")) {
                emit(
                    node.startOffset,
                    "Found a TODO comment. Please remove or fix it.",
                    false
                )
            }
        }
    }
}