package teias.learners;

import com.google.common.collect.Lists;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        MealyMachine mealyMachine = new MealyMachine(Lists.newArrayList("b","f"), "./src/main/resources/test.csv");
        System.out.println(mealyMachine.getReferenceAutomaton().getStates());
    }
}
