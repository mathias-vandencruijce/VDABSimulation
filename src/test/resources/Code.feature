Feature: Generate code
  Als lector wil ik een les code kunnen generen.

  Rule: Als ik een code wil generen
    Scenario: Code generen
      Given Aangemeld als 'Lector'
      When 'Lector' een code heeft gegenereerd
      Then Code is 7-digit long

