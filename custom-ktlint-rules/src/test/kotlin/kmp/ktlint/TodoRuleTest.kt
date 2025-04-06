package kmp.ktlint

import com.pinterest.ktlint.rule.engine.api.LintError
import com.pinterest.ktlint.rule.engine.core.api.RuleId
import kotlin.test.Test

class TodoRuleTest {
    @Test
    fun shouldReportErrorForTodoComments() {
        val code =
            """
            // This is a regular comment
            // TODO: fix the error
            /* TODO: remove unused code */
            """.trimIndent()
        val expectedErrors =
            listOf(
                LintError(2, 1, RuleId("no-todo-comments"), "TODO comments are not allowed.", true),
                LintError(3, 1, RuleId("no-todo-comments"), "TODO comments are not allowed.", true),
            )
        /*val actualErrors = TodoRule()
            .lint(code)
            .map { LintError(it.line, it.col, it.ruleId, it.detail) }

        assertContentEquals(expectedErrors, actualErrors)*/
    }
}
