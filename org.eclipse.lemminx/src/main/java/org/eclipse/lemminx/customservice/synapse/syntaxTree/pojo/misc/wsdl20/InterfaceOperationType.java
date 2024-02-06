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

package org.eclipse.lemminx.customservice.synapse.syntaxTree.pojo.misc.wsdl20;

import java.util.List;

public class InterfaceOperationType extends ExtensibleDocumentedType {

    Object otherAttributes;
    List<Object> inputOrOutputOrInfault;
    String name;
    String pattern;
    boolean safe;
    String style;

    @Override
    public Object getOtherAttributes() {

        return otherAttributes;
    }

    @Override
    public void setOtherAttributes(Object otherAttributes) {

        this.otherAttributes = otherAttributes;
    }

    public List<Object> getInputOrOutputOrInfault() {

        return inputOrOutputOrInfault;
    }

    public void setInputOrOutputOrInfault(List<Object> inputOrOutputOrInfault) {

        this.inputOrOutputOrInfault = inputOrOutputOrInfault;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getPattern() {

        return pattern;
    }

    public void setPattern(String pattern) {

        this.pattern = pattern;
    }

    public boolean isSafe() {

        return safe;
    }

    public void setSafe(boolean safe) {

        this.safe = safe;
    }

    public String getStyle() {

        return style;
    }

    public void setStyle(String style) {

        this.style = style;
    }
}