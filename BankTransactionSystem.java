import java.util.Scanner;

class BankTransaction extends Thread {

    int amount;
    boolean highValue;

    BankTransaction(int amount, boolean highValue) {
        this.amount = amount;
        this.highValue = highValue;
    }

    public void run() {

        if (highValue) {
            System.out.println("High-value transaction processed first");
        } else {
            System.out.println("Low-value transaction processed later");
        }
    }
}

public class BankTransactionSystem {

    public static void main(String[] args)
            throws InterruptedException {

        Scanner sc = new Scanner(System.in);

        System.out.print("Transaction1: ₹");
        int amount1 = sc.nextInt();

        System.out.print("Transaction2: ₹");
        int amount2 = sc.nextInt();

        if (amount1 == amount2) {
            System.out.println("Both transactions have equal amounts");
        } else {

            BankTransaction transaction1;
            BankTransaction transaction2;

            if (amount1 > amount2) {

                transaction1 = new BankTransaction(amount1, true);
                transaction2 = new BankTransaction(amount2, false);

            } else {

                transaction1 = new BankTransaction(amount1, false);
                transaction2 = new BankTransaction(amount2, true);
            }

            // Set thread priorities
            if (transaction1.highValue) {
                transaction1.setPriority(Thread.MAX_PRIORITY);
                transaction2.setPriority(Thread.MIN_PRIORITY);
            } else {
                transaction1.setPriority(Thread.MIN_PRIORITY);
                transaction2.setPriority(Thread.MAX_PRIORITY);
            }

            // Process the higher-value transaction first
            if (transaction1.highValue) {
                transaction1.start();
                transaction1.join();

                transaction2.start();
                transaction2.join();
            } else {
                transaction2.start();
                transaction2.join();

                transaction1.start();
                transaction1.join();
            }
        }

        sc.close();
    }
}