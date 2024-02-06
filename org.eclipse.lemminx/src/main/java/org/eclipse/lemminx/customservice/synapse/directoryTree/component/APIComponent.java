/*
 * Copyright (c) 2024, WSO2 LLC. (http://www.wso2.com).
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

package org.eclipse.lemminx.customservice.synapse.directoryTree.component;

import java.util.ArrayList;
import java.util.List;

public class APIComponent extends AdvancedComponent {

    private List<APIResource> resources = new ArrayList<>();

    public APIComponent(String type, String name, String path) {

        super(type, name, path);
    }

    public APIComponent(SimpleComponent component) {

        super(component);
    }

    public List<APIResource> getResources() {

        return resources;
    }

    public void setResources(List<APIResource> resources) {

        this.resources = resources;
    }

    public void addResource(APIResource resource) {

        resources.add(resource);
    }
}
