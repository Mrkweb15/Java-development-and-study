package Activity12Encapsulaton;

class Van extends Car {
    private int seatingCapacity;

    public Van(int yearModel, String brand, String color, int seatingCapacity) {
        super(yearModel, brand, color);
        this.seatingCapacity = seatingCapacity;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Seating Capacity: " + seatingCapacity);
    }
}
