package parking;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Calendar;

import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ParkingLot.class)
public class VipParkingStrategyPowerMockTest {

    @Test
    public void testCalculateHourlyPrice_givenSunday_thenPriceIsDoubleOfSundayPrice() {
        //given
        mockStatic(ParkingLot.class);
        VipParkingStrategy vipParkingStrategy = new VipParkingStrategy();
        when(ParkingLot.getBasicHourlyPrice()).thenReturn(25);
        //when
        Integer price = vipParkingStrategy.calculateHourlyPrice();
        Integer actual = 50;
        //then
        Assert.assertEquals(actual, price);

    }

    @Test
    public void testCalculateHourlyPrice_givenNotSunday_thenPriceIsDoubleOfNonSundayPrice() {

        //given
        mockStatic(ParkingLot.class);
        VipParkingStrategy vipParkingStrategy = new VipParkingStrategy();
        when(ParkingLot.getBasicHourlyPrice()).thenReturn(20);
        //when
        Integer price = vipParkingStrategy.calculateHourlyPrice();
        Integer actual = 40;
        //then
        Assert.assertEquals(actual, price);


    }
}
