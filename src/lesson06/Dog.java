package lesson06;

public class Dog extends Animal{
    public Dog() {
    }

    public Dog(String name, int maxSpeed) {
        super(name, maxSpeed);
    }

    @Override
    public int getMaxSpeed() {
        return 61;
    }
}
