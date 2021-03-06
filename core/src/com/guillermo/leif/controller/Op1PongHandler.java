package com.guillermo.leif.controller;

import com.guillermo.leif.controller.midiInput.Op1Handler;
import com.guillermo.leif.GameState;

public class Op1PongHandler implements Op1Handler {
    private GameState gameState;

    private Op1PongHandler() {
    }

    public Op1PongHandler(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public void handleBlueEncoderTurned(int messageValue) {
        this.gameState.player1.move(messageValue);
    }

    @Override
    public void handleGreenEncoderTurned(int messageValue) {
        this.gameState.player1.rotate(messageValue);
    }

    @Override
    public void handleWhiteEncoderTurned(int messageValue) {
        this.gameState.player2.rotate(messageValue);
    }

    @Override
    public void handleOrangeEncoderTurned(int messageValue) {
        this.gameState.player2.move(messageValue);
    }

    @Override
    public void handleBlueEncoderPressed(int messageValue) {

    }

    @Override
    public void handleGreenEncoderPressed(int messageValue) {

    }

    @Override
    public void handleWhiteEncoderPressed(int messageValue) {

    }

    @Override
    public void handleOrangeEncoderPressed(int messageValue) {

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
