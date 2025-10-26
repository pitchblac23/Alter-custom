package org.alter.game.message.handler

import net.rsprot.protocol.game.incoming.misc.client.MapBuildComplete
import org.alter.game.message.MessageHandler
import org.alter.game.model.entity.Client
import org.alter.game.pluginnew.event.impl.MapBuildCompleteEvent

/**
 * @author Tom <rspsmods@gmail.com>
 */
class MapBuildCompleteHandler : MessageHandler<MapBuildComplete> {
    override fun consume(
        client: Client,
        message: MapBuildComplete,
    ) {
        client.lastMapBuildTime = client.world.currentCycle
        MapBuildCompleteEvent(client).post()
    }
}
