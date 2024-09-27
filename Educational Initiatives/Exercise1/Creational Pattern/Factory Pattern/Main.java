abstract class Animal {
    public abstract void sound();
}

class Dog extends Animal {
    @Override
    public void sound() {
        System.out.println("Woof!");
    }
}

class Cat extends Animal {
    @Override
    public void sound() {
        System.out.println("Meow!");
    }
}

class AnimalFactory {
    public static Animal createAnimal(String type) {
        if (type.equalsIgnoreCase("dog")) {
            return new Dog();
        } else if (type.equalsIgnoreCase("cat")) {
            return new Cat();
        }
        return null;
    }
}

public class Main {
    public static void main(String[] args) {
        Animal dog = AnimalFactory.createAnimal("dog");
        dog.sound(); // Woof!

        Animal cat = AnimalFactory.createAnimal("cat");
        cat.sound(); // Meow!
    }
}
