package com.xftxyz.psmss.view.handlers;

import com.xftxyz.psmss.controller.service.ProcessManagementService;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class StopSimulationEventHandler implements EventHandler<ActionEvent> {

    private ProcessManagementService processManagementService;
    // private MenuItem menuItemStartSimulation;
    // private MenuItem menuItemStopSimulation;

    public StopSimulationEventHandler(ProcessManagementService processManagementService) {
        this.processManagementService = processManagementService;
        // this.menuItemStartSimulation = menuItemStartSimulation;
        // this.menuItemStopSimulation = menuItemStopSimulation;
    }

    @Override
    public void handle(ActionEvent event) {
        processManagementService.stop();

    }
}
