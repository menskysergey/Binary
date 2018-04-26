import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;

public class Solution {
    public static void main(String []args) throws Exception, SchedulerException {

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        // Извлекаем планировщик из schedule factory
        Scheduler scheduler = schedulerFactory.getScheduler();

        // Запускаем JobDetail с именем задания,
        // группой задания и классом выполняемого задания
        JobDetailImpl jobDetail = new JobDetailImpl("Binary", BinaryJob.class);
        // Запускаем CronTrigger с его именем и именем группы
        CronTriggerImpl cronTrigger = new CronTriggerImpl("d", "q","0 0 0-23 * * ?");

        // Планируем задание с помощью JobDetail и Trigger
        scheduler.scheduleJob(jobDetail, cronTrigger);

        // Запускаем планировщик
        scheduler.start();
    }
}
