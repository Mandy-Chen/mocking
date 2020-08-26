package parking;

import mocking.CustomerDao;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.refEq;
import static org.mockito.Mockito.*;

public class InOrderParkingStrategyTest {

    @Test
    public void testCreateReceipt_givenACarAndAParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {

        //given
        ParkingLot parkingLot = mock(ParkingLot.class);
        Car car = mock(Car.class);
        when(parkingLot.getName()).thenReturn("parkingLot");
        when(car.getName()).thenReturn("car");
        //when
        InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();
        Receipt receipt = inOrderParkingStrategy.createReceipt(parkingLot, car);
        //then
        Assert.assertEquals(receipt.getCarName(), "car");
        Assert.assertEquals(receipt.getParkingLotName(), "parkingLot");
    }

    @Test
    public void testCreateNoSpaceReceipt_givenACar_thenGiveANoSpaceReceipt() {

        //given
        Car car = mock(Car.class);
        when(car.getName()).thenReturn("car");

        //when
        InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();
        Receipt receipt = inOrderParkingStrategy.createNoSpaceReceipt(car);

        //then
        Assert.assertEquals(receipt.getCarName(), "car");
        Assert.assertEquals(receipt.getParkingLotName(), "No Parking Lot");
    }

    @Test
    public void testPark_givenNoAvailableParkingLot_thenCreateNoSpaceReceipt() {

        //given
        Car car = mock(Car.class);
        when(car.getName()).thenReturn("car");

        ParkingLot parkingLot = mock(ParkingLot.class);
        when(parkingLot.getName()).thenReturn("parkingLot");
        when(parkingLot.getMaxCapacity()).thenReturn(10);
        doReturn(true).when(parkingLot).isFull();

        //when
        InOrderParkingStrategy inOrderParkingStrategy = spy(new InOrderParkingStrategy());
        inOrderParkingStrategy.park(Arrays.asList(parkingLot),car);
        //then
        verify(inOrderParkingStrategy, times(1)).createNoSpaceReceipt(car);
    }

    @Test
    public void testPark_givenThereIsOneParkingLotWithSpace_thenCreateReceipt() {

        //given
        Car car=mock(Car.class);
        when(car.getName()).thenReturn("car");

        ParkingLot parkingLot=mock(ParkingLot.class);
        when(parkingLot.getName()).thenReturn("parkingLot");
        when(parkingLot.getMaxCapacity()).thenReturn(10);
        doReturn(false).when(parkingLot).isFull();

        //when
        InOrderParkingStrategy inOrderParkingStrategy=spy(new InOrderParkingStrategy());
        inOrderParkingStrategy.park(Arrays.asList(parkingLot),car);

        //then
        verify(inOrderParkingStrategy,times(1)).createReceipt(parkingLot,car);

    }

    @Test
    public void testPark_givenThereIsOneFullParkingLot_thenCreateReceipt() {

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot but it is full */

    }

    @Test
    public void testPark_givenThereIsMultipleParkingLotAndFirstOneIsFull_thenCreateReceiptWithUnfullParkingLot() {

        /* Exercise 3: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for multiple parking lot situation */

    }


}
