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

package org.eclipse.lemminx.customservice.syntaxmodel.factory.mediators;

import org.eclipse.lemminx.customservice.syntaxmodel.pojo.STNode;
import org.eclipse.lemminx.customservice.syntaxmodel.pojo.mediator.FilterSequence;
import org.eclipse.lemminx.customservice.syntaxmodel.pojo.mediator.Mediator;
import org.eclipse.lemminx.customservice.syntaxmodel.utils.Constant;
import org.eclipse.lemminx.customservice.syntaxmodel.utils.Utils;
import org.eclipse.lemminx.dom.DOMElement;
import org.eclipse.lemminx.dom.DOMNode;

import java.util.List;

public class FilterSequenceFactory extends AbstractMediatorFactory {

    private static final String SEQUENCE = "sequence";

    @Override
    public Mediator createSpecificMediator(DOMElement element) {

        FilterSequence filterSequence = new FilterSequence();
        filterSequence.elementNode(element);
        populateAttributes(filterSequence, element);
        List<DOMNode> children = element.getChildren();
        if (children != null && !children.isEmpty()) {
            List<Mediator> mediators = Utils.createMediators(children);
            filterSequence.setMediatorList(mediators);
        }
        return filterSequence;
    }

    @Override
    public void populateAttributes(STNode node, DOMElement element) {

        String key = element.getAttribute(Constant.KEY);
        if (key != null && !key.isEmpty()) {
            ((FilterSequence) node).setKey(key);
        }
        String name = element.getAttribute(Constant.NAME);
        if (name != null && !name.isEmpty()) {
            ((FilterSequence) node).setName(name);
        }
        String description = element.getAttribute(Constant.DESCRIPTION);
        if (description != null && !description.isEmpty()) {
            ((FilterSequence) node).setDescription(description);
        }
    }

    @Override
    public String getTagName() {

        return SEQUENCE;
    }
}
