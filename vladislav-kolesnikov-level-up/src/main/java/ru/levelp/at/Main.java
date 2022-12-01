package ru.levelp.at;

import static ru.levelp.at.LuckyTicket.isLuckyTicket;

public class Main {
    public static void main(String[] args) {
        String ticket = "321123";
        if (isLuckyTicket(ticket)) {
            System.out.println("It's a lucky ticket!");
        } else {
            System.out.println("This is not a lucky ticket");
        }
    }
}
