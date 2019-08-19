package dk.nine.kickoff.barsim.services;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BartenderPoolService {

  static final String RODRIGUEZ = "Rodriguez";
  static final String LORENA = "Lorena";
  static final String LUIS = "Luis";
  static final String MARLA = "Marla";
  static final String DANIELA = "Daniela";

  List<String> bartenders;
  Set<String> working;

  public BartenderPoolService() {
    bartenders = new ArrayList<>(5);
    bartenders.add(RODRIGUEZ);
    bartenders.add(LORENA);
    bartenders.add(LUIS);
    bartenders.add(MARLA);
    bartenders.add(DANIELA);
    working = new HashSet<>();
  }

  public String getBartender() {
    String bartender = bartenders.get(new Random().nextInt(4));
    while (working.contains(bartender)) {
      bartender = bartenders.get(new Random().nextInt(4));
    }

    working.add(bartender);
    return bartender;
  }

  public void releaseBartender(String bartender) {
    working.remove(bartender);
  }

  public Set<String> getWorkingBartenders() {
    return this.working;
  }

}
