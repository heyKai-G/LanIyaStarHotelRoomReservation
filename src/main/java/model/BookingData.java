/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;
import java.util.Date;
import javax.swing.JOptionPane;
/**
 *
 * @author 
 */
public class BookingData {
    private String Booker_name;
    private Date birthdate;
    private String email;
    private String mobile;

    private int adults;
    private int children;
    private List<Integer> childrenAges;

    private String destinationType;
    private String destination;

    private Date checkIn;
    private Date checkOut;
    
    private String selectedRoomIdInput;
    
    // DANIEL -> = = = START: Fields for Add-ons (Bed, Blanket, Pillow, Toiletries) = = = 
    private int numBeds;
    private int numNightsBed;
    private int numBlankets;
    private int numPillows;
    private int numToiletries;
    private String selectedRoomCapacity;
    // DANIEL -> = = = END: Fields for Add-ons = = =
    
    // START: Fields for Services (Guests, PWD/Senior, Days)
    // Swimming Pool
    private int sp_guests;
    private int sp_pwdSenior;
    private int sp_days;
    // Gym
    private int gym_guests;
    private int gym_pwdSenior;
    private int gym_days;
    // Foot Spa
    private int fs_guests;
    private int fs_pwdSenior;
    private int fs_days;
    // Aroma Facial Massage
    private int afm_guests;
    private int afm_pwdSenior;
    private int afm_days;
    // Thai Massage
    private int tm_guests;
    private int tm_pwdSenior;
    private int tm_days;
    // END: Fields for Services
    
    private String determinedSeason; // <<< ADDED
    private String selectedRateColumn; // <<< ADDED
    
    private double grandTotal;


    // ==== GETTERS & SETTERS ====
    public String getBookerName() { return Booker_name; }
    public void setBooker_name(String name) { this.Booker_name = name; }


    public Date getBirthdate() { return birthdate; }
    public void setBirthdate(Date birthdate) { this.birthdate = birthdate; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }

    public int getAdults() { return adults; }
    public void setAdults(int adults) { this.adults = adults; }

    public int getChildren() { return children; }
    public void setChildren(int children) { this.children = children; }

    public List<Integer> getChildrenAges() { return childrenAges; }
    public void setChildrenAges(List<Integer> childrenAges) { this.childrenAges = childrenAges; }

    public String getDestinationType() { return destinationType; }
    public void setDestinationType(String destinationType) { this.destinationType = destinationType; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public Date getCheckIn() { return checkIn; }
    public void setCheckIn(Date checkIn) { this.checkIn = checkIn; }

    public Date getCheckOut() { return checkOut; }
    public void setCheckOut(Date checkOut) { this.checkOut = checkOut; }

    public String getSelectedRoomIdInput() { return selectedRoomIdInput; }

    public void setSelectedRoomIdInput(String selectedRoomIdInput) { this.selectedRoomIdInput = selectedRoomIdInput; }
    
    // GLEN -> = = = START: New Getters & Setters for Add-ons = = =
    public int getNumBeds() { return numBeds; }
    public void setNumBeds(int numBeds) { this.numBeds = numBeds; }
    
    public int getNumNightsBed() { return numNightsBed; }
    public void setNumNightsBed(int numNightsBed) { this.numNightsBed = numNightsBed; }

    public int getNumBlankets() { return numBlankets; }
    public void setNumBlankets(int numBlankets) { this.numBlankets = numBlankets; }

    public int getNumPillows() { return numPillows; }
    public void setNumPillows(int numPillows) { this.numPillows = numPillows; }

    public int getNumToiletries() { return numToiletries; }
    public void setNumToiletries(int numToiletries) { this.numToiletries = numToiletries; }
    // GLEN -> = = = END: New Getters & Setters for Add-ons = = = 
    
    // START: New Getters & Setters for Services
    
    // Swimming Pool
    public int getSp_guests() { return sp_guests; }
    public void setSp_guests(int sp_guests) { this.sp_guests = sp_guests; }

    public int getSp_pwdSenior() { return sp_pwdSenior; }
    public void setSp_pwdSenior(int sp_pwdSenior) { this.sp_pwdSenior = sp_pwdSenior; }

    public int getSp_days() { return sp_days; }
    public void setSp_days(int sp_days) { this.sp_days = sp_days; }

    // Gym
    public int getGym_guests() { return gym_guests; }
    public void setGym_guests(int gym_guests) { this.gym_guests = gym_guests; }

    public int getGym_pwdSenior() { return gym_pwdSenior; }
    public void setGym_pwdSenior(int gym_pwdSenior) { this.gym_pwdSenior = gym_pwdSenior; }

    public int getGym_days() { return gym_days; }
    public void setGym_days(int gym_days) { this.gym_days = gym_days; }

    // Foot Spa
    public int getFs_guests() { return fs_guests; }
    public void setFs_guests(int fs_guests) { this.fs_guests = fs_guests; }

    public int getFs_pwdSenior() { return fs_pwdSenior; }
    public void setFs_pwdSenior(int fs_pwdSenior) { this.fs_pwdSenior = fs_pwdSenior; }

    public int getFs_days() { return fs_days; }
    public void setFs_days(int fs_days) { this.fs_days = fs_days; }

    // Aroma Facial Massage
    public int getAfm_guests() { return afm_guests; }
    public void setAfm_guests(int afm_guests) { this.afm_guests = afm_guests; }

    public int getAfm_pwdSenior() { return afm_pwdSenior; }
    public void setAfm_pwdSenior(int afm_pwdSenior) { this.afm_pwdSenior = afm_pwdSenior; }

    public int getAfm_days() { return afm_days; }
    public void setAfm_days(int afm_days) { this.afm_days = afm_days; }

    // Thai Massage
    public int getTm_guests() { return tm_guests; }
    public void setTm_guests(int tm_guests) { this.tm_guests = tm_guests; }

    public int getTm_pwdSenior() { return tm_pwdSenior; }
    public void setTm_pwdSenior(int tm_pwdSenior) { this.tm_pwdSenior = tm_pwdSenior; }

    public int getTm_days() { return tm_days; }
    public void setTm_days(int tm_days) { this.tm_days = tm_days; }
    
    public String getSelectedRoomCapacity() { return selectedRoomCapacity; }

public void setSelectedRoomCapacity(String selectedRoomCapacity) { this.selectedRoomCapacity = selectedRoomCapacity; }

public String getDeterminedSeason() { return determinedSeason; }
    public void setDeterminedSeason(String determinedSeason) { this.determinedSeason = determinedSeason; }

    public String getSelectedRateColumn() { return selectedRateColumn; }
    public void setSelectedRateColumn(String selectedRateColumn) { this.selectedRateColumn = selectedRateColumn; }
    
    public double getGrandTotal() { return grandTotal; }
    public void setGrandTotal(double grandTotal) { this.grandTotal = grandTotal; }
    
    // END: New Getters & Setters for Services

   public void displayBookingData(BookingData data) {
    String message =
        "Booking Information\n\n" +
        "Name: " + data.getBookerName() + "\n" +
        "Birthdate: " + data.getBirthdate() + "\n" +
        "Email: " + data.getEmail() + "\n" +
        "Mobile: " + data.getMobile() + "\n" +
        "Adults: " + data.getAdults() + "\n" +
        "Children: " + data.getChildren() + "\n" +
        "Check-in: " + data.getCheckIn() + "\n" +
        "Check-out: " + data.getCheckOut() + "\n";

    JOptionPane.showMessageDialog(null, message, "Stored Data Verification", JOptionPane.INFORMATION_MESSAGE);
}

    

}