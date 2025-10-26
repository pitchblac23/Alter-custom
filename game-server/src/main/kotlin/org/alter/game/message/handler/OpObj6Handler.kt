package org.alter.game.message.handler

import net.rsprot.protocol.game.incoming.objs.OpObj6
import org.alter.game.message.MessageHandler
import org.alter.game.model.ExamineEntityType
import org.alter.game.model.entity.Client
import org.alter.game.pluginnew.event.impl.ExamineEvent

class OpObj6Handler : MessageHandler<OpObj6> {
    override fun consume(
        client: Client,
        message: OpObj6,
    ) {
        client.world.sendExamine(client, message.id, ExamineEntityType.ITEM)
        ExamineEvent(message.id, ExamineEntityType.ITEM, client).post()
    }
}
