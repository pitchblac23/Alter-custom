package org.alter.plugins.content.objects.bankbooth

import org.alter.api.*
import org.alter.api.cfg.*
import org.alter.api.dsl.*
import org.alter.api.ext.*
import org.alter.game.*
import org.alter.game.model.*
import org.alter.game.model.attr.*
import org.alter.game.model.container.*
import org.alter.game.model.container.key.*
import org.alter.game.model.entity.*
import org.alter.game.model.item.*
import org.alter.game.model.queue.*
import org.alter.game.model.shop.*
import org.alter.game.model.timer.*
import org.alter.game.plugin.*
import org.alter.plugins.content.interfaces.bank.openBank

class BankBoothsPlugin(
    r: PluginRepository,
    world: World,
    server: Server
) : KotlinPlugin(r, world, server) {
        
    init {
        val BOOTHS =
            setOf(
                "objects.dwarf_keldagrim_bankbooth",
                "objects.bankbooth",
                "objects.bankbooth_deadman",
                "objects.elid_bankbooth",
                "objects.fai_varrock_bankbooth",
                "objects.fai_varrock_bankbooth_deadman",
                "objects.fever_bankbooth",
                "objects.burgh_bankbooth_repaired",
                "objects.pest_bankbooth",
                "objects.tob_surface_bankbooth",
                "objects.ahoy_bankbooth",
                "objects.lunar_moonclan_bankbooth",
                "objects.aide_bankbooth",
                "objects.contact_bank_booth",
                "objects.dorgesh_bank_booth",
                "objects.fai_falador_bankbooth",
                "objects.canafis_bankbooth",
                "objects.kr_bankbooth",
                "objects.fai_falador_bankbooth_deadman",
                "objects.pest_bankbooth_deadman",
                "objects.contact_bank_booth_deadman",
                "objects.kr_bankbooth_deadman",
                "objects.ahoy_bankbooth_deadman",
                "objects.aide_bankbooth_deadman",
                "objects.piscarilius_bank_booth_01",
                "objects.piscarilius_bank_booth_02",
                "objects.piscarilius_bank_booth_03",
                "objects.piscarilius_bank_booth_04",
                "objects.archeeus_bank_booth_open_01",
                "objects.archeeus_bank_booth_open_02",
                "objects.archeeus_bank_booth_open_03",
                "objects.archeeus_bank_booth_open_04",
                "objects.lova_bank_booth_01",
                "objects.lova_bank_booth_02",
                "objects.lova_bank_booth_03",
                "objects.lova_bank_booth_04",
                "objects.prif_bankbooth_open",
                // BANK_BOOTH_37959,// Has "use" option
                "objects.darkm_bankbooth",
                "objects.gim_island_bankbooth", // has only "bank option"
            )

        BOOTHS.forEach { booth ->
            onObjOption(obj = booth, option = "bank") {
                player.openBank()
            }
            if (objHasOption(booth, "Collect")) {
                onObjOption(obj = booth, option = "Collect") {
                    open_collect(player)
                }
            }
        }
    }

fun open_collect(p: Player) {
    p.setInterfaceUnderlay(color = -1, transparency = -1)
    p.openInterface(interfaceId = 402, dest = InterfaceDestination.MAIN_SCREEN)
}

}
