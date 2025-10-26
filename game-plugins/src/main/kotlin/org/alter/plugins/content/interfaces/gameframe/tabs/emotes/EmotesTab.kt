package org.alter.plugins.content.interfaces.emotes

import org.alter.api.EquipmentType
import org.alter.api.cfg.Varbit
import org.alter.api.ext.*
import org.alter.game.model.entity.Player
import org.alter.game.model.queue.TaskPriority
import org.alter.rscm.RSCM.getRSCM

/**
 * @author Tom <rspsmods@gmail.com>
 */
object EmotesTab {
    const val COMPONENT_ID = 216

    fun unlockAll(p: Player) {
        p.setVarbit(Varbit.GOBLIN_EMOTES_VARBIT, 7)
        p.setVarbit(Varbit.GLASS_BOX_EMOTE_VARBIT, 1)
        p.setVarbit(Varbit.CLIMB_ROPE_EMOTE_VARBIT, 1)
        p.setVarbit(Varbit.LEAN_EMOTE_VARBIT, 1)
        p.setVarbit(Varbit.GLASS_WALL_EMOTE_VARBIT, 1)
        p.setVarbit(Varbit.IDEA_EMOTE_VARBIT, 1)
        p.setVarbit(Varbit.STAMP_EMOTE_VARBIT, 1)
        p.setVarbit(Varbit.FLAP_EMOTE_VARBIT, 1)
        p.setVarbit(Varbit.SLAP_HEAD_EMOTE_VARBIT, 1)
        p.setVarbit(Varbit.ZOMBIE_WALK_EMOTE_VARBIT, 1)
        p.setVarbit(Varbit.ZOMBIE_DANCE_EMOTE_VARBIT, 1)
        p.setVarbit(Varbit.SCARED_EMOTE_VARBIT, 1)
        p.setVarbit(Varbit.RABBIT_HOP_EMOTE_VARBIT, 1)
        p.setVarbit(Varbit.EXERCISE_EMOTES, 1)
        p.setVarbit(Varbit.ZOMBIE_HAND_EMOTE_VARBIT, 1)
        p.setVarbit(Varbit.HYPERMOBILE_DRINKER_EMOTE_VARBIT, 1)
        p.setVarbit(Varbit.SKILLCAPE_EMOTE_VARBIT, 1)
        p.setVarbit(Varbit.AIR_GUITAR_EMOTE_VARBIT, 1)
        p.setVarbit(Varbit.URI_TRANSFORM_EMOTE_VARBIT, 1)
        p.setVarbit(Varbit.SMOOTH_DANCE_EMOTE_VARBIT, 1)
        p.setVarbit(Varbit.CRAZY_DANCE_EMOTE_VARBIT, 1)
        p.setVarbit(Varbit.PREMIER_SHIELD_EMOTE_VARBIT, 1)
        p.setVarbit(Varbit.EXPLORE_VARBIT, 1)
        p.setVarbit(Varbit.FLEX_EMOTE_VARBIT, 1)
        p.setVarbit(Varbit.RELIC_UNLOCKED_EMOTE_VARBIT, 9)
        p.setVarbit(Varbit.PARTY_EMOTE_VARBIT, 1)
    }

