package com.xftxyz.psmss.view.ui;

import com.xftxyz.psmss.controller.service.ProcessManagementService;
import com.xftxyz.psmss.view.handlers.AddButtonEventHandler;

import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ProcessAddStage extends Stage {

    private ProcessManagementService processManagementService;

    private TextField tfName;
    private TextField tfPriority;
    private TextField tfArriveTime;
    private TextField tfTotalTime;
    private CheckBox cbBlock;

    public TextField getTfName() {
        return tfName;
    }

    // public void setTfName(TextField tfName) {
    // this.tfName = tfName;
    // }

    public TextField getTfPriority() {
        return tfPriority;
    }

    // public void setTfPriority(TextField tfPriority) {
    // this.tfPriority = tfPriority;
    // }

    public TextField getTfArriveTime() {
        return tfArriveTime;
    }

    // public void setTfArriveTim(TextField tfArriveTim) {
    // this.tfArriveTim = tfArriveTim;
    // }

    public TextField getTfTotalTime() {
        return tfTotalTime;
    }

    // public void setTfTotalTime(TextField tfTotalTime) {
    // this.tfTotalTime = tfTotalTime;
    // }

    public CheckBox getCbBlock() {
        return cbBlock;
    }

    // public void setCbBlock(CheckBox cbBlock) {
    // this.cbBlock = cbBlock;
    // }

    public ProcessManagementService getProcessManagementService() {
        return processManagementService;
    }

    // public void setProcessManagementService(ProcessManagementService
    // processManagementService) {
    // this.processManagementService = processManagementService;
    // }

    public ProcessAddStage(ProcessManagementService processManagementService) {
        this.processManagementService = processManagementService;

        GridPane gp = new GridPane();
        gp.setHgap(10);
        gp.setVgap(10);
        // gp.setPadding(new Insets(8, 8, 8, 8));
        // int name, int priority, int arriveTime, int totalTime, boolean block
        Label labelName = new Label("进程标识符");
        labelName.setFont(UIData.lblfont);
        Label labelPriority = new Label("进程优先级");
        labelPriority.setFont(UIData.lblfont);
        Label labelArriveTime = new Label("到达时间");
        labelArriveTime.setFont(UIData.lblfont);
        Label labelTotalTime = new Label("进程总时间");
        labelTotalTime.setFont(UIData.lblfont);
        Label labelBlock = new Label("是否阻塞");
        labelBlock.setFont(UIData.lblfont);

        Button btnAdd = new Button("添加");
        // btnAdd.setAlignment(Pos.CENTER);
        btnAdd.setFont(UIData.lblfont);
        btnAdd.setOnAction(new AddButtonEventHandler(this));

        tfName = new TextField();
        tfName.setFont(UIData.lblfont);
        tfPriority = new TextField();
        tfPriority.setFont(UIData.lblfont);
        tfArriveTime = new TextField();
        tfArriveTime.setFont(UIData.lblfont);
        tfTotalTime = new TextField();
        tfTotalTime.setFont(UIData.lblfont);
        cbBlock = new CheckBox("");
        // cbBlock.setAlignment(Pos.CENTER);
        cbBlock.setFont(UIData.lblfont);

        // Button btnCancel = new Button("取消");
        // btnCancel.setAlignment(Pos.CENTER);
        // btnCancel.setFont(UIData.lblfont);
        // btnCancel.setOnAction(new ProcessAddStage.CancelEventHandler(this));

        gp.add(labelName, 0, 0);
        GridPane.setHgrow(labelName, Priority.ALWAYS);
        GridPane.setVgrow(labelName, Priority.ALWAYS);
        gp.add(labelPriority, 0, 1);
        GridPane.setHgrow(labelPriority, Priority.ALWAYS);
        GridPane.setVgrow(labelPriority, Priority.ALWAYS);
        gp.add(labelArriveTime, 0, 2);
        GridPane.setHgrow(labelArriveTime, Priority.ALWAYS);
        GridPane.setVgrow(labelArriveTime, Priority.ALWAYS);
        gp.add(labelTotalTime, 0, 3);
        GridPane.setHgrow(labelTotalTime, Priority.ALWAYS);
        GridPane.setVgrow(labelTotalTime, Priority.ALWAYS);
        gp.add(labelBlock, 0, 4);
        GridPane.setHgrow(labelBlock, Priority.ALWAYS);
        GridPane.setVgrow(labelBlock, Priority.ALWAYS);

        gp.add(tfName, 1, 0);
        GridPane.setHgrow(tfName, Priority.ALWAYS);
        GridPane.setVgrow(tfName, Priority.ALWAYS);
        gp.add(tfPriority, 1, 1);
        GridPane.setHgrow(tfPriority, Priority.ALWAYS);
        GridPane.setVgrow(tfPriority, Priority.ALWAYS);
        gp.add(tfArriveTime, 1, 2);
        GridPane.setHgrow(tfArriveTime, Priority.ALWAYS);
        GridPane.setVgrow(tfArriveTime, Priority.ALWAYS);
        gp.add(tfTotalTime, 1, 3);
        GridPane.setHgrow(tfTotalTime, Priority.ALWAYS);
        GridPane.setVgrow(tfTotalTime, Priority.ALWAYS);
        gp.add(cbBlock, 1, 4);
        GridPane.setHgrow(cbBlock, Priority.ALWAYS);
        GridPane.setVgrow(cbBlock, Priority.ALWAYS);
        GridPane.setHalignment(cbBlock, HPos.CENTER);

        gp.add(btnAdd, 1, 5);
        GridPane.setHgrow(btnAdd, Priority.ALWAYS);
        GridPane.setVgrow(btnAdd, Priority.ALWAYS);
        GridPane.setHalignment(btnAdd, HPos.CENTER);
        // gp.add(btnCancel, 1, 5);
        // GridPane.setHgrow(btnCancel, Priority.ALWAYS);
        // GridPane.setVgrow(btnCancel, Priority.ALWAYS);
        // GridPane.setHalignment(btnCancel, HPos.CENTER);

        Scene scene = new Scene(gp, UIData.processAddStageWidth, UIData.processAddStageHeight);

        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle(UIData.processAddStageTitle);
        this.setScene(scene);
        this.show();
    }

}
