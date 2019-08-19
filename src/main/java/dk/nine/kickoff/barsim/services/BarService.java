package dk.nine.kickoff.barsim.services;

import dk.nine.kickoff.barsim.model.Employees;
import dk.nine.kickoff.barsim.model.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Service
public class BarService {

  private ThreadPoolExecutor executor;
  private BartenderPoolService bartenderPoolService;
  private int maximumPrepTime;

  private Set<Order> pendingOrders;
  private List<Order> completedOrders;
  private Employees employees;
  private MetricCounterService metricCounterService;

  public BarService(@Value("${maximumPrepTime}") int maximumPrepTime,
                    BartenderPoolService bartenderPoolService,
                    MetricCounterService metricCounterService) {
    this.maximumPrepTime = maximumPrepTime;
    this.bartenderPoolService = bartenderPoolService;
    this.metricCounterService = metricCounterService;
    this.pendingOrders = new HashSet<>();
    this.completedOrders = new ArrayList<>();
    this.employees = new Employees();
    executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
  }

  public void orderDrink(String drinkName) {
    int preptime = new Random().nextInt(maximumPrepTime);
    Order order = new Order(drinkName, employees.getRandomEmployee(), preptime);
    metricCounterService.incrementOrder(order);
    pendingOrders.add(order);

    executor.submit( ()-> {
      try {
        String bartender = bartenderPoolService.getBartender();
        order.setBartender(bartender);
        metricCounterService.incrementBartender();
        System.out.println(String.format("%s is making a %s gonna take %d ms", bartender, drinkName, preptime));
        Thread.sleep(preptime);
        bartenderPoolService.releaseBartender(bartender);
        metricCounterService.decrementBartender();
        metricCounterService.updateDrinksInQueue(executor.getQueue().size());
        pendingOrders.remove(order);
        completedOrders.add(order);
        metricCounterService.incrementOrderServed(order);
      } catch (InterruptedException ie) {
        System.out.println("Interrupted making the drink " + drinkName);
      }
    });

    metricCounterService.updateDrinksInQueue(executor.getQueue().size());
  }

  public int getQueueSize() {
    return executor.getQueue().size();
  }

  public Set<Order> getPendingOrders() {
    return pendingOrders;
  }

  public List<Order> getCompletedOrders() {
    return completedOrders;
  }

  public void clear() {
    completedOrders.clear();
  }

}
