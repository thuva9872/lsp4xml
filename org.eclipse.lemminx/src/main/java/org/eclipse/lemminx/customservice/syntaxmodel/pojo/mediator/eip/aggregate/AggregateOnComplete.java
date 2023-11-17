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

package org.eclipse.lemminx.customservice.syntaxmodel.pojo.mediator.eip.aggregate;

import org.eclipse.lemminx.customservice.syntaxmodel.pojo.STNode;
import org.eclipse.lemminx.customservice.syntaxmodel.pojo.mediator.Mediator;

import java.util.ArrayList;
import java.util.List;

public class AggregateOnComplete extends STNode {

    List<Mediator> mediators;
    String expression;
    String sequenceAttribute;
    String enclosingElementProperty;
    String aggregateElementType;

    public AggregateOnComplete() {

        mediators = new ArrayList<>();
    }

    public List<Mediator> getMediators() {

        return mediators;
    }

    public void setMediators(List<Mediator> mediators) {

        this.mediators = mediators;
    }

    public String getExpression() {

        return expression;
    }

    public void setExpression(String expression) {

        this.expression = expression;
    }

    public String getSequenceAttribute() {

        return sequenceAttribute;
    }

    public void setSequenceAttribute(String sequenceAttribute) {

        this.sequenceAttribute = sequenceAttribute;
    }

    public String getEnclosingElementProperty() {

        return enclosingElementProperty;
    }

    public void setEnclosingElementProperty(String enclosingElementProperty) {

        this.enclosingElementProperty = enclosingElementProperty;
    }

    public String getAggregateElementType() {

        return aggregateElementType;
    }

    public void setAggregateElementType(String aggregateElementType) {

        this.aggregateElementType = aggregateElementType;
    }
}
