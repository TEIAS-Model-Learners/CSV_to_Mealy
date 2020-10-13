package teias.learners;

import de.learnlib.examples.DefaultLearningExample;
import net.automatalib.automata.transducers.MutableMealyMachine;
import net.automatalib.util.automata.builders.MealyBuilder;
import net.automatalib.words.impl.Alphabets;
import net.automatalib.util.automata.builders.AutomatonBuilders;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;


;import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class MealyMachine extends DefaultLearningExample.DefaultMealyLearningExample<String,String> {

    public MealyMachine(List<String> alphabet,String transitionTableAddress) throws IOException{
        super(Alphabets.fromList(alphabet),constructMachine(alphabet,transitionTableAddress));
    }

    public static MutableMealyMachine constructMachine(List<String> alphabet, String transitionTableAddress) throws IOException {
        Reader in = new FileReader(transitionTableAddress);
        MealyBuilder tempBuilder = AutomatonBuilders.newMealy(Alphabets.fromList(alphabet));
        MealyBuilder.MealyBuilder__7 builder = null;
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
        boolean init = true;
        for (CSVRecord record : records){
            String startState = record.get(0);
            String input = record.get(1);
            String output = record.get(2);
            String endState = record.get(3);
            if (init) {
                init = false;
                builder = tempBuilder.withInitial(startState)
                        .from(startState)
                        .on(input)
                        .withOutput(output)
                        .to(endState);
            }
            else {
                builder = builder.from(startState)
                        .on(input)
                        .withOutput(output)
                        .to(endState);
            }
        }
        return builder.create();
    }

}
