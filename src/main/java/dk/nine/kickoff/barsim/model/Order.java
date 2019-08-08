package dk.nine.kickoff.barsim.model;

import java.util.UUID;

public class Order {
  String id;
  String drinkName;
  String drinkFor;
  String bartender;
  int prepTime;

  public Order(String drinkName, String drinkFor, int prepTime) {
    this.drinkName = drinkName;
    this.drinkFor = drinkFor;
    id = UUID.randomUUID().toString();
    this.prepTime = prepTime;
    this.bartender = "pending";
  }

  public String getId() {
    return id;
  }

  public String getDrinkName() {
    return drinkName;
  }

  public int getPrepTime() {
    return prepTime;
  }

  public String getDrinkFor() {
    return drinkFor;
  }

  public String getBartender() {
    return bartender;
  }

  public void setBartender(String bartender) {
    this.bartender = bartender;
  }
}
