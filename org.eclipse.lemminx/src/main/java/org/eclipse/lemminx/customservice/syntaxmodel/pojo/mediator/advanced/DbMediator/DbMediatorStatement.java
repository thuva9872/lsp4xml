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

package org.eclipse.lemminx.customservice.syntaxmodel.pojo.mediator.advanced.DbMediator;

import org.eclipse.lemminx.customservice.syntaxmodel.pojo.STNode;

public class DbMediatorStatement extends STNode {

    String sql;
    DbMediatorStatementParameter[] parameter;
    DbMediatorStatementResult[] result;

    public String getSql() {

        return sql;
    }

    public void setSql(String sql) {

        this.sql = sql;
    }

    public DbMediatorStatementParameter[] getParameter() {

        return parameter;
    }

    public void setParameter(DbMediatorStatementParameter[] parameter) {

        this.parameter = parameter;
    }

    public DbMediatorStatementResult[] getResult() {

        return result;
    }

    public void setResult(DbMediatorStatementResult[] result) {

        this.result = result;
    }
}