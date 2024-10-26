package com.sakshamc.unescaper

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.LangDataKeys
import com.intellij.openapi.command.WriteCommandAction
import org.apache.commons.lang.StringEscapeUtils

class UnescapeHereAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project
        val editor = e.getData(LangDataKeys.EDITOR)
        val document = editor?.document
        val sourceCodeString = document?.text
        WriteCommandAction.runWriteCommandAction(
            project
        ) {
            var formattedStr = ""
            if (sourceCodeString != null && project != null) {
                formattedStr = StringEscapeUtils.unescapeJava(sourceCodeString)

                document.replaceString(
                    0,
                    sourceCodeString.length,
                    formattedStr
                )

            }
        }
    }
}