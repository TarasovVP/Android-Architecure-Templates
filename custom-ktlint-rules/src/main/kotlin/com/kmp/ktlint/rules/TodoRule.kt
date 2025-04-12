package com.kmp.ktlint.rules

import com.pinterest.ktlint.rule.engine.core.api.ElementType
import com.pinterest.ktlint.rule.engine.core.api.Rule
import com.pinterest.ktlint.rule.engine.core.api.RuleId
import org.jetbrains.kotlin.com.intellij.lang.ASTNode

class TodoRule : Rule(RuleId(Constants.TODO_RULE_ID), About()) {
    override fun beforeVisitChildNodes(
        node: ASTNode,
        autoCorrect: Boolean,
        emit: (offset: Int, errorMessage: String, canBeAutoCorrected: Boolean) -> Unit,
    ) {
        if (node.elementType == ElementType.EOL_COMMENT) {
            val text = node.text
            if (text.contains(Constants.TODO, ignoreCase = true)) {
                emit(
                    node.startOffset,
                    Constants.TODO_RULE_DESCRIPTION,
                    false,
                )
            }
        }
    }
}
