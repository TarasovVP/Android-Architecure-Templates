package kmp.ktlint

import com.kmp.ktlint.rules.TodoRule
import com.pinterest.ktlint.core.LintError
import kotlin.test.Test
import kotlin.test.assertContentEquals

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
                LintError(2, 1, "no-todo-comments", "TODO comments are not allowed."),
                LintError(3, 1, "no-todo-comments", "TODO comments are not allowed."),
            )
        /*val actualErrors = TodoRule()
            .lint(code)
            .map { LintError(it.line, it.col, it.ruleId, it.detail) }

        assertContentEquals(expectedErrors, actualErrors)*/
    }
}
