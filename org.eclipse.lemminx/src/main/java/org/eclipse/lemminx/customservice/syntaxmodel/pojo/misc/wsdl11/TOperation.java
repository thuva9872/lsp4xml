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

package org.eclipse.lemminx.customservice.syntaxmodel.pojo.misc.wsdl11;

import java.util.List;

public class TOperation extends TExtensibleDocumented {

    List<Object> rest;
    String name;
    Object parameterOrder;

    public List<Object> getRest() {

        return rest;
    }

    public void setRest(List<Object> rest) {

        this.rest = rest;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public Object getParameterOrder() {

        return parameterOrder;
    }

    public void setParameterOrder(Object parameterOrder) {

        this.parameterOrder = parameterOrder;
    }
}
