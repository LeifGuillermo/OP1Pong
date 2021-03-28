package com.guillermo.leif.controller.midiInput;

public interface MidiGameController {
    void midiReceived(byte[] msg, long timestamp);
}
