package dk.nine.kickoff.barsim.services.impl;

import dk.nine.kickoff.barsim.model.Order;
import dk.nine.kickoff.barsim.services.MetricCounterService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class MetricCounterServiceImpl implements MetricCounterService {

  private static final String ORDER_DRINK_COUNTER_NAME = "nine.barsim.drink.order";
  private static final String SERVED_DRINK_COUNTER_NAME = "nine.barsim.drink.served";
  private static final String ACTIVE_BARTENDER_GAUGE_NAME = "nine.barsim.bartender.active";
  private static final String DRINK_QUEUE_SIZE_GAUGE_NAME = "nine.barsim.drinksqueue.size";

  private static final String DRINK_NAME = "drinkname";
  private static final String BARTENDER = "bartender";
  private static final String NINER = "niner";

  private final MeterRegistry meterRegistry;
  private Map<String, Counter> counters = new HashMap<>();
  private AtomicInteger activeBartendersAmount;
  private AtomicInteger drinksInQueue;

  public MetricCounterServiceImpl(MeterRegistry meterRegistry) {
    this.meterRegistry = meterRegistry;
    activeBartendersAmount = meterRegistry.gauge(ACTIVE_BARTENDER_GAUGE_NAME, new AtomicInteger(0));
    drinksInQueue = meterRegistry.gauge(DRINK_QUEUE_SIZE_GAUGE_NAME, new AtomicInteger(0));
  }

  private String getDrinkCounterId(Order order, String base) {
    String counterName = String.format("%s.%s", base, order.getDrinkName());
    
    if (order.getBartender() != null) {
      counterName = String.format("%s.%s", counterName, order.getBartender());
    } else {
      counterName = String.format("%s.%s", counterName, order.getDrinkFor());
    }

    return counterName.replaceAll(" ", "");
  }

  @Override
  public void incrementOrder(Order order) {
    String counterId = String.format("%s.%s.%s", ORDER_DRINK_COUNTER_NAME, order.getDrinkName(), order.getDrinkFor());

    Counter counter = counters.computeIfAbsent(counterId, key -> meterRegistry.counter(ORDER_DRINK_COUNTER_NAME,
        DRINK_NAME, order.getDrinkName(),
        NINER, order.getDrinkFor()));
    
    counter.increment();
  }

  @Override
  public void incrementBartender() {
    activeBartendersAmount.incrementAndGet();
  }

  @Override
  public void decrementBartender() {
    activeBartendersAmount.decrementAndGet();
  }

  @Override
  public void updateDrinksInQueue(int queuesize) {
    drinksInQueue.set(queuesize);
  }

  @Override
  public void incrementOrderServed(Order order) {
    String counterId = String.format("%s.%s.%s", SERVED_DRINK_COUNTER_NAME, order.getDrinkName(), order.getBartender());

    Counter counter = counters.computeIfAbsent(counterId, key -> meterRegistry.counter(SERVED_DRINK_COUNTER_NAME,
        DRINK_NAME, order.getDrinkName(),
        BARTENDER, order.getBartender()));

    counter.increment();
  }

}
