package com.example.cyparking.spin;

public class ParkingLot {


    private int spots_quantity;
    private spot[] spot_array;
    private int available_spot;
    private int unavailable_spot;


    public ParkingLot(int spots_quantity)
    {

        this.spots_quantity = spots_quantity;
        this.spot_array = new spot[spots_quantity];
        for(int i=0; i<spots_quantity; i++)
        {
            spot_array[i] = new spot(true, i);
        }


    }
    public int getSpots_quantity()
    {
        return spots_quantity;
    }

    public spot[] getSpot_array()
    {
        return spot_array;
    }
    public void test_spotTaken(int num)
    {

        spot_array[num].setStatus(false);
    }


    public int getTotalAvailableSpots()
    {
        int counter = 0;
        for(int i=0; i<spots_quantity; i++)
        {
            if(spot_array[i].getStatus() == false)
            {
                counter ++;
            }
        }

        return counter;
    }


    public int getTotalUnavailableSpots()
    {
        int counter = 0;
        for(int i=0; i<spots_quantity; i++)
        {
            if(spot_array[i].getStatus() == true)
            {
                counter ++;
            }
        }

        return counter;
    }

    public boolean getNumStatus(int Num){
        return this.spot_array[Num].getStatus();
    }




    private class spot{
        private boolean availablity;
        private int number;
        private String liscence_plate;
        private spot(boolean availablity, int number){
            this.availablity = availablity;
            this.number = number;
        }

        private boolean getStatus ()
        {

            return availablity;
        }

        private int getNumber()
        {

            return number;
        }

        private void setStatus(boolean status)
        {

            this.availablity = status;
        }

        private void setLiscence_plate(String PlateNum)
        {
            this.liscence_plate = PlateNum;
        }

    }

}
