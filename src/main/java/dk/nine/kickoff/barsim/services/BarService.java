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

  ThreadPoolExecutor executor;
  BartenderPoolService bartenderPoolService;
  int maximumPrepTime;

  Set<Order> pendingOrders;
  List<Order> completedOrders;
  Employees employees;

  public BarService(@Value("${maximumPrepTime}") int maximumPrepTime, BartenderPoolService bartenderPoolService) {
    this.maximumPrepTime = maximumPrepTime;
    this.bartenderPoolService = bartenderPoolService;
    this.pendingOrders = new HashSet<>();
    this.completedOrders = new ArrayList<>();
    this.employees = new Employees();
    executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
  }

  public void orderDrink(String drinkName) {
    int preptime = new Random().nextInt(maximumPrepTime);
    Order order = new Order(drinkName, employees.getRandomEmployee(),preptime);
    pendingOrders.add(order);

    executor.submit( ()-> {
      try {
        String bartender = bartenderPoolService.getBartender();
        order.setBartender(bartender);
        System.out.println(String.format("%s is making a %s gonna take %d ms", bartender, drinkName,preptime));
        Thread.sleep(preptime);
        bartenderPoolService.releaseBartender(bartender);
        pendingOrders.remove(order);
        completedOrders.add(order);
      } catch (InterruptedException ie) {
        System.out.println("Interrupted making the drink "+drinkName);
      }
    });
  }

  public int getQueueSize() {
    return this.executor.getQueue().size();
  }

  public Set<Order> getPendingOrders() {
    return this.pendingOrders;
  }

  public List<Order> getCompletedOrders() {
    return this.completedOrders;
  }

  public void clear() {
    this.completedOrders.clear();
  }

  public String getRandomEmployee() {
    return this.employees.getRandomEmployee();
  }
}
