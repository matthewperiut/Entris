package com.matthewperiut.entris.network.server;

import com.matthewperiut.entris.client.SlotEnabler;
import com.matthewperiut.entris.config.EntrisConfig;
import com.matthewperiut.entris.network.ServerNetworkHelper;
import com.matthewperiut.entris.network.payload.AllowEntrisPayload;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.Optional;

import static com.matthewperiut.entris.Entris.playerDataMap;

public class HandleRequestEntrisEnchantsPayload {
    public static void handle(MinecraftServer server, ServerPlayerEntity player, ArrayList<String> enchants)
        {
            ArrayList<Identifier> enchantments = new ArrayList<>();
            ArrayList<Integer> levels = new ArrayList<>();

            for (int i = 0; i < enchants.size(); i++) {
                String[] enchant = enchants.get(i).split(" ");
                enchantments.add(Identifier.of(enchant[0]));
                levels.add(Integer.parseInt(enchant[1]));
            }

            int ct = 0;
            for (int l : levels) {
                ct += l;
            }

            if (ct * EntrisConfig.getPointsPerEnchant() > playerDataMap.get(player).score) {
                ServerNetworkHelper.send(player, new AllowEntrisPayload(false));
                playerDataMap.remove(player);
            } else {
                // Apply enchants from enchantments arraylist
                ItemStack stack = player.currentScreenHandler.getSlot(0).getStack();
                for (int i = 0; i < enchantments.size(); i++) {
                    Identifier enchantment = enchantments.get(i);
                    int level = levels.get(i);

                    // why is modern mc like this?
                    RegistryEntry<Enchantment> H = server.getOverworld().getRegistryManager().getOptional(RegistryKeys.ENCHANTMENT).get().getEntry(enchantment).get();

                    stack.addEnchantment(H, level);
                }

                player.currentScreenHandler.getSlot(0).setStack(stack);
                ((SlotEnabler) player.currentScreenHandler.getSlot(0)).setCanTake(true);
            }
        }
    }