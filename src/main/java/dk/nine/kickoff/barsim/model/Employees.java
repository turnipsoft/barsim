package dk.nine.kickoff.barsim.model;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Employees {
  private static final String employees = "Christian Hoffmann," +
    "Michael Foldbjerg," +
    "Søren Hartvig," +
    "Nikolaj Sakham," +
    "Jesper Steen," +
    "Jacob," +
    "Mikkel Gybel," +
    "Louis," +
    "Mikael Franck," +
    "Poul," +
    "Nikolaj Brinch," +
    "Claus Nordahl," +
    "Rasmus Rask," +
    "Søren Stuhde," +
    "Jens Lohmann" +
    "Henrik Svendsen," +
    "Kristjan," +
    "Pernille," +
    "Jesper Rønn," +
    "Niels Jørgen," +
    "Christian Landbo," +
    "Jannie," +
    "Erik," +
    "Thomas," +
    "Niels Peter," +
    "Jens Karlsmose," +
    "Patrick," +
    "Per," +
    "Jørgen," +
    "Kent," +
    "Mette," +
    "Lotte," +
    "Jesper Rugård," +
    "Peter," +
    "Morten Holm," +
    "Tim," +
    "Jens Christian," +
    "Tim Halwyll," +
    "Rune," +
    "Jan," +
    "Nikolaj," +
    "Frank Cornelius," +
    "Brian," +
    "Ricki," +
    "Mikkel Terpe," +
    "Daniel," +
    "Henrik," +
    "Alex," +
    "Morten Andreasen," +
    "Kenneth," +
    "Heidi," +
    "Ulrik," +
    "Thomas Sánchez," +
    "Anders Mørk," +
    "Thomas Dandanell," +
    "Theo," +
    "Mikkel Wivel," +
    "Susanne," +
    "Mads Hansen," +
    "Anders Aaberg," +
    "Elke," +
    "Mads Neegaard," +
    "Birger," +
    "Søren Falch," +
    "Sara," +
    "Anna," +
    "Andreas," +
    "Jakob Just," +
    "Hans Michael," +
    "Bojana," +
    "Jonas Daniel Jens," +
    "Carsten," +
    "Ranj," +
    "Bjarne Bue," +
    "Line," +
    "Lene," +
    "Claus," +
    "Mick," +
    "Ole Lykke," +
    "Simon," +
    "Lea Tolstrup," +
    "Louise," +
    "Kim," +
    "Jack Konrad," +
    "Alexandra Holk," +
    "Nikolaj Kæmpe," +
    "Daniel," +
    "Jakob," +
    "Andreas Hjordt," +
    "Thomas Nicolaj" +
    "Jørgen Mortensen," +
    "Nina," +
    "Anders Julfeldt," +
    "Thor," +
    "Lars Rosenqvist," +
    "Kristian," +
    "Kåre," +
    "Christina," +
    "Nikolaj Holm," +
    "Peter Thorning," +
    "Kristoffer," +
    "Jesper Loose," +
    "Mads Jacobsen," +
    "Eske ," +
    "Neeraj Kumar," +
    "Peter Olsen," +
    "Mikkel," +
    "Pelle," +
    "Chakib," +
    "Mette," +
    "Andreas," +
    "Kenneth Michael";

  private List<String> employeeNames;

  public Employees() {
    employeeNames = Arrays.asList(employees.split(","));
  }

  public List<String> getEmployeeNames() {
    return employeeNames;
  }

  public String getRandomEmployee() {
    return employeeNames.get(new Random().nextInt(employeeNames.size()-1));
  }
}
