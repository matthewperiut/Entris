package com.matthewperiut.entris.game;

import com.matthewperiut.entris.mixin.DrawContextAccessor;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;

public class DrawContextUtility {
    private static void drawTexturedQuad(DrawContext context, Identifier texture, int x1, int x2, int y1, int y2, int z, float u1, float u2, float v1, float v2, float red, float green, float blue, float alpha) {
        ((DrawContextAccessor) context).invokeDrawTexturedQuad(texture, x1, x2, y1, y2, z, u1, u2, v1, v2, red, green, blue, alpha);
    }

    public static void drawTransparentTexture(DrawContext context, Identifier texture, int x, int y, int width, int height, float alpha) {
        if (width != 0 && height != 0) {
            drawTexturedQuad(context, texture, x, x + width, y, y + height, 0, 0f, 1f, 0f, 1f, 1f, 1f, 1f, alpha);
        }
    }
}
