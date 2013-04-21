package com.kodokux.magedox.magerun;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.process.ScriptRunnerUtil;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectFileIndex;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.kodokux.magedox.MagedoxIcon;
import com.kodokux.magedox.config.MagedoxSettings;
import com.kodokux.magedox.util.IdeaIDE;

import java.io.ByteArrayOutputStream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.swing.*;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: johna
 * Date: 13/04/17
 * Time: 2:58
 * To change this template use File | SettingsForm | File Templates.
 */
public class MageRunAbstract extends AnAction {

    private static final Logger LOG = Logger.getInstance(MageRunAbstract.class.getName());

    public MageRunAbstract() {
        super(MagedoxIcon.MAGEDOX_ICON_16x16);
    }

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        Project project = anActionEvent.getProject();

        MagedoxSettings magedoxSettings = MagedoxSettings.getInstance(project);
//       GeneralCommandLine commandLine = new GeneralCommandLine("/usr/bin/php", "-r", getClass().getResource("/"));
        String magerun = getClass().getResource("/php/magerun.phar").getPath();
        String projectPath = project.getBasePath();

        if (magedoxSettings.pathTophp.isEmpty()) {
            IdeaIDE.showDialog(project, "PHP path is empty!");
            return;
        }


        String command = "/usr/bin/cd /";
//
////        command += " $product = Mage::getModel(\"catalog/product\"); " +
////                "$attributes = $product->getAttributes();" +
////                "foreach ($attributes as $attribute) { echo $attribute->getAttributeCode() . \"\n\"; }";
//        System.out.println(command);
//        GeneralCommandLine commandLine = new GeneralCommandLine(command);
//
//        try {
//            String output = ScriptRunnerUtil.getProcessOutput(commandLine);
//            System.out.printf(output);
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

//        command = "ls";
//        String comm[] = {"bash", "-c" ,"/usr/bin/php " + projectPath + "/.idea/.kodokuxmagerun/magerun.phar"};
        String comm[] = {"bash", "-c" ,"/usr/bin/php " + magerun};

        ConsoleView myConsole = TextConsoleBuilderFactory.getInstance().createBuilder(project).getConsole();
        JComponent myConsoleComponent = myConsole.getComponent();
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(comm);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | SettingsForm | File Templates.
        }
        InputStream is = process.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
//標準出力
        String line = null;
        try {
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                myConsole.print(line, ConsoleViewContentType.SYSTEM_OUTPUT);
            }
            br.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | SettingsForm | File Templates.
        }


//エラー出力
        InputStream es = process.getErrorStream();
        br = new BufferedReader(new InputStreamReader(es));
        try {
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                myConsole.print(line, ConsoleViewContentType.SYSTEM_OUTPUT);
            }
            br.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | SettingsForm | File Templates.
        }
    }


    public class ExecCommand {
        private byte[] stdout;
        private byte[] stderr;
        private Integer exitCode;

        public ExecCommand(String command) {
            try {
                ByteArrayOutputStream out;
                ByteArrayOutputStream err;
                StreamGobbler outGobbler;
                StreamGobbler errGobbler;

                // コマンド実行プロセス開始
                Process proc = Runtime.getRuntime().exec(command);

                // 標準出力Streamの読み込みスレッド開始
                out = new ByteArrayOutputStream();
                outGobbler = new StreamGobbler(proc.getInputStream(), out);
                outGobbler.start();

                // エラー出力Streamの読み込みスレッド開始
                err = new ByteArrayOutputStream();
                errGobbler = new StreamGobbler(proc.getErrorStream(), err);
                errGobbler.start();

                // すべての処理が終わるまで待機
                this.exitCode = proc.waitFor();
                outGobbler.join();
                errGobbler.join();
                this.stdout = out.toByteArray();
                this.stderr = err.toByteArray();

                out.close();
                err.close();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }

        public byte[] getStdout() {
            return stdout;
        }

        public byte[] getStderr() {
            return stderr;
        }

        public Integer getExitCode() {
            return exitCode;
        }
    }


    class StreamGobbler extends Thread {
        InputStream is;
        OutputStream os;

        StreamGobbler(InputStream is, OutputStream redirect) {
            this.is = is;
            this.os = redirect;
        }

        public void run() {
            PrintWriter pw = new PrintWriter(os);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            try {
                while ((line = br.readLine()) != null) {
                    pw.println(line);
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            pw.flush();
        }
    }


}
