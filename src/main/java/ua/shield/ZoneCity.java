package ua.shield;

import java.time.LocalTime;
import java.time.ZoneId;

/**
 * Created by sa on 13.09.17.
 */
public class ZoneCity {
    private String cityName;
    private String zoneName;
    private ZoneId zoneId;

    public ZoneCity(String cityName) {
        this.cityName = cityName;
        this.zoneId = ZoneId.of(zoneIdByCity(cityName.replace(" ", "_")));
        this.zoneName = zoneId.getId();
    }

    public ZoneCity(String cityName, String zoneName) {
        this.cityName = cityName;
        this.zoneName = zoneName;
        this.zoneId = ZoneId.of(zoneIdByCity(cityName.replace(" ", "_"), zoneName));
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public ZoneId getZoneId() {
        return zoneId;
    }

    public void setZoneId(ZoneId zoneId) {
        this.zoneId = zoneId;
    }

    private String zoneIdByCity(String cityName) {
        return ZoneId.getAvailableZoneIds().stream()
                .filter(e -> e.subSequence(e.indexOf("/") + 1, e.length()).equals(cityName))
                .findFirst().orElse("GMT");
    }

    private String zoneIdByCity(String cityName, String timeZone) {
        return ZoneId.getAvailableZoneIds().stream()
                .filter(e -> e.equals(timeZone))
                .findFirst().orElse(zoneIdByCity(cityName));
    }

    //as alternative we can return type ZoneId
    private ZoneId zoneIdByCityAlt(String cityName) {
        return ZoneId.getAvailableZoneIds().stream()
                .filter(e -> e.subSequence(e.indexOf("/") + 1, e.length()).equals(cityName))
                .findFirst().map(e -> ZoneId.of(e)).orElseGet(() -> ZoneId.of("GMT"));
    }

    //as alternative we can return type ZoneId
    private ZoneId zoneIdByCityAlt(String cityName, String timeZone) {
        return ZoneId.getAvailableZoneIds().stream()
                .filter(e -> e.equals(timeZone))
                .findFirst().map(e -> ZoneId.of(e)).orElseGet(() -> zoneIdByCityAlt(cityName));
    }

    /**********************************************/

    public LocalTime getLocalTimeZone() {
        return LocalTime.now(zoneId);
    }

}
