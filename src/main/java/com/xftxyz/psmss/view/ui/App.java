package com.xftxyz.psmss.view.ui;

import com.xftxyz.psmss.controller.service.ProcessInfomationTableViewService;
import com.xftxyz.psmss.controller.service.ProcessManagementService;
import com.xftxyz.psmss.controller.service.ProgramStatusBarService;
import com.xftxyz.psmss.model.bean.ProcessStatus;
import com.xftxyz.psmss.view.handlers.ConnfigEventHandler;
import com.xftxyz.psmss.view.handlers.StartSimulationEventHandler;
import com.xftxyz.psmss.view.handlers.StopSimulationEventHandler;
import com.xftxyz.psmss.view.handlers.WindowCloseEventHandler;
import com.xftxyz.psmss.view.handlers.help.AboutEventHandler;
import com.xftxyz.psmss.view.handlers.help.DocumentEventHandler;
import com.xftxyz.psmss.view.handlers.xedit.AddEventHandler;
import com.xftxyz.psmss.view.handlers.xedit.ClearAllEventHandler;
import com.xftxyz.psmss.view.handlers.xedit.QuickAddEventHandler;
import com.xftxyz.psmss.view.handlers.xedit.RemoveEventHandler;
import com.xftxyz.psmss.view.handlers.xfile.ExitEventHandler;
import com.xftxyz.psmss.view.handlers.xfile.OpenEventHandler;
import com.xftxyz.psmss.view.handlers.xfile.SaveConfigurationEventHandler;
import com.xftxyz.psmss.view.handlers.xfile.SaveResultEventHandler;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

// import javafx.application.Application;
// import javafx.fxml.FXMLLoader;
// import javafx.scene.Parent;
// import javafx.scene.Scene;
// import javafx.stage.Stage;

