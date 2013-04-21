package com.kodokux.magedox.ui;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import com.kodokux.magedox.MagedoxIcon;
import com.kodokux.magedox.config.MagedoxSettings;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: johna
 * Date: 13/04/20
 * Time: 17:55
 * To change this template use File | SettingsForm | File Templates.
 */
public class SettingsForm implements Configurable, ActionListener {
    private final Project project;
    private JTextField phppath;
    private JLabel PHPPathLabel;
    private JPanel magedoxPanel;
    private JTextField labelTextField;

    public SettingsForm(@NotNull final Project currentProject) {
        project = currentProject;
        System.out.println("SettingsForm");
        phppath.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Nls
    @Override
    public String getDisplayName() {
        return "Magedox";
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return null;
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        MagedoxSettings magedoxSettings = MagedoxSettings.getInstance(project);
        if (magedoxSettings != null) {
            phppath.setText(magedoxSettings.pathTophp);
        } else {
            System.out.println("null createComponent");
        }
        return (JComponent) magedoxPanel;
    }

    @Override
    public boolean isModified() {
        MagedoxSettings magedoxSettings = MagedoxSettings.getInstance(project);
        if (magedoxSettings != null) {
            File filePhpCommand = new File(phppath.getText());
            if (filePhpCommand.exists()) {
                if (!magedoxSettings.pathTophp.equals(phppath.getText())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void apply() throws ConfigurationException {
        MagedoxSettings magedoxSettings = MagedoxSettings.getInstance(project);
        if (magedoxSettings != null) {
            magedoxSettings.pathTophp = phppath.getText();
        }
    }

    @Override
    public void reset() {
        //To change body of implemented methods use File | SettingsForm | File Templates.
    }

    @Override
    public void disposeUIResources() {
        //To change body of implemented methods use File | SettingsForm | File Templates.
    }


}
