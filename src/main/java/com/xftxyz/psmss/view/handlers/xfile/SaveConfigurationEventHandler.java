package com.xftxyz.psmss.view.handlers.xfile;

import com.xftxyz.psmss.controller.service.ProcessManagementService;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SaveConfigurationEventHandler implements EventHandler<ActionEvent> {
    private ProcessManagementService processManagementService;

    public SaveConfigurationEventHandler(ProcessManagementService processManagementService) {
        this.processManagementService = processManagementService;
    }

    @Override
    public void handle(ActionEvent event) {
        processManagementService.saveProcessConfiguration();
    }

}
