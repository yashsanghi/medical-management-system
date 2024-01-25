import java.io.IOException;
import java.util.Scanner;

public class Admin extends Interaction{

    void printInstructions() {
        System.out.println("Hi Admin! Please select from the given options");
        System.out.println("1 - Display list of users");
        System.out.println("2 - Update notice board");
        System.out.println("3 - Display appointments for the day");
    }

    void options(int choice, Scanner sc) {
        if(choice == 1) {
            try {
                User u = new User();
                u.display();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if(choice == 2) {
            try {
                NoticeBoard.getInstance().updateNoticeBoard();
            }
            catch(Exception e) {
                System.out.println("Error update notice board, please check your parameters and try again");
            }
        }
        else if(choice == 3) {
            try {
                Appointment app = new Appointment();
                app.getTodayAppointments();
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Enter correct input");
        }
    }

    Admin() {
    }

}