// import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private ProgramStatusBarService programStatusBarService;
    private ProcessInfomationTableViewService processInfomationTableViewService;
    private ProcessManagementService processManagementService;

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();

        // this.statusBar = new Label("stutus bar");
        // root.setBottom(statusBar);
        programStatusBarService = new ProgramStatusBarService();
        processInfomationTableViewService = new ProcessInfomationTableViewService();
        processManagementService = new ProcessManagementService(programStatusBarService,
                processInfomationTableViewService);

        // MenuBar menuBar = createMenuBar();
        // root.setTop(menuBar);
        root.setTop(createMenuBar());
        root.setCenter(processInfomationTableViewService.getTableView());
        root.setBottom(programStatusBarService.getStatusBar());

        // class A {
        // private final SimpleStringProperty firstName = new
        // SimpleStringProperty("ghfjfghj");
        // private final SimpleStringProperty lastName = new
        // SimpleStringProperty("sdfg");
        // public String getFirstName() {
        // return firstName.get();
        // }
        // public String getLastName() {
        // return lastName.get();
        // }

        // }
        // ObservableList<A> data = FXCollections.observableArrayList();
        // tableView.setItems(data);
        // data.add(new A());
        // TableColumn firstNameCol = new TableColumn("First Name");
        // firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        // TableColumn lastNameCol = new TableColumn("Last Name");
        // lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        // tableView.getColumns().addAll(firstNameCol, lastNameCol);

        Scene scene = new Scene(root, UIData.SCENE_WIDTH, UIData.SCENE_HEIGHT);
        // 设置窗体标题
        primaryStage.setTitle(UIData.primaryStageTitle);
        primaryStage.setScene(scene);
        primaryStage.show();
        // 关闭窗体，询问是否关闭
        primaryStage.setOnCloseRequest(new WindowCloseEventHandler());
        // primaryStage.close();
    }

    // 创建菜单
    private MenuBar createMenuBar() {
        MenuItem menuItemOpen = new MenuItem("打开");
        menuItemOpen.setOnAction(new OpenEventHandler(processManagementService));
        MenuItem menuItemSaveProcessConfiguration = new MenuItem("保存进程配置");
        menuItemSaveProcessConfiguration
                .setOnAction(new SaveConfigurationEventHandler(processManagementService));
        MenuItem menuItemSaveProcessSimulation = new MenuItem("保存模拟结果");
        menuItemSaveProcessSimulation.setOnAction(new SaveResultEventHandler(processManagementService));
        MenuItem menuItemExit = new MenuItem("退出");
        menuItemExit.setOnAction(new ExitEventHandler());
        // Menu menu = new Menu("文件", null, menuItem);
        Menu menuFile = new Menu("文件", null, menuItemOpen, menuItemSaveProcessConfiguration,
                menuItemSaveProcessSimulation, menuItemExit);

        // menuStartSimulation.setOnAction(new
        // StartSimulationEventHandler(processManagementService,
        // menuStartSimulation));
        // menuSimulation.setOnAction();
        MenuItem menuItemStartSimulation = new Menu("开始模拟");
        MenuItem menuItemStopSimulation = new MenuItem("停止模拟");
        menuItemStartSimulation.setOnAction(new StartSimulationEventHandler(processManagementService));
        menuItemStopSimulation.setOnAction(new StopSimulationEventHandler(processManagementService));
        processManagementService.initMenuButton(menuItemStartSimulation, menuItemStopSimulation);
        menuItemStopSimulation.setVisible(false);
        Menu menuSimulation = new Menu("模拟", null, menuItemStartSimulation,
                menuItemStopSimulation);

        MenuItem menuItemAddProcess = new MenuItem("添加进程");
        menuItemAddProcess.setOnAction(new AddEventHandler(processManagementService));
        MenuItem menuItemQuickAddProcess = new MenuItem("快速添加");
        menuItemQuickAddProcess.setOnAction(new QuickAddEventHandler(processManagementService));
        MenuItem menuItemRemoveProcess = new MenuItem("删除进程");
        menuItemRemoveProcess.setOnAction(new RemoveEventHandler(processManagementService));
        MenuItem menuItemClearAllProcess = new MenuItem("清空进程");
        menuItemClearAllProcess.setOnAction(new ClearAllEventHandler(processManagementService));
        Menu menuEdit = new Menu("编辑", null, menuItemAddProcess, menuItemQuickAddProcess, menuItemRemoveProcess,
                menuItemClearAllProcess);

        MenuItem menuItemConfigEmptyQueue = new MenuItem("配置空闲队列");
        menuItemConfigEmptyQueue
                .setOnAction(new ConnfigEventHandler(processManagementService, ProcessStatus.IDLE));
        MenuItem menuItemConfigReadyQueue = new MenuItem("配置就绪队列");
        menuItemConfigReadyQueue
                .setOnAction(new ConnfigEventHandler(processManagementService, ProcessStatus.READY));
        MenuItem menuItemConfigBlockedQueue = new MenuItem("配置阻塞队列");
        menuItemConfigBlockedQueue
                .setOnAction(new ConnfigEventHandler(processManagementService, ProcessStatus.BLOCKED));
        Menu menuConfiguration = new Menu("配置队列", null, menuItemConfigEmptyQueue, menuItemConfigReadyQueue,
                menuItemConfigBlockedQueue);

        MenuItem menuItemDocument = new MenuItem("文档");
        menuItemDocument.setOnAction(new DocumentEventHandler());
        MenuItem menuItemAbout = new MenuItem("关于");
        menuItemAbout.setOnAction(new AboutEventHandler());
        Menu menuHelp = new Menu("帮助", null, menuItemDocument, menuItemAbout);

        MenuBar menuBar = new MenuBar(menuFile, menuSimulation, menuEdit, menuConfiguration, menuHelp);

        return menuBar;
    }
    // private static Scene scene;

    // @Override
    // public void start(Stage stage) throws IOException {
    // scene = new Scene(loadFXML("primary"), 640, 480);
    // stage.setScene(scene);
    // stage.show();
    // }

    // static void setRoot(String fxml) throws IOException {
    // scene.setRoot(loadFXML(fxml));
    // }

    // private static Parent loadFXML(String fxml) throws IOException {
    // FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml +
    // ".fxml"));
    // return fxmlLoader.load();
    // }

    public static void main(String[] args) {
        launch();
    }

}