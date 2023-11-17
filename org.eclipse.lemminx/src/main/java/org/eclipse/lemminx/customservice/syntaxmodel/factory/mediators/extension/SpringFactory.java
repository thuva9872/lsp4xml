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
import org.eclipse.lemminx.customservice.syntaxmodel.pojo.mediator.extension.Spring;
import org.eclipse.lemminx.customservice.syntaxmodel.utils.Constant;
import org.eclipse.lemminx.dom.DOMElement;

public class SpringFactory extends AbstractMediatorFactory {

    private static final String SPRING = "spring";

    @Override
    public Mediator createSpecificMediator(DOMElement element) {

        Spring spring = new Spring();
        spring.elementNode(element);
        populateAttributes(spring, element);
        return spring;
    }

    @Override
    public void populateAttributes(STNode node, DOMElement element) {

        String bean = element.getAttribute(Constant.BEAN);
        if (bean != null && !bean.isEmpty()) {
            ((Spring) node).setBean(bean);
        }
        String key = element.getAttribute(Constant.KEY);
        if (key != null && !key.isEmpty()) {
            ((Spring) node).setKey(key);
        }
        String description = element.getAttribute(Constant.DESCRIPTION);
        if (description != null && !description.isEmpty()) {
            ((Spring) node).setDescription(description);
        }
    }

    @Override
    public String getTagName() {

        return SPRING;
    }
}
