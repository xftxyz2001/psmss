package com.xftxyz.psmss.controller.service;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.xftxyz.psmss.controller.settings.XFSettings;
import com.xftxyz.psmss.model.bean.PCB;
import com.xftxyz.psmss.model.bean.ProcessStatus;
import com.xftxyz.psmss.model.comparator.XFComparator;
import com.xftxyz.psmss.model.domain.BlockQueue;
import com.xftxyz.psmss.model.domain.NewQueue;
import com.xftxyz.psmss.model.domain.ReadyQueue;
import com.xftxyz.psmss.model.domain.SimpleProcessQueue;
import com.xftxyz.psmss.view.utils.DialogUtil;
import com.xftxyz.psmss.view.utils.FileUtil;

import javafx.application.Platform;
import javafx.scene.control.MenuItem;

public class ProcessManagementService {
    private ProgramStatusBarService programStatusBarService;
    private ProcessInfomationTableViewService processInfomationTableViewService;

    private MenuItem menuItemStartSimulation;
    private MenuItem menuItemStopSimulation;

    private SimpleProcessQueue allProcess; // 存放所有进程
    private NewQueue newQueue; // 新建队列
    private ReadyQueue readyQueue; // 就绪队列
    private PCB currentRunningProcess; // 当前运行的进程
    private BlockQueue blockQueue; // 挂起队列

    private int currentSystemTime = 0; // 模拟系统时间

    private Timer timer; // 创建一个定时器，用于模拟系统时间
    private int timeRound = 0;

    public ProcessManagementService(ProgramStatusBarService programStatusBarService,
            ProcessInfomationTableViewService processInfomationTableViewService) {
        allProcess = new SimpleProcessQueue();
        this.newQueue = new NewQueue(XFSettings.DEFAULT_IDLE_QUEUE_CAPACITY);
        this.readyQueue = new ReadyQueue(XFSettings.DEFAULT_READY_QUEUE_CAPACITY);
        this.blockQueue = new BlockQueue(XFSettings.DEFAULT_BLOCKED_QUEUE_CAPACITY);
        this.currentSystemTime = 0;

        this.programStatusBarService = programStatusBarService;
        this.processInfomationTableViewService = processInfomationTableViewService;

        // 绑定表格数据：所有进程
        this.processInfomationTableViewService.setItems(allProcess.getQueue());
    }

    public NewQueue getNewQueue() {
        return newQueue;
    }

    public ReadyQueue getReadyQueue() {
        return readyQueue;
    }

    public BlockQueue getBlockQueue() {
        return blockQueue;
    }

    public int getCurrentSystemTime() {
        return currentSystemTime;
    }

    public void addProcess(PCB process) {
        // 判断进程是否已存在
        if (allProcess.contains(process)) {
            DialogUtil.showWarningDialog("提示", null, "进程已存在");
            return;
        }
        allProcess.add(process);
        newQueue.add(process);
    }

    public void loadProcessConfiguration() {
        ArrayList<PCB> processConfiguration = FileUtil.loadProcessConfigurationFromFile();
        if (processConfiguration != null) {
            for (PCB process : processConfiguration) {
                addProcess(process);
            }
        }
    }

    public void saveProcessConfiguration() {
        if (allProcess.size() <= 0) {
            DialogUtil.showWarningDialog("保存进程配置", null, "没有进程");
            return;
        }
        ArrayList<PCB> processConfiguration = new ArrayList<>(allProcess.getQueue());
        FileUtil.saveProcessConfigurationToFile(processConfiguration);
    }

    public void saveResult() {
        FileUtil.saveResult(programStatusBarService.getAllStatus());
    }

    public void setQueueCapacity(ProcessStatus processStatus, int num) {
        switch (processStatus) {
            case IDLE:
                XFSettings.DEFAULT_IDLE_QUEUE_CAPACITY = num;
                newQueue.setCapacity(num);
                break;
            case READY:
                XFSettings.DEFAULT_READY_QUEUE_CAPACITY = num;
                readyQueue.setCapacity(num);
                break;
            case BLOCKED:
                XFSettings.DEFAULT_BLOCKED_QUEUE_CAPACITY = num;
                blockQueue.setCapacity(num);
                break;
            default:
                break;
        }
    }

