package Activity12Encapsulaton;

public class Main {
    public static void main(String[] args) {
        Car truck = new Truck(2021, "Hyundai Santa Cruz", "Gray", 4217);
        Car van = new Van(2017, "Lamborghini Urus", "Orange", 4850);

        System.out.println("Truck Details:");
        truck.displayDetails();

        System.out.println("\nVan Details:");
        van.displayDetails();
    }
}
