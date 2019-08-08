package dk.nine.kickoff.barsim.model;

import java.util.List;
import java.util.Set;

public class OrderResponse {
  int drinksInQueue;
  Set<Order> pendingOrders;
  List<Order> completedOrders;

  public int getDrinksInQueue() {
    return drinksInQueue;
  }

  public void setDrinksInQueue(int drinksInQueue) {
    this.drinksInQueue = drinksInQueue;
  }

  public Set<Order> getPendingOrders() {
    return pendingOrders;
  }

  public void setPendingOrders(Set<Order> pendingOrders) {
    this.pendingOrders = pendingOrders;
  }

  public List<Order> getCompletedOrders() {
    return completedOrders;
  }

  public void setCompletedOrders(List<Order> completedOrders) {
    this.completedOrders = completedOrders;
  }

}
