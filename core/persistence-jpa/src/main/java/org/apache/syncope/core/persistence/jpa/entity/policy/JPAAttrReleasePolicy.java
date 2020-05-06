/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.syncope.core.persistence.jpa.entity.policy;

import java.util.Optional;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import org.apache.syncope.common.lib.policy.AttrReleasePolicyConf;
import org.apache.syncope.core.persistence.api.entity.policy.AttrReleasePolicy;
import org.apache.syncope.core.provisioning.api.serialization.POJOHelper;

@Entity
@Table(name = JPAAttrReleasePolicy.TABLE)
public class JPAAttrReleasePolicy extends AbstractPolicy implements AttrReleasePolicy {

    private static final long serialVersionUID = -4190607669908888884L;

    public static final String TABLE = "AttrReleasePolicy";

    @Lob
    private String jsonConf;

    @Override
    public AttrReleasePolicyConf getConf() {
        return jsonConf == null
                ? null
                : POJOHelper.deserialize(jsonConf, AttrReleasePolicyConf.class);
    }

    @Override
    public void setConf(final AttrReleasePolicyConf conf) {
        jsonConf = Optional.ofNullable(conf).map(POJOHelper::serialize).orElse(null);
    }
}
