package lesson07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Racing {
    public static Animal getWinnerAnimal(List<Animal> animals) {
        Animal winner = null;
        for (Animal currentAnimal : animals) {
            if (currentAnimal.isCanFly()) {
                if (winner == null) {
                    winner = currentAnimal;
                } else {
                    if (winner.getSpeed() < currentAnimal.getSpeed()) {
                        winner = currentAnimal;
                    }
                }
            }
        }
        return winner;
    }

        public static void main (String[]args){
            Animal tiger = new Animal.Builder().name("Tiger").speed(70).canFly(false).build();
            Animal horse = new Animal.Builder().name("Horse").speed(100).canFly(false).build();
            Animal falcon = new Animal.Builder().name("Falcon").speed(140).canFly(true).build();
            Animal owl = new Animal.Builder().name("Owl").speed(250).canFly(true).build();
            Animal goose = new Animal.Builder().name("Goose").speed(60).canFly(true).build();
            Animal winner = Racing.getWinnerAnimal(Arrays.asList(tiger, horse, falcon, owl, goose));

            System.out.println("Winner is: " + winner.getName());
            System.out.println("Winner's speed was: " + winner.getSpeed());
        }
    }
