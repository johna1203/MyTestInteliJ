package com.kodokux.magedox.action;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.ScriptRunnerUtil;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.util.indexing.FileBasedIndex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: johna
 * Date: 13/04/16
 * Time: 1:14
 * To change this template use File | Settings | File Templates.
 */
public class MageDbAcessAction extends AnAction {

    public MageDbAcessAction() {
        super("MageDbAcessAction");
    }

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        Presentation presentation = anActionEvent.getPresentation();
        DataContext dataContext = anActionEvent.getDataContext();
        Project project = PlatformDataKeys.PROJECT.getData(dataContext);
        Editor editor = PlatformDataKeys.EDITOR.getData(dataContext);
        StatusBar statusBar = WindowManager.getInstance().getStatusBar(project);
    }
}
