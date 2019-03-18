/**
 *  Copyright (c) 2018 Angelo ZERR.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *
 *  Contributors:
 *  Angelo Zerr <angelo.zerr@gmail.com> - initial API and implementation
 */
package org.eclipse.lsp4xml;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.eclipse.lsp4j.*;
import org.eclipse.lsp4j.services.WorkspaceService;
import org.eclipse.lsp4xml.utils.SynapseWorkspace;

/**
 * XML workspace service.
 *
 */
public class XMLWorkspaceService implements WorkspaceService {

	private final XMLLanguageServer xmlLanguageServer;

	public XMLWorkspaceService(XMLLanguageServer xmlLanguageServer) {
		this.xmlLanguageServer = xmlLanguageServer;
	}

	@Override
	public CompletableFuture<List<? extends SymbolInformation>> symbol(WorkspaceSymbolParams params) {
		return null;
	}

	@Override
	public void didChangeConfiguration(DidChangeConfigurationParams params) {
		xmlLanguageServer.updateSettings(params.getSettings());
		xmlLanguageServer.capabilityManager.syncDynamicCapabilitiesWithPreferences();
	}

	@Override
	public void didChangeWatchedFiles(DidChangeWatchedFilesParams params) {

	}

	@Override
	public void didChangeWorkspaceFolders(DidChangeWorkspaceFoldersParams params) {

		if (params.getEvent().getAdded().size() > 0) {
			for (int i = 0; i < params.getEvent().getAdded().size(); i++) {
				SynapseWorkspace.getInstance().addWorkspaceFolder(params.getEvent().getAdded().get(i));
			}
		}else if(params.getEvent().getRemoved().size() > 0) {
			for (int i = 0; i < params.getEvent().getRemoved().size(); i++) {
				SynapseWorkspace.getInstance().removeWorkspaceFolder(params.getEvent().getRemoved().get(i));
			}
		}
	}

}

