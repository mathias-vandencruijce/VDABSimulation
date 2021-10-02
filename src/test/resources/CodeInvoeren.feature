Feature: Code Invoeren
  Als een gebruiker  Wil ik een code kunnen invoeren
  Zodat ik geregistreerd ben als aanwezig in de les.

  Rule: Ik zie de naam, les en ID van de les waarvoor ik aanwezig ben.
    Scenario: Check naam, les en ID van een les
      Given Aangemeld als 'Student'
      When 'Student' aanwezig is op een les
      Then Naam en ID van een les staan op home pagina

  Rule: De join klas optie verdwijnt uit de header als je aangemeld bent.
    Scenario: Check dat les toetreden niet meer in header staat
      Given Aangemeld als 'Student'
      When 'Student' aanwezig is op een les
      Then les toetreden optie is niet meer in de header
