package parking;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class VipParkingStrategyTest {
    @Mock
    CarDao carDao;
    @InjectMocks
    VipParkingStrategy vipParkingStrategy;

    @Test
    public void testPark_givenAVipCarAndAFullParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {

        //given
        VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());

        Car car = mock(Car.class);
        when(car.getName()).thenReturn("ACar");
        doReturn(true).when(vipParkingStrategy).isAllowOverPark(car);

        ParkingLot parkingLot = mock(ParkingLot.class);
        doReturn(true).when(parkingLot).isFull();
        when(parkingLot.getName()).thenReturn("parkingLot");
        //when
        vipParkingStrategy.park(Arrays.asList(parkingLot), car);

        //then
        verify(vipParkingStrategy, times(1)).createReceipt(parkingLot, car);

    }

    @Test
    public void testPark_givenCarIsNotVipAndAFullParkingLog_thenGiveNoSpaceReceipt() {

        //given
        VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());

        Car car = mock(Car.class);
        when(car.getName()).thenReturn("car");
        doReturn(false).when(vipParkingStrategy).isAllowOverPark(car);

        ParkingLot parkingLot = mock(ParkingLot.class);
        doReturn(true).when(parkingLot).isFull();
        when(parkingLot.getName()).thenReturn("parkingLot");

        //when
        vipParkingStrategy.park(Arrays.asList(parkingLot), car);

        //then
        verify(vipParkingStrategy, times(1)).createNoSpaceReceipt(car);

    }

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsVipCar_thenReturnTrue() {

        Car car = createMockCar("ACar");
        vipParkingStrategy.isAllowOverPark(car);
        when(carDao.isVip(car.getName())).thenReturn(true);
        //when
        Boolean isVipParkingStrategy = vipParkingStrategy.isAllowOverPark(car);
        //then
        Assert.assertTrue(isVipParkingStrategy);
    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsVipCar_thenReturnFalse() {

        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
    }

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsNotVipCar_thenReturnFalse() {
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsNotVipCar_thenReturnFalse() {
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
    }

    private Car createMockCar(String carName) {
        Car car = mock(Car.class);
        when(car.getName()).thenReturn(carName);
        return car;
    }
}
