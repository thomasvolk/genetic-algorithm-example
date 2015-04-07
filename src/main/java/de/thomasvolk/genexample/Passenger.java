package de.thomasvolk.genexample;

public class Passenger {
    private final String ticketId;
    private final int window;
    private final int group;
    private final int direction;

    public Passenger(String ticketId, int window, int group, int direction) {
        this.ticketId = ticketId;
        this.window = window;
        this.group = group;
        this.direction = direction;
    }

    public String getTicketId() {
        return ticketId;
    }

    public int getWindow() {
        return window;
    }

    public int getGroup() {
        return group;
    }

    public int getDirection() {
        return direction;
    }
}
