/*
 * Copyright (c) NeoForged and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */

package net.neoforged.neoforge.client.util;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.ApiStatus;

/**
 * A version of {@link TooltipFlag} that knows about Screen and can provide modifier key states. It is patched into all vanilla uses of TooltipFlags in client classes.
 * <p>
 * When calling any tooltip method that needs a TooltipFlag yourself, use either this (by calling {@link #of(TooltipFlag)}) or {@link TooltipFlag.Default} depending on the <em>logical</em> side you're on.
 */
public record NeoTooltipFlag(boolean advanced, boolean creative, boolean shiftDown, boolean controlDown, boolean altDown) implements TooltipFlag {
    @ApiStatus.Internal
    public NeoTooltipFlag {}

    @Override
    public boolean isAdvanced() {
        return this.advanced;
    }

    @Override
    public boolean isCreative() {
        return this.creative;
    }

    @Override
    public boolean hasControlDown() {
        return controlDown;
    }

    @Override
    public boolean hasShiftDown() {
        return shiftDown;
    }

    @Override
    public boolean hasAltDown() {
        return altDown;
    }

    public NeoTooltipFlag asCreative() {
        return new NeoTooltipFlag(advanced, true, shiftDown, controlDown, altDown);
    }

    public static TooltipFlag of(TooltipFlag other) {
        return new NeoTooltipFlag(other.isAdvanced(), other.isCreative(), Screen.hasShiftDown(), Screen.hasControlDown(), Screen.hasAltDown());
    }
}
