package com.sakshamc.unescaper

import com.intellij.ide.scratch.ScratchRootType
import com.intellij.lang.Language
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.LangDataKeys
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.OpenFileDescriptor
import com.intellij.openapi.project.Project
import org.apache.commons.lang.StringEscapeUtils


class UnescapeScratchAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project
        val editor = e.getData(LangDataKeys.EDITOR)
        val document = editor?.document
        val sourceCodeString = document?.text
        val caretModel = editor!!.caretModel
        val selectedText = caretModel.currentCaret.selectedText
        WriteCommandAction.runWriteCommandAction(
            project
        ) {
            var formattedStr = ""
            if (selectedText != null && project != null) {
                formattedStr = StringEscapeUtils.unescapeJava(selectedText)
            } else if (sourceCodeString != null && project != null) {
                formattedStr = StringEscapeUtils.unescapeJava(sourceCodeString)
            }
            if (formattedStr != "" && project != null)
                openFile(formattedStr, project)
        }
    }


    private fun openFile(text: String, project: Project) {
        val file = ScratchRootType.getInstance().createScratchFile(project, "Unescaped Content", Language.ANY, text)
        if (file != null)
            FileEditorManager.getInstance(project).openTextEditor(
                OpenFileDescriptor(
                    project,
                    file
                ),
                true
            )
    }

}