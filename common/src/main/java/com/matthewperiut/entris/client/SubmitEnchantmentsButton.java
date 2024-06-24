package com.matthewperiut.entris.client;

import com.matthewperiut.entris.enchantment.EnchantmentHelp;
import com.matthewperiut.entris.enchantment.RomanNumeralUtil;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.text.Text;

public class SubmitEnchantmentsButton extends ButtonWidget {

    public SubmitEnchantmentsButton(int x, int y, PressAction onPress) {
        super(x, y, 81, 12, Text.literal("ENCHANT"), onPress, textSupplier -> getNarrationMessage(Text.literal("ENCHANT")));
    }

    @Override
    protected void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
        super.renderWidget(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean isSelected() {
        return this.isHovered();
    }

    @Override
    public void onPress() {
        super.onPress();
    }
}
