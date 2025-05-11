Feature: Validate user creation using JSON-driven strategy

  Scenario: Successful user creation and retrieval
    Given the actor named "api-user"
    And the user is created with
      | testDefinitionPath     | section            | saveRequestAs   | saveResponseAs  |
      | data/create-user.json  | FIRST_PRECONDITION | CREATE_USER_REQ | CREATE_USER_RES |
    Then the user is retrieved with
      | testDefinitionPath     | section | saveRequestAs | saveResponseAs |
      | data/create-user.json  | TEST    | GET_USER_REQ  | GET_USER_RES   |

