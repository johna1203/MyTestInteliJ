package com.kodokux.magedox.util;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.kodokux.magedox.MagedoxIcon;

/**
 * Created with IntelliJ IDEA.
 * User: johna
 * Date: 13/04/21
 * Time: 14:04
 * To change this template use File | Settings | File Templates.
 */
public class IdeaIDE {
    public static void showDialog(Project project, String message) {
        Messages.showMessageDialog(project, message, "Magedox", MagedoxIcon.MAGEDOX_ICON_32x32);
    }
}
