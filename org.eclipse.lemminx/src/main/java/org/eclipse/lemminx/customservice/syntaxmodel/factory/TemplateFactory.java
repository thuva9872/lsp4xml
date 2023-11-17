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

import org.eclipse.lemminx.customservice.syntaxmodel.factory.endpoint.EndpointFactory;
import org.eclipse.lemminx.customservice.syntaxmodel.pojo.NamedSequence;
import org.eclipse.lemminx.customservice.syntaxmodel.pojo.STNode;
import org.eclipse.lemminx.customservice.syntaxmodel.pojo.endpoint.NamedEndpoint;
import org.eclipse.lemminx.customservice.syntaxmodel.pojo.template.Template;
import org.eclipse.lemminx.customservice.syntaxmodel.pojo.template.TemplateParameter;
import org.eclipse.lemminx.customservice.syntaxmodel.utils.Constant;
import org.eclipse.lemminx.dom.DOMElement;
import org.eclipse.lemminx.dom.DOMNode;

import java.util.ArrayList;
import java.util.List;

public class TemplateFactory extends AbstractFactory {

    @Override
    public STNode create(DOMElement element) {

        Template template = new Template();
        template.elementNode(element);
        populateAttributes(template, element);
        List<DOMNode> children = element.getChildren();
        List<TemplateParameter> parameters = new ArrayList<>();
        if (children != null && !children.isEmpty()) {
            for (DOMNode child : children) {
                String name = child.getNodeName();
                if (name.equalsIgnoreCase(Constant.PARAMETER)) {
                    TemplateParameter parameter = createTemplateParameter(child);
                    parameters.add(parameter);
                } else if (name.equalsIgnoreCase(Constant.ENDPOINT)) {
                    AbstractFactory factory = new EndpointFactory();
                    NamedEndpoint endpoint = (NamedEndpoint) factory.create((DOMElement) child);
                    template.setEndpoint(endpoint);
                } else if (name.equalsIgnoreCase(Constant.SEQUENCE)) {
                    AbstractFactory factory = new NamedSequenceFactory();
                    NamedSequence sequence = (NamedSequence) factory.create((DOMElement) child);
                    template.setSequence(sequence);
                }
            }
        }
        return template;
    }

    @Override
    public void populateAttributes(STNode node, DOMElement element) {

        String name = element.getAttribute(Constant.NAME);
        if (name != null && !name.isEmpty()) {
            ((Template) node).setName(name);
        }
    }

    private TemplateParameter createTemplateParameter(DOMNode element) {

        TemplateParameter parameter = new TemplateParameter();
        parameter.elementNode((DOMElement) element);
        String name = element.getAttribute(Constant.NAME);
        if (name != null && !name.isEmpty()) {
            parameter.setName(name);
        }
        String isMandatory = element.getAttribute(Constant.IS_MANDATORY);
        if (isMandatory != null && !isMandatory.isEmpty()) {
            parameter.setMandatory(Boolean.parseBoolean(isMandatory));
        }
        String defaultValue = element.getAttribute(Constant.DEFAULT_VALUE);
        if (defaultValue != null && !defaultValue.isEmpty()) {
            parameter.setDefaultValue(defaultValue);
        }
        //TODO: handle xs:anytype (skipped as not used in Integration Studio)

        return parameter;
    }
}
