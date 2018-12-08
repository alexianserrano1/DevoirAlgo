package Devoir;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GeneratorTest {

    @Test
    public void generateTest(){
        Generator generator = new Generator(10);
        generator.generate();
        generator.printList();
    }


}
