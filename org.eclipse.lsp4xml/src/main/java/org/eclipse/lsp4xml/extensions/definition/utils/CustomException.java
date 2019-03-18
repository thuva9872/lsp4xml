package org.eclipse.lsp4xml.extensions.definition.utils;

import org.eclipse.lsp4xml.dom.DOMNode;

public class CustomException extends Exception {

    public DOMNode domNode;

    public CustomException(DOMNode domNode) {
        this.domNode = domNode;
    }
}
