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

package org.eclipse.lemminx.customservice.synapse.syntaxTree.factory;

import org.eclipse.lemminx.customservice.synapse.syntaxTree.factory.endpoint.EndpointFactory;
import org.eclipse.lemminx.customservice.synapse.syntaxTree.factory.misc.Wsdl11Factory;
import org.eclipse.lemminx.customservice.synapse.syntaxTree.factory.misc.Wsdl20Factory;
import org.eclipse.lemminx.customservice.synapse.syntaxTree.pojo.STNode;
import org.eclipse.lemminx.customservice.synapse.syntaxTree.pojo.endpoint.NamedEndpoint;
import org.eclipse.lemminx.customservice.synapse.syntaxTree.pojo.misc.common.Sequence;
import org.eclipse.lemminx.customservice.synapse.syntaxTree.pojo.misc.wsdl11.TDefinitions;
import org.eclipse.lemminx.customservice.synapse.syntaxTree.pojo.misc.wsdl20.DescriptionType;
import org.eclipse.lemminx.customservice.synapse.syntaxTree.pojo.proxy.Proxy;
import org.eclipse.lemminx.customservice.synapse.syntaxTree.pojo.proxy.ProxyPolicy;
import org.eclipse.lemminx.customservice.synapse.syntaxTree.pojo.proxy.ProxyPublishWSDL;
import org.eclipse.lemminx.customservice.synapse.syntaxTree.pojo.proxy.ProxyTarget;
import org.eclipse.lemminx.customservice.synapse.syntaxTree.pojo.proxy.Resource;
import org.eclipse.lemminx.customservice.synapse.syntaxTree.utils.SyntaxTreeUtils;
import org.eclipse.lemminx.customservice.synapse.utils.Constant;
import org.eclipse.lemminx.dom.DOMElement;
import org.eclipse.lemminx.dom.DOMNode;

import java.util.ArrayList;
import java.util.List;

public class ProxyFactory extends AbstractFactory {

    @Override
    public STNode create(DOMElement element) {

        Proxy proxy = new Proxy();
        proxy.elementNode(element);
        populateAttributes(proxy, element);
        List<DOMNode> children = element.getChildren();
        List<STNode> elements = new ArrayList<>();
        if (children != null && !children.isEmpty()) {
            for (DOMNode node : children) {
                String name = node.getNodeName();
                if (name.equalsIgnoreCase(Constant.DESCRIPTION)) {
                    STNode description = createTextNode(node);
                    elements.add(description);
                } else if (name.equalsIgnoreCase(Constant.TARGET)) {
                    STNode target = createProxyTarget(node);
                    elements.add(target);
                } else if (name.equalsIgnoreCase(Constant.PUBLISH_WSDL)) {
                    STNode publishWSDL = createPublishWSDL(node);
                    elements.add(publishWSDL);
                } else if (name.equalsIgnoreCase(Constant.ENABLE_ADDRESSING)) {
                    STNode enableAddressing = createTextNode(node);
                    elements.add(enableAddressing);
                } else if (name.equalsIgnoreCase(Constant.ENABLE_SEC)) {
                    STNode enableSec = createTextNode(node);
                    elements.add(enableSec);
                } else if (name.equalsIgnoreCase(Constant.ENABLE_RM)) {
                    STNode enableRM = createTextNode(node);
                    elements.add(enableRM);
                } else if (name.equalsIgnoreCase(Constant.POLICY)) {
                    STNode policy = createPolicy(node);
                    elements.add(policy);
                } else if (name.equalsIgnoreCase(Constant.PARAMETER)) {
                    STNode parameter = SyntaxTreeUtils.createParameter(node);
                    elements.add(parameter);
                }
            }
            proxy.setDescriptionOrTargetOrPublishWSDL(elements);
        }
        return proxy;
    }

    private STNode createProxyTarget(DOMNode node) {

        ProxyTarget target = new ProxyTarget();
        target.elementNode((DOMElement) node);
        populateProxyTargetAttributes(target, node);
        List<DOMNode> children = node.getChildren();
        if (children != null && !children.isEmpty()) {
            for (DOMNode child : children) {
                String name = child.getNodeName();
                if (name.equalsIgnoreCase(Constant.IN_SEQUENCE)) {
                    Sequence inSequence = SyntaxTreeUtils.createSequence(child);
                    target.setInSequence(inSequence);
                } else if (name.equalsIgnoreCase(Constant.OUT_SEQUENCE)) {
                    Sequence outSequence = SyntaxTreeUtils.createSequence(child);
                    target.setOutSequence(outSequence);
                } else if (name.equalsIgnoreCase(Constant.FAULT_SEQUENCE)) {
                    Sequence faultSequence = SyntaxTreeUtils.createSequence(child);
                    target.setFaultSequence(faultSequence);
                } else if (name.equalsIgnoreCase(Constant.ENDPOINT)) {
                    EndpointFactory factory = new EndpointFactory();
                    NamedEndpoint endpoint = (NamedEndpoint) factory.create((DOMElement) child);
                    target.setEndpoint(endpoint);
                }
            }
        }
        return target;
    }

