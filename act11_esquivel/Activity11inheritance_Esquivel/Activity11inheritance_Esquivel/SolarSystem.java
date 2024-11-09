package Activity11inheritance_Esquivel;

class SolarSystem {
    String name;
    double mass; 
    double distance;
    String color;
    boolean hasLife;
    
    public SolarSystem(String name, double mass, double distance, boolean hasLife) {
        this.name = name;
        this.mass = mass;
        this.distance = distance;
        this.hasLife = hasLife;
    }
    
    public void display() {
        System.out.println("Name: " + name);
        System.out.println("Mass: " + mass + " kg");
        System.out.println("Distance from the Sun: " + distance + " km");
        System.out.println("Has Life: " + (hasLife ? "Yes" : "No"));
    }
}

/*
	Mercury: Approximately 57.9 million km (5.79e7 km)
	Venus: Approximately 108.2 million km (1.082e8 km)
	Earth: Approximately 149.6 million km (1.496e8 km)
	Mars: Approximately 227.9 million km (2.279e8 km)
	Jupiter: Approximately 778.6 million km (7.786e8 km)
	Saturn: Approximately 1,433.5 million km (1.4335e9 km)
	Uranus: Approximately 2,872.5 million km (2.8725e9 km)
	Neptune: Approximately 4,495.1 million km (4.4951e9 km)
 */