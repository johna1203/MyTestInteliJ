package com.kodokux.magedox.action;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.ScriptRunnerUtil;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.project.Project;

/**
 * Created with IntelliJ IDEA.
 * User: johna
 * Date: 13/04/16
 * Time: 2:46
 * To change this template use File | SettingsForm | File Templates.
 */
public class MageGetAtributesAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        Presentation presentation = anActionEvent.getPresentation();
        DataContext dataContext = anActionEvent.getDataContext();
        Project project = PlatformDataKeys.PROJECT.getData(dataContext);
        String projectPath = project.getBaseDir().getPath();

        String command = "chdir('" + projectPath + "/app');require_once 'Mage.php';Mage::app();";
        command += " $product = Mage::getModel(\"catalog/product\"); " +
                "$attributes = $product->getAttributes();" +
                "foreach ($attributes as $attribute) { echo $attribute->getAttributeCode() . \"\n\"; }";
        GeneralCommandLine commandLine = new GeneralCommandLine("/usr/bin/php", "-r", command);
        try {
            String output = ScriptRunnerUtil.getProcessOutput(commandLine);
            System.out.println(output);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
