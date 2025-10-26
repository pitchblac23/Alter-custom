package org.alter.game.message.handler

import net.rsprot.protocol.game.incoming.events.EventKeyboard
import org.alter.game.message.MessageHandler
import org.alter.game.model.entity.Client
import org.alter.game.pluginnew.event.impl.KeyboardEvent

/**
 * @author Tom <rspsmods@gmail.com>
 */
class EventKeyboardHandler : MessageHandler<EventKeyboard> {
    override fun consume(
        client: Client,
        message: EventKeyboard,
    ) {
        // Note: EventKeyboard doesn't seem to have key data in the message
        // You may need to adjust this based on actual message structure
        KeyboardEvent(0, '\u0000', client).post()
    }
}