    fun performEmote(
        p: Player,
        emote: Emote,
    ) {
        if (emote.varbit != -1 && p.getVarbit(emote.varbit) != emote.requiredVarbitValue) {
            val description = emote.unlockDescription ?: "You have not unlocked this emote yet."
            p.queue { messageBox(p, description) }
            return
        }

        /**
         * @author Jarafi
         * If you move you get locked into uris form
         */
        if (emote == Emote.URI_TRANSFORM) {
            p.queue {
                p.lock()
                p.graphic(86)
                p.setTransmogId(7311)
                wait(1)
                p.setTransmogId(7313)
                p.graphic(1306)
                p.animate(7278)
                wait(10)
                p.animate(4069)
                wait(1)
                p.graphic(678)
                p.animate(4071)
                wait(2)
                p.graphic(86)
                p.setTransmogId(-1)
                p.unlock()
            }
        }
        /**
         * Thanks to @ClaroJack for the skill animation/gfx id's
         */
        if (emote == Emote.SKILLCAPE) {
            when (p.equipment[EquipmentType.CAPE.id]?.id) {
                getRSCM("items.skillcape_max_worn") -> {
                    p.animate(7121, 4)
                    p.graphic(1286, delay = 4)
                }
                getRSCM("items.skillcape_attack"), getRSCM("items.skillcape_attack_trimmed") -> {
                    p.animate(4959)
                    p.graphic(823)
                }
                getRSCM("items.skillcape_strength"), getRSCM("items.skillcape_strength_trimmed") -> {
                    p.animate(4981)
                    p.graphic(828)
                }
                getRSCM("items.skillcape_defence"), getRSCM("items.skillcape_defence_trimmed") -> {
                    p.animate(4961)
                    p.graphic(824)
                }
                getRSCM("items.skillcape_ranging"), getRSCM("items.skillcape_ranging_trimmed") -> {
                    p.animate(4973)
                    p.graphic(832)
                }
                getRSCM("items.skillcape_prayer"), getRSCM("items.skillcape_prayer_trimmed") -> {
                    p.animate(4979)
                    p.graphic(829)
                }
                getRSCM("items.skillcape_magic"), getRSCM("items.skillcape_magic_trimmed") -> {
                    p.animate(4939)
                    p.graphic(813)
                }
                getRSCM("items.skillcape_runecrafting"), getRSCM("items.skillcape_runecrafting_trimmed") -> {
                    p.animate(4947)
                    p.graphic(817)
                }
                getRSCM("items.skillcape_hitpoints"), getRSCM("items.skillcape_hitpoints_trimmed") -> {
                    p.animate(4971)
                    p.graphic(833)
                }
                getRSCM("items.skillcape_agility"), getRSCM("items.skillcape_agility_trimmed") -> {
                    p.animate(4977)
                    p.graphic(830)
                }
                getRSCM("items.skillcape_herblore"), getRSCM("items.skillcape_herblore_trimmed") -> {
                    p.animate(4969)
                    p.graphic(835)
                }
                getRSCM("items.skillcape_thieving"), getRSCM("items.skillcape_thieving_trimmed") -> {
                    p.animate(4965)
                    p.graphic(826)
                }
                getRSCM("items.skillcape_crafting"), getRSCM("items.skillcape_crafting_trimmed") -> {
                    p.animate(4949)
                    p.graphic(818)
                }
                getRSCM("items.skillcape_fletching"), getRSCM("items.skillcape_fletching_trimmed") -> {
                    p.animate(4937)
                    p.graphic(812)
                }
                getRSCM("items.skillcape_slayer"), getRSCM("items.skillcape_slayer_trimmed") -> {
                    p.animate(4967)
                    p.graphic(827)
                }
                getRSCM("items.skillcape_construction"), getRSCM("items.skillcape_construction_trimmed") -> {
                    p.animate(4953)
                    p.graphic(820)
                }
                getRSCM("items.skillcape_mining"), getRSCM("items.skillcape_mining_trimmed") -> {
                    p.animate(4941)
                    p.graphic(814)
                }
                getRSCM("items.skillcape_smithing"), getRSCM("items.skillcape_smithing_trimmed") -> {
                    p.animate(4943)
                    p.graphic(815)
                }
                getRSCM("items.skillcape_fishing"), getRSCM("items.skillcape_fishing_trimmed") -> {
                    p.animate(4951)
                    p.graphic(819)
                }
                getRSCM("items.skillcape_cooking"), getRSCM("items.skillcape_cooking_trimmed") -> {
                    p.animate(4955)
                    p.graphic(821)
                }
                getRSCM("items.skillcape_firemaking"), getRSCM("items.skillcape_firemaking_trimmed") -> {
                    p.animate(4975)
                    p.graphic(831)
                }
                getRSCM("items.skillcape_woodcutting"), getRSCM("items.skillcape_woodcutting_trimmed") -> {
                    p.animate(4957)
                    p.graphic(822)
                }
                getRSCM("items.skillcape_farming"), getRSCM("items.skillcape_farming_trimmed") -> {
                    p.animate(4963)
                    p.graphic(825)
                }
                getRSCM("items.skillcape_hunting"), getRSCM("items.skillcape_hunting_trimmed") -> {
                    p.animate(5158)
                    p.graphic(907)
                }
                getRSCM("items.skillcape_cabbage") -> {
                    p.animate(7209)
                }
                getRSCM("items.skillcape_qp"), getRSCM("items.skillcape_qp_trimmed") -> {
                    p.animate(4945)
                    p.graphic(816)
                }
                getRSCM("items.skillcape_ad"), getRSCM("items.skillcape_ad_trimmed") -> {
                    p.animate(2709)
                    p.graphic(323)
                }
                getRSCM("items.music_cape"), getRSCM("items.music_cape_trimmed") -> {
                    p.animate(4751)
                    p.graphic(1239)
                }
            }
        }

        if (emote == Emote.RELIC_UNLOCKED) {
            p.queue(TaskPriority.STANDARD) {
                p.graphic(-1)
                p.graphic(emote.gfx, 100)
                p.unlock()
            }
        }
        if (emote.anim != -1) {
            p.queue(TaskPriority.STANDARD) {
                p.animate(-1)
                p.animate(emote.anim, 1)
                p.unlock()
            }
        }
        if (emote.gfx != -1 && emote != Emote.RELIC_UNLOCKED) {
            p.queue(TaskPriority.STANDARD) {
                p.graphic(-1)
                p.graphic(emote.gfx)
                p.unlock()
            }
        }
    }
}
