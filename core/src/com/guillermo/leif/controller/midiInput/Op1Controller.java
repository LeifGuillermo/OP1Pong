package com.guillermo.leif.controller.midiInput;

import com.badlogic.gdx.Screen;

/**
 * The Op1Controller class should be updated (eventually) to handle more input.
 * */
public class Op1Controller implements MidiGameController {
    private static final int MAX_ENCODER_VALUE = 127;
    private static final int MIN_ENCODER_VALUE = 0;

    private Op1Handler handler;
    private Screen screen;

    public Op1Controller(Screen screen, Op1Handler handler) {
        System.out.println("HANNNNNNNNNNDLLLLLLLLER: " + handler);
        this.screen = screen;
        this.handler = handler;
    }

    @Override
    public void midiReceived(byte[] msg, long timestamp) {
        int encoderValue = msg[1];
        int messageValue = msg[2];
        System.out.println("Message received");
//        System.out.println("encoderValue: " + encoderValue); // use to debug
        switch (encoderValue) {
            // Encoders turned:
            case 1:
                handler.handleBlueEncoderTurned(messageValue);
                break;
            case 2:
                handler.handleGreenEncoderTurned(messageValue);
                break;
            case 3:
                handler.handleWhiteEncoderTurned(messageValue);
                break;
            case 4:
                handler.handleOrangeEncoderTurned(messageValue);
                break;

            // Encoders Pressed.
            case 64:
                handler.handleBlueEncoderPressed(messageValue);
                break;
            case 65:
                handler.handleGreenEncoderPressed(messageValue);
                break;
            case 66:
                handler.handleWhiteEncoderPressed(messageValue);
                break;
            case 67:
                handler.handleOrangeEncoderPressed(messageValue);
                break;

        }
    }
}
