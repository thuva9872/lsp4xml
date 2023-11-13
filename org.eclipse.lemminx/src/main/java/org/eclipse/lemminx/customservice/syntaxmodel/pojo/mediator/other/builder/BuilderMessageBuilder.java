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

package org.eclipse.lemminx.customservice.syntaxmodel.pojo.mediator.other.builder;

import org.eclipse.lemminx.customservice.syntaxmodel.pojo.STNode;

public class BuilderMessageBuilder extends STNode {

    String contentType;
    String clazz;
    String formatterClass;

    public String getContentType() {

        return contentType;
    }

    public void setContentType(String contentType) {

        this.contentType = contentType;
    }

    public String getClazz() {

        return clazz;
    }

    public void setClazz(String clazz) {

        this.clazz = clazz;
    }

    public String getFormatterClass() {

        return formatterClass;
    }

    public void setFormatterClass(String formatterClass) {

        this.formatterClass = formatterClass;
    }
}
