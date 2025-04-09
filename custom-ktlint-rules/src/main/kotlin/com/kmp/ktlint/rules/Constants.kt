package com.kmp.ktlint.rules

class Constants {
    companion object {
        const val TODO = "TODO"
        const val TODO_RULE_ID = "custom:todo-rule"
        const val TODO_RULE_DESCRIPTION = "TODO comments are not allowed."
        const val MAGIC_NUMBERS_RULE_ID = "custom:magic-numbers-rule"
        const val MAGIC_NUMBERS_RULE_DESCRIPTION =
            "Magic numbers are not allowed. Use constants instead."
        const val RAW_STRING_RULE_ID = "custom:raw-string-rule"
        const val RAW_STRING_RULE_DESCRIPTION = "Avoid using raw string literals. Use const val instead."
        const val CUSTOM_RULES_GROUP = "custom-ktlint-rules"
    }
}