package com.kodilla;



public class Counter {
    private int firstPlayersCounterStart;
    private int secondPlayersCounterStart;
    private int firstPlayersCounterEnd;
    private int secondPlayersCounterEnd;

    public Counter(int firstPlayersCounterStart, int secondPlayersCounterStart, int firstPlayersCounterEnd, int secondPlayersCounterEnd) {
        this.firstPlayersCounterStart = firstPlayersCounterStart;
        this.secondPlayersCounterStart = secondPlayersCounterStart;
        this.firstPlayersCounterEnd = firstPlayersCounterEnd;
        this.secondPlayersCounterEnd = secondPlayersCounterEnd;
    }

    public int getFirstPlayersCounterStart() {
        return firstPlayersCounterStart;
    }

    public void setFirstPlayersCounterStart(int firstPlayersCounterStart) {
        this.firstPlayersCounterStart = firstPlayersCounterStart;
    }

    public int getSecondPlayersCounterStart() {
        return secondPlayersCounterStart;
    }

    public void setSecondPlayersCounterStart(int secondPlayersCounterStart) {
        this.secondPlayersCounterStart = secondPlayersCounterStart;
    }

    public int getFirstPlayersCounterEnd() {
        return firstPlayersCounterEnd;
    }

    public void setFirstPlayersCounterEnd(int firstPlayersCounterEnd) {
        this.firstPlayersCounterEnd = firstPlayersCounterEnd;
    }

    public int getSecondPlayersCounterEnd() {
        return secondPlayersCounterEnd;
    }

    public void setSecondPlayersCounterEnd(int secondPlayersCounterEnd) {
        this.secondPlayersCounterEnd = secondPlayersCounterEnd;
    }
}
