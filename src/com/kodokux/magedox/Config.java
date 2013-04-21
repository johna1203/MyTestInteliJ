package com.kodokux.magedox;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.StoragePathMacros;
import org.jetbrains.annotations.Nullable;

/**
 * Created with IntelliJ IDEA.
 * User: johna
 * Date: 13/04/17
 * Time: 2:48
 * To change this template use File | SettingsForm | File Templates.
 */
@State(
        name = "Config",
        storages = {@Storage(
                file = StoragePathMacros.APP_CONFIG + "/menus.xml")}
)
public class Config implements PersistentStateComponent<Config>{

    @Nullable
    @Override
    public Config getState() {
        return null;  //To change body of implemented methods use File | SettingsForm | File Templates.
    }

    @Override
    public void loadState(Config config) {
        //To change body of implemented methods use File | SettingsForm | File Templates.
    }
}
