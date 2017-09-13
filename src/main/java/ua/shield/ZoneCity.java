package ua.shield;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Optional;

/**
 * Created by sa on 13.09.17.
 */
public class ZoneCity {
    private String cityName;
    private String zoneName;
    private ZoneId zoneId;

    public ZoneCity(String cityName) {
        this.cityName = cityName;
        this.zoneId=zoneIdByCity(cityName.replace(" ","_"));
        this.zoneName=zoneId.getId();
    }

    public ZoneCity(String cityName, String zoneName) {
        this.cityName = cityName;
        this.zoneName = zoneName;
        this.zoneId=zoneIdByCity(cityName.replace(" ","_"),zoneName);
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

    private ZoneId zoneIdByCity(String cityName){
        Optional<String> first = ZoneId.getAvailableZoneIds().stream()
                .filter(e -> e.subSequence(e.indexOf("/") + 1,e.length()).equals(cityName))
                .findFirst();
        return (first.isPresent())?ZoneId.of(first.get()):ZoneId.of("GMT");
    }

    private ZoneId zoneIdByCity(String cityName,String timeZone){
        Optional<String> first = ZoneId.getAvailableZoneIds().stream()
                .filter(e -> e.equals(timeZone))
                .findFirst();
    return (first.isPresent())?ZoneId.of(first.get()):zoneIdByCity(cityName);
    }

    public LocalTime getLocalTimeZone(){
        return  LocalTime.now(zoneId);
    }

}