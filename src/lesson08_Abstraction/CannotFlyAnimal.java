package lesson08_Abstraction;

public class CannotFlyAnimal extends Animal{
    private String name;

    public CannotFlyAnimal(String name) {
        this.name = name;
    }

    @Override
    protected int speed() {
        return speed;
    }

    @Override
    protected boolean canFly() {
        return false;
    }


}
