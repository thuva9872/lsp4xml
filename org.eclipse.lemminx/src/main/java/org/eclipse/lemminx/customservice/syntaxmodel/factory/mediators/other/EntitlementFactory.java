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

package org.eclipse.lemminx.customservice.syntaxmodel.factory.mediators.other;

import org.eclipse.lemminx.customservice.syntaxmodel.factory.mediators.AbstractMediatorFactory;
import org.eclipse.lemminx.customservice.syntaxmodel.pojo.STNode;
import org.eclipse.lemminx.customservice.syntaxmodel.pojo.mediator.Mediator;
import org.eclipse.lemminx.customservice.syntaxmodel.pojo.mediator.other.entitlement.EntitlementService;
import org.eclipse.lemminx.customservice.syntaxmodel.pojo.misc.common.Sequence;
import org.eclipse.lemminx.customservice.syntaxmodel.utils.Constant;
import org.eclipse.lemminx.customservice.syntaxmodel.utils.Utils;
import org.eclipse.lemminx.dom.DOMElement;
import org.eclipse.lemminx.dom.DOMNode;

import java.util.List;

public class EntitlementFactory extends AbstractMediatorFactory {

    private static final String ENTITLEMENT = "entitlementService";

    @Override
    public Mediator createSpecificMediator(DOMElement element) {

        EntitlementService entitlementService = new EntitlementService();
        entitlementService.elementNode(element);
        populateAttributes(entitlementService, element);
        List<DOMNode> children = element.getChildren();
        if (children != null && !children.isEmpty()) {
            for (DOMNode child : children) {
                if (child.getNodeName().equalsIgnoreCase(Constant.ON_REJECT)) {
                    Sequence onReject = Utils.createSequence(child);
                    entitlementService.setOnReject(onReject);
                } else if (child.getNodeName().equalsIgnoreCase(Constant.ON_ACCEPT)) {
                    Sequence onAccept = Utils.createSequence(child);
                    entitlementService.setOnAccept(onAccept);
                } else if (child.getNodeName().equalsIgnoreCase(Constant.ADVICE)) {
                    Sequence advice = Utils.createSequence(child);
                    entitlementService.setAdvice(advice);
                } else if (child.getNodeName().equalsIgnoreCase(Constant.OBLIGATIONS)) {
                    Sequence obligations = Utils.createSequence(child);
                    entitlementService.setObligations(obligations);
                }
            }
        }
        return entitlementService;
    }

    @Override
    public void populateAttributes(STNode node, DOMElement element) {

        String remoteServiceUrl = element.getAttribute(Constant.REMOTE_SERVICE_URL);
        if (remoteServiceUrl != null && !remoteServiceUrl.isEmpty()) {
            ((EntitlementService) node).setRemoteServiceUrl(remoteServiceUrl);
        }
        String remoteServiceUsername = element.getAttribute(Constant.REMOTE_SERVICE_USERNAME);
        if (remoteServiceUsername != null && !remoteServiceUsername.isEmpty()) {
            ((EntitlementService) node).setRemoteServiceUserName(remoteServiceUsername);
        }
        String remoteServicePassword = element.getAttribute(Constant.REMOTE_SERVICE_PASSWORD);
        if (remoteServicePassword != null && !remoteServicePassword.isEmpty()) {
            ((EntitlementService) node).setRemoteServicePassword(remoteServicePassword);
        }
        String callbackClass = element.getAttribute(Constant.CALLBACK_CLASS);
        if (callbackClass != null && !callbackClass.isEmpty()) {
            ((EntitlementService) node).setCallbackClass(callbackClass);
        }
        String client = element.getAttribute(Constant.CLIENT);
        if (client != null && !client.isEmpty()) {
            ((EntitlementService) node).setClient(client);
        }
        String thriftHost = element.getAttribute(Constant.THRIFT_HOST);
        if (thriftHost != null && !thriftHost.isEmpty()) {
            ((EntitlementService) node).setThriftHost(thriftHost);
        }
        String thriftPort = element.getAttribute(Constant.THRIFT_PORT);
        if (thriftPort != null && !thriftPort.isEmpty()) {
            ((EntitlementService) node).setThriftPort(thriftPort);
        }
        String onReject = element.getAttribute(Constant.ON_REJECT);
        if (onReject != null && !onReject.isEmpty()) {
            ((EntitlementService) node).setOnRejectAttribute(onReject);
        }
        String onAccept = element.getAttribute(Constant.ON_ACCEPT);
        if (onAccept != null && !onAccept.isEmpty()) {
            ((EntitlementService) node).setOnAcceptAttribute(onAccept);
        }
        String advice = element.getAttribute(Constant.ADVICE);
        if (advice != null && !advice.isEmpty()) {
            ((EntitlementService) node).setAdviceAttribute(advice);
        }
        String obligations = element.getAttribute(Constant.OBLIGATIONS);
        if (obligations != null && !obligations.isEmpty()) {
            ((EntitlementService) node).setObligationsAttribute(obligations);
        }
        String description = element.getAttribute(Constant.DESCRIPTION);
        if (description != null && !description.isEmpty()) {
            ((EntitlementService) node).setDescription(description);
        }
    }

    @Override
    public String getTagName() {

        return ENTITLEMENT;
    }
}
