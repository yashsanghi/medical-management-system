import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Login {
    private String emailAddress;
    Login() {
        loginDetails();
    }

    private void loginDetails() {
        System.out.println("Enter login details");
        Scanner sc = new Scanner(System.in);
        emailAddress = sc.nextLine();
        String[] email = emailAddress.split("@");
        while(!checkEmail(emailAddress)) {
            System.out.println("Please enter a valid email address");
            emailAddress = sc.nextLine();
        }
        if(checkUser(emailAddress)) {
            System.out.println("Successfully logged in");
            if(email[0].equals("admin")) {
                Admin a = new Admin();
                a.loop();
            }
            else if(email[0].equals("medicalStoreOwner")) {
                MedicalStoreOwner owner = new MedicalStoreOwner();
                owner.loop();
            }
            else {
                User u = new User(emailAddress);
                Thread userObject = new Thread(u);
                userObject.start();
            }
        }
        else {
            System.out.println("User with the given credentials does not exist");
            System.out.println("1 - Try again");
            System.out.println("2 - Register");
            int choice = Integer.parseInt(sc.nextLine());
            if (choice == 1) {
                loginDetails();
            }
            else if(choice == 2){
                Register r = new Register();
            }
            else {
                System.out.println("Wrong choice");
            }
        }
    }
    private boolean checkEmail(String emailAddress) {
        try {
            String[] email = emailAddress.split("@");
            return email[1].equals("pilani.bits-pilani.ac.in");
        }
        catch(Exception e) {
            return false;
        }
    }
    private boolean checkUser(String emailAddress) {
        try
        {
            FileInputStream fis = new FileInputStream("usersList.txt");
            Scanner sc = new Scanner(fis);
            boolean userFound = false;
            while(sc.hasNextLine())
            {
                String[] s = sc.nextLine().split(",");
                if(s[2].equals(emailAddress)) {
                    userFound = true;
                    break;
                }
                else {
                    userFound = false;
                }
            }
            sc.close();
            return userFound;
        }
        catch(IOException e)
        {
            e.printStackTrace(); 
            return false;
        }
    }
}
