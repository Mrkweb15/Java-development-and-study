package Activity12Polymorphism_Esquivel;

class Reptile extends Animal {
    public void makeSound(boolean isDangerous) {
        if (isDangerous) {
            System.out.println("\nWarning! Dangerous reptile is making sound.");
        } else {
            System.out.println("\nReptile is making a sound.");
        }
    }
}
