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
import org.eclipse.lemminx.customservice.syntaxmodel.pojo.mediator.extension.ejb.Ejb;
import org.eclipse.lemminx.customservice.syntaxmodel.pojo.mediator.extension.ejb.EjbArgs;
import org.eclipse.lemminx.customservice.syntaxmodel.pojo.mediator.extension.ejb.EjbArgsArg;
import org.eclipse.lemminx.customservice.syntaxmodel.utils.Constant;
import org.eclipse.lemminx.dom.DOMElement;
import org.eclipse.lemminx.dom.DOMNode;

import java.util.ArrayList;
import java.util.List;

public class EjbFactory extends AbstractMediatorFactory {

    private static final String EJB = "ejb";

    @Override
    public Mediator createSpecificMediator(DOMElement element) {

        Ejb ejb = new Ejb();
        ejb.elementNode(element);
        populateAttributes(ejb, element);
        List<DOMNode> children = element.getChildren();
        if (children != null && !children.isEmpty()) {
            for (DOMNode child : children) {
                if (child.getNodeName().equalsIgnoreCase(Constant.ARGS)) {
                    EjbArgs args = new EjbArgs();
                    args.elementNode((DOMElement) child);
                    List<EjbArgsArg> ejbArgsArgs = createEjbArgsArg((DOMElement) child);
                    args.setArg(ejbArgsArgs.toArray(new EjbArgsArg[ejbArgsArgs.size()]));
                    ejb.setArgs(args);
                }
            }
        }
        return ejb;
    }

    @Override
    public void populateAttributes(STNode node, DOMElement element) {

        String beanstalk = element.getAttribute(Constant.BEANSTALK);
        if (beanstalk != null && !beanstalk.isEmpty()) {
            ((Ejb) node).setBeanstalk(beanstalk);
        }
        String clazz = element.getAttribute(Constant.CLASS);
        if (clazz != null && !clazz.isEmpty()) {
            ((Ejb) node).setClazz(clazz);
        }
        String sessionId = element.getAttribute(Constant.SESSION_ID);
        if (sessionId != null && !sessionId.isEmpty()) {
            ((Ejb) node).setSessionId(sessionId);
        }
        String remove = element.getAttribute(Constant.REMOVE);
        if (remove != null && !remove.isEmpty()) {
            ((Ejb) node).setRemove(Boolean.parseBoolean(remove));
        }
        String method = element.getAttribute(Constant.METHOD);
        if (method != null && !method.isEmpty()) {
            ((Ejb) node).setMethod(method);
        }
        String target = element.getAttribute(Constant.TARGET);
        if (target != null && !target.isEmpty()) {
            ((Ejb) node).setTarget(target);
        }
        String jndiName = element.getAttribute(Constant.JNDI_NAME);
        if (jndiName != null && !jndiName.isEmpty()) {
            ((Ejb) node).setJndiName(jndiName);
        }
        String id = element.getAttribute(Constant.ID);
        if (id != null && !id.isEmpty()) {
            ((Ejb) node).setId(id);
        }
        String stateful = element.getAttribute(Constant.STATEFUL);
        if (stateful != null && !stateful.isEmpty()) {
            ((Ejb) node).setStateful(Boolean.parseBoolean(stateful));
        }
        String description = element.getAttribute(Constant.DESCRIPTION);
        if (description != null && !description.isEmpty()) {
            ((Ejb) node).setDescription(description);
        }
    }

    private List<EjbArgsArg> createEjbArgsArg(DOMElement node) {

        List<DOMNode> argsChildren = node.getChildren();
        List<EjbArgsArg> args = new ArrayList<>();
        if (argsChildren != null && !argsChildren.isEmpty()) {
            for (DOMNode argsChild : argsChildren) {
                if (argsChild.getNodeName().equalsIgnoreCase(Constant.ARG)) {
                    EjbArgsArg arg = new EjbArgsArg();
                    arg.elementNode((DOMElement) argsChild);
                    String value = argsChild.getAttribute(Constant.VALUE);
                    if (value != null && !value.isEmpty()) {
                        arg.setValue(value);
                    }
                    args.add(arg);
                }
            }
        }
        return args;
    }

    @Override
    public String getTagName() {

        return EJB;
    }
}
