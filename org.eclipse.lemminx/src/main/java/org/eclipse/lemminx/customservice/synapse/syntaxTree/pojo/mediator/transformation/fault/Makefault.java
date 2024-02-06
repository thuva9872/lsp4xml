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

package org.eclipse.lemminx.customservice.synapse.syntaxTree.pojo.mediator.transformation.fault;

import org.eclipse.lemminx.customservice.synapse.syntaxTree.pojo.mediator.Mediator;

public class Makefault extends Mediator {

    MakefaultCode code;
    MakefaultReason reason;
    Object node;
    Object role;
    MakefaultDetail detail;
    String version;
    boolean response;
    String description;

    public MakefaultCode getCode() {

        return code;
    }

    public void setCode(MakefaultCode code) {

        this.code = code;
    }

    public MakefaultReason getReason() {

        return reason;
    }

    public void setReason(MakefaultReason reason) {

        this.reason = reason;
    }

    public Object getNode() {

        return node;
    }

    public void setNode(Object node) {

        this.node = node;
    }

    public Object getRole() {

        return role;
    }

    public void setRole(Object role) {

        this.role = role;
    }

    public MakefaultDetail getDetail() {

        return detail;
    }

    public void setDetail(MakefaultDetail detail) {

        this.detail = detail;
    }

    public String getVersion() {

        return version;
    }

    public void setVersion(String version) {

        this.version = version;
    }

    public boolean isResponse() {

        return response;
    }

    public void setResponse(boolean response) {

        this.response = response;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }
}
