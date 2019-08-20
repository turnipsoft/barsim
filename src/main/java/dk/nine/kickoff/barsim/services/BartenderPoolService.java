package dk.nine.kickoff.barsim.services;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BartenderPoolService {

  private static final String RODRIGUEZ = "Rodriguez";
  private static final String LORENA = "Lorena";
  private static final String LUIS = "Luis";
  private static final String MARLA = "Marla";
  private static final String DANIELA = "Daniela";

  private List<String> bartenders;
  private Set<String> working;

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
    String bartender;
    do {
      bartender = bartenders.get(new Random().nextInt(bartenders.size()));
    } while (working.contains(bartender));

    working.add(bartender);
    return bartender;
  }

  public void releaseBartender(String bartender) {
    working.remove(bartender);
  }

  public Set<String> getWorkingBartenders() {
    return working;
  }

}
