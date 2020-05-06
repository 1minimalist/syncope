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
package org.apache.syncope.wa.starter.mapping;

import org.apache.syncope.common.lib.policy.AccessPolicyConf;
import org.apache.syncope.common.lib.policy.DefaultAccessPolicyConf;
import org.apereo.cas.services.DefaultRegisteredServiceAccessStrategy;
import org.apereo.cas.services.RegisteredServiceAccessStrategy;
import org.springframework.stereotype.Component;

@AccessMapFor(
        accessPolicyConfClass = DefaultAccessPolicyConf.class,
        registeredServiceAccessStrategyClass = DefaultRegisteredServiceAccessStrategy.class)
@Component
public class DefaultAccessMapper implements AccessMapper {

    @Override
    public RegisteredServiceAccessStrategy build(final AccessPolicyConf conf) {
        RegisteredServiceAccessStrategy accessStrategy =
                new DefaultRegisteredServiceAccessStrategy(conf.isEnabled(), conf.isSsoEnabled());
        accessStrategy.getRequiredAttributes().putAll(conf.getRequiredAttrs());
        return accessStrategy;
    }

    @Override
    public AccessPolicyConf build(final RegisteredServiceAccessStrategy strategy) {
        DefaultAccessPolicyConf conf = new DefaultAccessPolicyConf();
        conf.setEnabled(((DefaultRegisteredServiceAccessStrategy) strategy).isEnabled());
        conf.setSsoEnabled(((DefaultRegisteredServiceAccessStrategy) strategy).isSsoEnabled());
        conf.getRequiredAttrs().putAll(((DefaultRegisteredServiceAccessStrategy) strategy).getRejectedAttributes());
        return conf;
    }
}
