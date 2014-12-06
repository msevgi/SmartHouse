package com.msevgi.smarthouse.event;

public class NavigationItemSelectEvent {
    private int position;

    public NavigationItemSelectEvent() {
    }

    public NavigationItemSelectEvent(int position) {
        this.position = position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
