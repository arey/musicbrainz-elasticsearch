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

import fm.last.musicbrainz.data.model.ArtistType;
import fm.last.musicbrainz.data.model.Gender;

public class Artist {
	
	private String gid;
	
	private String name;	
	
	private ArtistType type;
	
	private String beginDateYear;
	
	private Gender gender;
	
	private String country;
	
	private Rating rating = new Rating();

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

	public Rating getRating() {
		return rating;
	}

	public ArtistType getType() {
		return type;
	}

	public void setType(ArtistType type) {
		this.type = type;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getBeginDateYear() {
		return beginDateYear;
	}

	public void setBeginDateYear(String beginDateYear) {
		this.beginDateYear = beginDateYear;
	}

	
	
}
