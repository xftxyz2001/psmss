# psmss
单处理器进程调度管理系统模拟 Process Scheduling Management System Simulation（操作系统实验三）


## 提示
关键三点：
1. 如何组织进程：
   - 确定PCB内容：标识信息、状态和运行时间与存储地址等信息、现场信息、管理信息
   - PCB组织方式：相同状态的进程PCB构成一个队列（即有空闲、就绪、运行、阻塞和完成5个队列）
2. 如何创建进程：
   - 申请PCB（从空闲队列） —> 申请资源 —> 填写PCB —> 挂就绪队列
3. 如何实现处理机调度及进程状态切换：
   - 采用先来先服务（FCFS）调度策略实现进程调度；
   - 从就绪队列选择一个进程；摘取PCB，挂运行队列；修改状态等PCB内容； 保存现场、恢复现场；
   - 模拟运行--可以按照两种场景模拟进程运行：
     1. 可以预先设置好各进程的运行时间、I/O时间、I/O发生的时刻等信息，之后操作系统控制进程运行，实现状态切换，直到全部进程完成。
     2. 亦可以采用人工干预方式控制进程状态切换（运行时间已预先设置），比如输入“Esc”进入“阻塞”状态，输入“Enter” 则选择（新）进程运行（进程调度），当前进程回到就绪状态;输入“wakeup”,再选择阻塞进程，则被选中的阻塞进程回到就绪状态；输入“finished”，当前进程运行结束，回到完成状态；
   - 修改（剩余）运行时间。

扩展
- 采用多队列反馈式调度策略实现进程调度。


## 参考资料
- 操作系统：模拟进程调度管理系统_oldfar的博客-CSDN博客
- JavaFX Dialog对话框_潘小狮的博客-CSDN博客_javafx弹出框