    public void start() {
        // 初始化
        init();
        allProcess.getQueue().sort(XFComparator.ArrivalTimeComparator);
        //
        for (PCB pcb : allProcess.getQueue()) {
            newQueue.add(pcb);
        }

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        StringBuffer sbChangelog = new StringBuffer();
                        currentSystemTime++;
                        // 空闲 -> 就绪
                        for (int i = 0; i < newQueue.size(); i++) {
                            PCB current = newQueue.get();
                            if (current.getArriveTimeProperty().get() <= currentSystemTime) {
                                sbChangelog.append("进程" + current.getNameProperty().get() + "到达; ");
                                boolean succ = readyQueue.add(current);
                                if (succ) {
                                    sbChangelog.append("进程" + current.getNameProperty().get() + "进入就绪队列; ");
                                    newQueue.deleteQueue();
                                    sbChangelog.append("进程" + current.getNameProperty().get() + "从新建队列中移除; ");
                                    // newQueue.remove(current);
                                } else {
                                    sbChangelog.append("进程" + current.getNameProperty().get() + "进入就绪队列失败(就绪队列已满); ");
                                    // newQueue.add(current);
                                }
                            } else {
                                break;
                            }
                        }
                        // 运行
                        if (currentRunningProcess == null) {
                            currentRunningProcess = readyQueue.deleteQueue();
                            if (currentRunningProcess == null) {
                                // 就绪队列为空
                                if (newQueue.size() <= 0 && blockQueue.size() <= 0) {
                                    // timer.cancel();
                                    sbChangelog.append("模拟结束; ");
                                    ProcessManagementService.this.stop();
                                }
                            } else {
                                currentRunningProcess.getStatusProperty().set(ProcessStatus.RUNNING);
                                getTimeRound();
                                sbChangelog.append("进程" + currentRunningProcess.getNameProperty().get() + "得到时间片; ");
                            }
                        }
                        // 当前进程不为空
                        // 运行 -> 结束
                        if (currentRunningProcess != null) {
                            // 当前运行进程仍有时间片
                            if (timeRound > 0) {
                                timeRound--;
                                currentRunningProcess.getRunTimeProperty()
                                        .set(currentRunningProcess.getRunTimeProperty().get() + 1);
                                sbChangelog.append("进程" + currentRunningProcess.getNameProperty().get() + "正在运行; ");

                            }
                            // 当前进程运行结束
                            if (currentRunningProcess.getRunTimeProperty().get() >= currentRunningProcess
                                    .getTotalTimeProperty().get()) {
                                currentRunningProcess.getStatusProperty().set(ProcessStatus.FINISHED);
                                sbChangelog.append("进程" + currentRunningProcess.getNameProperty().get() + "运行结束; ");
                                currentRunningProcess = null;
                            } else if (timeRound <= 0) {
                                // 运行 -> 就绪： 当前进程未完成且时间片用完
                                readyQueue.add(currentRunningProcess);
                                sbChangelog.append("进程" + currentRunningProcess.getNameProperty().get() + "时间片用完; ");
                                currentRunningProcess = null;
                            } else if (currentRunningProcess.getBlockProperty().get()
                                    && currentRunningProcess.getHasBlockedProperty().get()) {
                                // 运行 -> 阻塞
                                boolean isBlocked = Math.random() < 0.3;
                                if (isBlocked) {
                                    boolean succ = blockQueue.add(currentRunningProcess);
                                    if (!succ) {
                                        PCB temp = blockQueue.deleteQueue();
                                        boolean succ2 = readyQueue.add(temp);
                                        if (!succ2) {
                                            PCB temp2 = readyQueue.deleteQueue();
                                            readyQueue.add(temp);
                                            currentRunningProcess = temp2;
                                        }
                                        blockQueue.add(currentRunningProcess);
                                    }
                                    currentRunningProcess = null;
                                    sbChangelog
                                            .append("进程" + currentRunningProcess.getNameProperty().get() + "进入阻塞队列; ");
                                }
                            }
                        }

                        // 阻塞 -> 就绪
                        blockQueue.getQueue().sort(XFComparator.BlockTimeComparator);
                        for (PCB pcb : blockQueue.getQueue()) {
                            // if (pcb.getBlockTimeProperty().get() > 0) {
                            pcb.getBlockTimeProperty().set(pcb.getBlockTimeProperty().get() - 1);
                            // }
                            if (pcb.getBlockTimeProperty().get() <= 0) {
                                // readyQueue.add(pcb);
                                boolean succ = readyQueue.add(pcb);
                                if (succ) {
                                    sbChangelog.append("进程" + pcb.getNameProperty().get() + "从阻塞队列移除; ");
                                    blockQueue.deleteQueue();
                                } else {
                                    sbChangelog.append("进程" + pcb.getNameProperty().get() + "进入就绪队列失败(就绪队列已满); ");
                                }
                            }
                        }

                        // 更新StatusBar
                        programStatusBarService.update(allProcess, currentSystemTime, sbChangelog.toString());
                        // processInfomationTableViewService.getTableView().refresh();
                    }
                });
            }
        }, 100, 500);// 定时器的延迟时间及间隔时间，不要设置太小

        menuItemStartSimulation.setVisible(false);
        menuItemStopSimulation.setVisible(true);
    }

    private void getTimeRound() {
        timeRound = (int) (Math.random() * 8) + 1;
    }

    private void init() {
        programStatusBarService.initStatusBar();
        currentSystemTime = 0;
        timer = new Timer();// 先new一个定时器
    }

    // 强行终止线程执行
    @SuppressWarnings("all")
    public void stop() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        // TODO: 收尾
        programStatusBarService.setText("就绪");
        menuItemStartSimulation.setVisible(true);
        menuItemStopSimulation.setVisible(false);
    }

    public void clearAllProcess() {
        stop();
        allProcess.clear();
        newQueue.clear();
        readyQueue.clear();
        blockQueue.clear();
    }

    public void removeProcess() {
        PCB p = processInfomationTableViewService.getTableView().getSelectionModel().getSelectedItem();
        if (p == null) {
            DialogUtil.showWarningDialog("删除进程", null, "没有选中进程");
            return;
        }
        ProcessStatus ps = p.getStatusProperty().get();
        if (ps == ProcessStatus.RUNNING) {
            DialogUtil.showWarningDialog("删除进程", null, "运行中的进程不能删除");
            return;
        }
        allProcess.remove(p);
        switch (ps) {
            case IDLE:
                newQueue.remove(p);
                break;
            case READY:
                readyQueue.remove(p);
                break;
            case BLOCKED:
                blockQueue.remove(p);
                break;
            default:
                break;
        }
    }

    public SimpleProcessQueue getAllProcess() {
        return allProcess;
    }

    public Timer getTimer() {
        return timer;
    }

    public void initMenuButton(MenuItem menuItemStartSimulation, MenuItem menuItemStopSimulation) {
        this.menuItemStartSimulation = menuItemStartSimulation;
        this.menuItemStopSimulation = menuItemStopSimulation;
    }

}
