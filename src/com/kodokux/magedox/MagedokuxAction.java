package com.kodokux.magedox;

import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.actionSystem.impl.ActionManagerImpl;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.ListPopup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: johna
 * Date: 13/04/15
 * Time: 23:13
 * To change this template use File | Settings | File Templates.
 */
public class MagedokuxAction extends AnAction {

    public MagedokuxAction() {
        super("Text _Boxes");
    }

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
//        Project project = anActionEvent.getData(PlatformDataKeys.PROJECT);
//        String txt= Messages.showInputDialog(project, "What is your name?", "Input your name", Messages.getQuestionIcon());
//        Messages.showMessageDialog(project, "Oi, " + txt + "!\n Magedox test.", "Informação", Messages.getInformationIcon());

        DefaultActionGroup actionGroup = new DefaultActionGroup();
        List<AnAction> actions = _getMagentoContextActions();
        for (AnAction action : actions) {
            actionGroup.add(action);
        }
        final ListPopup popup =
                JBPopupFactory.getInstance().createActionGroupPopup(
                        "Magedox",
                        actionGroup,
                        anActionEvent.getDataContext(),
                        JBPopupFactory.ActionSelectionAid.SPEEDSEARCH,
                        false);

        popup.showInBestPositionFor(anActionEvent.getDataContext());
    }

    protected List<AnAction> _getMagentoContextActions() {
        List<AnAction> actions = new ArrayList<AnAction>();
        String[] actionIds = {
            "MageDbAcessAction",
            "GetAtributesAction"
        };
        ActionManager actionManager = ActionManagerImpl.getInstance();
        for (String actionId : actionIds) {
            AnAction action = actionManager.getAction(actionId);
            actions.add(action);
        }
        return actions;
    }
}
