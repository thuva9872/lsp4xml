package org.eclipse.xml.languageserver.xsd;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.xerces.xs.XSConstants;
import org.apache.xerces.xs.XSElementDeclaration;
import org.apache.xerces.xs.XSModel;
import org.apache.xerces.xs.XSNamedMap;
import org.eclipse.xml.languageserver.contentmodel.CMDocument;
import org.eclipse.xml.languageserver.contentmodel.CMElement;
import org.eclipse.xml.languageserver.model.Node;
import org.eclipse.xml.languageserver.model.XMLDocument;

public class XSDDocument implements CMDocument {

	private final XSModel model;

	private Collection<CMElement> elements;

	public XSDDocument(XSModel model) {
		this.model = model;
	}

	private Collection<CMElement> getElements() {
		if (elements == null) {
			elements = new ArrayList<>();
			XSNamedMap map = model.getComponents(XSConstants.ELEMENT_DECLARATION);
			for (int j = 0; j < map.getLength(); j++) {
				XSElementDeclaration elementDeclaration = (XSElementDeclaration) map.item(j);
				elements.add(new XSDElement(elementDeclaration));
			}
		}
		return elements;
	}

	@Override
	public CMElement findCMElement(Node node) {
		String tagName = node.tag;
		String namespace = node.getOwnerDocument().getNamespaceURI();
		List<Node> paths = new ArrayList<>();
		Node element = node;
		while (element != null && !(element instanceof XMLDocument)) {
			paths.add(0, element);
			element = element.parent;
		}
		CMElement declaration = null;
		for (int i = 0; i < paths.size(); i++) {
			Node elt = paths.get(i);
			if (i == 0) {
				declaration = findElementDeclaration(elt.tag, namespace);
			} else {
				declaration = declaration.findCMElement(elt.tag, namespace);
			}
			if (declaration == null) {
				break;
			}
		}
		return declaration;
	}

	private CMElement findElementDeclaration(String tag, String namespace) {
		for (CMElement cmElement : getElements()) {
			if (cmElement.getName().equals(tag)) {
				return cmElement;
			}
		}
		return null;
	}
}
