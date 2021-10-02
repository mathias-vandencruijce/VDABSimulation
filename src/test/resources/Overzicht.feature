Feature: Overzicht
  Als een lector wil ik een overzicht van alle studenten kunnen zien.

  Rule: Lector ziet de Studenten in huidige les
    Scenario: Header bevat User Overview
      Given Aangemeld als 'Lector'
      When 'Lector' op de hoofdpagina kijkt
      Then Staat "Lessen Overzicht" in de header

  Rule: Lector ziet een overview van alle personen
    Scenario: Lector kijkt naar studenten in klas
      Given Aangemeld als 'Lector'
      When 'Lector' op de Huidige Les Pagina kijkt
      Then Staan Studenten op lijst

  Rule: Een user heeft een userid, voornaam en achternaam
    Scenario: Lector kijkt naar studenten in klas en bekijkt de details
      Given Aangemeld als 'Lector'
      When 'Lector' op de Huidige Les Pagina kijkt
      Then Staan Studenten in lijst met userid, voornaam, Lastnaam
