package com.matthewperiut.entris;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class EntrisClient {
    public static KeyBinding leftTetris;
    public static KeyBinding rightTetris;
    public static KeyBinding downTetris;
    public static KeyBinding upTetris;
    public static KeyBinding holdTetris;
    public static KeyBinding hardDropTetris;
    public static KeyBinding leftRotateTetris;
    public static KeyBinding rightRotateTetris;
    public static void init() {
        leftTetris = new KeyBinding(
                "key.entris.left",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_LEFT,
                "category.entris.entris"
        );
        EntrisClient.leftTetris = new KeyBinding(
                "key.entris.left",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_LEFT,
                "category.entris.entris"
        );
        EntrisClient.rightTetris = new KeyBinding(
                "key.entris.right",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT,
                "category.entris.entris"
        );
        EntrisClient.downTetris = new KeyBinding(
                "key.entris.down",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_DOWN,
                "category.entris.entris"
        );
        EntrisClient.upTetris = new KeyBinding(
                "key.entris.up",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_UP,
                "category.entris.entris"
        );
        EntrisClient.holdTetris = new KeyBinding(
                "key.entris.hold",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_C,
                "category.entris.entris"
        );
        EntrisClient.hardDropTetris = new KeyBinding(
                "key.entris.hard_drop",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_SPACE,
                "category.entris.entris"
        );
        EntrisClient.rightRotateTetris = new KeyBinding(
                "key.entris.right_rotate",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_X,
                "category.entris.entris"
        );
        EntrisClient.leftRotateTetris = new KeyBinding(
                "key.entris.left_rotate",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_Z,
                "category.entris.entris"
        );
    }
}
