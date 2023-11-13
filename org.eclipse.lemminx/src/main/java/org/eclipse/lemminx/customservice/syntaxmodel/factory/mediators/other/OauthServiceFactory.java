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

package org.eclipse.lemminx.customservice.syntaxmodel.factory.mediators.other;

import org.eclipse.lemminx.customservice.syntaxmodel.factory.mediators.AbstractMediatorFactory;
import org.eclipse.lemminx.customservice.syntaxmodel.pojo.STNode;
import org.eclipse.lemminx.customservice.syntaxmodel.pojo.mediator.Mediator;
import org.eclipse.lemminx.customservice.syntaxmodel.pojo.mediator.other.OauthService;
import org.eclipse.lemminx.customservice.syntaxmodel.utils.Constant;
import org.eclipse.lemminx.dom.DOMElement;

public class OauthServiceFactory extends AbstractMediatorFactory {

    private static final String OAUTH = "oauthService";

    @Override
    public Mediator createSpecificMediator(DOMElement element) {

        OauthService oauthService = new OauthService();
        oauthService.elementNode(element);
        populateAttributes(oauthService, element);
        return oauthService;
    }

    @Override
    public void populateAttributes(STNode node, DOMElement element) {

        String remoteServiceUrl = element.getAttribute(Constant.REMOTE_SERVICE_URL);
        if (remoteServiceUrl != null && !remoteServiceUrl.isEmpty()) {
            ((OauthService) node).setRemoteServiceUrl(remoteServiceUrl);
        }
        String username = element.getAttribute(Constant.USERNAME);
        if (username != null && !username.isEmpty()) {
            ((OauthService) node).setUsername(username);
        }
        String password = element.getAttribute(Constant.PASSWORD);
        if (password != null && !password.isEmpty()) {
            ((OauthService) node).setPassword(password);
        }
        String description = element.getAttribute(Constant.DESCRIPTION);
        if (description != null && !description.isEmpty()) {
            ((OauthService) node).setDescription(description);
        }
    }

    @Override
    public String getTagName() {

        return OAUTH;
    }
}
