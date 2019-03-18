package org.eclipse.lsp4xml.extensions.contentmodel.completions;

public class SnippetBlock {

    private String parent = "";
    private String snippet;
    private SnippetType snippetType;

    public SnippetBlock(String parent, String snippet, SnippetType snippetType) {
        this.parent = parent;
        this.snippet = snippet;
        this.snippetType = snippetType;
    }

    public SnippetBlock(String snippet,  SnippetType snippetType) {
        this.snippet = snippet;
        this.snippetType = snippetType;
    }

    public enum SnippetType {
        SNIPPET;
    }

    public String getString(boolean isSnippet) {
        return isSnippet ? this.snippet : getPlainTextSnippet();
    }

    // Private Methods

    private String getPlainTextSnippet() {
        return this.snippet
                .replaceAll("(\\$\\{\\d:)([a-zA-Z]*:*[a-zA-Z]*)(\\})", "$2")
                .replaceAll("(\\$\\{\\d\\})", "");
    }
}
