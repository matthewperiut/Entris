package com.matthewperiut.entris.enchantment;

import com.ibm.icu.impl.CollectionSet;
import com.matthewperiut.entris.client.EnchantmentSelectButton;
import net.minecraft.client.MinecraftClient;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.enchantment.*;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class EnchantmentHelp {
    public static Text getEnchantmentText(World world, Identifier enchantment) {
        return Text.translatable(world.getRegistryManager().get(RegistryKeys.ENCHANTMENT).get(enchantment).getTranslationKey());
    }

    public static RegistryEntry<Enchantment> getEnchantmentRegistry(World world, Enchantment enchantment) {
        return world.getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(enchantment);
    }

    public static Text getEnchantmentText(Enchantment enchantment) {
        return Text.translatable(enchantment.getTranslationKey());
    }
    public static String getEnchantmentIdStr(World world, Enchantment enchantment) {
        return world.getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(enchantment).getKey().orElseThrow().getValue().getPath();
    }
    public static Enchantment getEnchantmentIdStr(World world, String enchantmentId) {
        return world.getRegistryManager().get(RegistryKeys.ENCHANTMENT).get(Identifier.tryParse(enchantmentId));
    }

    public static Enchantment[] getPossibleEnchantments(World world, ItemStack itemStack) {
        List<Enchantment> applicableEnchantments = new ArrayList<>();
        for (Enchantment enchantment : world.getRegistryManager().get(RegistryKeys.ENCHANTMENT)) {
            if (enchantment.isAcceptableItem(itemStack)) {
                applicableEnchantments.add(enchantment);
            }
        }
        return applicableEnchantments.toArray(new Enchantment[0]);
    }

    public static boolean disallowedEnchanting(Enchantment enchantment) {
        return enchantment instanceof MendingEnchantment ||
                enchantment instanceof FrostWalkerEnchantment ||
                enchantment instanceof SwiftSneakEnchantment ||
                enchantment instanceof SoulSpeedEnchantment ||
                enchantment instanceof BindingCurseEnchantment ||
                enchantment instanceof VanishingCurseEnchantment;
    }

    public static boolean rejectEnchantment(World world, ArrayList<EnchantmentSelectButton> enchants, EnchantmentSelectButton wantedEnchantmentButton) {
        ArrayList<Enchantment> includedEnchants = new ArrayList<>();
        Enchantment wantedEnchantment = wantedEnchantmentButton.enchantment;

        for (EnchantmentSelectButton enchantButton : enchants) {
            if (wantedEnchantment.equals(enchantButton))
                continue;

            if (enchantButton.number > 0) {
                includedEnchants.add(enchantButton.enchantment);
            }
        }

        boolean canBeCombined = true;
        for (Enchantment e : includedEnchants) {
            RegistryEntry<Enchantment> included = getEnchantmentRegistry(world, wantedEnchantment);
            RegistryEntry<Enchantment> wanted = getEnchantmentRegistry(world, e);
            if (!included.equals(wanted)) {
                ArrayList<Enchantment> registryEntries = new ArrayList<>();
                registryEntries.add(wantedEnchantment);
                canBeCombined = EnchantmentHelper.isCompatible(registryEntries, e);
            }
        }

        if (!canBeCombined)
            return true;
        return false;
    }

    public static void updateAvailableEnchantButtons(World world, ArrayList<EnchantmentSelectButton> enchants) {
        ArrayList<EnchantmentSelectButton> includedEnchants = new ArrayList<>();

        for (EnchantmentSelectButton enchantButton : enchants) {
            if (enchantButton.number > 0) {
                includedEnchants.add(enchantButton);
            }
        }

        for (EnchantmentSelectButton includedButton : enchants) {
            for (EnchantmentSelectButton enchantButton : enchants) {
                RegistryEntry<Enchantment> included = getEnchantmentRegistry(world, includedButton.enchantment);
                RegistryEntry<Enchantment> wanted = getEnchantmentRegistry(world, enchantButton.enchantment);
                if (!included.equals(wanted)) {
                    ArrayList<Enchantment> registryEntries = new ArrayList<>();
                    registryEntries.add(includedButton.enchantment);
                    if(!EnchantmentHelper.isCompatible(registryEntries, enchantButton.enchantment)) {
                        enchantButton.active = false;
                    }
                }
            }
        }

        for (EnchantmentSelectButton enchantButton : enchants) {
            if (enchantButton.number > 0) {
                enchantButton.active = true;
            }
        }
    }
}
