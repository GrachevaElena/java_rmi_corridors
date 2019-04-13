package com.client.form;

public enum Info {
    TRY_CONNECT("Click to bind"),
    WAIT_FOR_ANOTHER_PLAYER("Waiting for another player"),
    WAIT_FOR_YOUR_MOVE("Waiting for your move"),
    WAIT_FOR_ANOTHER_MOVE("Waiting for another player's move"),
    YOU_WON("You won :)"),
    YOU_LOSE("You lose :(");

    private String value;

    Info(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
