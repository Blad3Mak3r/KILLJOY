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

package dev.killjoy.extensions.jda

import dev.killjoy.framework.ColorExtra
import net.dv8tion.jda.api.EmbedBuilder

enum class EmbedColor {
    RED, BLUE
}

fun EmbedBuilder.setDefaultColor(color: EmbedColor = EmbedColor.RED): EmbedBuilder {
    when (color) {
        EmbedColor.RED -> setColor(ColorExtra.VAL_RED)
        EmbedColor.BLUE -> setColor(ColorExtra.VAL_BLUE)
    }
    return this
}