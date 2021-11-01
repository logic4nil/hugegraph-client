/*
 * Copyright 2017 HugeGraph Authors
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership. The ASF
 * licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.baidu.hugegraph.api.traverser;

import java.util.Map;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.baidu.hugegraph.structure.constant.Direction;
import com.baidu.hugegraph.structure.constant.T;
import com.baidu.hugegraph.structure.graph.Vertex;
import com.baidu.hugegraph.structure.traverser.SingleSourceJaccardSimilarityRequest;
import com.baidu.hugegraph.testutil.Assert;
import com.google.common.collect.ImmutableSet;

public class AdamicAdarAPITest extends TraverserApiTest {

    @BeforeClass
    public static void initShortestPathGraph() {
        schema().vertexLabel("node")
                .useCustomizeNumberId()
                .ifNotExist()
                .create();

        schema().edgeLabel("link")
                .sourceLabel("node").targetLabel("node")
                .ifNotExist()
                .create();
        schema().edgeLabel("relateTo")
                .sourceLabel("node").targetLabel("node")
                .ifNotExist()
                .create();

        Vertex v1 = graph().addVertex(T.label, "node", T.id, 1);
        Vertex v2 = graph().addVertex(T.label, "node", T.id, 2);
        Vertex v3 = graph().addVertex(T.label, "node", T.id, 3);
        Vertex v4 = graph().addVertex(T.label, "node", T.id, 4);
        Vertex v5 = graph().addVertex(T.label, "node", T.id, 5);
        Vertex v6 = graph().addVertex(T.label, "node", T.id, 6);
        Vertex v7 = graph().addVertex(T.label, "node", T.id, 7);
        Vertex v8 = graph().addVertex(T.label, "node", T.id, 8);
        Vertex v9 = graph().addVertex(T.label, "node", T.id, 9);
        Vertex v10 = graph().addVertex(T.label, "node", T.id, 10);

        v1.addEdge("link", v3);
        v2.addEdge("link", v3);
        v4.addEdge("link", v1);
        v4.addEdge("link", v2);

        v1.addEdge("relateTo", v5);
        v2.addEdge("relateTo", v5);
        v6.addEdge("relateTo", v1);
        v6.addEdge("relateTo", v2);

        v1.addEdge("link", v7);
        v8.addEdge("link", v1);
        v2.addEdge("link", v9);
        v10.addEdge("link", v2);
    }

    @Test
    public void testAdamicAdar() {
        double aa = adamicAdarAPI.get(1, 2, Direction.BOTH,
                                           null, -1);
        Assert.assertEquals(5.7707801635558535D, aa, Double.MIN_VALUE);
    }

    @Test
    public void testJAdamicAdarWithDirection() {
        double aa = adamicAdarAPI.get(1, 2, Direction.OUT,
                                           null, -1);
        Assert.assertEquals(0.0D, aa, Double.MIN_VALUE);

        aa = adamicAdarAPI.get(4, 8, Direction.OUT,
                                    null, -1);
        Assert.assertEquals(0.9102392266268373D, aa, Double.MIN_VALUE);
    }

    @Test
    public void testAdamicAdarWithLabel() {
        double aa = adamicAdarAPI.get(1, 2, Direction.BOTH,
                                           "link", -1);
        Assert.assertEquals(2.8853900817779268D, aa, Double.MIN_VALUE);

        aa = adamicAdarAPI.get(1, 2, Direction.OUT,
                                    "link", -1);
        Assert.assertEquals(0.0D, aa, Double.MIN_VALUE);

        aa = adamicAdarAPI.get(4, 7, Direction.BOTH,
                                    "link", -1);
        Assert.assertEquals(0.7213475204444817D, aa, Double.MIN_VALUE);

        aa = adamicAdarAPI.get(1, 2, Direction.BOTH,
                                    "relateTo", -1);
        Assert.assertEquals(2.8853900817779268D, aa, Double.MIN_VALUE);

        aa = adamicAdarAPI.get(1, 2, Direction.OUT,
                                    "relateTo", -1);
        Assert.assertEquals(0.0D, aa, Double.MIN_VALUE);

        aa = adamicAdarAPI.get(1, 2, Direction.IN,
                                    "relateTo", -1);
        Assert.assertEquals(0.0D, aa, Double.MIN_VALUE);
    }

    @Test
    public void testAdamicAdarWithDegree() {
        double aa = adamicAdarAPI.get(1, 2, Direction.BOTH,
                                           null, 3);
        Assert.assertEquals(2.8853900817779268D, aa, Double.MIN_VALUE);

        aa = adamicAdarAPI.get(1, 2, Direction.BOTH,
                                    null, 1);
        Assert.assertEquals(1.4426950408889634D, aa, Double.MIN_VALUE);
    }

    // Test until support post method in adamic adar
    @Ignore
    public void testJAdamicAdar() {
        SingleSourceJaccardSimilarityRequest.Builder builder =
                SingleSourceJaccardSimilarityRequest.builder();
        builder.vertex(4);
        builder.step().direction(Direction.BOTH);
        SingleSourceJaccardSimilarityRequest request = builder.build();
        Map<Object, Double> results = adamicAdarAPI.post(request);

        Assert.assertEquals(9, results.size());
        Set<Object> expected = ImmutableSet.of("1", "2", "3", "5", "6",
                                               "7", "8", "9", "10");
        Assert.assertEquals(expected, results.keySet());

        Assert.assertEquals(1.0, results.get("3"));
        Assert.assertEquals(1.0, results.get("5"));
        Assert.assertEquals(1.0, results.get("6"));
        Assert.assertEquals(0.5, results.get("7"));
        Assert.assertEquals(0.5, results.get("8"));
        Assert.assertEquals(0.5, results.get("9"));
        Assert.assertEquals(0.5, results.get("10"));
        Assert.assertEquals(0.0, results.get("1"));
        Assert.assertEquals(0.0, results.get("2"));
    }

    @Ignore
    public void testAdamicAdarWithTop() {
        SingleSourceJaccardSimilarityRequest.Builder builder =
                SingleSourceJaccardSimilarityRequest.builder();
        builder.vertex(4);
        builder.step().direction(Direction.BOTH);
        builder.top(5);
        SingleSourceJaccardSimilarityRequest request = builder.build();
        Map<Object, Double> results = adamicAdarAPI.post(request);

        Assert.assertEquals(5, results.size());
        Set<Object> expected = ImmutableSet.of("3", "5", "6", "7", "8");
        Assert.assertEquals(expected, results.keySet());

        Assert.assertEquals(1.0, results.get("3"));
        Assert.assertEquals(1.0, results.get("5"));
        Assert.assertEquals(1.0, results.get("6"));
        Assert.assertEquals(0.5, results.get("7"));
        Assert.assertEquals(0.5, results.get("8"));
    }

    @Ignore
    public void testAdamicAdarWithLabelPost() {
        SingleSourceJaccardSimilarityRequest.Builder builder =
                SingleSourceJaccardSimilarityRequest.builder();
        builder.vertex(4);
        builder.step().direction(Direction.BOTH).labels("link");
        SingleSourceJaccardSimilarityRequest request = builder.build();
        Map<Object, Double> results = adamicAdarAPI.post(request);

        Assert.assertEquals(7, results.size());
        Set<Object> expected = ImmutableSet.of("3", "7", "8", "9",
                                               "10", "1", "2");
        Assert.assertEquals(expected, results.keySet());

        Assert.assertEquals(1.0, results.get("3"));
        Assert.assertEquals(0.5, results.get("7"));
        Assert.assertEquals(0.5, results.get("8"));
        Assert.assertEquals(0.5, results.get("9"));
        Assert.assertEquals(0.5, results.get("10"));
        Assert.assertEquals(0.0, results.get("1"));
        Assert.assertEquals(0.0, results.get("2"));
    }

    @Ignore
    public void testAdamicAdarWithDirection() {
        SingleSourceJaccardSimilarityRequest.Builder builder =
                SingleSourceJaccardSimilarityRequest.builder();
        builder.vertex(4);
        builder.step().direction(Direction.OUT);
        SingleSourceJaccardSimilarityRequest request = builder.build();
        Map<Object, Double> results = adamicAdarAPI.post(request);

        Assert.assertEquals(6, results.size());
        Set<Object> expected = ImmutableSet.of("1", "2", "3",
                                               "5", "7", "9");
        Assert.assertEquals(expected, results.keySet());

        Assert.assertEquals(0.0, results.get("3"));
        Assert.assertEquals(0.0, results.get("5"));
        Assert.assertEquals(0.0, results.get("7"));
        Assert.assertEquals(0.0, results.get("9"));
        Assert.assertEquals(0.0, results.get("1"));
        Assert.assertEquals(0.0, results.get("2"));
    }
}