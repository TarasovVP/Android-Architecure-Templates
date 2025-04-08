package com.kmp.ktlint.rules

import com.pinterest.ktlint.rule.engine.core.api.ElementType
import com.pinterest.ktlint.rule.engine.core.api.Rule
import com.pinterest.ktlint.rule.engine.core.api.RuleId
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.KtCallExpression
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.psi.psiUtil.getParentOfType

class MagicNumberRule : Rule(RuleId(Constants.MAGIC_NUMBERS_RULE_ID), About()) {

    private val allowedNumbers = setOf("0", "1", "-1")

    override fun beforeVisitChildNodes(
        node: ASTNode,
        autoCorrect: Boolean,
        emit: (offset: Int, errorMessage: String, canBeAutoCorrected: Boolean) -> Unit
    ) {
        if (node.elementType == ElementType.INTEGER_CONSTANT || node.elementType == ElementType.FLOAT_CONSTANT) {
            val text = node.text
            val property = node.psi.getParentOfType<KtProperty>(false)
            if (property != null && property.hasModifier(KtTokens.CONST_KEYWORD)) {
                return
            }

            val callExpression = node.psi.getParentOfType<KtCallExpression>(true)
            val isColorCall = callExpression?.calleeExpression?.text == "Color"

            val isHexColorLiteral = text.startsWith("0xFF", ignoreCase = true)

            if (isColorCall && isHexColorLiteral) {
                return
            }
            if (text !in allowedNumbers) {
                emit(
                    node.startOffset,
                    Constants.MAGIC_NUMBERS_RULE_DESCRIPTION,
                    false
                )
            }
        }
    }
}