package kmp.ktlint

import com.kmp.ktlint.rules.Constants
import com.kmp.ktlint.rules.TodoRule
import com.pinterest.ktlint.test.KtLintAssertThat.Companion.assertThatRule
import kotlin.test.Test

class TodoRuleTest {
    private val wrappingRuleAssertThat = assertThatRule { TodoRule() }

    @Test
    fun `should report error when TODO is present in an end-of-line comment`() {
        val code =
            """
            // This is a regular comment
            // TODO: fix the issue
            val x = 42
            """.trimIndent()
        val line = 2
        val column = 1
        wrappingRuleAssertThat(code)
            .hasLintViolationWithoutAutoCorrect(line, column, Constants.TODO_RULE_DESCRIPTION)
    }
}
