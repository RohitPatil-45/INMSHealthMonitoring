
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.InetAddress;
import javax.swing.JFrame;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.S
 */
/**
 *
 * @author Swapnil Lade Edited by Darshana Mali 26/09/2019
 */
public class Parameters1 {

    public void paramLoad() {
        readFromConfigFile();
    }

    public int readFromConfigFile() {
        AppData.DB_SERVER = "";
        AppData.DB_PORT = "";
        AppData.DB_USER = "";
        AppData.DB_PASS = "";
        //AppData.CONF_FILE = "config1.txt";
        FileReader file = null;
        String line = null;
        String Ip = null;
        String password = null;
        String username = null;
        String port = null;

        BufferedReader buffer = null;
        String schema = null;
        ;

        try {

            InetAddress hostip = InetAddress.getLocalHost();
           // AppData.PC_IPADDRESS = hostip.getHostAddress();
            String path = "config1.txt";

            file = new FileReader(path);
            buffer = new BufferedReader(file);

            while ((line = buffer.readLine()) != null) {
                if (line.contains("IPADDRESS")) {
                    Ip = line.substring(line.lastIndexOf("=") + 1);
                    AppData.DB_SERVER = Ip.trim();
                    System.out.println("AppData.DB_SERVER = " + AppData.DB_SERVER);
                }
                if (line.contains("DB_PORT")) {
                    port = line.substring(line.lastIndexOf("=") + 1);
                    AppData.DB_PORT = port.trim();
                    System.out.println(" AppData.DB_PORT = " + AppData.DB_PORT);
                }
                if (line.contains("USERNAME")) {
                    username = line.substring(line.lastIndexOf("=") + 1);
                    AppData.DB_USER = username.trim();
                    System.out.println(" AppData.DB_USER = " + AppData.DB_USER);
                }
                if (line.contains("PASSWORD")) {
                    password = line.substring(line.lastIndexOf("=") + 1);
                    AppData.DB_PASS = password.trim();
                    System.out.println(" AppData.DB_PASS = " + AppData.DB_PASS);
                }

                if (line.contains("SCHEMA")) {
                    schema = line.substring(line.indexOf("=") + 1);
                    AppData.SCHEMA = schema.trim();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (AppData.DB_SERVER != null && AppData.DB_PORT != null && AppData.DB_USER != null && AppData.DB_PASS != null) {
            return 1;
        } else {
            return 0;
        }
    }
}
