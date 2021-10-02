Feature: Aanwezige studenten
  Als een leerkracht wil ik een lijst van alle aanwezige studenten.

  Rule: Als ik de Aanwezig header zie
    Scenario: De optie "aanwezigen studenten" staan in de header van de lector
      Given Aangemeld als 'Lector'
      When 'Lector' op de hoofdpagina kijkt
      Then Staat aanwezigen studenten bij de header

  Rule: Als ik een lijst aanwezig studenten wil zien
    Scenario: Ziet lijst aanwezige studenten
      Given Aangemeld als 'Lector'
      When 'Lector' op de Aanwezige Studente pagina kijkt
      Then Staan lijst met aanwezige studenten

  Rule: een student heeft een userid, voornaam en achternaam
    Scenario: Bekijkt student Userid, first Name, Last Name
      Given Aangemeld als 'Lector'
      When 'Lector' op de Aanwezige Studente pagina kijkt
      Then Staan lijst met aanwezige studenten met naam Userid, First Name, Last Name.
