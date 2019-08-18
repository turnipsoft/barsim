package dk.nine.kickoff.barsim.controllers;

import dk.nine.kickoff.barsim.model.DrinkMenu;
import dk.nine.kickoff.barsim.model.OrderResponse;
import dk.nine.kickoff.barsim.services.BarService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController()
@RequestMapping("barapi")
public class BarApi {

  BarService barService;
  DrinkMenu drinkMenu;

  public BarApi(BarService barService) {
    this.barService = barService;
    this.drinkMenu = new DrinkMenu();
  }

  @GetMapping(value = "order/{drink}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<OrderResponse> order (@PathVariable("drink") String drink) {
    barService.orderDrink(drink);
    return buildResponse();
  }

  private ResponseEntity<OrderResponse> buildResponse() {
    OrderResponse orderResponse = new OrderResponse();
    orderResponse.setDrinksInQueue(barService.getQueueSize());
    orderResponse.setPendingOrders(barService.getPendingOrders());
    orderResponse.setCompletedOrders(barService.getCompletedOrders());
    return ResponseEntity.ok(orderResponse);
  }

  @GetMapping(value = "ninearrives", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<OrderResponse> arrive()  {
    List<String> availabledrinks = drinkMenu.getDrinks();
    for (int i=0;i<100;i++) {
      String drink = availabledrinks.get(new Random().nextInt(availabledrinks.size()-1));
      barService.orderDrink(drink);
    }
    return buildResponse();
  }

  @GetMapping(value = "update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<OrderResponse> update() {
    return buildResponse();
  }

  @GetMapping(value = "closeBar", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<OrderResponse> closeBar() {
    barService.clear();
    return buildResponse();
  }
}
