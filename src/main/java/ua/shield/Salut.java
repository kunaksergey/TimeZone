package ua.shield;

import java.time.LocalTime;

/**
 * Created by sa on 15.09.17.
 */
public class Salut {
    private String salutation;
    private LocalTime start;
    private LocalTime end;

    Salut(String salutation) {
        this.salutation = salutation;
    }

    Salut(String salutation, LocalTime start, LocalTime end) {
        this.salutation = salutation;
        this.start = start;
        this.end = end;
    }

    String getSalutation() {
        return salutation;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }
}
