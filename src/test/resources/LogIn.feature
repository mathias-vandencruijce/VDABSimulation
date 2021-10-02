Feature: Login
  als een Gebruiker ik moet mij kunnen inloggen op de website.
  Dan past de navbar home-pagina zich aan.

  Rule: Als ik mij aanmeld als lector moet ik de overview pagina kunnen accessen
    Scenario: Overview staat op navbar
      Given Aangemeld als 'Lector'
      When 'Lector' op de hoofdpagina kijkt
      Then Staat Overview bij de header

  Rule: Als ik mij aanmeld als VDAB student zie ik een venster om de code in te voeren. Als ik mij aanmeld als VDAB student zie ik een venster om de code in te voeren
    Scenario: Les Toetreden staat op navbar
      Given Aangemeld als 'Student'
      When 'Student' op de hoofdpagina kijkt
      Then Staat Les Toetreden bij de header

  Rule: Als ik foutief passwoord/userid ingeef krijg ik daar een error
    Scenario: Foutieve naam
      Given Niemand Aangemeld
      When 'Student' op de hoofdpagina kijkt
      Then Toont juiste Error 'No valid userid/password'

    Scenario: Foutief paswoord
      Given Niemand Aangemeld
      When 'Student' op de hoofdpagina kijkt
      Then Toont juiste Error 'No valid userid/password'

  Rule: Als ik ingelogd ben zie ik een logout button
    Scenario: Logoutbutton is zichtbaar
      Given Aangemeld als 'Student'
      When 'Student' op de hoofdpagina kijkt
      Then Logoutbutton werkt

  Rule: Als ik Ingelogd ben zie ik mijn naam.
    Scenario: Student logt zich in
      Given Aangemeld als 'Student'
      When 'Student' op de hoofdpagina kijkt
      Then Naam van gebruiker is zichtbaar

    Scenario: Lector logt zich in
      Given Aangemeld als 'Lector'
      When 'Lector' op de hoofdpagina kijkt
      Then Naam van gebruiker is zichtbaar
