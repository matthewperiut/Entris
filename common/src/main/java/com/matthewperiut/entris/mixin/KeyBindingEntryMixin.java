package com.matthewperiut.entris.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.gui.screen.option.ControlsListWidget;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ControlsListWidget.KeyBindingEntry.class)
public class KeyBindingEntryMixin {
    @Shadow private boolean duplicate;

    @Shadow @Final private ButtonWidget editButton;

    @Shadow @Final private KeyBinding binding;

    @Inject(method = "update", at = @At("TAIL"))
    void doubleCheck(CallbackInfo ci) {
        if (duplicate) {
            if (binding.getTranslationKey().contains("entris")) {
                duplicate = false;
                this.editButton.setMessage(Text.literal(this.editButton.getMessage().getString().replace("[ ", "").replace(" ]", "")).formatted(Formatting.WHITE));
                this.editButton.setTooltip((Tooltip)null);
            }
        }
    }
}
