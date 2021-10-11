package lesson06;

public class Tiger extends Animal {

    public Tiger() {
    }

    public Tiger(String name, int maxSpeed) {
        super(name, maxSpeed);
    }

    @Override
    public int getMaxSpeed() {
        return 101;
    }
}
