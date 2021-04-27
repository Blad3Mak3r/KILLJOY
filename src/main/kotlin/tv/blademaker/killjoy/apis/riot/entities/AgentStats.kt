/*******************************************************************************
 * Copyright (c) 2021. Blademaker
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 ******************************************************************************/

/*******************************************************************************
 * Copyright (c) 2021. Blademaker
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 ******************************************************************************/

package tv.blademaker.killjoy.apis.riot.entities

import org.json.JSONObject

data class AgentStats(
    val key: String,
    val pickRate: Double,
    val winRate: Double,
    val avgDamage: Int,
    val avgScore: Int,
    val kd: Double,
    val kdaPerMatch: KDA,
    val kdaPerRound: KDA
) {

    constructor(json: JSONObject) : this (
        json.getString("key"),
        json.getDouble("pickRate"),
        json.getDouble("winRate"),
        json.getInt("damagePerRound"),
        json.getInt("scorePerRound"),
        json.getDouble("kd"),
        KDA(
            json.getDouble("killsPerMatch"),
            json.getDouble("deathsPerMatch"),
            json.getDouble("assistsPerMatch")
        ),
        KDA(
            json.getDouble("killsPerRound"),
            json.getDouble("deathsPerRound"),
            json.getDouble("assistsPerRound")
        )
    )

    data class KDA(
        val kills: Double,
        val deaths: Double,
        val assists: Double
    ) {

        override fun toString(): String {
            return "$kills/$deaths/$assists"
        }
    }
}
