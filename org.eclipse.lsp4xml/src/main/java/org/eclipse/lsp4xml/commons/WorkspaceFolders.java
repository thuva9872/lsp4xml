package org.eclipse.lsp4xml.commons;

import org.eclipse.lsp4j.DidChangeWorkspaceFoldersParams;
import org.eclipse.lsp4j.WorkspaceFolder;
import org.eclipse.lsp4j.WorkspaceFoldersChangeEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkspaceFolders {

    private static final WorkspaceFolders INSTANCE = new WorkspaceFolders();

    private List<WorkspaceFolder> xmlWorkspaceFolders;

    private Map<String, WorkspaceFolder> xmlWorkspaceFoldersNew;

    private WorkspaceFolders() {
        xmlWorkspaceFolders = new ArrayList<>();
        xmlWorkspaceFoldersNew = new HashMap<>();
    }

    public static WorkspaceFolders getInstance() {
        return INSTANCE;
    }

    public void addWorkspaceFolder(WorkspaceFolder workspaceFolder) {
        this.xmlWorkspaceFolders.add(workspaceFolder);
        this.xmlWorkspaceFoldersNew.put(workspaceFolder.getUri(), workspaceFolder);
    }

    public List<WorkspaceFolder> getWorkspaceFolders() {
        return this.xmlWorkspaceFolders;
    }

    public  void removeWorkspaceFolder(WorkspaceFolder workspaceFolder) {
        this.xmlWorkspaceFolders.remove(workspaceFolder);
        this.xmlWorkspaceFoldersNew.remove(workspaceFolder.getUri());
    }

    public void didChangeWorkspaceFolders(DidChangeWorkspaceFoldersParams params) {
        WorkspaceFoldersChangeEvent changeEvent = params.getEvent();

        if (changeEvent.getAdded().size() > 0) {
            for (int i = 0; i < changeEvent.getAdded().size(); i++) {
                addWorkspaceFolder(changeEvent.getAdded().get(i));
            }
        }else if(changeEvent.getRemoved().size() > 0) {
            for (int i = 0; i < changeEvent.getRemoved().size(); i++) {
               removeWorkspaceFolder(changeEvent.getRemoved().get(i));
            }
        }
    }
}
