package com.guillermo.leif.controller.midiInput;

public interface Op1Handler {
    /*Encoder turns*/
    void handleBlueEncoderTurned(int messageValue);
    void handleGreenEncoderTurned(int messageValue);
    void handleWhiteEncoderTurned(int messageValue);
    void handleOrangeEncoderTurned(int messageValue);
    /*Encoder presses*/
    void handleBlueEncoderPressed(int messageValue);
    void handleGreenEncoderPressed(int messageValue);
    void handleWhiteEncoderPressed(int messageValue);
    void handleOrangeEncoderPressed(int messageValue);


    /*Encoder turns with timestamps*/
    void handleBlueEncoderTurned(int messageValue, long timestamp);
    void handleGreenEncoderTurned(int messageValue, long timestamp);
    void handleWhiteEncoderTurned(int messageValue, long timestamp);
    void handleOrangeEncoderTurned(int messageValue, long timestamp);
    /*Encoder presses with timestamps*/
    void handleBlueEncoderPressed(int messageValue, long timestamp);
    void handleGreenEncoderPressed(int messageValue, long timestamp);
    void handleWhiteEncoderPressed(int messageValue, long timestamp);
    void handleOrangeEncoderPressed(int messageValue, long timestamp);
}
