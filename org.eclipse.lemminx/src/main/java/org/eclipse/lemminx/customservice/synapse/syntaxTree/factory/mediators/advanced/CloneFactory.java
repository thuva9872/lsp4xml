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

package org.eclipse.lemminx.customservice.synapse.syntaxTree.factory.mediators.advanced;

import org.eclipse.lemminx.customservice.synapse.syntaxTree.factory.AbstractFactory;
import org.eclipse.lemminx.customservice.synapse.syntaxTree.factory.mediators.AbstractMediatorFactory;
import org.eclipse.lemminx.customservice.synapse.syntaxTree.factory.misc.SequenceFactory;
import org.eclipse.lemminx.customservice.synapse.syntaxTree.pojo.STNode;
import org.eclipse.lemminx.customservice.synapse.syntaxTree.pojo.mediator.Mediator;
import org.eclipse.lemminx.customservice.synapse.syntaxTree.pojo.mediator.advanced.Clone.Clone;
import org.eclipse.lemminx.customservice.synapse.syntaxTree.pojo.mediator.advanced.Clone.CloneTarget;
import org.eclipse.lemminx.customservice.synapse.syntaxTree.pojo.misc.common.Sequence;
import org.eclipse.lemminx.customservice.synapse.utils.Constant;
import org.eclipse.lemminx.dom.DOMElement;
import org.eclipse.lemminx.dom.DOMNode;

import java.util.ArrayList;
import java.util.List;

public class CloneFactory extends AbstractMediatorFactory {

    private static final String CLONE_MEDIATOR = "clone";

    @Override
    public Mediator createSpecificMediator(DOMElement element) {

        Clone clone = new Clone();
        populateAttributes(clone, element);
        List<DOMNode> children = element.getChildren();
        List<CloneTarget> cloneTargetList = new ArrayList<>();
        for (DOMNode node : children) {
            if (node.getNodeName().equalsIgnoreCase(Constant.TARGET)) {
                CloneTarget cloneTarget = createCloneTarget((DOMElement) node);
                cloneTargetList.add(cloneTarget);
            } else {
                //invalid configuration
            }

        }
        clone.setTarget(cloneTargetList.toArray(new CloneTarget[cloneTargetList.size()]));
        return clone;
    }

    @Override
    public String getTagName() {

        return CLONE_MEDIATOR;
    }

    @Override
    public void populateAttributes(STNode node, DOMElement element) {

        Clone clone = (Clone) node;
        String sequential = element.getAttribute(Constant.SEQUENTIAL);
        if (sequential != null) {
            clone.setSequential(Boolean.valueOf(sequential));
        }
        String continueParent = element.getAttribute(Constant.CONTINUE_PARENT);
        if (continueParent != null) {
            clone.setContinueParent(Boolean.valueOf(continueParent));
        }
        String id = element.getAttribute(Constant.ID);
        if (id != null && !id.isEmpty()) {
            clone.setId(id);
        }
        String description = element.getAttribute(Constant.DESCRIPTION);
        if (description != null && !description.isEmpty()) {
            clone.setDescription(description);
        }
    }

    private CloneTarget createCloneTarget(DOMElement element) {

        CloneTarget cloneTarget = new CloneTarget();
        cloneTarget.elementNode(element);
        populateCloneTargetAttributes(cloneTarget, element);
        List<DOMNode> children = element.getChildren();
        for (DOMNode node : children) {
            if (node.getNodeName().equalsIgnoreCase(Constant.SEQUENCE)) {
                AbstractFactory sequenceFactory = new SequenceFactory();
                Sequence sequence = (Sequence) sequenceFactory.create((DOMElement) node);
                cloneTarget.setSequence(sequence);
            } else if (node.getNodeName().equalsIgnoreCase(Constant.ENDPOINT)) {
//                AbstractFactory endpointFactory = new EndpointFactory();
//                Endpoint endpoint = endpointFactory.create(element);
//                cloneTarget.setEndpoint(endpoint);
            }
        }
        return cloneTarget;
    }

    public void populateCloneTargetAttributes(CloneTarget cloneTarget, DOMElement element) {

        String to = element.getAttribute(Constant.TO);
        if (to != null && !to.isEmpty()) {
            cloneTarget.setTo(to);
        }
        String soapAction = element.getAttribute(Constant.SOAP_ACTION);
        if (soapAction != null && !soapAction.isEmpty()) {
            cloneTarget.setSoapAction(soapAction);
        }
        String sequenceAttribute = element.getAttribute(Constant.SEQUENCE);
        if (sequenceAttribute != null && !sequenceAttribute.isEmpty()) {
            cloneTarget.setSequenceAttribute(sequenceAttribute);
        }
        String endpointAttribute = element.getAttribute(Constant.ENDPOINT);
        if (endpointAttribute != null && !endpointAttribute.isEmpty()) {
            cloneTarget.setEndpointAttribute(endpointAttribute);
        }
    }
}
