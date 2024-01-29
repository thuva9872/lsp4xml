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

package org.eclipse.lemminx.customservice.syntaxmodel.factory;

import org.eclipse.lemminx.customservice.syntaxmodel.pojo.STNode;
import org.eclipse.lemminx.customservice.syntaxmodel.pojo.api.API;
import org.eclipse.lemminx.customservice.syntaxmodel.pojo.api.APIHandlers;
import org.eclipse.lemminx.customservice.syntaxmodel.pojo.api.APIHandlersHandler;
import org.eclipse.lemminx.customservice.syntaxmodel.pojo.api.APIHandlersHandlerProperty;
import org.eclipse.lemminx.customservice.syntaxmodel.pojo.api.APIResource;
import org.eclipse.lemminx.customservice.syntaxmodel.utils.Constant;
import org.eclipse.lemminx.dom.DOMElement;
import org.eclipse.lemminx.dom.DOMNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class APIFactory extends AbstractFactory {

    @Override
    public STNode create(DOMElement element) {

        API apiNode = new API();
        apiNode.elementNode(element);
        List<DOMNode> children = element.getChildren();
        List<APIResource> resources = new ArrayList<>();
        List<APIHandlers> handlers = new ArrayList<>();
        populateAttributes(apiNode, element);
        if (Objects.nonNull(children)) {
            for (DOMNode node : children) {
                if (node.getNodeName().equalsIgnoreCase(Constant.RESOURCE)) {
                    STNode resource = createAPIResource(node);
                    resources.add((APIResource) resource);
                } else if (node.getNodeName().equalsIgnoreCase(Constant.HANDLERS)) {
                    APIHandlers apiHandler = createAPIHandlers(node);
                    handlers.add(apiHandler);
                }
            }
        }
        apiNode.setResource(resources.toArray(new APIResource[resources.size()]));
        apiNode.setHandlers(handlers.toArray(new APIHandlers[handlers.size()]));
        return apiNode;
    }

    @Override
    public void populateAttributes(STNode node, DOMElement element) {

        API api = (API) node;
        String name = element.getAttribute(Constant.NAME);
        if (Objects.nonNull(name) && !name.isEmpty()) {
            api.setName(name);
        }
        String context = element.getAttribute(Constant.CONTEXT);
        if (Objects.nonNull(context) && !context.isEmpty()) {
            api.setContext(context);
        }
        String hostname = element.getAttribute(Constant.HOSTNAME);
        if (Objects.nonNull(hostname) && !hostname.isEmpty()) {
            api.setHostname(hostname);
        }
        String port = element.getAttribute(Constant.PORT);
        if (Objects.nonNull(port) && !port.isEmpty()) {
            api.setPort(port);
        }
        String version = element.getAttribute(Constant.VERSION);
        if (Objects.nonNull(version) && !version.isEmpty()) {
            api.setVersion(version);
        }
        String versionType = element.getAttribute(Constant.VERSION_TYPE);
        if (Objects.nonNull(versionType) && !versionType.isEmpty()) {
            api.setVersionType(versionType);
        }
        String publishSwagger = element.getAttribute(Constant.PUBLISH_SWAGGER);
        if (Objects.nonNull(publishSwagger) && !publishSwagger.isEmpty()) {
            api.setPublishSwagger(publishSwagger);
        }
        String description = element.getAttribute(Constant.DESCRIPTION);
        if (Objects.nonNull(description) && !description.isEmpty()) {
            api.setDescription(description);
        }
        String statistics = element.getAttribute(Constant.STATISTICS);
        if (Objects.nonNull(statistics) && !statistics.isEmpty()) {
            api.setStatistics(statistics);
        }
        String trace = element.getAttribute(Constant.TRACE);
        if (Objects.nonNull(trace) && !trace.isEmpty()) {
            api.setTrace(trace);
        }
    }

    public STNode createAPIResource(DOMNode node) {

        ResourceFactory resourceFactory = new ResourceFactory();
        STNode resource = resourceFactory.create((DOMElement) node);
        return resource;
    }

    public APIHandlers createAPIHandlers(DOMNode node) {

        APIHandlers apiHandlers = new APIHandlers();
        apiHandlers.elementNode((DOMElement) node);
        List<DOMNode> children = node.getChildren();
        List<APIHandlersHandler> handlers = new ArrayList<>();
        if (children != null && !children.isEmpty()) {
            for (DOMNode childNode : children) {
                if (childNode.getNodeName().equalsIgnoreCase(Constant.HANDLER)) {
                    APIHandlersHandler handler = createAPIHandlersHandler(childNode);
                    handlers.add(handler);
                }
            }
            apiHandlers.setHandler(handlers.toArray(new APIHandlersHandler[handlers.size()]));
        }
        return apiHandlers;
    }

    private APIHandlersHandler createAPIHandlersHandler(DOMNode childNode) {

        APIHandlersHandler apiHandlersHandler = new APIHandlersHandler();
        apiHandlersHandler.elementNode((DOMElement) childNode);
        String className = childNode.getAttribute(Constant.CLASS);
        if (className != null && !className.isEmpty()) {
            apiHandlersHandler.setClazz(className);
        }
        List<DOMNode> children = childNode.getChildren();
        List<APIHandlersHandlerProperty> properties = new ArrayList<>();
        if (children != null && !children.isEmpty()) {
            for (DOMNode node : children) {
                if (node.getNodeName().equalsIgnoreCase(Constant.PROPERTY)) {
                    APIHandlersHandlerProperty mediatorPropertyFactory = createAPIHandlersHandlerProperty(node);
                    properties.add(mediatorPropertyFactory);
                }
            }
            apiHandlersHandler.setProperty(properties.toArray(new APIHandlersHandlerProperty[properties.size()]));
        }
        return apiHandlersHandler;
    }

    private APIHandlersHandlerProperty createAPIHandlersHandlerProperty(DOMNode node) {

        APIHandlersHandlerProperty apiHandlersHandlerProperty = new APIHandlersHandlerProperty();
        apiHandlersHandlerProperty.elementNode((DOMElement) node);
        String name = node.getAttribute(Constant.NAME);
        if (name != null && !name.isEmpty()) {
            apiHandlersHandlerProperty.setName(name);
        }
        String value = node.getAttribute(Constant.VALUE);
        if (value != null && !value.isEmpty()) {
            apiHandlersHandlerProperty.setValue(value);
        }
        return apiHandlersHandlerProperty;
    }
}