package lesson06;

import java.util.*;

public class GetTheWinner {
    public static void main(String[] args) {
        Map<Animal, Integer> animalList = new HashMap<>();

        Horse horse = new Horse();
        Tiger tiger = new Tiger();
        Dog dog = new Dog();

        int horseSpeed = horse.generateRandomSpeed();
        int dogSpeed = dog.generateRandomSpeed();
        int tigerSpeed = tiger.generateRandomSpeed();

        animalList.put(horse, horseSpeed);
        animalList.put(tiger, tigerSpeed);
        animalList.put(dog, dogSpeed);

        List<Integer> speedList = Arrays.asList(horseSpeed, dogSpeed, tigerSpeed);
        Collections.sort(speedList);

        for (Animal animal : animalList.keySet()) {
            int winnerAnimalPosition = speedList.size() - 1;
            if (speedList.get(winnerAnimalPosition) == animalList.get(animal)) {
                System.out.println("The winner is " + animal.getClass().getSimpleName() + " with speed: " + speedList.get(winnerAnimalPosition));
            }
        }
    }
}
