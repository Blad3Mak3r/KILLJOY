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

package dev.killjoy.commands.game

import dev.killjoy.apis.news.NewsRetriever
import dev.killjoy.extensions.jda.setDefaultColor
import dev.killjoy.extensions.jda.supportedLocale
import dev.killjoy.i18n.i18nCommand
import dev.killjoy.slash.api.AbstractSlashCommand
import dev.killjoy.slash.api.SlashCommandContext
import dev.killjoy.utils.ParseUtils
import dev.killjoy.utils.Utils
import net.dv8tion.jda.api.EmbedBuilder

@Suppress("unused")
class NewsSlashCommand : AbstractSlashCommand("news") {

    override suspend fun handle(ctx: SlashCommandContext) {
        val isCached = NewsRetriever.isCached(ctx.guild.supportedLocale)

        if (!isCached) ctx.acknowledge().queue()

        val latestNews = NewsRetriever.lastNews(ctx.guild.supportedLocale)

        val embed = EmbedBuilder().run {
            setDefaultColor()
            setTitle(ctx.i18nCommand("news.header"))
            setDescription(ctx.i18nCommand("news.info"))
            for (new in latestNews) {
                addField(new.asEmbedField(ctx.guild))
            }
            setImage(latestNews.firstOrNull()?.image)
            build()
        }

        if (!isCached) ctx.send(embed).queue()
        else ctx.reply(embed).queue()
    }

}