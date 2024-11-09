package Activity11inheritance_Esquivel;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        boolean exit = true;

        SolarSystem mercury = new Mercury();
        SolarSystem venus = new Venus();
        SolarSystem earth = new Earth();
        SolarSystem mars = new Mars();
        SolarSystem jupiter = new Jupiter();
        SolarSystem saturn = new Saturn();
        SolarSystem uranus = new Uranus();
        SolarSystem neptune = new Neptune();
        
        Moon moon1 = new Moon("Moon", 1737.1, 27.3);

        while (exit) {
            System.out.println("Select an option:");
            System.out.println("=====================================");
            System.out.println("| Display inner planets.........| 1 |");
            System.out.println("| Display outer planets.........| 2 |");
            System.out.println("| Display planet................| 3 |");
            System.out.println("| Exit..........................| 4 |");
            System.out.print("=====================================\n: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    //inner planets
                    mercury.display();
                    System.out.println();
                    venus.display();
                    System.out.println();
                    earth.display();
                    System.out.println();
                    mars.display();
                    System.out.println();
                    break;
                case 2:
                    //outer planets
                    jupiter.display();
                    System.out.println();
                    saturn.display();
                    System.out.println();
                    uranus.display();
                    System.out.println();
                    neptune.display();
                    System.out.println();
                    break;
                case 3:
                    planets(mercury, venus, earth, mars, jupiter, saturn, uranus, neptune, moon1);
                    break;
                case 4:
                    exit = false;
                    System.out.println("Bye bye");
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }

        scanner.close();
    }
    
    public static void planets(SolarSystem mercury, SolarSystem venus, SolarSystem earth, SolarSystem mars, 
                               SolarSystem jupiter, SolarSystem saturn, SolarSystem uranus, SolarSystem neptune,
                               Moon moon1) {
        boolean stat = true;
        while(stat) {
            System.out.println("Select a planet:");
            System.out.println("=======================");
            System.out.println("| Mercury.........| 1 |");
            System.out.println("| Venus...........| 2 |");
            System.out.println("| Earth...........| 3 |");
            System.out.println("| Mars............| 4 |");
            System.out.println("| Jupiter.........| 5 |");
            System.out.println("| Saturn..........| 6 |");
            System.out.println("| Uranus..........| 7 |");
            System.out.println("| Neptune.........| 8 |");
            System.out.println("| Exit............| 9 |");
            System.out.print("=======================\n: ");

            int planetChoice = scanner.nextInt();

            switch (planetChoice) {
                case 1:
                    mercury.display();
                    break;
                case 2:
                    venus.display();
                    break;
                case 3:
                    earth.display();
                    System.out.println();
                    moon1.display();
                    break;
                case 4:
                    mars.display();
                    break;
                case 5:
                    jupiter.display();
                    break;
                case 6:
                    saturn.display();
                    break;
                case 7:
                    uranus.display();
                    break;
                case 8:
                    neptune.display();
                    break;
                case 9:
                    stat = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
            System.out.println();
        }
    }
}
