package com.c446.ironbound_core.capability.statuses;

import com.c446.ironbound_core.events.mod_events.MobStatusTriggered.Post;
import com.c446.ironbound_core.events.mod_events.MobStatusTriggered.Pre;
import com.c446.ironbound_core.registry.ModRegistry;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import com.c446.ironbound_core.util.StatusTypes;

import java.util.ArrayList;
import java.util.UUID;

public class StatusResistanceCap implements IStatusResistanceCap {
    private int madness_max;
    private int madness_current;

    private int soul_shattered_current;
    private int soul_shattered_max;

    private int over_charged_max;
    private int over_charged_current;

    private int frost_max;
    private int frost_current;

    private int bleed_max;
    private int bleed_current;

    private int hollow_max;
    private int hollow_current;

    private int fervor_max;
    private int fervor_current;
    
    public static final UUID FOCUS_ATTRIBUTE_UUID = UUID.fromString("3d3349b1-02db-4f41-9a98-482f686047be");

    public void createResStuff(LivingEntity entity) {
        this.madness_max = ((int) (20 * entity.getAttributeValue(ModRegistry.AttributeRegistry.FOCUS_ATTRIBUTE.get())));
        this.hollow_max = ((int) (15 * entity.getAttributeValue(ModRegistry.AttributeRegistry.FOCUS_ATTRIBUTE.get())));
        this.fervor_max = ((int) (10 * entity.getAttributeValue(ModRegistry.AttributeRegistry.FOCUS_ATTRIBUTE.get())));
        this.soul_shattered_max = ((int) (10 * entity.getAttributeValue(ModRegistry.AttributeRegistry.FOCUS_ATTRIBUTE.get())));
        this.bleed_max = ((int) (5 * (entity.getAttributeValue(ModRegistry.AttributeRegistry.VITALITY_ATTRIBUTE.get()) + entity.getHealth() / 2)));
        this.frost_max = ((int) (3 * (entity.getAttributeValue(ModRegistry.AttributeRegistry.VITALITY_ATTRIBUTE.get()) + entity.getAttributeValue(ModRegistry.AttributeRegistry.FOCUS_ATTRIBUTE.get()))));
        this.over_charged_max = ((int) (3 * (entity.getAttributeValue(ModRegistry.AttributeRegistry.VITALITY_ATTRIBUTE.get()) + entity.getAttributeValue(ModRegistry.AttributeRegistry.FOCUS_ATTRIBUTE.get()))));
    }

    public int getMadness_max() {
        return madness_max;
    }

    public void setMadness_max(int madness_max) {
        this.madness_max = madness_max;
    }

    public int getMadness_current() {
        return madness_current;
    }

    public void setMadnessCurrent(int madness_current, Player entity) {
        this.madness_current = madness_current;
    }

    public int getFrost_max() {
        return frost_max;
    }

    public void setFrost_max(int frost_max) {
        this.frost_max = frost_max;
    }

    public int getFrost_current() {
        return frost_current;
    }

    public void setFrost_current(int frost_current, Player entity) {
        this.frost_current = frost_current;
    }

    public int getBleed_max() {
        return bleed_max;
    }

    public void setBleed_max(int bleed_max) {
        this.bleed_max = bleed_max;
    }

    public int getBleed_current() {
        return bleed_current;
    }

    public void setBleed_current(int bleed_current) {
        this.bleed_current = bleed_current;
    }

    public int getSoul_shattered_current() {
        return soul_shattered_current;
    }

    public void setSoul_shattered_current(int soul_shattered_current) {
        this.soul_shattered_current = soul_shattered_current;
    }

    public int getSoul_shattered_max() {
        return soul_shattered_max;
    }

    public void setSoul_shattered_max(int soul_shattered_max) {
        this.soul_shattered_max = soul_shattered_max;
    }

    public void generatePlayerResistances(Player player) {
        player.getCapability(StatusAttacher.StatusProvider.STATUS_RESISTANCE_CAP).ifPresent(a -> {
                    a.bleed_max = (int) (player.getAttributeValue(ModRegistry.AttributeRegistry.VITALITY_ATTRIBUTE.get()) * player.getAttributeValue(Attributes.MAX_HEALTH));
                    a.frost_max = (int) (player.getAttributeValue(ModRegistry.AttributeRegistry.VITALITY_ATTRIBUTE.get()) * player.getAttributeValue(Attributes.MAX_HEALTH));

                    a.madness_max = (int) (player.getAttributeValue(ModRegistry.AttributeRegistry.FOCUS_ATTRIBUTE.get()) * player.getAttributeValue(io.redspace.ironsspellbooks.api.registry.AttributeRegistry.MAX_MANA.get()) / 100);
                    a.soul_shattered_max = (int) (player.getAttributeValue(ModRegistry.AttributeRegistry.FOCUS_ATTRIBUTE.get()));

                }
        );
    }

    public int getHollow_max() {
        return hollow_max;
    }

    public void setHollow_max(int hollow_max) {
        this.hollow_max = hollow_max;
    }

    public int getHollowCurrent() {
        return hollow_current;
    }

    public void setHollow_current(int hollow_current, Player entity) {
        this.hollow_current = hollow_current;
    }

    public int getOver_charged_max() {
        return over_charged_max;
    }

    public void setOver_charged_max(int over_charged_max) {
        this.over_charged_max = over_charged_max;
    }

    public int getOver_charged_current() {
        return over_charged_current;
    }

    public void setOver_charged_current(int over_charged_current, Player entity) {
        this.over_charged_current = over_charged_current;
    }

    public int getFervor_max() {
        return fervor_max;
    }

    public void setFervor_max(int fervor_max) {
        this.fervor_max = fervor_max;
    }

    public int getFervor_current() {
        return fervor_current;
    }

    public void setFervor_current(int fervor_current, Player entity) {
        this.fervor_current = fervor_current;
    }

    public ArrayList<StatusTypes> checkStatus(Player player) {
        ArrayList<StatusTypes> list = new ArrayList<StatusTypes>();
        if (hollow_current > hollow_max) {
            list.add(StatusTypes.HOLLOW);
        }
        if (fervor_current > fervor_max) {
            list.add(StatusTypes.FERVOR);
        }
        if (madness_current > madness_max) {
            list.add(StatusTypes.MADNESS);
        }
        if (bleed_current > bleed_max) {
            list.add(StatusTypes.BLEED);
        }
        if (frost_current > frost_max) {
            list.add(StatusTypes.FROST);
        }
        if (soul_shattered_current > soul_shattered_max) {
            list.add(StatusTypes.WEAK_MIND);
        }
        if (over_charged_current > over_charged_max) {
            list.add(StatusTypes.OVERCHARGED);
        }
        Pre preEvent = new Pre(player, list);
        MinecraftForge.EVENT_BUS.post(preEvent);
        Post postEvent = new Post(preEvent.player, preEvent.statusList);
        return list;
    }
}
