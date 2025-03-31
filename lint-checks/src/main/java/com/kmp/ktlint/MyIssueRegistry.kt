package com.kmp.ktlint

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.Issue

class MyIssueRegistry : IssueRegistry() {
    override val issues: List<Issue> =
        listOf(
            UnresolvedComponentDetector.ISSUE,
        )
    override val api = 11
}
