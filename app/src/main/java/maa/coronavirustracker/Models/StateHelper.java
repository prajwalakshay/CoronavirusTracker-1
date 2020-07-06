package maa.coronavirustracker.Models;

public class StateHelper {
    String StateNames, StateConfirmCases, StateDeltaConfirmCases, StateActiveCases, StateRecoveredCases, StateDeltaRecoveredCases, StateDeathCases, StateDeltaDeathCases, StateUpdatedTime;

    public StateHelper(String stateNames, String stateConfirmCases, String stateDeltaConfirmCases, String stateActiveCases, String stateRecoveredCases, String stateDeltaRecoveredCases, String stateDeathCases, String stateDeltaDeathCases, String stateUpdatedTime) {
        StateNames = stateNames;
        StateConfirmCases = stateConfirmCases;
        StateDeltaConfirmCases = stateDeltaConfirmCases;
        StateActiveCases = stateActiveCases;
        StateRecoveredCases = stateRecoveredCases;
        StateDeltaRecoveredCases = stateDeltaRecoveredCases;
        StateDeathCases = stateDeathCases;
        StateDeltaDeathCases = stateDeltaDeathCases;
        StateUpdatedTime = stateUpdatedTime;
    }

    public String getStateNames() {
        return StateNames;
    }

    public void setStateNames(String stateNames) {
        StateNames = stateNames;
    }

    public String getStateConfirmCases() {
        return StateConfirmCases;
    }

    public void setStateConfirmCases(String stateConfirmCases) {
        StateConfirmCases = stateConfirmCases;
    }

    public String getStateDeltaConfirmCases() {
        return StateDeltaConfirmCases;
    }

    public void setStateDeltaConfirmCases(String stateDeltaConfirmCases) {
        StateDeltaConfirmCases = stateDeltaConfirmCases;
    }

    public String getStateActiveCases() {
        return StateActiveCases;
    }

    public void setStateActiveCases(String stateActiveCases) {
        StateActiveCases = stateActiveCases;
    }

    public String getStateRecoveredCases() {
        return StateRecoveredCases;
    }

    public void setStateRecoveredCases(String stateRecoveredCases) {
        StateRecoveredCases = stateRecoveredCases;
    }

    public String getStateDeltaRecoveredCases() {
        return StateDeltaRecoveredCases;
    }

    public void setStateDeltaRecoveredCases(String stateDeltaRecoveredCases) {
        StateDeltaRecoveredCases = stateDeltaRecoveredCases;
    }

    public String getStateDeathCases() {
        return StateDeathCases;
    }

    public void setStateDeathCases(String stateDeathCases) {
        StateDeathCases = stateDeathCases;
    }

    public String getStateDeltaDeathCases() {
        return StateDeltaDeathCases;
    }

    public void setStateDeltaDeathCases(String stateDeltaDeathCases) {
        StateDeltaDeathCases = stateDeltaDeathCases;
    }

    public String getStateUpdatedTime() {
        return StateUpdatedTime;
    }

    public void setStateUpdatedTime(String stateUpdatedTime) {
        StateUpdatedTime = stateUpdatedTime;
    }
}