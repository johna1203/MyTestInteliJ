package com.kodokux.magedox.vfs;

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.fileTypes.FileTypeManager;
import com.intellij.openapi.fileTypes.StdFileTypes;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.*;
import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created with IntelliJ IDEA.
 * User: johna
 * Date: 13/04/15
 * Time: 23:54
 * To change this template use File | Settings | File Templates.
 */
public class MagedoxVfs implements ProjectComponent {
    private final Project myProject;

    private static int ourJavaFilesCount;

    public MagedoxVfs(Project project) {
        myProject = project;
    }

    @Override
    public void projectOpened() {
        System.out.println("projectOpened");
        ProjectRootManager projectRootManager = ProjectRootManager.getInstance(myProject);
        VirtualFile[] sourceRoots = projectRootManager.getContentSourceRoots();
        ourJavaFilesCount = 0;
        // Count the current number of Java files in the opened project.
        for (VirtualFile sourceRoot : sourceRoots) {
            countJavaFiles(sourceRoot);
        }
        // Add the Virtual File listener
        MyVfsListener vfsListener = new MyVfsListener();
        VirtualFileManager.getInstance().addVirtualFileListener(vfsListener, myProject);

        VirtualFile dir = myProject.getBaseDir();
        System.out.println(dir.getFileType());
    }

    @Override
    public void projectClosed() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void initComponent() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void disposeComponent() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    @Override
    public String getComponentName() {
        return "MagedoxVfs";
    }

    private static boolean updateCount(VirtualFile file, int increase) {
        FileTypeManager fileTypeManager = FileTypeManager.getInstance();
        System.out.println(file.getFileType());
        if (!fileTypeManager.isFileIgnored(file)
                && file.getFileType() == StdFileTypes.JAVA) {
            ourJavaFilesCount += increase;
            return true;
            //System.out.println("ourJavaFilesCount = " + ourJavaFilesCount);
        }
        return false;
    }

    private static void countJavaFiles(VirtualFile virtualFile) {
        VirtualFile[] children = virtualFile.getChildren();
        if (children == null) return;

        for (VirtualFile child : children) {
            updateCount(child, +1);
            countJavaFiles(child);
        }
    }

    // -------------------------------------------------------------------------
    // MyVfsListener
    // -------------------------------------------------------------------------

    private static class MyVfsListener extends VirtualFileAdapter {

        @Override
        public void beforeFileMovement(VirtualFileMoveEvent event) {
            VirtualFile file = event.getFile();




        }

        public void fileCreated(VirtualFileEvent event) {
            VirtualFile file = event.getFile();
            System.out.println("fileCreated : " + file.getFileType().getName());
            System.out.println("fileCreated : " + file.getName());
//            if (updateCount(event.getFile(), +1)) {
//                Messages.showMessageDialog("A new Java file added. Now " + String.valueOf(ourJavaFilesCount) +
//                        " Java files in this project.", "Notification", Messages.getInformationIcon());
//            }
        }

        public void fileDeleted(VirtualFileEvent event) {
            VirtualFile file = event.getFile();
            System.out.println("fileDeleted : " + file.getFileType().getName());
            System.out.println("fileDeleted : " + file.getName());
//            if (updateCount(event.getFile(), -1)) {
//                Messages.showMessageDialog("A Java file deleted. Now " + String.valueOf(ourJavaFilesCount) +
//                        " Java files in this project.", "Notification", Messages.getInformationIcon());
//            }
        }
    }

}
