Feature: Groups

  Scenario: Group Creation
    Given a set of groups
    When I create a new group with name xxx, header yyy, footer zzz
    Then the new set of groups is equal to the old set of groups with added group