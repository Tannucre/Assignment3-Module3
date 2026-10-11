import java.util.Scanner;

class UniversityLogin {

    void login(String username) {

        if (username == null) {
            throw new NullPointerException(
                    "Username cannot be null"
            );
        }

        System.out.println("Login successful");
    }
}

public class UniversityLoginSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = sc.nextLine();

        // Convert the word "null" into actual null
        if (username.equalsIgnoreCase("null")) {
            username = null;
        }

        UniversityLogin loginSystem = new UniversityLogin();

        try {

            loginSystem.login(username);

        } catch (NullPointerException e) {

            System.out.println("Exception: " + e.getMessage());
        }

        sc.close();
    }
}