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

package org.eclipse.lemminx.customservice.syntaxmodel.factory.mediators.extension;

import org.eclipse.lemminx.customservice.syntaxmodel.factory.mediators.AbstractMediatorFactory;
import org.eclipse.lemminx.customservice.syntaxmodel.pojo.STNode;
import org.eclipse.lemminx.customservice.syntaxmodel.pojo.mediator.Mediator;
import org.eclipse.lemminx.customservice.syntaxmodel.pojo.mediator.extension.command.PojoCommand;
import org.eclipse.lemminx.customservice.syntaxmodel.pojo.mediator.extension.command.PojoCommandProperty;
import org.eclipse.lemminx.customservice.syntaxmodel.utils.Constant;
import org.eclipse.lemminx.customservice.syntaxmodel.utils.Utils;
import org.eclipse.lemminx.dom.DOMElement;
import org.eclipse.lemminx.dom.DOMNode;

import java.util.ArrayList;
import java.util.List;

public class PojoCommandFactory extends AbstractMediatorFactory {

    private static final String POJO_COMMAND = "pojoCommand";

    @Override
    public Mediator createSpecificMediator(DOMElement element) {

        PojoCommand command = new PojoCommand();
        command.elementNode(element);
        populateAttributes(command, element);
        List<DOMNode> children = element.getChildren();
        List<PojoCommandProperty> properties = new ArrayList<>();
        if (children != null && !children.isEmpty()) {
            for (DOMNode child : children) {
                if (child.getNodeName().equalsIgnoreCase(Constant.PROPERTY)) {
                    PojoCommandProperty property = new PojoCommandProperty();
                    property.elementNode((DOMElement) child);
                    populatePojoCommandPropertyAttributes(property, (DOMElement) child);

                    List<DOMNode> anyChildren = element.getChildren();
                    List<String> contents = new ArrayList<>();
                    if (anyChildren != null && !anyChildren.isEmpty()) {
                        for (DOMNode anyChild : anyChildren) {
                            String content = Utils.getInlineString(anyChild);
                            contents.add(content);
                        }
                        property.setAny(contents.toArray(new String[contents.size()]));
                    }
                    properties.add(property);
                }
            }
            command.setProperty(properties.toArray(new PojoCommandProperty[properties.size()]));
        }

        return command;
    }

    @Override
    public void populateAttributes(STNode node, DOMElement element) {

        String name = element.getAttribute(Constant.NAME);
        if (name != null && !name.isEmpty()) {
            ((PojoCommand) node).setName(name);
        }
        String description = element.getAttribute(Constant.DESCRIPTION);
        if (description != null && !description.isEmpty()) {
            ((PojoCommand) node).setDescription(description);
        }
    }

    public void populatePojoCommandPropertyAttributes(PojoCommandProperty property, DOMElement element) {

        String name = element.getAttribute(Constant.NAME);
        if (name != null && !name.isEmpty()) {
            property.setName(name);
        }
        String value = element.getAttribute(Constant.VALUE);
        if (value != null && !value.isEmpty()) {
            property.setValue(value);
        }
        String expression = element.getAttribute(Constant.EXPRESSION);
        if (expression != null && !expression.isEmpty()) {
            property.setExpression(expression);
        }
        String contextName = element.getAttribute(Constant.CONTEXT_NAME);
        if (contextName != null && !contextName.isEmpty()) {
            property.setContextName(contextName);
        }
        String action = element.getAttribute(Constant.ACTION);
        if (action != null && !action.isEmpty()) {
            property.setAction(action);
        }
    }

    @Override
    public String getTagName() {

        return POJO_COMMAND;
    }
}
