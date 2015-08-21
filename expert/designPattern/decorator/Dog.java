package expert.designPattern.decorator;

/**
 * Created by IFT8
 * on 2015/8/17.
 */
public class Dog extends Animal{
    private Animal animal;

    public Dog(Animal animal) {
        this.animal = animal;
    }

    @Override
    public void cry() {
        animal.cry();
        System.out.println("I'm Dog!");
    }
}


