Feature: Data Integrity Validation

  Scenario: Verify user created via API appears correctly in AdminLTE UI
    Given a new user is created via GoREST API
    When I open the AdminLTE DataTables page
    And I search for the newly created user
    Then the user details should exactly match the API data
    And I should be able to upload a sample file successfully