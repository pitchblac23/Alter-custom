package org.rsmod.content.quest.manager

import org.rsmod.content.quest.area.lumbridge.RuneMysteries
import org.rsmod.plugin.module.PluginModule

public class QuestModule : PluginModule() {
    override fun bind() {
        bindInstance<QuestRequirementResolver>()
        bindInstance<RuneMysteries>()
    }
}
