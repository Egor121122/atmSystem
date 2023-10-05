package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    DataBaseCards dbCards = new DataBaseCards();
    Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        print();
        new Main().update();
    }
    void update(){
        String input = scanner.nextLine();
        String[] arg = input.split(" ");
        switch (arg[0]){
            case "addCard":
                dbCards.addCard(arg[1], arg[2]);
                break;
            case "replenish":
                dbCards.replenish(arg[1], Integer.parseInt(arg[2]));
                break;
            case "withdraw":
                dbCards.withdraw(arg[1], Integer.parseInt(arg[2]));
                break;
            case "pay":
                dbCards.pay(arg[1], Integer.parseInt(arg[2]), arg[3]);
                break;
            case "balance":
                dbCards.balance(arg[1]);
                break;
            case "info":
                dbCards.info(arg[1]);
                break;
            default:
                if(!arg[0].equals("quit"))
                    System.out.println("- invalid input");
                break;
        }if(!arg[0].equals("quit"))
            update();
    }
    public static void print(){
        System.out.println();
        System.out.println();
        System.out.println("addCard <cardNum> <cardOwnerName>");
        System.out.println("replenish <cardNum> <sum>");
        System.out.println("withdraw <cardNum> <sum>");
        System.out.println("pay <cardNumPayer> <sum> <cardNumReceiver>");
        System.out.println("balance <cardNum>");
        System.out.println("info <cardNum>");
        System.out.println("quit");
        System.out.println();
    }
}

class Card{
    private final String cardNum;
    private final String cardOwnerName;
    private int balance;
    public Card(String cardNum, String cardOwnerName) {
        this.cardNum = cardNum;
        this.cardOwnerName = cardOwnerName;
    }

    public String getCardNum() {return cardNum;}
    public String getCardOwnerName() {return cardOwnerName;}
    public int getBalance() {return balance;}
    public void setBalance(int balance) {this.balance = balance;}
}
class DataBaseCards{
    private static final ArrayList<Card> cards = new ArrayList<>();
    void addCard(String cardNum, String cardOwnerName){
        for (Card card : cards) {
            if (card.getCardNum().equals(cardNum)) {System.out.println("- You already have card with this number");break;
            }
        } cards.add(new Card(cardNum, cardOwnerName));
    }
    void replenish(String cardNum, int sum){
        if (sum > 0) {
            for (Card card : cards) {
                if (card.getCardNum().equals(cardNum)) {card.setBalance(card.getBalance() + sum);return;}
            }
            System.out.println("- invalid number of card");
        } else {System.out.println("- invalid sum");}
    }
    void withdraw(String cardNum, int sum){
        if (sum > 0) {
            for (Card card : cards) {
                if (card.getCardNum().equals(cardNum))
                    if (card.getBalance() >= sum) {card.setBalance(card.getBalance() - sum);return;}
            }
        } System.out.println("- invalid sum or number of card");
    }
    void pay(String cardNumPayer, int sum, String cardNumReceiver){
        if(sum > 0) {
            for (int i = 0; i < cards.size(); i++) {
                if (cards.get(i).getCardNum().equals(cardNumPayer)) {
                    for (Card card : cards) {
                        if (card.getCardNum().equals(cardNumReceiver)) {
                            if (cards.get(i).getBalance() >= sum) {
                                cards.get(i).setBalance(cards.get(i).getBalance() - sum);
                                card.setBalance(card.getBalance() + sum);
                            } else {
                                System.out.println("- invalid sum");
                            }
                            return;
                        }
                    }
                }
            }
            System.out.println("- invalid number of payer or receiver's card");
        } else {System.out.println("- invalid sum");}
    }
    void balance(String cardNum) {
        for (Card card : cards)
            if (card.getCardNum().equals(cardNum)) {System.out.println("Your balance is: " + card.getBalance());return;}
        System.out.println("- invalid number of card");
    }
    void info(String cardNum) {
        for (Card card : cards) {
            if (card.getCardNum().equals(cardNum)) {
                System.out.println();
                System.out.println("Card Number: " + card.getCardNum());
                System.out.println("Card Owner:  " + card.getCardOwnerName());
                System.out.println("Balance:     " + card.getBalance());
                System.out.println();
                return;
            }
        }
        System.out.println("- invalid number of card");
    }
}