
import org.snmp4j.Target;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.OID;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author testsys
 */
public class RequestSNMPMain {

    public static void main(String[] args) {
        String device_ip = "192.168.0.1";
        String community_string="public";
        RequestSNMPMain req = new RequestSNMPMain();
        req.monitoring(device_ip,community_string);

    }

    public void monitoring(String router_ipadress,String community_string) {


        System.out.println("Device ip:" + router_ipadress);
        SNMPUtilHealth su = null;
        su = new SNMPUtilHealth();
        try {
            String oid_cpu = "1.3.6.1.4.1.12356.101.4.1.3.0";
            OID cpuOID = null;
            cpuOID = new OID(oid_cpu);
            Target target = null;
            su.start();
            target = su.getTarget("udp:" + router_ipadress + "/161", community_string, SnmpConstants.version2c);
            String cpu_val = su.BandwidthGetVect(target, "Out", cpuOID);
            System.out.println("cpu_val:" + cpu_val);
        } catch (Exception e12) {
            System.out.println("Exception CPU:" + e12);
        } finally {
            try {
                if (su != null) {
                    su.stop();
                }
            } catch (Exception ex2) {
                System.out.println("SNMP Close Exception....." + ex2);
            }
        }   
        
        
        

    }

}
