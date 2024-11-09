package Activity12Polymorphism_Esquivel;

class Dog extends Mammal {
    public void makeSound(int count) {
        System.out.print("Dog is barking. ");
        for (int i = 0; i < count; i++) {
            System.out.print("Arrfff ");
        }
        System.out.println();
    }
}
