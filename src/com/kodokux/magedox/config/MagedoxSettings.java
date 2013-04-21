package com.kodokux.magedox.config;

import com.intellij.openapi.components.*;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.Nullable;

/**
 * Created with IntelliJ IDEA.
 * User: johna
 * Date: 13/04/20
 * Time: 18:14
 * To change this template use File | Settings | File Templates.
 */
@State(
    name = "MagedoxSettings",
    storages = {
        @Storage(id = "default", file = StoragePathMacros.PROJECT_FILE),
        @Storage(id = "dir", file = StoragePathMacros.PROJECT_CONFIG_DIR + "/magedox.xml", scheme = StorageScheme.DIRECTORY_BASED)
    }
)
public class MagedoxSettings implements PersistentStateComponent<MagedoxSettings> {

    public String pathTophp;

    public static MagedoxSettings getInstance(Project project) {
        return ServiceManager.getService(project, MagedoxSettings.class);
    }

    @Nullable
    @Override
    public MagedoxSettings getState() {
        System.out.println("getState()");
        return this;
    }

    @Override
    public void loadState(MagedoxSettings magedoxSettings) {
        System.out.println("loadState()");
        pathTophp = magedoxSettings.pathTophp;
    }
}