    private void populateProxyTargetAttributes(ProxyTarget target, DOMNode node) {

        String inSequence = node.getAttribute(Constant.IN_SEQUENCE);
        if (inSequence != null && !inSequence.isEmpty()) {
            target.setInSequenceAttribute(inSequence);
        }
        String outSequence = node.getAttribute(Constant.OUT_SEQUENCE);
        if (outSequence != null && !outSequence.isEmpty()) {
            target.setOutSequenceAttribute(outSequence);
        }
        String faultSequence = node.getAttribute(Constant.FAULT_SEQUENCE);
        if (faultSequence != null && !faultSequence.isEmpty()) {
            target.setFaultSequenceAttribute(faultSequence);
        }
        String endpoint = node.getAttribute(Constant.ENDPOINT);
        if (endpoint != null && !endpoint.isEmpty()) {
            target.setEndpointAttribute(endpoint);
        }
    }

    private STNode createPublishWSDL(DOMNode node) {

        ProxyPublishWSDL publishWSDL = new ProxyPublishWSDL();
        publishWSDL.elementNode((DOMElement) node);
        populatePublishWSDLAttributes(publishWSDL, node);
        List<DOMNode> children = node.getChildren();
        List<Resource> resourceList = new ArrayList<>();
        if (children != null && !children.isEmpty()) {
            for (DOMNode child : children) {
                String name = child.getNodeName();
                if (name.equalsIgnoreCase(Constant.WSDL_DEFINITIONS)) {
                    Wsdl11Factory factory = new Wsdl11Factory();
                    TDefinitions definitions = (TDefinitions) factory.create((DOMElement) child);
                    publishWSDL.setDefinitions(definitions);
                } else if (name.equalsIgnoreCase(Constant.WSDL_DESCRIPTION)) {
                    Wsdl20Factory factory = new Wsdl20Factory();
                    DescriptionType description = (DescriptionType) factory.create((DOMElement) child);
                    publishWSDL.setDescription(description);
                } else if (name.equalsIgnoreCase(Constant.RESOURCE)) {
                    Resource resource = createResource(child);
                    resourceList.add(resource);
                }
            }
            publishWSDL.setResource(resourceList.toArray(new Resource[resourceList.size()]));
        }
        return publishWSDL;
    }

    private Resource createResource(DOMNode child) {

        Resource resource = new Resource();
        resource.elementNode((DOMElement) child);
        String location = child.getAttribute(Constant.LOCATION);
        if (location != null && !location.isEmpty()) {
            resource.setLocation(location);
        }
        String key = child.getAttribute(Constant.KEY);
        if (key != null && !key.isEmpty()) {
            resource.setKey(key);
        }
        return resource;
    }

    private void populatePublishWSDLAttributes(ProxyPublishWSDL publishWSDL, DOMNode node) {

        String uri = node.getAttribute(Constant.URI);
        if (uri != null && !uri.isEmpty()) {
            publishWSDL.setUri(uri);
        }
        String key = node.getAttribute(Constant.KEY);
        if (key != null && !key.isEmpty()) {
            publishWSDL.setKey(key);
        }
        String endpoint = node.getAttribute(Constant.ENDPOINT);
        if (endpoint != null && !endpoint.isEmpty()) {
            publishWSDL.setEndpoint(endpoint);
        }
        String preservePolicy = node.getAttribute(Constant.PRESERVE_POLICY);
        if (preservePolicy != null && !preservePolicy.isEmpty()) {
            publishWSDL.setPreservePolicy(Boolean.valueOf(preservePolicy));
        }

    }

    private STNode createTextNode(DOMNode node) {

        STNode textNode = new STNode();
        textNode.elementNode((DOMElement) node);
        return textNode;
    }

    private STNode createPolicy(DOMNode node) {

        ProxyPolicy policy = new ProxyPolicy();
        policy.elementNode((DOMElement) node);
        String key = node.getAttribute(Constant.KEY);
        if (key != null && !key.isEmpty()) {
            policy.setKey(key);
        }
        String type = node.getAttribute(Constant.TYPE);
        if (type != null && !type.isEmpty()) {
            policy.setType(type);
        }
        return policy;
    }

    @Override
    public void populateAttributes(STNode node, DOMElement element) {

        String name = element.getAttribute(Constant.NAME);
        if (name != null && !name.isEmpty()) {
            ((Proxy) node).setName(name);
        }
        String transports = element.getAttribute(Constant.TRANSPORTS);
        if (transports != null && !transports.isEmpty()) {
            ((Proxy) node).setTransports(transports);
        }
        String pinnedServers = element.getAttribute(Constant.PINNED_SERVERS);
        if (pinnedServers != null && !pinnedServers.isEmpty()) {
            ((Proxy) node).setPinnedServers(pinnedServers);
        }
        String serviceGroup = element.getAttribute(Constant.SERVICE_GROUP);
        if (serviceGroup != null && !serviceGroup.isEmpty()) {
            ((Proxy) node).setServiceGroup(serviceGroup);
        }
        String startOnLoad = element.getAttribute(Constant.START_ON_LOAD);
        if (startOnLoad != null && !startOnLoad.isEmpty()) {
            ((Proxy) node).setStartOnLoad(Boolean.valueOf(startOnLoad));
        }
    }
}
