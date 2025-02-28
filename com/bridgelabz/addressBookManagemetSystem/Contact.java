package com.bridgelabz.addressBookManagemetSystem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Contact {
    Scanner sc = new Scanner(System.in);
    HashMap<String, ArrayList<UserData>> hashMap = new HashMap<>();


    //UC6 = Refactor to add multiple Address Book to the System. Each Address Book
    //has a unique Name - Use Console to add new Address Book - Maintain Dictionary of Address Book Name to address bookk

    //-------------------------------------------------------------------------------------------------------------------//
    // method For Adding Multiple Address Book.

    public void addressBook(Contact contact) {
        int ans;
        do {
            System.out.print("Enter Name for Address Book: ");
            String addressBookName = sc.next();

            if (hashMap.containsKey(addressBookName)) {
                System.out.println("The Address Book is already exist...\nPlease Rename Address Book.");
            } else {
                ArrayList<UserData> userData = new ArrayList<>();
                contact.menuOption(contact, userData);
                hashMap.put(addressBookName, userData);
            }
            System.out.println("AddressBook Added... \n" +hashMap+ " " + "\n");
            System.out.println("If you want to add another address book Press 1");
            System.out.println("For exit press 0 ");
            ans = sc.nextInt();
        } while (ans == 1);
    }
    //-------------------------------------------------------------------------------------------------------------------//
    //method For Displaying the Menu Option.

    private void menuOption(Contact contact, ArrayList<UserData> userData) {
        int choice;
        while (true) {
            System.out.println("""
                    1: Add Contact.\s
                    2: Update Contact.\s
                    3: Delete Contact.\s
                    4: View Contact.\s
                    5: Search Contact in City or State.\s
                    6: Sort Contact By Name.\s
                    7: Sort Contact By City.\s
                    8: Sort Contact By State.\s
                    9: Sort Contact By ZipCode.\s
                    10: Exit.""" );
            System.out.println();
            System.out.print("Enter Your Choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    contact.addUser(userData);
                    break;

                case 2:
                    contact.update(userData);
                    break;

                case 3:
                    contact.deleteContact(userData);
                    break;

                case 4:
                    contact.display(userData);
                    break;

                case 5:
                    contact.searchPersonByCityOrState(userData);
                    break;

                case 6:
                    contact.sortContactByName(userData);
                    break;

                case 7:
                    contact.sortContactByCity(userData);
                    break;

                case 8:
                    contact.sortContactByState(userData);
                    break;

                case 9:
                    contact.sortContactByZipCode(userData);
                    break;

                case 10:
                    System.out.println(" Exit");
                    return;

                default:
                    System.out.println("You have Entered Wrong Choice ");
                    System.out.println();
            }
        }
    }

    //-------------------------------------------------------------------------------------------------------------------//
    //method For Adding contact in  Address Book.

    public void addUser(ArrayList<UserData> userData) {
        Scanner sc = new Scanner(System.in);
        System.out.println();

        System.out.print("Enter First Name: ");
        String firstName = sc.next();

        System.out.print("Enter Last Name: ");
        String lastName = sc.next();

        System.out.print("Enter Contact Number: ");
        long phoneNumber = sc.nextLong();

        System.out.print("Enter Email_ID: ");
        String emailID = sc.next();

        System.out.print("Enter Address: ");
        String address = sc.next();

        System.out.print("Enter City Name: ");
        String cityName = sc.next();

        System.out.print("Enter StateName: ");
        String stateName = sc.next();

        System.out.print("Enter Postal Code(Zip Code): ");
        int zipCode = sc.nextInt();

        if (userData.size() == 0) {
            UserData data = new UserData(firstName, lastName, phoneNumber, emailID, address, cityName, stateName, zipCode);
            userData.add(data);
            System.out.println("\n Added Successfully... \n");
        } else {
            /*
            For searching the duplicate names in arraylist
             */
            for (int i = 0; i < userData.size(); i++) {
                UserData details = userData.get(i);
                /*
                checking the duplicate name.
                 */
                if (firstName.equalsIgnoreCase(details.getFirstName())) {
                    System.out.println("--First Name is Already Exist \nPlease change First Name\n ");
                    return;
                } else {
                    //all data store in list
                    UserData data = new UserData(firstName, lastName, phoneNumber, emailID, address, cityName, stateName, zipCode);
                    userData.add(data);
                    System.out.println("\n Added Successfully... \n");
                    return;

                }
            }
        }
    }


    //-------------------------------------------------------------------------------------------------------------------//
    //method For Display contacts details of Address Book.

    public void display(ArrayList<UserData> userData) {

        if (userData.size() == 0) {
            System.out.println("No Data Found....Empty Address Book ");
            System.out.println();
        } else {
            System.out.println(+userData.size()+ " Contacts Available in This address Book");
            for (int i = 0; i < userData.size(); i++) {
                System.out.println(userData.get(i));
                System.out.println(" ");
            }
        }
    }

    //-------------------------------------------------------------------------------------------------------------------//
    //method For Update contacts details of Address Book.

    public void update(ArrayList<UserData> userData) {
        System.out.println("Enter First And Last Name to Update details ");
        String first_name = sc.next();
        String last_name = sc.next();

        for (int i = 0; i < userData.size(); i++) {
            UserData data = userData.get(i);

            if (first_name.equals(data.getFirstName()) && last_name.equals(data.getLastName())) {
                System.out.println("""
                        Enter 1 for Update Address.\s
                        Enter 2 for contact Number.\s
                        Enter 3 for Email adderss.""");

                System.out.print("Enter Choice to Update. ");
                int choice =  sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter New Address: ");
                        String address = sc.next();
                        data.setAddress(address);

                        System.out.print("Enter New City Name: ");
                        String city = sc.next();
                        data.setCityName(city);

                        System.out.print("Enter New State: ");
                        String state = sc.next();
                        data.setStateName(state);

                        System.out.print("Enter New postal code(Zip Code): ");
                        int zipCode = sc.nextInt();
                        data.setZipCode(zipCode);
                        break;

                    case 2:
                        System.out.print("Enter New Contact Number: ");
                        long phoneNumber = sc.nextLong();
                        data.setPhoneNumber(phoneNumber);
                        break;

                    case 3:
                        System.out.print("Enter New Email ID: ");
                        String emailID = sc.next();
                        data.setEmailID(emailID);
                        break;
                } System.out.println("Updated Successfully...\n");
                return;
            } else {
                System.out.println("No record Found ");
            }

        }
        System.out.println();
    }
    //-------------------------------------------------------------------------------------------------------------------//
    //method For Delete contacts from Address Book.
    public void deleteContact(ArrayList<UserData> userData) {
        if (userData.size() == 0) {
            System.out.println("No Record Found...Empty Address Book");
        }

        else {
            System.out.print("Enter First Name to delete Contact from Address Book: ");
            String first_name =  sc.next();
            for (int i = 0; i < userData.size(); i++) {
                UserData data = userData.get(i);
                if (first_name.equals(data.getFirstName())) {
                    userData.remove(i);
                    System.out.println();
                    System.out.println("Contact Deleted SuccessFully...");

                } else {
                    System.out.println("User does not exist ");
                }
            }
        }
        System.out.println();
    }
    //-------------------------------------------------------------------------------------------------------------------//
    //UC8 = Ability to search Person in a City or State across the multiple AddressBook
    //UC9 = Ability to view Persons by City or State
    //Using Java Streams

    public void searchPersonByCityOrState(ArrayList<UserData> userData) {
        System.out.print("Enter The City Name: ");
        String city = sc.next();

        System.out.print("Enter The State Name: ");
        String state = sc.next();

        userData.stream().filter(data -> data.getCityName().equalsIgnoreCase(city) && data.getStateName().equalsIgnoreCase(state))
                .collect(Collectors.toList())
                .forEach(data -> System.out.println("\n...Contacts Found...\n" +data));

        //UC10=Ability to get number of contact persons i.e. count by City or State
        long count = userData.stream().filter(data -> data.getCityName().equalsIgnoreCase(city) && data.getStateName().equalsIgnoreCase(state)).count();
        System.out.println("\n" +count+ " Persons Found in City " +city+ " and " +state+ ". \n");
    }

    //UC11 = Ability to sort the entries in the address book alphabetically by Person’s name
    public void sortContactByName(ArrayList<UserData> userData) {
        userData.stream().sorted(Comparator.comparing(UserData::getFirstName))
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    //UC12 = Ability to sort the entries in the address book by City, State, or Zip - Write functions to sort
    public void sortContactByCity(ArrayList<UserData> userData) {
        userData.stream().sorted(Comparator.comparing(UserData::getCityName))
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    public void sortContactByState(ArrayList<UserData> userData) {
        userData.stream().sorted(Comparator.comparing(UserData::getStateName))
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    public void sortContactByZipCode(ArrayList<UserData> userData) {
        userData.stream().sorted(Comparator.comparing(UserData::getZipCode))
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }
}
