package lesson08_Abstraction;


public class CanFlyAnimal extends Animal {
    private String name;

    public CanFlyAnimal(String name) {
        this.name = name;
    }

    @Override
    protected int speed() {
        return speed;
    }

    @Override
    protected boolean canFly() {
        return true;
    }

    @Override
    public String toString() {
        return name;

    }
}
