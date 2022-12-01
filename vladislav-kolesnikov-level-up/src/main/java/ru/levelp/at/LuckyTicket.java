package ru.levelp.at;

public class LuckyTicket {
    public static boolean isLuckyTicket(String ticketNumber) {
        if (ticketNumber.length() == 6) {
            int firstPart = ticketNumber.charAt(0) + ticketNumber.charAt(1) + ticketNumber.charAt(2);
            int secondPart = ticketNumber.charAt(3) + ticketNumber.charAt(4) + ticketNumber.charAt(5);
            return  firstPart == secondPart;
        } else {
            System.out.println("Incorrect length of ticket");
        }
        return false;
    }
}
