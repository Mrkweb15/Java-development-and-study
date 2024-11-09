package Activity11inheritance_Esquivel;

class Earth extends InnerPlanet {
    public Earth() {
        super("Earth", 5.97237e24, 1.496e8, true);
    }
}


class Moon {
    String name;
    double radius;
    double orbitalPeriod;
    
    public Moon(String name, double radius, double orbitalPeriod) {
        this.name = name;
        this.radius = radius;
        this.orbitalPeriod = orbitalPeriod;
    }
    
    public void display() {
        System.out.println("Moon Name: " + name);
        System.out.println("Radius: " + radius + " km");
        System.out.println("Orbital Period: " + orbitalPeriod + " days");
    }
}