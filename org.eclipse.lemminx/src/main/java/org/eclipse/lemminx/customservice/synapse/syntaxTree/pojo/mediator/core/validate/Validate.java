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

package org.eclipse.lemminx.customservice.synapse.syntaxTree.pojo.mediator.core.validate;

import org.eclipse.lemminx.customservice.synapse.syntaxTree.pojo.mediator.Mediator;
import org.eclipse.lemminx.customservice.synapse.syntaxTree.pojo.mediator.core.Feature;

public class Validate extends Mediator {

    ValidateProperty[] property;
    ValidateSchema[] schema;
    ValidateOnFail onFail;
    Feature[] feature;
    ValidateResource[] resource;
    boolean cacheSchema;
    String source;
    String description;

    public ValidateProperty[] getProperty() {

        return property;
    }

    public void setProperty(ValidateProperty[] property) {

        this.property = property;
    }

    public ValidateSchema[] getSchema() {

        return schema;
    }

    public void setSchema(ValidateSchema[] schema) {

        this.schema = schema;
    }

    public ValidateOnFail getOnFail() {

        return onFail;
    }

    public void setOnFail(ValidateOnFail onFail) {

        this.onFail = onFail;
    }

    public Feature[] getFeature() {

        return feature;
    }

    public void setFeature(Feature[] feature) {

        this.feature = feature;
    }

    public ValidateResource[] getResource() {

        return resource;
    }

    public void setResource(ValidateResource[] resource) {

        this.resource = resource;
    }

    public boolean isCacheSchema() {

        return cacheSchema;
    }

    public void setCacheSchema(boolean cacheSchema) {

        this.cacheSchema = cacheSchema;
    }

    public String getSource() {

        return source;
    }

    public void setSource(String source) {

        this.source = source;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }
}
