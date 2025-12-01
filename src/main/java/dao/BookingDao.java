/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Lenovo
 */

import java.sql.*;
import model.BookingData;

public class BookingDao {
     private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/hotel_reservation",
                "root",
                "password"
        );
    }
     
         public boolean saveBooking(BookingData data) {
    
    // =======================================================
    // 1. ROBUSTLY DETERMINE AND SET room_type (YOUR FIX)
    // =======================================================
    int roomId;
    String roomType;

    try {
        // 1a. Ensure the Room ID is not null/empty and can be parsed
        if (data.getSelectedRoomIdInput() == null || data.getSelectedRoomIdInput().trim().isEmpty()) {
            System.err.println("CRITICAL ERROR: Room ID input is missing in BookingData.");
            return false;
        }
        
        roomId = Integer.parseInt(data.getSelectedRoomIdInput().trim());
    } catch (NumberFormatException e) {
        System.err.println("CRITICAL ERROR: Room ID is not a valid number: " + data.getSelectedRoomIdInput());
        e.printStackTrace();
        return false;
    }

    // 1b. Map the Room ID to the Room Type String
    switch (roomId) {
        case 1:
            roomType = "Standard";
            break;
        case 2:
            roomType = "Deluxe";
            break;
        case 3:
            roomType = "Quadruple";
            break;
        case 4:
            roomType = "Family";
            break;
        case 5:
            roomType = "Suite";
            break;
        default:
            System.err.println("CRITICAL ERROR: Unknown Room ID found: " + roomId);
            return false; 
    }

    // 1c. Set the determined Room Type in the BookingData object
    data.setSelectedRoomType(roomType);

        String sqlBookingForm = 
            "INSERT INTO booking_form (room_id, check_in, check_out) VALUES (?, ?, ?)";

        String sqlGuestInfo =
            "INSERT INTO guest_info (booking_id, room_id, booker_name, booker_birthdate, "
          + "booker_age, adults, children, children_ages, contact, email) "
          + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        String sqlAddon =
            "INSERT INTO addon (room_id, guest_id, bed, blanket, pillow, toiletries) "
          + "VALUES (?, ?, ?, ?, ?, ?)";

        String sqlService =
            "INSERT INTO service (room_id, guest_id, pool, gym, spa, pwd_senior) "
          + "VALUES (?, ?, ?, ?, ?, ?)";

        String sqlDestinationLocal =
            "INSERT INTO local_destinations (booking_id, room_id, guest_id, destination_type, "
          + "destination, room_type, check_in, check_out) "
          + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        String sqlDestinationInternational =
            "INSERT INTO international_destinations (booking_id, room_id, guest_id, destination_type, "
          + "destination, room_type, check_in, check_out) "
          + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        String sqlPayment = 
    "INSERT INTO payment (transaction_id, booking_id, room_id, guest_id, payment_method) "
  + "VALUES (?, ?, ?, ?, ?)"; // <-- Added 'transaction_id' and its placeholde

        Connection conn = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false); // START TRANSACTION

            // =======================
            // 1. INSERT INTO booking_form
            // =======================
            PreparedStatement ps1 = conn.prepareStatement(sqlBookingForm, Statement.RETURN_GENERATED_KEYS);
            ps1.setInt(1, Integer.parseInt(data.getSelectedRoomIdInput()));
            ps1.setDate(2, new java.sql.Date(data.getCheckIn().getTime()));
            ps1.setDate(3, new java.sql.Date(data.getCheckOut().getTime()));
            ps1.executeUpdate();

            ResultSet rs1 = ps1.getGeneratedKeys();
            int bookingId = 0;
            if (rs1.next()) bookingId = rs1.getInt(1);

            // =======================
            // 2. INSERT guest_info
            // =======================
            PreparedStatement ps2 = conn.prepareStatement(sqlGuestInfo, Statement.RETURN_GENERATED_KEYS);
            ps2.setInt(1, bookingId);
            ps2.setInt(2, Integer.parseInt(data.getSelectedRoomIdInput()));
            ps2.setString(3, data.getBookerName());
            ps2.setDate(4, new java.sql.Date(data.getBirthdate().getTime()));
            ps2.setInt(5, data.getAge());
            ps2.setInt(6, data.getAdults());
            ps2.setInt(7, data.getChildren());
            ps2.setString(8, data.getChildrenAgeStr());
            ps2.setString(9, data.getMobile());
            ps2.setString(10, data.getEmail());
            ps2.executeUpdate();

            ResultSet rs2 = ps2.getGeneratedKeys();
            int guestId = 0;
            if (rs2.next()) guestId = rs2.getInt(1);

            // =======================
            // 3. ADD-ONS
            // =======================
            PreparedStatement ps3 = conn.prepareStatement(sqlAddon);
            ps3.setInt(1, Integer.parseInt(data.getSelectedRoomIdInput()));
            ps3.setInt(2, guestId);
            ps3.setInt(3, data.getNumBeds());
            ps3.setInt(4, data.getNumBlankets());
            ps3.setInt(5, data.getNumPillows());
            ps3.setInt(6, data.getNumToiletries());
            ps3.executeUpdate();

            // =======================
            // 4. SERVICES
            // =======================
            PreparedStatement ps4 = conn.prepareStatement(sqlService);
            ps4.setInt(1, Integer.parseInt(data.getSelectedRoomIdInput()));
            ps4.setInt(2, guestId);
            ps4.setInt(3, data.getSp_guests());
            ps4.setInt(4, data.getGym_guests());
            ps4.setInt(5, data.getallSpa());
            ps4.setInt(6, data.getallPwd());
            ps4.executeUpdate();

            // =======================
            // 5. DESTINATION — check local/international
            // =======================
            if (data.getDestinationType().equalsIgnoreCase("Local")) {
                PreparedStatement psLocal = conn.prepareStatement(sqlDestinationLocal);
                psLocal.setInt(1, bookingId);
                psLocal.setInt(2, Integer.parseInt(data.getSelectedRoomIdInput()));
                psLocal.setInt(3, guestId);
                psLocal.setString(4, data.getDestinationType());
                psLocal.setString(5, data.getDestination());
                psLocal.setString(6, data.getSelectedRoomType());
                psLocal.setDate(7, new java.sql.Date(data.getCheckIn().getTime()));
                psLocal.setDate(8, new java.sql.Date(data.getCheckOut().getTime()));
                psLocal.executeUpdate();
            } else {
                PreparedStatement psIntl = conn.prepareStatement(sqlDestinationInternational);
                psIntl.setInt(1, bookingId);
                psIntl.setInt(2, Integer.parseInt(data.getSelectedRoomIdInput()));
                psIntl.setInt(3, guestId);
                psIntl.setString(4, data.getDestinationType());
                psIntl.setString(5, data.getDestination());
                psIntl.setString(6, data.getSelectedRoomType());
                psIntl.setDate(7, new java.sql.Date(data.getCheckIn().getTime()));
                psIntl.setDate(8, new java.sql.Date(data.getCheckOut().getTime()));
                psIntl.executeUpdate();
            }

            // =======================
            // 6. PAYMENT
            // =======================
            // Generate a simple, unique transaction ID (for cash payments)
String transactionId = "TXN_" + System.currentTimeMillis() + "_" + (int)(Math.random() * 1000);

PreparedStatement ps6 = conn.prepareStatement(sqlPayment);

// Bind the new transaction ID (at index 1)
ps6.setString(1, transactionId); // <-- NEW LINE

// Shift all subsequent indices down by 1
ps6.setInt(2, bookingId); // was 1
ps6.setInt(3, Integer.parseInt(data.getSelectedRoomIdInput())); // was 2
ps6.setInt(4, guestId); // was 3
ps6.setString(5, data.getPaymentMethod()); // was 4
ps6.executeUpdate();

            // =======================
            //     COMMIT SUCCESS
            // =======================
            conn.commit();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();

            if (conn != null) {
                try { conn.rollback(); } catch (SQLException ignore) {}
            }

            return false;

        } finally {
            try { if (conn != null) conn.setAutoCommit(true); } catch (SQLException ignore) {}
        }
    }
     
     
}
