package org.alter.game.message.handler

import net.rsprot.protocol.game.incoming.misc.user.MoveGameClick
import org.alter.game.message.MessageHandler
import org.alter.game.model.Tile
import org.alter.game.model.attr.CLIENT_KEY_COMBINATION
import org.alter.game.model.move.MovementQueue
import org.alter.game.model.entity.Client
import org.alter.game.model.move.walkTo
import org.alter.game.pluginnew.event.impl.ClickMapEvent

class ClickMapHandler : MessageHandler<MoveGameClick> {
    override fun consume(
        client: Client,
        message: MoveGameClick
    ) {
        log(client, "Click map: x=%d, y=%d, type=%d", message.x, message.z, message.keyCombination)
        client.attr[CLIENT_KEY_COMBINATION] = message.keyCombination
        client.walkTo(message.x, message.z, stepType = MovementQueue.StepType.NORMAL)
        ClickMapEvent(Tile(message.x, message.z), message.keyCombination, client).post()
    }
}
