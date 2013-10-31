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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.ExitCodeMapper;
import org.springframework.batch.core.launch.support.JvmSystemExiter;
import org.springframework.batch.core.launch.support.SimpleJvmExitCodeMapper;
import org.springframework.batch.core.launch.support.SystemExiter;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Main class indexing music albums from the MusicBrainz database into anElasticsearch index.
 * <p>
 * Like freedb, [MusicBrainz](http://musicbrainz.org/ "MusicBrainz Home") is an open music
 * encyclopedia that collects music metadata and makes it available to the public.
 * </p>
 * <p>
 * This javabatch indexes release groups of the MusicBrainz database into anElasticsearch index.<br/>
 * From release groups, only "real" Album are indexed. Single, EP and Broadcast are not indexed. And
 * from Album release group primary type, neither Compilation, Live, Remix or Soundtrack secondary
 * types are indexed.
 * </p>
 * 
 * @see https://github.com/arey/musicbrainz-elasticsearch/blob/master/readme.md
 * 
 */
public class IndexBatchMain {

    private static final Logger   LOG            = LoggerFactory.getLogger(IndexBatchMain.class);

    private static ExitCodeMapper exitCodeMapper = new SimpleJvmExitCodeMapper();

    private static SystemExiter   systemExiter   = new JvmSystemExiter();

    public static void main(String... args) {
        AbstractApplicationContext context = null;
        try {
            context = new ClassPathXmlApplicationContext(new String[] {
                    "com/javaetmoi/elasticsearch/musicbrainz/batch/applicationContext-datasource.xml",
                    "com/javaetmoi/elasticsearch/musicbrainz/batch/applicationContext-elasticsearch.xml",
                    "com/javaetmoi/elasticsearch/musicbrainz/batch/applicationContext-batch.xml" });

            JobLauncher jobLauncher = context.getBean(JobLauncher.class);
            Job musicAlbumJob = context.getBean("musicAlbumJob", Job.class);
            jobLauncher.run(musicAlbumJob, new JobParameters());
        } catch (Throwable e) {
            String message = "Job Terminated in error: " + e.getMessage();
            LOG.error(message, e);
            systemExiter.exit(exitCodeMapper.intValue(ExitStatus.FAILED.getExitCode()));
        } finally {
            if (context != null) {
                context.close();
            }
        }
        systemExiter.exit(exitCodeMapper.intValue(ExitStatus.COMPLETED.getExitCode()));
    }

}
