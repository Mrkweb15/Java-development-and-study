package Activity12Encapsulaton;

class Car {
    private int yearModel;
    private String brand;
    private String color;

    public Car(int yearModel, String brand, String color) {
        this.yearModel = yearModel;
        this.brand = brand;
        this.color = color;
    }

    public int getYearModel() {
        return yearModel;
    }

    public void setYearModel(int yearModel) {
        this.yearModel = yearModel;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void displayDetails() {
        System.out.println("Brand: " + brand);
        System.out.println("Color: " + color);
        System.out.println("Year Model: " + yearModel);
    }
}

