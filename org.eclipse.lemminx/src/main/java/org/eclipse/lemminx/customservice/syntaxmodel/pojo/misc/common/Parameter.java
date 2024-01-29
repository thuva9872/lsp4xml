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

package org.eclipse.lemminx.customservice.syntaxmodel.pojo.misc.common;

import org.eclipse.lemminx.customservice.syntaxmodel.pojo.STNode;

public class Parameter extends STNode {

    Object otherAttributes;
    Object[] content;
    String name;
    String key;
    boolean locked;

    public Object getOtherAttributes() {

        return otherAttributes;
    }

    public void setOtherAttributes(Object otherAttributes) {

        this.otherAttributes = otherAttributes;
    }

    public Object[] getContent() {

        return content;
    }

    public void setContent(Object[] content) {

        this.content = content;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getKey() {

        return key;
    }

    public void setKey(String key) {

        this.key = key;
    }

    public boolean isLocked() {

        return locked;
    }

    public void setLocked(boolean locked) {

        this.locked = locked;
    }
}