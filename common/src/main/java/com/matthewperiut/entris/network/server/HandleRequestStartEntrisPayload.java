package com.matthewperiut.entris.network.server;

import com.matthewperiut.entris.BookShelvesUtil;
import com.matthewperiut.entris.Entris;
import com.matthewperiut.entris.client.SlotEnabler;
import com.matthewperiut.entris.network.payload.AllowEntrisPayload;
import dev.architectury.networking.NetworkManager;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.EnchantmentScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;

import static com.matthewperiut.entris.Entris.MARGIN_OF_ERROR_TIME;
import static com.matthewperiut.entris.Entris.playerDataMap;
import static net.minecraft.util.math.MathHelper.ceil;

public class HandleRequestStartEntrisPayload implements NetworkManager.NetworkReceiver {
    public static void handle(ServerPlayerEntity player, int requiredLevels) {
        int requiredLapis = ceil(requiredLevels / 10.f);

        if (player.isCreative()) {
            playerDataMap.put(player, new Entris.PlayerData(System.currentTimeMillis(), (requiredLevels * 6) + MARGIN_OF_ERROR_TIME));
            AllowEntrisPayload payload = new AllowEntrisPayload(true);
            NetworkManager.sendToPlayer(player, payload.getId(), payload.getPacket());
            return;
        }

        if (requiredLevels > 30) {
            AllowEntrisPayload payload = new AllowEntrisPayload(false);
            NetworkManager.sendToPlayer(player, payload.getId(), payload.getPacket());
            return;
        }

        int bookshelveMaxLevel = (int) (4 + (1.74* BookShelvesUtil.countBookShelves(player)));
        if (requiredLevels > bookshelveMaxLevel) {
            AllowEntrisPayload payload = new AllowEntrisPayload(false);
            NetworkManager.sendToPlayer(player, payload.getId(), payload.getPacket());
            return;
        }

        if (player.currentScreenHandler.getSlot(0).getStack().hasEnchantments()){
            AllowEntrisPayload payload = new AllowEntrisPayload(false);
            NetworkManager.sendToPlayer(player, payload.getId(), payload.getPacket());
            return;
        }
        if (!player.currentScreenHandler.getSlot(0).getStack().isEnchantable()){
            AllowEntrisPayload payload = new AllowEntrisPayload(false);
            NetworkManager.sendToPlayer(player, payload.getId(), payload.getPacket());
            return;
        }
        if (player.currentScreenHandler.getSlot(0).getStack().getItem() == Items.BOOK){
            AllowEntrisPayload payload = new AllowEntrisPayload(false);
            NetworkManager.sendToPlayer(player, payload.getId(), payload.getPacket());
            return;
        }

        if (requiredLapis > 0) {
            int lapisCt = ((EnchantmentScreenHandler)player.currentScreenHandler).getLapisCount();
            if (requiredLapis <= lapisCt) {
                if (player.experienceLevel >= requiredLevels) {
                    player.addExperienceLevels(-requiredLapis);
                    player.currentScreenHandler.getSlot(1).takeStack(requiredLapis);

                    // todo: check if the item is valid
                    ((SlotEnabler) player.currentScreenHandler.getSlot(0)).setCanTake(false);
                    AllowEntrisPayload payload = new AllowEntrisPayload(true);
                    NetworkManager.sendToPlayer(player, payload.getId(), payload.getPacket());
                    playerDataMap.put(player, new Entris.PlayerData(System.currentTimeMillis(), (requiredLevels * 6) + MARGIN_OF_ERROR_TIME));
                    return;
                }
            }

            AllowEntrisPayload payload = new AllowEntrisPayload(false);
            NetworkManager.sendToPlayer(player, payload.getId(), payload.getPacket());
        }
    }

    @Override
    public void receive(PacketByteBuf buf, NetworkManager.PacketContext context) {
        handle((ServerPlayerEntity) context.getPlayer(), buf.getInt(0));
    }
}
