package com.matthewperiut.entris.mixin;

import com.matthewperiut.entris.Entris;
import com.matthewperiut.entris.config.EntrisConfig;
import com.matthewperiut.entris.network.ServerNetworkHelper;
import com.matthewperiut.entris.network.payload.ConfigEntrisPayload;
import net.minecraft.block.BlockState;
import net.minecraft.block.EnchantingTableBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantingTableBlock.class)
public class EnchantingTableBlockMixin {
    @Inject(method = "onUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;openHandledScreen(Lnet/minecraft/screen/NamedScreenHandlerFactory;)Ljava/util/OptionalInt;"))
    void sendConfigPacket(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {
        ServerNetworkHelper.send((ServerPlayerEntity) player, new ConfigEntrisPayload(EntrisConfig.getPointsPerEnchant(), EntrisConfig.getSecondsPerLevel(), EntrisConfig.getAllowNormalEnchanting()));
    }
    @Inject(method = "onUse", at = @At("HEAD"))
    void identifyConfigUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {
        Entris.localConfig = !world.isClient;
    }
}
