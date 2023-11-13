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

package org.eclipse.lemminx.customservice.syntaxmodel.pojo.mediator.transformation.rewrite;

import org.eclipse.lemminx.customservice.syntaxmodel.pojo.STNode;
import org.eclipse.lemminx.customservice.syntaxmodel.pojo.misc.evaluators.And;
import org.eclipse.lemminx.customservice.syntaxmodel.pojo.misc.evaluators.Equal;
import org.eclipse.lemminx.customservice.syntaxmodel.pojo.misc.evaluators.Not;
import org.eclipse.lemminx.customservice.syntaxmodel.pojo.misc.evaluators.Or;

public class RewriteRewriteruleCondition extends STNode {

    And and;
    Or or;
    Equal equal;
    Not not;

    public And getAnd() {

        return and;
    }

    public void setAnd(And and) {

        this.and = and;
    }

    public Or getOr() {

        return or;
    }

    public void setOr(Or or) {

        this.or = or;
    }

    public Equal getEqual() {

        return equal;
    }

    public void setEqual(Equal equal) {

        this.equal = equal;
    }

    public Not getNot() {

        return not;
    }

    public void setNot(Not not) {

        this.not = not;
    }
}
