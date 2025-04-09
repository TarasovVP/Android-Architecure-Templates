package com.kmp.ktlint.rules

import com.pinterest.ktlint.rule.engine.core.api.ElementType
import com.pinterest.ktlint.rule.engine.core.api.Rule
import com.pinterest.ktlint.rule.engine.core.api.RuleId
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.KtAnnotationEntry
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.psi.KtStringTemplateExpression
import org.jetbrains.kotlin.psi.psiUtil.getParentOfType

class StringLiteralRule : Rule(RuleId(Constants.RAW_STRING_RULE_ID), About()) {

    override fun beforeVisitChildNodes(
        node: ASTNode,
        autoCorrect: Boolean,
        emit: (offset: Int, errorMessage: String, canBeAutoCorrected: Boolean) -> Unit
    ) {
        if (node.elementType == ElementType.STRING_TEMPLATE) {
            val text = node.text
            
            if (text == "\"\"" || text == "\"\"\"\"\"\"") {
                return
            }

            val property = node.psi.getParentOfType<KtProperty>(false)
            val isConstProperty = property?.hasModifier(KtTokens.CONST_KEYWORD) == true

            if (isConstProperty) {
                return
            }

            val psi = node.psi as? KtStringTemplateExpression ?: return
            if (psi.entries.any { it.expression != null }) {
                return
            }

            val annotationEntry = psi.getParentOfType<KtAnnotationEntry>(true)
            if (annotationEntry != null) {
                return
            }

            emit(
                node.startOffset,
                Constants.RAW_STRING_RULE_DESCRIPTION,
                false
            )
        }
    }
}