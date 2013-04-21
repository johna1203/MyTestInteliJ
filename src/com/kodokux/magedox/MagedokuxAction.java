package com.kodokux.magedox;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.actionSystem.impl.ActionManagerImpl;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.StoragePathMacros;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.ListPopup;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 * Created with IntelliJ IDEA.
 * User: johna
 * Date: 13/04/15
 * Time: 23:13
 * To change this template use File | SettingsForm | File Templates.
 */
public class MagedokuxAction extends AnAction {
    public MagedokuxAction() {
        super("Text _Boxes");
    }

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {

        Project project = anActionEvent.getData(PlatformDataKeys.PROJECT);
        final DataContext dataContext = anActionEvent.getDataContext();
        final PsiFile psiFile = LangDataKeys.PSI_FILE.getData(dataContext);

        ActionManager actionManager = ActionManager.getInstance();
        DefaultActionGroup actionGroup = new DefaultActionGroup();


        actionGroup.add(_getAdminContextActions());
        actionGroup.add(_getCacheContextActions());
        actionGroup.add(_getCmsContextActions());
        actionGroup.add(_getConfigContextActions());
        actionGroup.add(_getCustomerContextActions());
        actionGroup.add(_getDbContextActions());
        actionGroup.add(_getDesignContextActions());
        actionGroup.add(_getDevContextActions());
        actionGroup.add(_getExtensionContextActions());
        actionGroup.add(_getIndexContextActions());
        actionGroup.add(_getSysContextActions());
        actionGroup.add(actionManager.getAction("magerun_local-config_generate"));

        final ListPopup popup = JBPopupFactory.getInstance()
                .createActionGroupPopup(
                        "Magedox",
                        actionGroup,
                        anActionEvent.getDataContext(),
                        JBPopupFactory.ActionSelectionAid.ALPHA_NUMBERING,
                        false
                );

        popup.showInBestPositionFor(anActionEvent.getDataContext());
    }

    protected DefaultActionGroup _getAdminContextActions() {
        final DefaultActionGroup group = new DefaultActionGroup("_Admin", true);
        String[] baseAction = {
                "magedox_admin_notifications_action",
                "magedox_admin_user_change-password_action",
                "magedox_admin_user_create_action",
                "magedox_admin_list_action"
        };
        ActionManager actionManager = ActionManager.getInstance();
        for (String actionId : baseAction) {
            AnAction action = actionManager.getAction(actionId);
            group.add(action);
        }
        return group;
    }

    protected DefaultActionGroup _getCacheContextActions() {
        final DefaultActionGroup group = new DefaultActionGroup("_Cache", true);
        String[] baseAction = {
                "magedox_cache_clean_action",
                "magedox_cache_disable_action",
                "magedox_cache_enable_action",
                "magedox_cache_flush_action",
                "magedox_cache_list_action"
        };
        ActionManager actionManager = ActionManager.getInstance();
        for (String actionId : baseAction) {
            AnAction action = actionManager.getAction(actionId);
            group.add(action);

        }
        return group;
    }

    protected DefaultActionGroup _getCmsContextActions() {
        final DefaultActionGroup group = new DefaultActionGroup("_Cms", true);
        String[] baseAction = {
                "magedox_cms_banner_toggle",
                "magedox_cms_page_publish"
        };
        ActionManager actionManager = ActionManager.getInstance();
        for (String actionId : baseAction) {
            AnAction action = actionManager.getAction(actionId);
            group.add(action);

        }
        return group;
    }

    protected DefaultActionGroup _getConfigContextActions() {
        final DefaultActionGroup group = new DefaultActionGroup("_Config", true);
        String[] baseAction = {
                "magedox_config_dump",
                "magedox_config_get",
                "magedox_config_set"
        };
        ActionManager actionManager = ActionManager.getInstance();
        for (String actionId : baseAction) {
            AnAction action = actionManager.getAction(actionId);
            group.add(action);

        }
        return group;
    }

    protected DefaultActionGroup _getCustomerContextActions() {
        final DefaultActionGroup group = new DefaultActionGroup("_Customer", true);
        String[] baseAction = {
                "magedox_customer_change-password",
                "magedox_customer_create",
                "magedox_customer_create_dummy",
                "magedox_customer_info",
                "magedox_customer_list"
        };
        ActionManager actionManager = ActionManager.getInstance();
        for (String actionId : baseAction) {
            AnAction action = actionManager.getAction(actionId);
            group.add(action);

        }
        return group;
    }

