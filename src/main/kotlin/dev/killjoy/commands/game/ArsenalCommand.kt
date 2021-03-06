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

import dev.killjoy.Launcher
import dev.killjoy.framework.Category
import dev.killjoy.framework.CommandContext
import dev.killjoy.framework.abs.Command
import dev.killjoy.framework.annotations.CommandArgument
import dev.killjoy.framework.annotations.CommandProperties
import dev.killjoy.i18n.i18nCommand
import dev.killjoy.utils.Emojis

@CommandProperties(
    name = "arsenal",
    category = Category.Game,
    aliases = ["weapons", "weapon"],
    arguments = [
        CommandArgument("weapon", "A valid Valorant weapon name [tactical knife]", false)
    ]
)
class ArsenalCommand : Command() {
    override suspend fun handle(ctx: CommandContext) {
        if (ctx.args.isEmpty()) {
            val arsenal = Launcher.arsenal

            ctx.replyEmbed {
                setTitle(ctx.guild.i18nCommand("arsenal.title"))
                for (weapon in arsenal) {
                    addField("${weapon.name(ctx.guild)} //${weapon.type(ctx.guild)}", weapon.short(ctx.guild), true)
                }
                setFooter("If you want to get more information about an weapon use \"joy arsenal weapon_name\"")
            }.queue()

        } else {
            val weapon = Launcher.getWeapon(ctx.args.joinToString(" "))
                ?: Launcher.getWeaponById(ctx.args[0])
                ?: return ctx.send(Emojis.NoEntry, ctx.guild.i18nCommand("arsenal.notFound")).queue()

            ctx.reply(weapon.asEmbed(ctx.guild).build()).queue()
        }
    }

    override val help: String = "Get information and statistics about a Valorant weapon or the entire arsenal."
}