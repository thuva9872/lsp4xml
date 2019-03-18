/**
 *  Copyright (c) 2018 Angelo ZERR
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *
 *  Contributors:
 *  Angelo Zerr <angelo.zerr@gmail.com> - initial API and implementation
 */
package org.eclipse.lsp4xml.extensions.references.participants;

import java.util.List;

import org.eclipse.lsp4j.Location;
import org.eclipse.lsp4j.Position;
import org.eclipse.lsp4j.Range;
import org.eclipse.lsp4xml.commons.BadLocationException;
import org.eclipse.lsp4xml.dom.DOMAttr;
import org.eclipse.lsp4xml.dom.DOMDocument;
import org.eclipse.lsp4xml.dom.DOMElement;
import org.eclipse.lsp4xml.dom.DOMNode;
import org.eclipse.lsp4xml.extensions.references.XMLReferencesManager;
import org.eclipse.lsp4xml.services.extensions.IDefinitionParticipant;
import org.eclipse.lsp4xml.utils.XMLPositionUtility;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLReferencesDefinitionParticipant implements IDefinitionParticipant {

	@Override
	public void findDefinition(DOMDocument document, Position position, List<Location> locations) {
		try {
			int offset = document.offsetAt(position);
			DOMNode node = document.findNodeAt(offset);

			Document ownerDocument = node.getOwnerDocument();
			NodeList children = ownerDocument.getChildNodes();

//			DOMAttr

//			findDefinitionChild(children);


			if (node != null) {
				XMLReferencesManager.getInstance().collect(node, n -> {
					DOMDocument doc = n.getOwnerDocument();
					Range range = XMLPositionUtility.createRange(n.getStart(), n.getEnd(), doc);
					locations.add(new Location(doc.getDocumentURI(), range));
				});
			}
		} catch (BadLocationException e) {

		}
	}


//	public boolean findDefinitionChild(NodeList children) {
//		if (children != null && children.getLength() > 0) {
//			loop:
//			for (int i = 0; i < children.getLength(); i++) {
//				if (children.item(i) instanceof DOMElement) {
//					String tagName = ((DOMElement) children.item(i)).getTagName();
//					if (tagName.equals("sequence") && children.item(i).hasAttributes()) {
//						List<DOMAttr> list = ((DOMElement) children.item(i)).getAttributeNodes();
//						if (list != null) {
//							for (DOMAttr domAttr: list) {
//								String key = domAttr.getName();
//								if (key.equals("name") && domAttr.getValue().equals("abc")) {
//									return true;
//								}
//							}
//						}
////						((DOMElement) children.item(i)).getAttribute("name").equals("abc")
////						return true;
//						findDefinitionChild(children.item(i).getChildNodes());
//					}else {
//						findDefinitionChild(children.item(i).getChildNodes());
//					}
//				}
//			}
//		}
//		return false;
//	}

}
