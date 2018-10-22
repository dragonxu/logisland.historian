/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hurence.logisland.historian.repository;

import com.hurence.logisland.historian.rest.v1.model.DataFlowSimple;
import com.hurence.logisland.historian.rest.v1.model.PrivateSelection;
import com.hurence.logisland.historian.rest.v1.model.dashboard.Dashboard;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SolrDashboardRepository extends SolrCrudRepository<Dashboard, String> {

    @Query(value = "*:*", filters = { "owner:?0" })
    List<Dashboard> findByOwner(String owner);

    @Query(value = "*:*", filters = { "name:?0 AND owner:?1" })
    Optional<Dashboard> findByNameAndOwner(String name, String owner);
}