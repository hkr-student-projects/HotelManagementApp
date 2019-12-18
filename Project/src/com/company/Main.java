package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        String Customer = null;
        int roomNum = 1;
        String Option;

        String[] hotel = new String[12];

        initialise(hotel);

        while ( roomNum  < 11 )
        {

            System.out.println("Hotel Booking Options");
            System.out.println("V: To View all rooms");
            System.out.println("A: To Add customer to a room");
            System.out.println("E: To Display empty rooms");
            System.out.println("D: To Delete customer from a room");
            System.out.println("F: Find room from customer name");
            System.out.println("O: View rooms alphabetically by name");

            Option = input.next();

            if (Option.equals("V")){
                view(hotel, Customer);
            }

            if (Option.equals("A")){ // adding a customer to a room

                System.out.println("Enter room number(1-10)");
                roomNum =input.nextInt();
                System.out.println("Enter name for room " + roomNum + " : " ) ;
                Customer = input.next();
                hotel[roomNum] = Customer ;
                add(hotel, Customer);
                System.out.println(" ");

            }

            if (Option.equals("E")){ //view all empty rooms
                vacant(hotel, Customer); //links to private static void empty
            }

            if (Option.equals("D")){ //Deletes a customer from a room

                //Searches if room is occupied, if it is then it will delete customer from that room
                view(hotel, Customer);
                System.out.println("Enter the room which you want to delete a customer from: ");
                roomNum = input.nextInt();
                hotel[roomNum] = "empty";

                delete(hotel, Customer);
                System.out.println("");

            }

            if (Option.equals("F")){ //view all empty rooms
                find(hotel); //links to private static void empty
            }

        }
    }

    private static void initialise( String hotelRef[] )
    {
        for (int x = 1; x < 11; x++ )
            hotelRef[x] = "empty";
        System.out.println( "Welcome to The Plaza");
    }


    public static void view(String hotel[], String Customer){

        for (int x =1; x <11; x++)
        {
            int z=0;
            String Customername = Customer;
            hotel[z]= Customername;

            if (hotel[x].equals("empty"))
                System.out.println("room " + x + " is empty");
            else {
                System.out.println("room " + x + " is occupied by "+ hotel[z]);
            }
        }
    }

    private static void add(String hotel[], String Customer){
        for (int x =1; x <11; x++)
        {
            int z=0;
            String Customername = Customer;
            hotel[z]= Customername;
            if (hotel[x].equals("empty"))
                System.out.println("room " + x + " is empty");
            else {
                System.out.println("room " + x + " is occupied by "+ hotel[z]);
            }

        }
    }

    private static void vacant(String hotel[], String Customer){
        for (int x = 1; x < 11; x++ )
        {
            int z=0;
            String Customername = Customer;
            hotel[z]= Customername;
            if (hotel[x].equals("empty")) //if statement
                System.out.println("room " + x + " is empty");
        }

    }


    private static void delete(String hotel[], String Customer){ //link to this when pressed the D key
        //view (hotel);
        for (int x = 1; x < 11; x++ )
        {
            int z=0;
            String Customername = Customer;
            hotel[z]= Customername;
            if (hotel[x].equals("empty"))
            {
                System.out.println("room " + x + " is empty");
            }
            else {
                System.out.println("room " + x + " occupied by " + hotel[x]);
            }
        }
    }

    private static void find(String hotel[]){

        Scanner input = new Scanner(System.in);
        System.out.println("Enter customer name for room:" );
        String customersname;
        customersname = input.next();  //stores name they enter as customers name
        for (int x = 0; x < 10; x++ )
        {
            if (hotel[x].equals(customersname))
                System.out.println("room " + x + " is occupied                       by "+hotel[x]);

        }
    }}