    protected DefaultActionGroup _getDbContextActions() {
        final DefaultActionGroup group = new DefaultActionGroup("_Db", true);
        String[] baseAction = {
                "magedox_db_console",
                "magedox_db_drop",
                "magedox_db_dump",
                "magedox_db_import",
                "magedox_db_info",
                "magedox_db_query"
        };
        ActionManager actionManager = ActionManager.getInstance();
        for (String actionId : baseAction) {
            AnAction action = actionManager.getAction(actionId);
            group.add(action);

        }
        return group;
    }

    protected DefaultActionGroup _getDesignContextActions() {
        final DefaultActionGroup group = new DefaultActionGroup("_Design", true);
        String[] baseAction = {
                "magedox_design_demo-notice"
        };
        ActionManager actionManager = ActionManager.getInstance();
        for (String actionId : baseAction) {
            AnAction action = actionManager.getAction(actionId);
            group.add(action);

        }
        return group;
    }

    protected DefaultActionGroup _getDevContextActions() {
        final DefaultActionGroup group = new DefaultActionGroup("_Dev", true);
        String[] baseAction = {
                "magedox_dev_console",
                "magedox_dev_ide_phpstorm_meta",
                "magedox_dev_log",
                "magedox_dev_log_db",
                "magedox_dev_log_size",
                "magedox_dev_module_create",
                "magedox_dev_module_list",
                "magedox_dev_module_observer_list",
                "magedox_dev_module_rewrite_conflicts",
                "magedox_dev_module_rewrite_list",
                "magedox_dev_profiler",
                "magedox_dev_report_count",
                "magedox_dev_symlinks",
                "magedox_dev_template-hints",
                "magedox_dev_template-hints-blocks",
                "magedox_dev_theme_duplicates",
                "magedox_dev_theme_list",
                "magedox_dev_translate_admin",
                "magedox_dev_translate_shop"
        };
        ActionManager actionManager = ActionManager.getInstance();
        for (String actionId : baseAction) {
            AnAction action = actionManager.getAction(actionId);
            group.add(action);

        }
        return group;
    }

    protected DefaultActionGroup _getExtensionContextActions() {
        final DefaultActionGroup group = new DefaultActionGroup("_Extesion", true);
        String[] baseAction = {
                "magedox_extension_download",
                "magedox_extension_install",
                "magedox_extension_list",
                "magedox_extension_search",
                "magedox_extension_upgrade"
        };
        ActionManager actionManager = ActionManager.getInstance();
        for (String actionId : baseAction) {
            AnAction action = actionManager.getAction(actionId);
            group.add(action);

        }
        return group;
    }

    protected DefaultActionGroup _getIndexContextActions() {
        final DefaultActionGroup group = new DefaultActionGroup("_Index", true);
        String[] baseAction = {
                "magedox_index_list",
                "magedox_index_reindex",
                "magedox_index_reindex_all"
        };
        ActionManager actionManager = ActionManager.getInstance();
        for (String actionId : baseAction) {
            AnAction action = actionManager.getAction(actionId);
            group.add(action);

        }
        return group;
    }

    protected DefaultActionGroup _getSysContextActions() {
        final DefaultActionGroup group = new DefaultActionGroup("_Sys", true);
        String[] baseAction = {
                "magedox_sys_check",
                "magedox_sys_cron_history",
                "magedox_sys_cron_list",
                "magedox_sys_cron_run",
                "magedox_sys_info",
                "magedox_sys_maintenance",
                "magedox_sys_modules_list",
                "magedox_sys_setup_compare-versions",
                "magedox_sys_setup_run",
                "magedox_sys_store_config_base-url_list",
                "magedox_sys_store_list",
                "magedox_sys_url_list",
                "magedox_sys_website_list"
        };
        ActionManager actionManager = ActionManager.getInstance();
        for (String actionId : baseAction) {
            AnAction action = actionManager.getAction(actionId);
            group.add(action);

        }
        return group;
    }
}
