package org.eclipse.lsp4xml.utils;

public class SynapseSchemaUtils {

    public static String schemaLocation;

    private SynapseSchemaUtils(){}

    static {
        schemaLocation = System.getProperty("SCHEMA_PATH");
    }

}
