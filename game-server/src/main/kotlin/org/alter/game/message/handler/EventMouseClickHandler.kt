package org.alter.game.message.handler

import net.rsprot.protocol.game.incoming.events.EventMouseClickV2
import org.alter.game.message.MessageHandler
import org.alter.game.model.entity.Client
import org.alter.game.pluginnew.event.impl.MouseClickEvent

/**
 * @author Tom <rspsmods@gmail.com>
 */
class EventMouseClickHandler : MessageHandler<EventMouseClickV2> {
    override fun consume(client: Client, message: EventMouseClickV2, ) {
        MouseClickEvent(message.x, message.y, message.code, message.rightClick,message.lastTransmittedMouseClick,client).post()
    }
}
