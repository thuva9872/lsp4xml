package org.eclipse.lsp4xml.extensions.contentmodel.completions;

import org.eclipse.lsp4xml.commons.utils.CommonUtil;
import org.eclipse.lsp4xml.extensions.contentmodel.completions.SnippetBlock.SnippetType;

public class SnippetGenerator {

    private SnippetGenerator() {
    }

    public static SnippetBlock getAPIDefSnippet() {
        String snippet = "<api name=\"$0\" context=\"$1\">" + CommonUtil.LINE_SEPARATOR +
                "\t<resource methods=\"GET\" uri-template=\"$5\">" + CommonUtil.LINE_SEPARATOR +
                "\t\t<inSequence>$2</inSequence>" + CommonUtil.LINE_SEPARATOR +
                "\t\t<outSequence>$3</outSequence>" + CommonUtil.LINE_SEPARATOR +
                "\t</resource>" + CommonUtil.LINE_SEPARATOR +
                "</api>$4";

        return new SnippetBlock("definitions", snippet, SnippetType.SNIPPET);
    }

    public static SnippetBlock getResourceDefSnippet() {
        String snippet = "<resource>" + CommonUtil.LINE_SEPARATOR +
                "\t<inSequence>$0</inSequence>" + CommonUtil.LINE_SEPARATOR +
                "\t<outSequence>$1</outSequence>" + CommonUtil.LINE_SEPARATOR +
                "</resource>";

        return new SnippetBlock("api", snippet, SnippetType.SNIPPET);
    }

    public static SnippetBlock getInSequenceDefSnippet() {
        String snippet = "<inSequence>$0</inSequence>";

        return new SnippetBlock("inSequence", snippet,SnippetType.SNIPPET);
    }
    public static SnippetBlock getOutSequenceDefSnippet() {
        String snippet = "<outSequence>$0</outSequence>";

        return new SnippetBlock("outSequence", snippet,SnippetType.SNIPPET);
    }
    public static SnippetBlock getFaultSequenceDefSnippet() {
        String snippet = "<faultSequence>$0</faultSequence>";

        return new SnippetBlock("faultSequence", snippet,SnippetType.SNIPPET);
    }

    public static SnippetBlock getProxyDefSnippet() {
        String snippet = "<proxy name=\"$0\">" + CommonUtil.LINE_SEPARATOR +
                //TODO complete
                "</proxy>";

        return new SnippetBlock("proxy", snippet,SnippetType.SNIPPET);
    }

    public static SnippetBlock getSequenceDefSnippet() {
        String snippet = "<sequence name=\"$0\">" + CommonUtil.LINE_SEPARATOR +
                "</sequence>";

        return new SnippetBlock("sequence", snippet,SnippetType.SNIPPET);
    }

}
