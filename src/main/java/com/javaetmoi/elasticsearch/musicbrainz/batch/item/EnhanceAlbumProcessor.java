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
package com.javaetmoi.elasticsearch.musicbrainz.batch.item;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.javaetmoi.elasticsearch.musicbrainz.domain.Album;


public class EnhanceAlbumProcessor  implements ItemProcessor<Album, Album> {
    
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private EnhanceAlbumProcessor(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Album process(Album album) throws Exception {
        // Add to the album the top five tags
        String sql = "select t.name from release_group_tag rgt " 
                +" inner join tag t on rgt.tag = t.id"
                +" where release_group=?"
                +" order by rgt.count desc"
                +" limit 5";
        List<String> tags = jdbcTemplate.queryForList(sql, String.class, album.getId());
        album.setTags(tags);
        return album;
    }

}
