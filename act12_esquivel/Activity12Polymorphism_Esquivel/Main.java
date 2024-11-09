package Activity12Polymorphism_Esquivel;

class Main {
    public static void main(String[] args) {
        Animal animal = new Animal();
        animal.makeSound();
        
        Mammal mammal = new Mammal();
        mammal.makeSound();
        
        Dog dog = new Dog();
        dog.makeSound(3);
        
        Cat cat = new Cat();
        cat.makeSound("meows");
        
        Reptile reptile = new Reptile();
        reptile.makeSound(false);
        reptile.makeSound(true);
        
        Snake snake = new Snake();
        snake.makeSound();
        
        Lizard lizard = new Lizard();
        lizard.makeSound();
    }
}

