import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByValue;

public class StreamCollectors {
    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0)
        );

        Map<String, Double> productMap = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getProduct,
                        Collectors.summingDouble(Order::getCost)
                ));

        List<Map.Entry<String, Double>> sortByTotalPrice = productMap.entrySet().stream()
                .sorted((e1, e2) ->
                        e2.getValue().compareTo(e1.getValue()))
                .toList();


        List<Map.Entry<String, Double>> top3 = sortByTotalPrice.stream()
                .limit(3)
                .toList();

        top3.forEach(entry -> System.out.print(entry.getKey() + ": " + entry.getValue()));

    }
}
