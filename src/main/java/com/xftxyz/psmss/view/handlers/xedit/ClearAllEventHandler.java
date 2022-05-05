package com.xftxyz.psmss.view.handlers.xedit;

import com.xftxyz.psmss.controller.service.ProcessManagementService;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ClearAllEventHandler implements EventHandler<ActionEvent> {

    private ProcessManagementService processManagementService;

    public ClearAllEventHandler(ProcessManagementService processManagementService) {
        this.processManagementService = processManagementService;
    }

    @Override
    public void handle(ActionEvent event) {
        processManagementService.clearAllProcess();
    }

}
