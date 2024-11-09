package Activity12Encapsulaton;

class Truck extends Car {
    private int towingCapacity;

    public Truck(int yearModel, String brand, String color, int towingCapacity) {
        super(yearModel, brand, color);
        this.towingCapacity = towingCapacity;
    }

    public int getTowingCapacity() {
        return towingCapacity;
    }

    public void setTowingCapacity(int towingCapacity) {
        this.towingCapacity = towingCapacity;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Towing Capacity: " + towingCapacity + " lbs.");
    }
}
