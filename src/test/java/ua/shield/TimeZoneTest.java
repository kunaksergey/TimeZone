package ua.shield;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;

/**
 * Created by sa on 13.09.17.
 */
public class TimeZoneTest {

    @Before
    public void set() {

    }


    //6:00-9:00
    //6:00 -start morning
    @Test
    public void testTimeMorningStart() {
        LocalTime lt = LocalTime.of(6, 0);
        Message message = new Message();
        Salut salut = message.getSalutByTime(lt);
        Assert.assertEquals(Message.getBundle().getString("message.morning"), salut.getSalutation());

    }

    //6:00-9:00
    //7:45 -middle morning
    @Test
    public void testTimeMorningMiddle() {
        LocalTime lt = LocalTime.of(7, 45);
        Message message = new Message();
        Salut salut = message.getSalutByTime(lt);
        Assert.assertEquals(Message.getBundle().getString("message.morning"), salut.getSalutation());

    }


    //9:00-19:00
    //9:00 -start afternoon
    @Test
    public void testTimeAfternoonStart() {
        LocalTime lt = LocalTime.of(9, 0);
        Message message = new Message();
        Salut salut = message.getSalutByTime(lt);
        Assert.assertEquals(Message.getBundle().getString("message.afternoon"), salut.getSalutation());

    }

    //9:00-19:00
    //14:00 -middle afternoon
    @Test
    public void testTimeAfternoonMiddle() {
        LocalTime lt = LocalTime.of(14, 0);
        Message message = new Message();
        Salut salut = message.getSalutByTime(lt);
        Assert.assertEquals(Message.getBundle().getString("message.afternoon"), salut.getSalutation());

    }

    //19:00-23:00
    //19:00-start evening
    @Test
    public void testTimeEveningStart() {
        LocalTime lt = LocalTime.of(19, 0);
        Message message = new Message();
        Salut salut = message.getSalutByTime(lt);
        Assert.assertEquals(Message.getBundle().getString("message.evening"), salut.getSalutation());
    }

    //19:00-23:00
    //21:45 -middle evening
    @Test
    public void testTimeEveningMiddle() {
        LocalTime lt = LocalTime.of(21, 45);
        Message message = new Message();
        Salut salut = message.getSalutByTime(lt);
        Assert.assertEquals(Message.getBundle().getString("message.evening"), salut.getSalutation());
    }


    //23:00-06:00
    //23:00- start night
    @Test
    public void testTimeNightStart() {
        LocalTime lt = LocalTime.of(23, 0);
        Message message = new Message();
        Salut salut = message.getSalutByTime(lt);
        Assert.assertEquals(Message.getBundle().getString("message.night"), salut.getSalutation());
    }

    //23:00-06:00
    //4:00- middle night
    @Test
    public void testTimeNightMiddle() {
        LocalTime lt = LocalTime.of(4, 0);
        Message message = new Message();
        Salut salut = message.getSalutByTime(lt);
        Assert.assertEquals(Message.getBundle().getString("message.night"), salut.getSalutation());
    }

    //there is zone for city "New York"
    @Test
    public void testZoneCityHasNewYourk() {
        String city="New York";
        ZoneCity zoneCity=new ZoneCity(city);
        Assert.assertEquals("America/New_York",zoneCity.getZoneName());
    }


    //there is zone for city "Kiev"
    @Test
    public void testZoneCityThatHasKiev() {
        String city="Kiev";
        ZoneCity zoneCity=new ZoneCity(city);
        Assert.assertEquals("Europe/Kiev",zoneCity.getZoneName());
    }

    //there is zone for city "Kiev"
    @Test
    public void testZoneCityThatHasTbilisi() {
        String city="Tbilisi";
        ZoneCity zoneCity=new ZoneCity(city);
        Assert.assertEquals("Asia/Tbilisi",zoneCity.getZoneName());
    }

    //there is not for city "Dnipro",use GMT
    @Test
    public void testZoneCityNotHasDnipro() {
        String city="Dnipro";
        ZoneCity zoneCity=new ZoneCity(city);
        Assert.assertEquals("GMT",zoneCity.getZoneName());
    }

    //there is not for zone for city "Dniepr",but is timeZone
    @Test
    public void testZoneThatHasBatumi() {
        String city="Batumi";
        String zone="Asia/Tbilisi";
        ZoneCity zoneCity=new ZoneCity(city,zone);
        Assert.assertEquals("Asia/Tbilisi",zoneCity.getZoneId().getId());
    }

    //there is not for zone for city "Dniepr" and wrong timeZone
    @Test
    public void testZoneThatHasDniproAndWrongZone() {
        String city="Dnipro";
        String zone="Labuda";
        ZoneCity zoneCity=new ZoneCity(city,zone);
        Assert.assertEquals("GMT",zoneCity.getZoneId().getId());
    }




}
