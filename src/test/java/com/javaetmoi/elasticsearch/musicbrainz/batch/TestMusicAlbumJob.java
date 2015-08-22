/**
 * Copyright 2013 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.javaetmoi.elasticsearch.musicbrainz.batch;

import com.javaetmoi.core.elasticsearch.ElasticSearchHelper;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthStatus;
import org.elasticsearch.action.admin.indices.stats.IndicesStatsRequest;
import org.elasticsearch.action.admin.indices.stats.IndicesStatsResponse;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.ClusterAdminClient;
import org.elasticsearch.index.shard.DocsStats;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@DirtiesContext
public class TestMusicAlbumJob {

    @Autowired
    private Client               client;

    @Autowired
    private DataSource           dataSource;

    @Autowired
    private JobLauncherTestUtils testUtils;

    @Before
    public void checkElasticSearchHealth() throws Throwable {
        AdminClient admin = client.admin();
        ClusterAdminClient cluster = admin.cluster();
        ClusterHealthRequest request = new ClusterHealthRequest().waitForGreenStatus();
        ActionFuture<ClusterHealthResponse> health = cluster.health(request);
        ClusterHealthResponse healthResponse = health.get();
        assertEquals(ClusterHealthStatus.GREEN, healthResponse.getStatus());
        
        Operation operation = sequenceOf(StaticDataOperations.ALL_STATIC_DATA_ROWS,
                ArtistU2Operations.RELEASE_GROUP_U2_ROWS);
        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource),
                operation);
        dbSetup.launch();
    }

    @Test
    public void indexMusicAlbum() throws Exception {
        JobExecution execution = testUtils.launchJob();

        // Batch Status
        assertEquals(ExitStatus.COMPLETED, execution.getExitStatus());
        StepExecution indexMusicAlbum = getStepExecution(execution, "indexMusicAlbum");
        assertEquals(13, indexMusicAlbum.getWriteCount());
        assertEquals(2*2,  indexMusicAlbum.getCommitCount());
        
        // Elasticsearch status
        ElasticSearchHelper.refreshIndex(client);
        DocsStats docs = getDocStats();
        assertEquals(13, docs.getCount());
    }

    protected DocsStats getDocStats() {
        IndicesStatsRequest request = new IndicesStatsRequest().docs(true);
        IndicesStatsResponse response = client.admin().indices().stats(request).actionGet();
        return response.getIndex("musicbrainz").getTotal().docs;
    }
        
    
    
    private StepExecution getStepExecution(JobExecution jobExecution, String name) {
        for (StepExecution step : jobExecution.getStepExecutions()) {
            if (step.getStepName().equals(name)) {
                return step;
            }
        }
        return null;
    }

}
