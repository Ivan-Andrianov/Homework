import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Stream;

public class FirstPracticalTask {

    static FirstPracticalTask task;
    List<Double> data;
    double averageValue;
    double variance;
    double standardDeviation;
    double averageValueMinusStandardDeviation;
    double averageValuePlusStandardDeviation;
    static final List<Function<Double,Double>> functions = List.of(x-> task.averageValue,x->task.averageValuePlusStandardDeviation,x->task.averageValueMinusStandardDeviation);


    public FirstPracticalTask(){
        task = this;
        data = Stream.generate(()-> new Random().nextDouble()).filter(x->x>0).limit(50).toList();
        averageValue = data.stream().reduce((x,y)->x+y).get()/data.size();
        variance = data.stream().reduce(0.0,(x,y)->x+Math.pow(y-averageValue,2))/(data.size()-1);
        standardDeviation = Math.sqrt(variance);
        averageValueMinusStandardDeviation = averageValue-standardDeviation;
        averageValuePlusStandardDeviation = averageValue+standardDeviation;
    }

}
