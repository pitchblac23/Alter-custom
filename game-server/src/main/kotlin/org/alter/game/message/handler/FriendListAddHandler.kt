package org.alter.game.message.handler

import net.rsprot.protocol.game.incoming.social.FriendListAdd
import org.alter.game.message.MessageHandler
import org.alter.game.model.entity.Client
import org.alter.game.pluginnew.event.impl.FriendListAddEvent

class FriendListAddHandler : MessageHandler<FriendListAdd> {
    override fun consume(
        client: Client,
        message: FriendListAdd,
    ) {
        client.social.addFriend(client, message.name)
        FriendListAddEvent(message.name, client).post()
    }
}
