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
import org.eclipse.lemminx.customservice.syntaxmodel.pojo.mediator.extension.Script;
import org.eclipse.lemminx.customservice.syntaxmodel.utils.Constant;
import org.eclipse.lemminx.customservice.syntaxmodel.utils.Utils;
import org.eclipse.lemminx.dom.DOMElement;
import org.eclipse.lemminx.dom.DOMNode;

import java.util.ArrayList;
import java.util.List;

public class ScriptFactory extends AbstractMediatorFactory {

    private static final String SCRIPT = "script";

    @Override
    public Mediator createSpecificMediator(DOMElement element) {

        Script script = new Script();
        script.elementNode(element);
        populateAttributes(script, element);
        // TODO: check handling <xs:any> elements
        List<DOMNode> children = element.getChildren();
        List<Object> elemets = new ArrayList<>();
        if (children != null && !children.isEmpty()) {
            for (DOMNode node : children) {
                String xml = Utils.getInlineString(node);
                elemets.add(xml);
            }
            script.setContent(elemets.toArray());
        }
        return script;
    }

    @Override
    public void populateAttributes(STNode node, DOMElement element) {

        String language = element.getAttribute(Constant.LANGUAGE);
        if (language != null && !language.isEmpty()) {
            ((Script) node).setLanguage(language);
        }
        String key = element.getAttribute(Constant.KEY);
        if (key != null && !key.isEmpty()) {
            ((Script) node).setKey(key);
        }
        String function = element.getAttribute(Constant.FUNCTION);
        if (function != null && !function.isEmpty()) {
            ((Script) node).setFunction(function);
        }
        String description = element.getAttribute(Constant.DESCRIPTION);
        if (description != null && !description.isEmpty()) {
            ((Script) node).setDescription(description);
        }
    }

    @Override
    public String getTagName() {

        return SCRIPT;
    }
}