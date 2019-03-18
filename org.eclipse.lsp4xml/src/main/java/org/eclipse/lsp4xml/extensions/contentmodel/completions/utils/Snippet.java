package org.eclipse.lsp4xml.extensions.contentmodel.completions.utils;

import org.eclipse.lsp4xml.extensions.contentmodel.completions.SnippetBlock;
import org.eclipse.lsp4xml.extensions.contentmodel.completions.SnippetGenerator;

public enum Snippet {

    //root level Synapse elements snippets
    API(SnippetGenerator.getAPIDefSnippet(), "api"),
    PROXY(SnippetGenerator.getProxyDefSnippet(), "proxy"),
    SEQUENCE(SnippetGenerator.getSequenceDefSnippet(), "sequence"),

    //misc
    RESOURCE(SnippetGenerator.getResourceDefSnippet(), "resource"),


    //mediators snippets
    IN_SEQUENCES(SnippetGenerator.getInSequenceDefSnippet(), "inSequence"),
    OUT_SEQUENCES(SnippetGenerator.getOutSequenceDefSnippet(), "outSequence"),
    FAULT_SEQUENCES(SnippetGenerator.getFaultSequenceDefSnippet(), "faultSequence");


    private SnippetBlock snippetBlock;
    private String tagName;

    Snippet(SnippetBlock snippetBlock, String tagName) {
        this.snippetBlock = snippetBlock;
        this.tagName = tagName;
    }

    public SnippetBlock get() {
        return this.snippetBlock;
    }

    public String getTagName(){
        return this.tagName;
    }
}
