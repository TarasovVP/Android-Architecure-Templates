package com.kmp.ktlint.rules

class Constants {
    companion object {
        const val TODO = "TODO"
        const val TODO_RULE_ID = "custom:todo-rule"
        const val MAGIC_NUMBERS_RULE_ID = "custom:magic-numbers-rule"
        const val TODO_RULE_DESCRIPTION = "TODO comments are not allowed."
        const val MAGIC_NUMBERS_RULE_DESCRIPTION =
            "Magic numbers are not allowed. Use constants instead."
        const val CUSTOM_RULES_GROUP = "custom-ktlint-rules"
    }
}