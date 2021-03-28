package com.guillermo.leif.controller;

import com.guillermo.leif.controller.midiInput.Op1Handler;
import com.guillermo.leif.screens.PongTitleScreen;

// TODO: access options menu and use this as well after a refactor?
public class Op1PongTitleHandler implements Op1Handler {
    private PongTitleScreen screen;

    public Op1PongTitleHandler(PongTitleScreen screen) {
        this.screen = screen;
    }

    @Override
    public void handleBlueEncoderTurned(int messageValue) {

    }

    @Override
    public void handleGreenEncoderTurned(int messageValue) {

    }

    @Override
    public void handleWhiteEncoderTurned(int messageValue) {

    }

    @Override
    public void handleOrangeEncoderTurned(int messageValue) {

    }

    @Override
    public void handleBlueEncoderPressed(int messageValue) {
        screen.setEncoderPressed(true);
    }

    @Override
    public void handleGreenEncoderPressed(int messageValue) {
        System.out.println("pressed");
        screen.setEncoderPressed(true);
    }

    @Override
    public void handleWhiteEncoderPressed(int messageValue) {
        System.out.println("pressed");
        screen.setEncoderPressed(true);
    }

    @Override
    public void handleOrangeEncoderPressed(int messageValue) {
        System.out.println("pressed");
        screen.setEncoderPressed(true);
    }

    @Override
    public void handleBlueEncoderTurned(int messageValue, long timestamp) {

    }

    @Override
    public void handleGreenEncoderTurned(int messageValue, long timestamp) {

    }

    @Override
    public void handleWhiteEncoderTurned(int messageValue, long timestamp) {

    }

    @Override
    public void handleOrangeEncoderTurned(int messageValue, long timestamp) {

    }

    @Override
    public void handleBlueEncoderPressed(int messageValue, long timestamp) {

    }

    @Override
    public void handleGreenEncoderPressed(int messageValue, long timestamp) {

    }

    @Override
    public void handleWhiteEncoderPressed(int messageValue, long timestamp) {

    }

    @Override
    public void handleOrangeEncoderPressed(int messageValue, long timestamp) {

    }
}
