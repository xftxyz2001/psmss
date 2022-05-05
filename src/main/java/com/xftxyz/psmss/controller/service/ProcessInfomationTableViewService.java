package com.xftxyz.psmss.controller.service;

import com.xftxyz.psmss.model.bean.PCB;
import com.xftxyz.psmss.view.ui.UIData;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ProgressBarTableCell;
import javafx.util.Callback;

public class ProcessInfomationTableViewService {
    private TableView<PCB> processInfomation;

    private TableColumn<PCB, Number> tcName;
    private TableColumn<PCB, String> tcStatus;
    private TableColumn<PCB, Number> tcPriority;
    private TableColumn<PCB, Number> tcTotalTime;
    private TableColumn<PCB, Number> tcRunTime;
    private TableColumn<PCB, Double> tcProgressBar;

    public ProcessInfomationTableViewService() {
        processInfomation = new TableView<>();

        tcName = new TableColumn<>("进程标识符");
        tcName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PCB, Number>, ObservableValue<Number>>() {
            @Override
            public ObservableValue<Number> call(CellDataFeatures<PCB, Number> param) {
                return param.getValue().getNameProperty();
            }
        });
        // tcName.setSortable(false);
        processInfomation.getColumns().add(tcName);

        tcStatus = new TableColumn<>("进程状态");
        tcStatus.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<PCB, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(CellDataFeatures<PCB, String> param) {
                        return param.getValue().getStatusProperty().asString();
                    }
                });
        // tcStatus.setSortable(false);
        processInfomation.getColumns().add(tcStatus);

        tcPriority = new TableColumn<>("进程优先级");
        tcPriority.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<PCB, Number>, ObservableValue<Number>>() {
                    @Override
                    public ObservableValue<Number> call(CellDataFeatures<PCB, Number> param) {
                        return param.getValue().getPriorityProperty();
                    }
                });
        // tcPriority.setSortable(false);
        processInfomation.getColumns().add(tcPriority);

        tcTotalTime = new TableColumn<>("进程总时间");
        tcTotalTime.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<PCB, Number>, ObservableValue<Number>>() {
                    @Override
                    public ObservableValue<Number> call(CellDataFeatures<PCB, Number> param) {
                        return param.getValue().getTotalTimeProperty();
                    }
                });
        // tcTotalTime.setSortable(false);
        processInfomation.getColumns().add(tcTotalTime);

        tcRunTime = new TableColumn<>("已运行时间");
        tcRunTime.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<PCB, Number>, ObservableValue<Number>>() {
                    @Override
                    public ObservableValue<Number> call(CellDataFeatures<PCB, Number> param) {
                        return param.getValue().getRunTimeProperty();
                    }
                });
        // tcRunTime.setSortable(false);
        processInfomation.getColumns().add(tcRunTime);

        tcProgressBar = new TableColumn<>("进程运行进度");
        tcProgressBar.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<PCB, Double>, ObservableValue<Double>>() {
                    @Override
                    public ObservableValue<Double> call(CellDataFeatures<PCB, Double> param) {
                        // System.out.println(param.getValue().getRunTimePercentProperty().get());
                        return param.getValue().getRunTimePercentProperty().asObject();
                    }
                });
        // tcProgressBar.setCellValueFactory();
        tcProgressBar.setCellFactory(ProgressBarTableCell.forTableColumn());
        tcProgressBar.setPrefWidth(UIData.SCENE_WIDTH / 2);
        // tcProgressBar.setSortable(false);
        processInfomation.getColumns().add(tcProgressBar);

    }

    public TableView<PCB> getTableView() {
        return processInfomation;
    }

    public void setItems(ObservableList<PCB> value) {
        processInfomation.setItems(value);

    }

}
