import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class SecondPracticalTask {
    static SecondPracticalTask task;
    static List<Double> salary;
    double averageValue;
    double variance;
    double standardDeviation;
    double averageValueMinusStandardDeviation;
    double averageValuePlusStandardDeviation;
    static final List<Function<Double,Double>> functions = List.of(x-> task.averageValue/1000, x->task.averageValuePlusStandardDeviation/1000, x->task.averageValueMinusStandardDeviation/1000,x->salary.get(x.intValue()));


    public SecondPracticalTask(){
        task = this;
        salary = Stream.generate(()->Double.valueOf(new Random().nextInt(1000,100000))).limit(150).toList();
        averageValue = salary.stream().mapToDouble(x->x).average().getAsDouble();
        variance = salary.stream().reduce(0.0,(x,y)->x+Math.pow(y-averageValue,2))/(salary.size()-1);
        standardDeviation = Math.sqrt(variance);
        averageValueMinusStandardDeviation = averageValue-standardDeviation;
        averageValuePlusStandardDeviation = averageValue+standardDeviation;
    }
}
