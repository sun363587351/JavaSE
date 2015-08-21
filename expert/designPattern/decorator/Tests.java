package expert.designPattern.decorator;

import org.testng.annotations.Test;

/**
 * Created by IFT8
 * on 2015/8/17.
 */
public class Tests {
    @Test
    public void test() {
        Animal animal = new Animal();
        Dog dog = new Dog(animal);
        dog.cry();
        dog.sleep();
    }
}
