package lesson08_Abstraction;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

public class RunningContest {
    public static void main(String[] args) {
        Animal horse = new CannotFlyAnimal("Horse");
        Animal owl = new CanFlyAnimal("Owl");
        Animal falcon = new CanFlyAnimal("Falcon");
        Animal goose = new CanFlyAnimal("Goose");

        horse.speed = new SecureRandom().nextInt(100);
        owl.speed =  new SecureRandom().nextInt(200);
        falcon.speed =  new SecureRandom().nextInt(130);
        goose.speed = new SecureRandom().nextInt(150);

        List<Animal> animals = Arrays.asList(horse,owl,falcon,goose);

        Animal winner = FilterAnimal.racingAnimal(animals);
        System.out.println("The winner is " +  winner);
        System.out.println("The winner's speed is " + winner.speed());


    }
}
