package kmp.ktlint

import com.github.shyiko.ktlint.core.LintError
import com.github.shyiko.ktlint.test.lint
import com.kmp.ktlint.NoTodoCommentsRule
import kotlin.test.Test
import kotlin.test.assertContentEquals

class NoTodoCommentsRuleTest {
    @Test
    fun shouldReportErrorForTodoComments() {
        val code = """
            // This is a regular comment
            // TODO: fix the error
            /* TODO: remove unused code */
        """.trimIndent()

        val expectedErrors = listOf(
            LintError(26, 1, "no-todo-comments", "TODO comments are not allowed."),
            LintError(52, 1, "no-todo-comments", "TODO comments are not allowed.")
        )
        val actualErrors = NoTodoCommentsRule().lint(code)
        assertContentEquals(expectedErrors, actualErrors)
    }
}