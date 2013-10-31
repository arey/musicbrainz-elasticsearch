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

import static com.javaetmoi.elasticsearch.musicbrainz.domain.TagConstants.*;

import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;

import com.ninja_squad.dbsetup.operation.Insert;
import com.ninja_squad.dbsetup.operation.Operation;

public final class StaticDataOperations {

    public static final int AREA_IRELAND_ID = 103;

	public static final int ARTIST_TYPE_GROUP_ID = 2;

	private static final int AREA_TYPE_COUNTRY = 1;	
	
	private StaticDataOperations() {
		// Utility class
	}
	
	public static final Insert RELEASE_GROUPE_PRIMARY_TYPE_ROWS =
			insertInto("release_group_primary_type")
	                .columns("id", "name")
	                .values(1, "Album")
	                .values(2, "Single")
	                .values(3, "EP")
	                .values(4, "Other")
	                .values(5, "Broadcast")
	                .build();
	
	public static final Insert RELEASE_GROUPE_SECONDARY_TYPE_ROWS =
			insertInto("release_group_secondary_type")
	                .columns("id", "name")
	                .values(1, "Compilation")
	                .values(2, "Soundtrack")
	                .values(3, "Spokenword")
	                .values(4, "Interview")
	                .values(5, "Audiobook")
	                .values(6, "Live")
	                .values(7, "Remix")
	                .values(8, "DJ-mix")
	                .values(9, "Mixtape/Street")
	                .build();	

	public static final Insert ARTIST_TYPE_ROWS =
			insertInto("artist_type")
	                .columns("id", "name")
	                .values(1, "Person")
	                .values(ARTIST_TYPE_GROUP_ID, "Group")
	                .values(3, "Special MusicBrainz Artist")
	                .build();


	public static final Insert AREA_TYPE_ROWS =
			insertInto("area_type")
	                .columns("id", "name")
	                .values(AREA_TYPE_COUNTRY, "Country")
	                .build();
	
	public static final Insert AREA_ROWS =
			insertInto("area")
	                .columns("id", "gid", "name", "sort_name", "type")
	                .values(AREA_IRELAND_ID, "390b05d4-11ec-3bce-a343-703a366b34a5", "Ireland", "Ireland", AREA_TYPE_COUNTRY)
	                .values(221, "8a754a16-0027-3a29-b6d7-2b40ea0481ed", "United Kingdom", "United Kingdom", AREA_TYPE_COUNTRY)
	                .values(222, "489ce91b-6658-3307-9877-795b68554c98", "United States", "United States", AREA_TYPE_COUNTRY)
	                .build();
	
	public static final Insert GENDER_ROWS =
			insertInto("gender")
	                .columns("id", "name")
	                .values(1, "Male")
	                .values(2, "Female")
	                .build();
	
    public static final Insert TAG_ROWS =
            insertInto("tag")
                    .columns("id", "name")
                    .values(TAG_ROCK_ID, "rock")
                    .values(TAG_ELECTRONIC_ID, "electronic")
                    .values(TAG_POP_ID, "pop")
                    .values(TAG_ALTERNATIVE_ROCK_ID, "alternative rock")
                    .values(TAG_BLUES_ID, "blues")
                    .values(TAG_EXPERIMENTAL_ID, "experimental")
                    .values(TAG_IRELAND_ID, "ireland")
                    .values(TAG_POP_ROCK_ID, "pop rock")
                    .values(TAG_ALTERNATIVE_ID, "alternative")
                    .values(TAG_ALTERNATIVE_POP_ROCK_ID, "alternative pop/rock")
                    .build();
	
	
	public static final Operation ALL_STATIC_DATA_ROWS = sequenceOf(RELEASE_GROUPE_PRIMARY_TYPE_ROWS,
																	RELEASE_GROUPE_SECONDARY_TYPE_ROWS,
																	ARTIST_TYPE_ROWS,
																	AREA_TYPE_ROWS,
																	AREA_ROWS,
																	GENDER_ROWS,
																	TAG_ROWS
																	);	
}
