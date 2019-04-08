package com.example.demo.MockTest;

import com.example.demo.CurrentlyParked.CurrentlyParked;
import com.example.demo.CurrentlyParked.CurrentlyParkedController;
import com.example.demo.CurrentlyParked.CurrentlyParkedRepository;
import com.example.demo.CurrentlyParked.CurrentlyParkedService;
import com.example.demo.UserLogin.UserLogin;
import com.example.demo.UserLogin.UserLoginRepository;
import com.example.demo.UserLogin.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ParkTest {

    @InjectMocks
    CurrentlyParkedController ctrl;

    @Mock
    CurrentlyParkedRepository repo;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testObject() {


        CurrentlyParked user = new CurrentlyParked();
        user.setUsername("mrs poop");
        user.setLotid("A");//steaksauce
        user.setSpotnum(1);
        user.setLicense("313B4ME");
        user.setTimeparked((long) 11111111);

        assertEquals("mrs poop", user.getUsername());
        assertEquals("A", user.getLotid());
        assertEquals(new Integer(1), user.getSpotnum());
        assertEquals("313B4ME", user.getLicense());
        assertEquals(new Long( 11111111), user.getTimeparked());
    }

    @Test
    public void getAllUsersTest() {

        List<CurrentlyParked> list =  new ArrayList<CurrentlyParked>();
        CurrentlyParked u1 = new CurrentlyParked();
        u1.setUsername("mary jane");
        u1.setLotid("A");
        u1.setSpotnum(1);
        u1.setLicense("HEYSUP");
        u1.setTimeparked((long) 11111);

        CurrentlyParked u2 = new CurrentlyParked();
        u2.setUsername("joe muskrat");
        u2.setLotid("A");
        u2.setSpotnum(2);
        u2.setLicense("HOWDY");
        u2.setTimeparked( (long) 11112);
        list.add(u1);
        list.add(u2);

        List<Integer> fList = new ArrayList<>();
        fList.add(1);
        fList.add(2);

        when(repo.getFilledSpots("A")).thenReturn(fList);

        List<Integer> filled = repo.getFilledSpots("A");



        assertEquals(2,filled.size());

    }

    @Test
    public void getParkedByUsername(){
        CurrentlyParked u1 = new CurrentlyParked();
        u1.setUsername("mary jane");
        u1.setLotid("A");
        u1.setSpotnum(1);
        u1.setLicense("HEYSUP");
        u1.setTimeparked((long) 11111);

        CurrentlyParked u2 = new CurrentlyParked();
        u2.setUsername("joe muskrat");
        u2.setLotid("A");
        u2.setSpotnum(2);
        u2.setLicense("HOWDY");
        u2.setTimeparked( (long) 11112);

        when(repo.getLicenseFromUsername("joe muskrat")).thenReturn("HOWDY");

        assertEquals("HOWDY",repo.getLicenseFromUsername("joe muskrat"));

    }


}
