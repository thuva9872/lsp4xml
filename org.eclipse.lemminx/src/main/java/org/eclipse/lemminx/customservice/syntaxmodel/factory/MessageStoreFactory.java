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

import org.eclipse.lemminx.customservice.syntaxmodel.pojo.MessageStore;
import org.eclipse.lemminx.customservice.syntaxmodel.pojo.STNode;
import org.eclipse.lemminx.customservice.syntaxmodel.pojo.misc.common.Parameter;
import org.eclipse.lemminx.customservice.syntaxmodel.utils.Constant;
import org.eclipse.lemminx.customservice.syntaxmodel.utils.Utils;
import org.eclipse.lemminx.dom.DOMElement;
import org.eclipse.lemminx.dom.DOMNode;

import java.util.ArrayList;
import java.util.List;

public class MessageStoreFactory extends AbstractFactory {

    @Override
    public STNode create(DOMElement element) {

        MessageStore messageStore = new MessageStore();
        messageStore.elementNode(element);
        populateAttributes(messageStore, element);
        List<DOMNode> children = element.getChildren();
        List<Parameter> parameters = new ArrayList<>();
        if (children != null && !children.isEmpty()) {
            for (DOMNode node : children) {
                String name = node.getNodeName();
                if (name.equals(Constant.PARAMETER)) {
                    Parameter parameter = Utils.createParameter(node);
                    parameters.add(parameter);
                }
            }
            messageStore.setParameter(parameters.toArray(new Parameter[parameters.size()]));
        }
        return messageStore;
    }

    @Override
    public void populateAttributes(STNode node, DOMElement element) {

        String name = element.getAttribute(Constant.NAME);
        if (name != null && !name.isEmpty()) {
            ((MessageStore) node).setName(name);
        }
        String clazz = element.getAttribute(Constant.CLASS);
        if (clazz != null && !clazz.isEmpty()) {
            ((MessageStore) node).setClazz(clazz);
        }
    }
}