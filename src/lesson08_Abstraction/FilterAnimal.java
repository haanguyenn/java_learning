package lesson08_Abstraction;

import java.util.ArrayList;
import java.util.List;

public class FilterAnimal {
    public static Animal racingAnimal(List<Animal> animals) {
        List<Animal> applicableAnimals = applicableAnimals(animals);
        Animal winner = null;
        for (Animal currentAnimal : applicableAnimals) {
            if (winner == null) {
                winner = currentAnimal;
            } else {
                if (winner.speed() < currentAnimal.speed()) {
                    winner = currentAnimal;
                }
            }
        }
        return winner;
    }

    private static List<Animal> applicableAnimals(List<Animal> animals) {
        List<Animal> canJoinRunning = new ArrayList<>();
        for (Animal animal : animals) {
            if (animal.canFly()) {
                canJoinRunning.add(animal);
            }
        }
        return canJoinRunning;
    }
}

