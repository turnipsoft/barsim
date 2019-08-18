package dk.nine.kickoff.barsim.services;

import dk.nine.kickoff.barsim.model.Order;

public interface MetricCounterService {
  void incrementOrder(Order order);
  void incrementBartender();
  void decrementBartender();
  void updateDrinksInQueue(int queuesize);
  void incrementOrderServed(Order order);
}
