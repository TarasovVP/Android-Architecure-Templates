package com.kmp.ktlint

import com.github.shyiko.ktlint.core.RuleSet
import com.github.shyiko.ktlint.core.RuleSetProvider
import com.kmp.ktlint.rules.NoTodoCommentsRule

class CustomRuleSetProvider : RuleSetProvider {
    override fun get() = RuleSet("custom-ktlint-rules", NoTodoCommentsRule())
}
