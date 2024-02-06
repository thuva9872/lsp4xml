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

package org.eclipse.lemminx.customservice.synapse.syntaxTree.factory.mediators.eip;

import org.eclipse.lemminx.customservice.synapse.syntaxTree.factory.mediators.AbstractMediatorFactory;
import org.eclipse.lemminx.customservice.synapse.syntaxTree.pojo.STNode;
import org.eclipse.lemminx.customservice.synapse.syntaxTree.pojo.mediator.Mediator;
import org.eclipse.lemminx.customservice.synapse.syntaxTree.pojo.mediator.eip.aggregate.Aggregate;
import org.eclipse.lemminx.customservice.synapse.syntaxTree.pojo.mediator.eip.aggregate.AggregateCompleteCondition;
import org.eclipse.lemminx.customservice.synapse.syntaxTree.pojo.mediator.eip.aggregate.AggregateCompleteConditionMessageCount;
import org.eclipse.lemminx.customservice.synapse.syntaxTree.pojo.mediator.eip.aggregate.AggregateCorrelateOn;
import org.eclipse.lemminx.customservice.synapse.syntaxTree.pojo.mediator.eip.aggregate.AggregateOnComplete;
import org.eclipse.lemminx.customservice.synapse.syntaxTree.pojo.mediator.eip.aggregate.CorrelateOnOrCompleteConditionOrOnComplete;
import org.eclipse.lemminx.customservice.synapse.syntaxTree.utils.SyntaxTreeUtils;
import org.eclipse.lemminx.customservice.synapse.utils.Constant;
import org.eclipse.lemminx.customservice.synapse.utils.Utils;
import org.eclipse.lemminx.dom.DOMElement;
import org.eclipse.lemminx.dom.DOMNode;

import java.util.List;
import java.util.Optional;

public class AggregateFactory extends AbstractMediatorFactory {

    private static final String AGGREGATE = "aggregate";

    @Override
    public Mediator createSpecificMediator(DOMElement element) {

        Aggregate aggregate = new Aggregate();
        aggregate.elementNode(element);
        populateAttributes(aggregate, element);
        List<DOMNode> children = element.getChildren();
        if (children != null && !children.isEmpty()) {
            CorrelateOnOrCompleteConditionOrOnComplete configs =
                    new CorrelateOnOrCompleteConditionOrOnComplete();
            for (DOMNode child : children) {
                String name = child.getNodeName();
                if (name.equalsIgnoreCase(Constant.CORRELATE_ON)) {
                    AggregateCorrelateOn correlateOn = createCorrelateOn(child);
                    configs.setCorrelateOn(Optional.ofNullable(correlateOn));
                } else if (name.equalsIgnoreCase(Constant.COMPLETE_CONDITION)) {
                    AggregateCompleteCondition completeCondition = createCompleteCondition(child);
                    configs.setCompleteCondition(Optional.ofNullable(completeCondition));
                } else if (name.equalsIgnoreCase(Constant.ON_COMPLETE)) {
                    AggregateOnComplete onComplete = createOnComplete(child);
                    configs.setOnComplete(Optional.ofNullable(onComplete));
                }
            }
            aggregate.setCorrelateOnOrCompleteConditionOrOnComplete(configs);
        }
        return aggregate;
    }

    private AggregateCorrelateOn createCorrelateOn(DOMNode child) {

        AggregateCorrelateOn correlateOn = new AggregateCorrelateOn();
        correlateOn.elementNode((DOMElement) child);
        String expression = child.getAttribute(Constant.EXPRESSION);
        if (expression != null && !expression.isEmpty()) {
            correlateOn.setExpression(expression);
        }
        return correlateOn;
    }

    private AggregateCompleteCondition createCompleteCondition(DOMNode child) {

        AggregateCompleteCondition completeCondition = new AggregateCompleteCondition();
        completeCondition.elementNode((DOMElement) child);
        String timeout = child.getAttribute(Constant.TIMEOUT);
        if (timeout != null && !timeout.isEmpty()) {
            completeCondition.setTimeout(Utils.parseInt(timeout));
        }

        List<DOMNode> children = child.getChildren();
        if (children != null && !children.isEmpty()) {
            for (DOMNode node : children) {
                String name = node.getNodeName();
                if (name.equalsIgnoreCase(Constant.MESSAGE_COUNT)) {
                    AggregateCompleteConditionMessageCount messageCount = createMessageCount(node);
                    completeCondition.setMessageCount(messageCount);
                }
            }
        }
        return completeCondition;
    }

    private AggregateCompleteConditionMessageCount createMessageCount(DOMNode node) {

        AggregateCompleteConditionMessageCount messageCount = new AggregateCompleteConditionMessageCount();
        messageCount.elementNode((DOMElement) node);
        String min = node.getAttribute(Constant.MIN);
        if (min != null && !min.isEmpty()) {
            messageCount.setMin(min);
        }
        String max = node.getAttribute(Constant.MAX);
        if (max != null && !max.isEmpty()) {
            messageCount.setMax(max);
        }
        return messageCount;
    }

    private AggregateOnComplete createOnComplete(DOMNode child) {

        AggregateOnComplete onComplete = new AggregateOnComplete();
        onComplete.elementNode((DOMElement) child);
        String expression = child.getAttribute(Constant.EXPRESSION);
        if (expression != null && !expression.isEmpty()) {
            onComplete.setExpression(expression);
        }
        String sequence = child.getAttribute(Constant.SEQUENCE);
        if (sequence != null && !sequence.isEmpty()) {
            onComplete.setSequenceAttribute(sequence);
        }
        String enclosingElementProperty = child.getAttribute(Constant.ENCLOSING_ELEMENT_PROPERTY);
        if (enclosingElementProperty != null && !enclosingElementProperty.isEmpty()) {
            onComplete.setEnclosingElementProperty(enclosingElementProperty);
        }
        String aggregateElementType = child.getAttribute(Constant.AGGREGATE_ELEMENT_TYPE);
        if (aggregateElementType != null && !aggregateElementType.isEmpty()) {
            onComplete.setAggregateElementType(aggregateElementType);
        }

        List<DOMNode> children = child.getChildren();
        List<Mediator> mediators = SyntaxTreeUtils.createMediators(children);
        onComplete.setMediators(mediators);
        return onComplete;
    }

    @Override
    public void populateAttributes(STNode node, DOMElement element) {

        String id = element.getAttribute(Constant.ID);
        if (id != null && !id.isEmpty()) {
            ((Aggregate) node).setId(id);
        }
        String description = element.getAttribute(Constant.DESCRIPTION);
        if (description != null && !description.isEmpty()) {
            ((Aggregate) node).setDescription(description);
        }
    }

    @Override
    public String getTagName() {

        return AGGREGATE;
    }
}
