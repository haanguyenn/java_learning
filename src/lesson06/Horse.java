package lesson06;

public class Horse extends Animal {
    public Horse() {
    }

    public Horse(String name, int maxSpeed) {
        super(name, maxSpeed);
    }

    @Override
    public int getMaxSpeed() {
        return 76;
    }
}
