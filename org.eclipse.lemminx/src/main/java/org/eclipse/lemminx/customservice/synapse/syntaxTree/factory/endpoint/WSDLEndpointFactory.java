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

package org.eclipse.lemminx.customservice.synapse.syntaxTree.factory.endpoint;

import org.eclipse.lemminx.customservice.synapse.syntaxTree.factory.AbstractFactory;
import org.eclipse.lemminx.customservice.synapse.syntaxTree.factory.misc.Wsdl11Factory;
import org.eclipse.lemminx.customservice.synapse.syntaxTree.factory.misc.Wsdl20Factory;
import org.eclipse.lemminx.customservice.synapse.syntaxTree.pojo.STNode;
import org.eclipse.lemminx.customservice.synapse.syntaxTree.pojo.endpoint.common.EndpointEnableAddressing;
import org.eclipse.lemminx.customservice.synapse.syntaxTree.pojo.endpoint.common.EndpointEnableRM;
import org.eclipse.lemminx.customservice.synapse.syntaxTree.pojo.endpoint.common.EndpointEnableSec;
import org.eclipse.lemminx.customservice.synapse.syntaxTree.pojo.endpoint.common.EndpointMarkForSuspension;
import org.eclipse.lemminx.customservice.synapse.syntaxTree.pojo.endpoint.common.EndpointSuspendOnFailure;
import org.eclipse.lemminx.customservice.synapse.syntaxTree.pojo.endpoint.common.EndpointTimeout;
import org.eclipse.lemminx.customservice.synapse.syntaxTree.pojo.endpoint.wsdl.WSDLEndpoint;
import org.eclipse.lemminx.customservice.synapse.syntaxTree.pojo.misc.wsdl11.TDefinitions;
import org.eclipse.lemminx.customservice.synapse.syntaxTree.pojo.misc.wsdl20.DescriptionType;
import org.eclipse.lemminx.customservice.synapse.utils.Constant;
import org.eclipse.lemminx.dom.DOMElement;
import org.eclipse.lemminx.dom.DOMNode;

import java.util.List;

public class WSDLEndpointFactory extends AbstractFactory {

    @Override
    public STNode create(DOMElement element) {

        WSDLEndpoint wsdlEndpoint = new WSDLEndpoint();
        wsdlEndpoint.elementNode(element);
        populateAttributes(wsdlEndpoint, element);
        List<DOMNode> children = element.getChildren();
        if (children != null & !children.isEmpty()) {
            for (DOMNode child : children) {
                String name = child.getNodeName();
                if (name.equalsIgnoreCase(Constant.WSDL_DEFINITIONS)) {
                    Wsdl11Factory factory = new Wsdl11Factory();
                    TDefinitions definitions = (TDefinitions) factory.create((DOMElement) child);
                    wsdlEndpoint.setDefinitions(definitions);
                } else if (name.equalsIgnoreCase(Constant.WSDL_DESCRIPTION)) {
                    Wsdl20Factory factory = new Wsdl20Factory();
                    DescriptionType description = (DescriptionType) factory.create((DOMElement) child);
                    wsdlEndpoint.setDescription(description);
                } else if (name.equalsIgnoreCase(Constant.ENABLE_SEC)) {
                    EndpointEnableSec enableSec = EndpointUtils.createEnableSec(child);
                    wsdlEndpoint.setEnableSec(enableSec);
                } else if (name.equalsIgnoreCase(Constant.ENABLE_RM)) {
                    EndpointEnableRM enableRM = EndpointUtils.createEnableRM(child);
                    wsdlEndpoint.setEnableRM(enableRM);
                } else if (name.equalsIgnoreCase(Constant.ENABLE_ADDRESSING)) {
                    EndpointEnableAddressing enableAddressing = EndpointUtils.createEnableAddressing(child);
                    wsdlEndpoint.setEnableAddressing(enableAddressing);
                } else if (name.equalsIgnoreCase(Constant.TIMEOUT)) {
                    EndpointTimeout timeout = EndpointUtils.createTimeout(child);
                    wsdlEndpoint.setTimeout(timeout);
                } else if (name.equalsIgnoreCase(Constant.SUSPEND_ON_FAILURE)) {
                    EndpointSuspendOnFailure suspendOnFailure = EndpointUtils.createSuspendOnFailure(child);
                    wsdlEndpoint.setSuspendOnFailure(suspendOnFailure);
                } else if (name.equalsIgnoreCase(Constant.MARK_FOR_SUSPENSION)) {
                    EndpointMarkForSuspension markForSuspension = EndpointUtils.createMarkForSuspension(child);
                    wsdlEndpoint.setMarkForSuspension(markForSuspension);
                }
            }
        }
        return wsdlEndpoint;
    }

    @Override
    public void populateAttributes(STNode node, DOMElement element) {

        String format = element.getAttribute(Constant.FORMAT);
        if (format != null && !format.isEmpty()) {
            ((WSDLEndpoint) node).setFormat(format);
        }
        String optimize = element.getAttribute(Constant.OPTIMIZE);
        if (optimize != null && !optimize.isEmpty()) {
            ((WSDLEndpoint) node).setOptimize(optimize);
        }
        String encoding = element.getAttribute(Constant.ENCODING);
        if (encoding != null && !encoding.isEmpty()) {
            ((WSDLEndpoint) node).setEncoding(encoding);
        }
        String statistics = element.getAttribute(Constant.STATISTICS);
        if (statistics != null && !statistics.isEmpty()) {
            ((WSDLEndpoint) node).setStatistics(statistics);
        }
        String trace = element.getAttribute(Constant.TRACE);
        if (trace != null && !trace.isEmpty()) {
            ((WSDLEndpoint) node).setTrace(trace);
        }
        String uri = element.getAttribute(Constant.URI);
        if (uri != null && !uri.isEmpty()) {
            ((WSDLEndpoint) node).setUri(uri);
        }
        String service = element.getAttribute(Constant.SERVICE);
        if (service != null && !service.isEmpty()) {
            ((WSDLEndpoint) node).setService(service);
        }
        String port = element.getAttribute(Constant.PORT);
        if (port != null && !port.isEmpty()) {
            ((WSDLEndpoint) node).setPort(port);
        }
    }
}
