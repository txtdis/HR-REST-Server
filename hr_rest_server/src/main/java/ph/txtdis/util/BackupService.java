package ph.txtdis.util;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ph.txtdis.exception.FailedBackupException;

@Component
public class BackupService {

    @Value("${database.version}")
    private String databaseVersion;

    @Value("${database.name}")
    private String databaseName;

    private static final String BACKUP = System.getProperty("user.home")
            + "\\txtdis.backup";

    public byte[] getBackupBytes() {
        try {
            return process(build());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new FailedBackupException();
        }
    }

    private byte[] process(ProcessBuilder pb) throws IOException,
            InterruptedException {
        Process process = pb.start();
        process.waitFor();
        return toBytes(process);
    }

    private byte[] toBytes(Process process) {
        if (process.exitValue() != 0)
            throw new FailedBackupException();
        return DIS.toBytes(BACKUP);
    }

    private ProcessBuilder build() {
        ProcessBuilder pb = new ProcessBuilder(backupCommand());
        pb.environment().put("PGPASSWORD", Secure.password());
        return pb;
    }

    private List<String> backupCommand() {
        // @formatter:off
        List<String> c = Arrays.asList(
            "c:\\Program Files\\PostgreSQL\\" + databaseVersion + "\\bin\\pg_dump.exe",
            "--host=localhost",
            "--port=5432",
            "--username=" + Secure.username(),
            "--file=" + BACKUP,
            "--format=custom",
            "--dbname=" + databaseName);
        // @formatter:on
        return c;
    }
}
