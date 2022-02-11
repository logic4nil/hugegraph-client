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

package com.baidu.hugegraph.api.auth;

import com.baidu.hugegraph.client.RestClient;
import com.baidu.hugegraph.rest.RestResult;
import com.baidu.hugegraph.structure.auth.AuthElement;
import com.baidu.hugegraph.structure.auth.KGLogin;
import com.baidu.hugegraph.structure.auth.KGLoginResult;
import com.baidu.hugegraph.structure.constant.HugeType;

public class KGLoginAPI extends AuthAPI {

    public KGLoginAPI(RestClient client) {
        super(client);
    }

    @Override
    protected String type() {
        return HugeType.KG_LOGIN.string();
    }

    public KGLoginResult kgLogin(KGLogin login) {
        RestResult result = this.client.post(this.path(), login);
        return result.readObject(KGLoginResult.class);
    }

    @Override
    protected Object checkCreateOrUpdate(AuthElement authElement) {
        return null;
    }
}