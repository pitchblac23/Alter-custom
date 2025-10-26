package org.alter.game.message.handler

import net.rsprot.protocol.game.incoming.social.IgnoreListAdd
import org.alter.game.message.MessageHandler
import org.alter.game.model.entity.Client
import org.alter.game.pluginnew.event.impl.IgnoreListAddEvent

class IgnoreListAddHandler : MessageHandler<IgnoreListAdd> {
    override fun consume(
        client: Client,
        message: IgnoreListAdd,
    ) {
        client.social.addIgnore(client, message.name)
        IgnoreListAddEvent(message.name, client).post()
    }
}
