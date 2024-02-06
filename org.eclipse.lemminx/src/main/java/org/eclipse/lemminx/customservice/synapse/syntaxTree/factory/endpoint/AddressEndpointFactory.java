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

package org.eclipse.lemminx.customservice.synapse.syntaxTree.factory.endpoint;

import org.eclipse.lemminx.customservice.synapse.syntaxTree.factory.AbstractFactory;
import org.eclipse.lemminx.customservice.synapse.syntaxTree.pojo.STNode;
import org.eclipse.lemminx.customservice.synapse.syntaxTree.pojo.endpoint.DefaultEndpoint;
import org.eclipse.lemminx.customservice.synapse.syntaxTree.pojo.endpoint.EndpointAddress;
import org.eclipse.lemminx.customservice.synapse.utils.Constant;
import org.eclipse.lemminx.dom.DOMElement;

public class AddressEndpointFactory extends AbstractFactory {

    @Override
    public STNode create(DOMElement element) {

        DefaultEndpointFactory defaultEndpointFactory = new DefaultEndpointFactory();
        DefaultEndpoint defaultEndpoint = (DefaultEndpoint) defaultEndpointFactory.create(element);
        EndpointAddress endpointAddress = new EndpointAddress(defaultEndpoint);
        populateAttributes(endpointAddress, element);
        return endpointAddress;
    }

    @Override
    public void populateAttributes(STNode node, DOMElement element) {

        String uri = element.getAttribute(Constant.URI);
        if (uri != null && !uri.isEmpty()) {
            ((EndpointAddress) node).setUri(uri);
        }
    }
}
