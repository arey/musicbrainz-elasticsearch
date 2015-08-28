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

import static com.javaetmoi.elasticsearch.musicbrainz.batch.StaticDataOperations.AREA_IRELAND_ID;
import static com.javaetmoi.elasticsearch.musicbrainz.domain.TagConstants.*;

import static com.javaetmoi.elasticsearch.musicbrainz.batch.StaticDataOperations.ARTIST_TYPE_GROUP_ID;
import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;

import com.ninja_squad.dbsetup.operation.Insert;
import com.ninja_squad.dbsetup.operation.Operation;

public class ArtistU2Operations {
	
	private static final int RELEASE_NAME_WAR_ID = 172891;

	private static final int RELEASE_GROUP_WAR_ID = 1281;

	private static final int ARTIST_ID_U2 = 197;

	private static final int ARTIST_CREDIT_U2_ID = ARTIST_ID_U2;

	private static final int RELEASE_GROUP_OCTOBER_ID = 3941;

	private static final int RELEASE_GROUP_CHILDREN_REVOLUTION_ID = 484375;

	private static final int RELEASE_GROUP_BOY_ID = 28463;

	private static final int RELEASE_GROUP_POP_ID = 245642;

	private static final int RELEASE_GROUP_NO_LINE_HORIZON_ID = 814478;

	private static final int RELEASE_GROUP_ZOOROPA_ID = 20423;

	private static final int RELEASE_GROUP_JOSHUA_TREE_ID = 20435;

	private static final int RELEASE_GROUP_ALL_CANT_LEAVE_BEHIND_ID = 558565;

	private static final int RELEASE_GROUP_UNFORGETTABLE_FIRE_ID = 17857;

	private static final int RELEASE_GROUP_ACHTUNG_BABY_ID = 28502;

	private static final int RELEASE_GROUP_HOW_DISMANTLE_ATOMIC_BOMB_ID = 283473;

	private static final int RELEASE_GROUP_RATTLE_HUM_ID = 17817;
	
	
	private ArtistU2Operations() {
		// Utility class
	}
		
	
	public static final Operation RELEASE_GROUP_U2_ROWS =
		 sequenceOf(
				insertArtist(),
				insertArtistMeta(),
				insertArtistCredit(),
				insertArtistCreditName(),
				insertReleaseGroup(),
				insertReleaseGroupMeta(),
				insertReleaseGroupTag());
	

	
	private static Operation insertArtist() {
        return insertInto("artist")
	            .columns("id", "gid", "name", "sort_name", "begin_date_year", "type", "area", "gender")
	            .values(ARTIST_ID_U2, "a3cb23fc-acd3-4ce0-8f36-1e5aa6a18432", "U2", "U2", 1976, ARTIST_TYPE_GROUP_ID, AREA_IRELAND_ID, null)
	            .build();
	}
	
	private static Operation insertArtistMeta() {
        return insertInto("artist_meta")
	            .columns("id", "rating", "rating_count")
	            .values(ARTIST_ID_U2, 87, 21)
	            .build();
	}
	
	private static Operation insertArtistCredit() {
        return insertInto("artist_credit")
	            .columns("id", "name", "artist_count")
	            .values(ARTIST_CREDIT_U2_ID, "U2", 1)
	            .build();
	}
	
	private static Operation insertArtistCreditName() {
        return insertInto("artist_credit_name")
        		.withDefaultValue("position", 1)
	            .columns("artist_credit", "artist", "name")
	            .values(ARTIST_CREDIT_U2_ID, ARTIST_ID_U2, "U2")
	            .build();
	}		
	

	private static Insert insertReleaseGroup() {
	        return insertInto("release_group")
	            .columns("id", "gid", "name")
	            .withDefaultValue("type", 1)
	            .withDefaultValue("artist_credit", ARTIST_CREDIT_U2_ID)
	            .values(RELEASE_GROUP_WAR_ID, "c6b36664-7e60-3b3e-a24d-d096c67a11e9", "War")
	            .values(RELEASE_GROUP_OCTOBER_ID, "87febb2c-e117-3a0d-a7f6-8fd9a6421af1", "October")
	            .values(RELEASE_GROUP_CHILDREN_REVOLUTION_ID, "19b0ac91-f0eb-3ac9-8187-f602039e4090", "Children of the Revolution")
	            .values(RELEASE_GROUP_BOY_ID, "53b0d89e-f856-3015-a3a6-a70e1e935fd6", "Boy")
	            .values(RELEASE_GROUP_POP_ID, "5eca5ea6-841b-3835-b368-94fb71d8f932", "Pop")
	            .values(RELEASE_GROUP_NO_LINE_HORIZON_ID, "acaa5e04-685c-3e47-af62-1cd3012008b6", "No Line on the Horizon")
	            .values(RELEASE_GROUP_ZOOROPA_ID, "7c705df7-46e7-3c27-9693-72b3ea559c48", "Zooropa")
	            .values(RELEASE_GROUP_JOSHUA_TREE_ID, "6f3e9fa6-be7a-3de8-a2b2-2072ece8a54d", "The Joshua Tree")
	            .values(RELEASE_GROUP_ALL_CANT_LEAVE_BEHIND_ID, "88c59b55-200d-3ac1-beff-8eb99830807e", "All That You Can't Leave Behind")
	            .values(RELEASE_GROUP_UNFORGETTABLE_FIRE_ID, "c84d00f4-afd3-3184-be62-6ae5f135aa38", "The Unforgettable Fire")
	            .values(RELEASE_GROUP_ACHTUNG_BABY_ID, "744c7a1b-ac79-35c4-bd92-7e2c6a24c8d8", "Achtung Baby")
	            .values(RELEASE_GROUP_HOW_DISMANTLE_ATOMIC_BOMB_ID, "3ab33753-c16f-3e44-ae67-2fba4fd0e823", "How to Dismantle an Atomic Bomb")
	            .values(RELEASE_GROUP_RATTLE_HUM_ID, "bf965ead-7c91-3a9b-93c8-cf941242ee65", "Rattle and Hum")
	            .build();
	}
	
