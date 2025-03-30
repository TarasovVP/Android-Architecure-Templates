package com.example.lint

import com.android.SdkConstants.ANDROID_URI
import com.android.tools.lint.detector.api.*
import com.intellij.psi.PsiClass
import org.w3c.dom.Element
import com.android.tools.lint.detector.api.resolveManifestName

class UnresolvedComponentDetector : Detector(), XmlScanner {

    override fun getApplicableElements(): Collection<String> =
        listOf("activity", "service", "receiver", "provider")

    override fun visitElement(context: XmlContext, element: Element) {
        val className = element.getAttributeNS(ANDROID_URI, "name") ?: return
        if (className.isBlank()) return

        val fqcn = resolveManifestName(element, context.project)

        val parser = context.client.getUastParser(context.project)
        if (!parser.prepared) {
            return
        }
        val evaluator = parser.evaluator
        val psiClass: PsiClass? = evaluator.findClass(fqcn)
        if (psiClass == null) {
            context.report(
                ISSUE,
                element,
                context.getLocation(element),
                "Class $fqcn not found in the project. It may have been removed."
            )
        }
    }

    companion object {
        val ISSUE = Issue.create(
            id = "UnresolvedManifestClass",
            briefDescription = "Unexpected class in AndroidManifest.xml",
            explanation = "The component in the manifest refers to a non-existent class.",
            category = Category.CORRECTNESS,
            priority = 6,
            severity = Severity.ERROR,
            implementation = Implementation(
                UnresolvedComponentDetector::class.java,
                Scope.MANIFEST_SCOPE
            )
        )
    }
}