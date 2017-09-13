package ua.shield;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by sa on 13.09.17.
 */
public class Message {
    private ZoneCity zoneCity;
    private List<Salut> salutList = new ArrayList<>();

    {
        salutList.add(new Salut(getBundle().getString("message.morning"), LocalTime.of(6, 0), LocalTime.of(9, 0)));
        salutList.add(new Salut(getBundle().getString("message.afternoon"), LocalTime.of(9, 0), LocalTime.of(19, 0)));
        salutList.add(new Salut(getBundle().getString("message.evening"), LocalTime.of(19, 0), LocalTime.of(23, 0)));
        salutList.add(new Salut(getBundle().getString("message.night"), LocalTime.of(23, 0), LocalTime.of(6, 0)));

    }

    public Message() {
    }

    public Message(ZoneCity zoneCity) {
        this.zoneCity = zoneCity;
    }

    Salut getSalutByTime(LocalTime localTime) {
        Optional<Salut> first = salutList.stream()
                .filter(e -> {
                            if (e.start.toSecondOfDay() > e.end.toSecondOfDay()) {
                                return e.start.toSecondOfDay() >= localTime.toSecondOfDay() || e.end.toSecondOfDay() < localTime.toSecondOfDay();
                            } else
                                return e.start.toSecondOfDay() <= localTime.toSecondOfDay() && e.end.toSecondOfDay() > localTime.toSecondOfDay();
                        }
                )
                .findFirst();
        return first.isPresent() ? first.get() : new Salut(getBundle().getString("message.error"));
    }

    public static ResourceBundle getBundle() {
        return ResourceBundle.getBundle("message", new UTF8Control());
    }

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
    }

    String getMessageByZoneId() {
        return getSalutByTime(zoneCity.getLocalTimeZone()).getSalutation() + ", " + zoneCity.getCityName() + "!";
    }

    ZoneCity getZoneCity() {
        return zoneCity;
    }
}
