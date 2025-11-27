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
