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
import org.eclipse.lemminx.customservice.syntaxmodel.pojo.mediator.extension.Bean;
import org.eclipse.lemminx.customservice.syntaxmodel.utils.Constant;
import org.eclipse.lemminx.dom.DOMElement;

public class BeanFactory extends AbstractMediatorFactory {

    private static final String BEAN = "bean";

    @Override
    public Mediator createSpecificMediator(DOMElement element) {

        Bean bean = new Bean();
        bean.elementNode(element);
        populateAttributes(bean, element);
        return bean;
    }

    @Override
    public void populateAttributes(STNode node, DOMElement element) {

        String action = element.getAttribute(Constant.ACTION);
        if (action != null && !action.isEmpty()) {
            ((Bean) node).setAction(action);
        }
        String var = element.getAttribute(Constant.VAR);
        if (var != null && !var.isEmpty()) {
            ((Bean) node).setVar(var);
        }
        String clazz = element.getAttribute(Constant.CLASS);
        if (clazz != null && !clazz.isEmpty()) {
            ((Bean) node).setClazz(clazz);
        }
        String property = element.getAttribute(Constant.PROPERTY);
        if (property != null && !property.isEmpty()) {
            ((Bean) node).setProperty(property);
        }
        String value = element.getAttribute(Constant.VALUE);
        if (value != null && !value.isEmpty()) {
            ((Bean) node).setValue(value);
        }
        String description = element.getAttribute(Constant.DESCRIPTION);
        if (description != null && !description.isEmpty()) {
            ((Bean) node).setDescription(description);
        }
    }

    @Override
    public String getTagName() {

        return BEAN;
    }
}
