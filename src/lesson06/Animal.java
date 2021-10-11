package lesson06;

import java.util.Random;

public class Animal {
    private String name;
    private int maxSpeed;
    //class variable
    private static String defaultName;

    public Animal() {
    }

    public Animal(String name, int maxSpeed) {
        this.name = name;
        this.maxSpeed = maxSpeed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public static String getDefaultName() {
        if (defaultName == null) {
            return "Animal";
        }
        return "Animal's default name " + defaultName;
    }

    public int generateRandomSpeed() {
        Random randomNum = new Random();
        int randomSpeed = randomNum.nextInt(getMaxSpeed());
        return randomSpeed;
    }

}
