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
package com.javaetmoi.elasticsearch.musicbrainz.batch.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.javaetmoi.elasticsearch.musicbrainz.domain.ArtistType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.javaetmoi.elasticsearch.musicbrainz.domain.Album;
import com.javaetmoi.elasticsearch.musicbrainz.domain.Artist;

import fm.last.musicbrainz.data.model.Gender;
import fm.last.musicbrainz.data.model.ReleaseGroupPrimaryType;

@Component
public class AlbumRowMapper implements RowMapper<Album> {

    @Override
    public Album mapRow(ResultSet rs, int rowNum) throws SQLException {
        Album album = new Album();
        album.setId(rs.getInt("albumId"));
        album.setGid(rs.getString("albumGid"));
        album.setName(rs.getString("albumName"));
        album.setType(ReleaseGroupPrimaryType.valueOf(rs.getInt("albumPrimaryTypeId")));
        album.setYear(rs.getInt("albumYear"));
        album.getRating().setScore(rs.getInt("albumRatingScore"));
        album.getRating().setCount(rs.getInt("albumRatingCount"));

        Artist artist = album.getArtist();
        artist.setGid(rs.getString("artistGid"));
        artist.setName(rs.getString("artistName"));
        artist.setBeginDateYear(rs.getString("artistBeginDateYear"));
        if (rs.getObject("artistTypeId") != null) {
            artist.setType(ArtistType.valueOf(rs.getInt("artistTypeId")));
        }
        if (rs.getObject("artistGenderId") != null) {
            artist.setGender(Gender.valueOf(rs.getInt("artistGenderId")));
        }
        artist.setCountry(rs.getString("artistCountryName"));
        artist.getRating().setScore(rs.getInt("artistRatingScore"));
        artist.getRating().setCount(rs.getInt("artistRatingCount"));

        return album;
    }
}