package lesson07;


import java.security.SecureRandom;

public class Animal {
    private int speed;
    private String name;
    private boolean canFly;

    private Animal(Builder builder){
        this.speed = builder.speed;
        this.canFly = builder.canFly;
        this.name = builder.name;
    }
    public void printAnimal(){
        System.out.println("The speed of the animal is: " + this.speed);
        System.out.println("The animal is in the racing: " + this.canFly);
    }

    public int getSpeed() {
        return this.speed;
    }
    public String getName(){
        return this.name;
    }
    public boolean isCanFly(){
        return this.canFly;
    }

    public static class Builder{
        private int speed;
        private boolean canFly;
        private String name;

        Builder(){
        }

        public Builder speed(int speed){
            this.speed = new SecureRandom().nextInt(speed);
            return this;
        }
        public Builder canFly(boolean canFly){
            this.canFly = canFly;
            return this;
        }
        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Animal build(){
            return new Animal(this);
        }
    }
}
