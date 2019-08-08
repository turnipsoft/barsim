package dk.nine.kickoff.barsim.model;

import java.util.ArrayList;
import java.util.List;

public class DrinkMenu {
  List<String> drinks;

  public DrinkMenu() {
    drinks = new ArrayList<>();
    drinks.add("Gin & Tonic");
    drinks.add("Mai Thai");
    drinks.add("Nine Colada");
    drinks.add("Whiskey Sour");
    drinks.add("Long Island Ice Tea");
    drinks.add("Pina Colada");
    drinks.add("Strawberry Daquiri");
    drinks.add("Rum & Cola");
    drinks.add("Vodka & Juice");
    drinks.add("Beer");
    drinks.add("Red Wine");
    drinks.add("White Wine");
    drinks.add("Champagne");
    drinks.add("White Russian");
    drinks.add("Black Russian");
    drinks.add("Mojito");
  }

  public List<String> getDrinks() {
    return this.drinks;
  }
}
