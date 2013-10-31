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
package com.javaetmoi.elasticsearch.musicbrainz.domain;

import java.util.List;

import fm.last.musicbrainz.data.model.ReleaseGroupPrimaryType;

public class Album {
	
	private Integer id;
	
	private String gid;
	
	private String name;
	
	private ReleaseGroupPrimaryType type;
	
	private Integer year;
	
	private Rating rating = new Rating();
	
	private Artist artist = new Artist();
	
	private List<String> tags; 

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ReleaseGroupPrimaryType getType() {
		return type;
	}

	public void setType(ReleaseGroupPrimaryType type) {
		this.type = type;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Rating getRating() {
		return rating;
	}

	public Artist getArtist() {
		return artist;
	}

    
    public List<String> getTags() {
        return tags;
    }

    
    public void setTags(List<String> tags) {
        this.tags = tags;
    }
	
	
	
	

}
