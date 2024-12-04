package com.matthewperiut.entris.enchantment;

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

public class EnchantmentHelp {
    public static Text getEnchantmentText(World world, Identifier enchantment) {
        return world.getRegistryManager().getOptional(RegistryKeys.ENCHANTMENT).get().get(enchantment).description();
    }

    public static RegistryEntry<Enchantment> getEnchantmentRegistry(World world, Enchantment enchantment) {
        return world.getRegistryManager().getOptional(RegistryKeys.ENCHANTMENT).get().getEntry(enchantment);
    }

    public static Text getEnchantmentText(Enchantment enchantment) {
        return enchantment.description();
    }
    public static String getEnchantmentIdStr(World world, Enchantment enchantment) {
        return world.getRegistryManager().getOptional(RegistryKeys.ENCHANTMENT).get().getEntry(enchantment).getIdAsString();
    }
    public static Enchantment getEnchantmentIdStr(World world, String enchantmentId) {
        return world.getRegistryManager().getOptional(RegistryKeys.ENCHANTMENT).get().get(Identifier.of(enchantmentId));
    }

    public static Enchantment[] getPossibleEnchantments(World world, ItemStack itemStack) {
        List<Enchantment> applicableEnchantments = new ArrayList<>();
        for (Enchantment enchantment : world.getRegistryManager().getOptional(RegistryKeys.ENCHANTMENT).get()) {
            if (enchantment.isAcceptableItem(itemStack)) {
                applicableEnchantments.add(enchantment);
            }
        }
        return applicableEnchantments.toArray(new Enchantment[0]);
    }

    public static boolean disallowedEnchanting(Enchantment enchantment) {
        return enchantment.description().toString().toLowerCase().contains("mending") ||
        enchantment.description().toString().toLowerCase().contains("frost_walker") ||
        enchantment.description().toString().toLowerCase().contains("swift_sneak") ||
        enchantment.description().toString().toLowerCase().contains("soul_speed") ||
        enchantment.description().toString().toLowerCase().contains("curse");
    }

    public static boolean rejectEnchantment(World world, ArrayList<EnchantmentSelectButton> enchants, EnchantmentSelectButton wantedEnchantmentButton) {
        ArrayList<Enchantment> includedEnchants = new ArrayList<>();
        Enchantment wantedEnchantment = wantedEnchantmentButton.enchantment;

        for (EnchantmentSelectButton enchantButton : enchants) {
            if (enchantButton.number > 0) {
                includedEnchants.add(enchantButton.enchantment);
            }
        }

        boolean canBeCombined = true;
        for (Enchantment e : includedEnchants) {
            RegistryEntry<Enchantment> included = getEnchantmentRegistry(world, wantedEnchantment);
            RegistryEntry<Enchantment> wanted = getEnchantmentRegistry(world, e);
            System.out.println(included);
            System.out.println(wanted);
            if (!included.equals(wanted)) {
                canBeCombined  = Enchantment.canBeCombined(included, wanted);
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

        /* this code rejects silk touch and fortune if efficiency is present, why?? */
        for (EnchantmentSelectButton enchantButton : enchants) {
            // Start assuming enchantment is valid
            boolean canBeActivated = true;

            for (EnchantmentSelectButton includedButton : includedEnchants) {
                RegistryEntry<Enchantment> included = getEnchantmentRegistry(world, includedButton.enchantment);
                RegistryEntry<Enchantment> wanted = getEnchantmentRegistry(world, enchantButton.enchantment);

                if (!included.equals(wanted)) {
                    if (!Enchantment.canBeCombined(included, wanted)) {
                        canBeActivated = false;
                        break; // No need to check further; we found an incompatibility
                    }
                }
            }

            enchantButton.active = canBeActivated; // Set final result
        }

        /* end of modifiable snippet */

        for (EnchantmentSelectButton enchantButton : enchants) {
            if (enchantButton.number > 0) {
                enchantButton.active = true;
            }
        }
    }
}
