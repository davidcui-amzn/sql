/*
 *   Copyright 2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License").
 *   You may not use this file except in compliance with the License.
 *   A copy of the License is located at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   or in the "license" file accompanying this file. This file is distributed
 *   on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 *   express or implied. See the License for the specific language governing
 *   permissions and limitations under the License.
 */

package com.amazon.opendistroforelasticsearch.sql.ppl.plans.logical;

import com.amazon.opendistroforelasticsearch.sql.ppl.node.AbstractNodeVisitor;
import com.amazon.opendistroforelasticsearch.sql.ppl.plans.expression.Expression;
import com.google.common.collect.ImmutableList;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Logical plan node of Filter, the interface for building filters in queries
 */
@ToString
@EqualsAndHashCode(callSuper = false)
@Getter
public class Filter extends UnresolvedPlan {
    private Expression condition;
    private UnresolvedPlan child;

    public Filter(Expression condition) {
        this.condition = condition;
    }

    @Override
    public Filter attach(UnresolvedPlan child) {
        this.child = child;
        return this;
    }

    @Override
    public List<UnresolvedPlan> getChild() {
        return ImmutableList.of(child);
    }

    @Override
    public <T, C> T accept(AbstractNodeVisitor<T, C> nodeVisitor, C context) {
        return nodeVisitor.visitFilter(this, context);
    }
}