	private static Operation insertReleaseGroupMeta() {
        return insertInto("release_group_meta")
        		.withDefaultValue("first_release_date_month", 1)
        		.withDefaultValue("first_release_date_day", 1)
	            .columns("id", "first_release_date_year", "rating", "rating_count")
	            .values(RELEASE_GROUP_WAR_ID, 1983, 79, 9)
	            .values(RELEASE_GROUP_OCTOBER_ID, 1981, 69, 7)
	            .values(RELEASE_GROUP_CHILDREN_REVOLUTION_ID, 2002, null, null)
	            .values(RELEASE_GROUP_BOY_ID, 1980, 86, 7)
	            .values(RELEASE_GROUP_POP_ID, 1997, 87, 3)
	            .values(RELEASE_GROUP_NO_LINE_HORIZON_ID, 2009, 63, 7)
	            .values(RELEASE_GROUP_ZOOROPA_ID, 1993, 78, 8)
	            .values(RELEASE_GROUP_JOSHUA_TREE_ID, 1987, 80, 9)
	            .values(RELEASE_GROUP_ALL_CANT_LEAVE_BEHIND_ID, 2000, 63, 7)
	            .values(RELEASE_GROUP_UNFORGETTABLE_FIRE_ID, 1984, 80, 10)
	            .values(RELEASE_GROUP_HOW_DISMANTLE_ATOMIC_BOMB_ID, 2004, 70, 8)
	            .values(RELEASE_GROUP_RATTLE_HUM_ID, 1988, 78, 8)
	            .build();
	}
	
	private static Operation insertReleaseGroupTag() {
        return insertInto("release_group_tag")
                .withDefaultValue("count", 1)
                .columns("release_group", "tag")
                .values(RELEASE_GROUP_WAR_ID, TAG_ROCK_ID)
                .values(RELEASE_GROUP_WAR_ID, TAG_ALTERNATIVE_POP_ROCK_ID)
                .values(RELEASE_GROUP_OCTOBER_ID, TAG_ROCK_ID)
                .values(RELEASE_GROUP_BOY_ID, TAG_ROCK_ID)
                .values(RELEASE_GROUP_BOY_ID, TAG_ALTERNATIVE_ID)
                .values(RELEASE_GROUP_POP_ID, TAG_ELECTRONIC_ID)
                .values(RELEASE_GROUP_POP_ID, TAG_EXPERIMENTAL_ID)
                .values(RELEASE_GROUP_POP_ID, TAG_ROCK_ID)
                .values(RELEASE_GROUP_POP_ID, TAG_POP_ROCK_ID)
                .values(RELEASE_GROUP_NO_LINE_HORIZON_ID, TAG_ROCK_ID)
                .values(RELEASE_GROUP_NO_LINE_HORIZON_ID, TAG_POP_ID)
                .values(RELEASE_GROUP_ZOOROPA_ID, TAG_ROCK_ID)
                .values(RELEASE_GROUP_ZOOROPA_ID, TAG_POP_ID)
                .values(RELEASE_GROUP_JOSHUA_TREE_ID, TAG_ROCK_ID)
                .values(RELEASE_GROUP_JOSHUA_TREE_ID, TAG_ALTERNATIVE_POP_ROCK_ID)
                .values(RELEASE_GROUP_JOSHUA_TREE_ID, TAG_IRELAND_ID)
                .values(RELEASE_GROUP_JOSHUA_TREE_ID, TAG_BLUES_ID)
                .values(RELEASE_GROUP_ALL_CANT_LEAVE_BEHIND_ID, TAG_ROCK_ID)
                .values(RELEASE_GROUP_ALL_CANT_LEAVE_BEHIND_ID, TAG_POP_ROCK_ID)
                .values(RELEASE_GROUP_UNFORGETTABLE_FIRE_ID, TAG_ROCK_ID)
                .values(RELEASE_GROUP_UNFORGETTABLE_FIRE_ID, TAG_POP_ROCK_ID)
                .values(RELEASE_GROUP_HOW_DISMANTLE_ATOMIC_BOMB_ID, TAG_POP_ID)
                .values(RELEASE_GROUP_HOW_DISMANTLE_ATOMIC_BOMB_ID, TAG_ROCK_ID)
                .values(RELEASE_GROUP_RATTLE_HUM_ID, TAG_ROCK_ID)
                .values(RELEASE_GROUP_RATTLE_HUM_ID, TAG_ALTERNATIVE_ROCK_ID)
                .build();
    }

}
