import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class BinaryJob implements Job {
    public void execute(JobExecutionContext jobExecutionContext) {
        try {
            new DBwriter();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
