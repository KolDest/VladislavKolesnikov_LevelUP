package ru.levelp.at;

public class LuckyTicket {
    public static void main(String[] args) {
        String ticket = "321123";
        if (isLuckyTicket(ticket)) {
            System.out.println("It's a lucky ticket!");
        } else {
            System.out.println("This is not a lucky ticket");
        }
    }

    @SuppressWarnings({"checkstyle:WhitespaceAround", "checkstyle:WhitespaceAfter"})
    public static boolean isLuckyTicket(String ticketNumber) {
        if (ticketNumber.length() == 6) {
            for (int i = 0; i < ticketNumber.length(); i++) {
                if(!Character.isDigit(ticketNumber.charAt(i))) {
                    System.out.println("The ticket must contain only numbers");
                    return false;
                }
            }
            int firstPart = ticketNumber.charAt(0) + ticketNumber.charAt(1) + ticketNumber.charAt(2);
            int secondPart = ticketNumber.charAt(3) + ticketNumber.charAt(4) + ticketNumber.charAt(5);
            return  firstPart == secondPart;
        } else {
            System.out.println("Incorrect length of ticket");
        }
        return false;
    }
}
