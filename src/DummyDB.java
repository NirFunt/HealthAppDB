import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DummyDB {

    public static void main(String[] args) {

        Meal pastaTomatoSauce = new Meal("Pasta with Tomattow Sauce",200.0, 30, "100g Pasta with Tomattow Sauce");
        Meal chickenSoup = new Meal("Chicken Soup",50.5,15, "two bowls of soup");
        Meal saladOil = new Meal("Salad with oil",15.85,5,"Salad with one spoon of olive oil");

        List<Meal> listOfMeals = new ArrayList<>();
        listOfMeals.add(pastaTomatoSauce);
        listOfMeals.add(chickenSoup);
        listOfMeals.add(saladOil);

        try (Socket socket = new Socket("localhost",3022)) {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(),true);

            Scanner scanner = new Scanner(System.in);
            while (true) {
               String command = scanner.nextLine();

               if (command.equals("Send")) {
                   output.println("Start Sending Meals");
                   for (Meal meal : listOfMeals) {
                       String str = meal.getName() + "," + meal.getCalories() + "," + meal.getScore() + "," + meal.getDescription();
                       output.println(str);
                   }
                   output.println("Finish");
               }

               if (command.equals("Exit")) {
                   output.println("Exit");
                   break;

               }

            }




        } catch (IOException e) {
            System.out.println("could not create a socket " + e.getMessage());
        }

    }


}
