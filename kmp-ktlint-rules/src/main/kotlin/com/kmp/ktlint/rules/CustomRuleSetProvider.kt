package com.kmp.ktlint.rules

import com.github.shyiko.ktlint.core.RuleSetProvider


class CustomRuleSetProvider : RuleSetProvider {
    override fun get() = RuleSet("custom-ktlint-rules", NoTodoCommentsRule())
}