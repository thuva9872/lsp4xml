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

package org.eclipse.lemminx.customservice.syntaxmodel.pojo.endpoint.loadbalance;

import org.eclipse.lemminx.customservice.syntaxmodel.pojo.STNode;

public class EndpointLoadbalance extends STNode {

    EndpointOrMember[] endpointOrMember;
    String algorithm;
    boolean failover;
    String policy;
    boolean buildMessage;

    public EndpointOrMember[] getEndpointOrMember() {

        return endpointOrMember;
    }

    public void setEndpointOrMember(EndpointOrMember[] endpointOrMember) {

        this.endpointOrMember = endpointOrMember;
    }

    public String getAlgorithm() {

        return algorithm;
    }

    public void setAlgorithm(String algorithm) {

        this.algorithm = algorithm;
    }

    public boolean isFailover() {

        return failover;
    }

    public void setFailover(boolean failover) {

        this.failover = failover;
    }

    public String getPolicy() {

        return policy;
    }

    public void setPolicy(String policy) {

        this.policy = policy;
    }

    public boolean isBuildMessage() {

        return buildMessage;
    }

    public void setBuildMessage(boolean buildMessage) {

        this.buildMessage = buildMessage;
    }
}