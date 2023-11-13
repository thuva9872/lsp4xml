/*
 * Copyright (c) 2023, WSO2 LLC. (http://www.wso2.com).
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.eclipse.lemminx.customservice.syntaxmodel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.eclipse.lemminx.customservice.syntaxmodel.factory.APIFactory;
import org.eclipse.lemminx.customservice.syntaxmodel.factory.AbstractFactory;
import org.eclipse.lemminx.customservice.syntaxmodel.factory.InboundEndpointFactory;
import org.eclipse.lemminx.customservice.syntaxmodel.factory.LocalEntryFactory;
import org.eclipse.lemminx.customservice.syntaxmodel.factory.MessageProcessorFactory;
import org.eclipse.lemminx.customservice.syntaxmodel.factory.MessageStoreFactory;
import org.eclipse.lemminx.customservice.syntaxmodel.factory.NamedSequenceFactory;
import org.eclipse.lemminx.customservice.syntaxmodel.factory.ProxyFactory;
import org.eclipse.lemminx.customservice.syntaxmodel.factory.RegistryFactory;
import org.eclipse.lemminx.customservice.syntaxmodel.factory.TaskFactory;
import org.eclipse.lemminx.customservice.syntaxmodel.factory.TemplateFactory;
import org.eclipse.lemminx.customservice.syntaxmodel.factory.endpoint.EndpointFactory;
import org.eclipse.lemminx.customservice.syntaxmodel.factory.misc.Wsdl11Factory;
import org.eclipse.lemminx.customservice.syntaxmodel.factory.misc.Wsdl20Factory;
import org.eclipse.lemminx.customservice.syntaxmodel.pojo.STNode;
import org.eclipse.lemminx.customservice.syntaxmodel.utils.Constant;
import org.eclipse.lemminx.dom.DOMDocument;
import org.eclipse.lemminx.dom.DOMElement;

public class SyntaxTreeGenerator {

    public SyntaxTreeResponse getSyntaxTree(DOMDocument document) {

        SyntaxTreeResponse response = new SyntaxTreeResponse(null, document.getDocumentURI());
        if (document.getChildren().size() >= 2) {
            DOMElement rootElement = (DOMElement) document.getChild(1);
            STNode tree = buildTree(rootElement);
            String rootTag = tree.getTag();
            Gson gson = new GsonBuilder().serializeNulls().disableHtmlEscaping().create();
            JsonElement nextNode = gson.toJsonTree(tree);
            JsonObject root = new JsonObject();
            root.add(rootTag, nextNode);
            response.setSyntaxTree(root);
        }
        return response;
    }

    private static STNode buildTree(DOMElement xmlNode) {

        AbstractFactory factory = null;
        STNode root = null;
        if (xmlNode.getNodeName().equalsIgnoreCase(Constant.API)) {
            factory = new APIFactory();
        } else if (xmlNode.getNodeName().equalsIgnoreCase(Constant.ENDPOINT)) {
            factory = new EndpointFactory();
        } else if (xmlNode.getNodeName().equalsIgnoreCase(Constant.INBOUND_ENDPOINT)) {
            factory = new InboundEndpointFactory();
        } else if (xmlNode.getNodeName().equalsIgnoreCase(Constant.MESSAGE_PROCESSOR)) {
            factory = new MessageProcessorFactory();
        } else if (xmlNode.getNodeName().equalsIgnoreCase(Constant.LOCAL_ENTRY)) {
            factory = new LocalEntryFactory();
        } else if (xmlNode.getNodeName().equalsIgnoreCase(Constant.MESSAGE_STORE)) {
            factory = new MessageStoreFactory();
        } else if (xmlNode.getNodeName().equalsIgnoreCase(Constant.PROXY)) {
            factory = new ProxyFactory();
        } else if (xmlNode.getNodeName().equalsIgnoreCase(Constant.REGISTRY)) {
            factory = new RegistryFactory();
        } else if (xmlNode.getNodeName().equalsIgnoreCase(Constant.SEQUENCE)) {
            factory = new NamedSequenceFactory();
        } else if (xmlNode.getNodeName().equalsIgnoreCase(Constant.TASK)) {
            factory = new TaskFactory();
        } else if (xmlNode.getNodeName().equalsIgnoreCase(Constant.TEMPLATE)) {
            factory = new TemplateFactory();
        } else if (xmlNode.getNodeName().equalsIgnoreCase(Constant.WSDL_DEFINITIONS)) {
            factory = new Wsdl11Factory();
        } else if (xmlNode.getNodeName().equalsIgnoreCase(Constant.WSDL_DESCRIPTION)) {
            factory = new Wsdl20Factory();
        }

        if (factory != null) {
            root = factory.create(xmlNode);
        }
        return root;
    }
}
