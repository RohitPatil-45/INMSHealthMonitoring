/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package SNMPHealth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author velox
 */
public class NodeHealthMon {
    
      public static boolean isSimulation=true;

    public static ConcurrentHashMap<String, List<String>> snmpOID_hashmap = null;
    public static CopyOnWriteArrayList tipsize = null;
    //public static ConcurrentHashMap<String, List<String>> cpuhashmap = null;
    public static ConcurrentHashMap staus_cpumap = null;
    public static ConcurrentHashMap staus_rammap = null;
    public static CopyOnWriteArrayList<HealthMaster> cpu_temp_list = null;
    public static CopyOnWriteArrayList<HealthMaster> cpu_temp_list_temp = null;
    public static CopyOnWriteArrayList<HealthMaster> ram_list = null;
    public static CopyOnWriteArrayList<HealthMaster> ram_list_temp = null;
    public static ConcurrentHashMap cpu_history_list = null;

    public static ConcurrentHashMap<String, List<String>> device_discover_map = null;
    public static HashMap<String, HealthModel> healthMonData = null;

    public static void main(String[] args) {

        healthMonData = new HashMap<>();
        System.out.println("Version: 181224 latest");
        System.out.println("Memory Monitoring Mikrotik Device");

        try {
            Parameters1 param = null;
            param = new Parameters1();
            param.paramLoad();
        } catch (Exception exception) {
            System.out.println("Parameter Exception:" + exception);
        }

        try {
            NodeHealthMon health = null;
            health = new NodeHealthMon();
            health.HelathMonitoring();
        } catch (Exception ex) {
            System.out.println("SNMP Health exception:" + ex);
        }

    }

    // public static void main(String[] args) {
    public void HelathMonitoring() {
        System.out.println("Health Monitoring NPM 06Jun25 7:05PM");
        cpu_history_list = new ConcurrentHashMap();
        snmpOID_hashmap = new ConcurrentHashMap();
        staus_cpumap = new ConcurrentHashMap();
        staus_rammap = new ConcurrentHashMap();
        tipsize = new CopyOnWriteArrayList();
        cpu_temp_list = new CopyOnWriteArrayList();
        cpu_temp_list_temp = new CopyOnWriteArrayList();
        ram_list = new CopyOnWriteArrayList();
        ram_list_temp = new CopyOnWriteArrayList();
        device_discover_map = new ConcurrentHashMap();
//        
//         try {
//            Thread t3 = null;
//            t3 = new Thread(new DiscoveBasicInfo());
//            t3.start();
//        } catch (Exception e) {
//            System.out.println("Exception:" + e);
//        }

        try {
            Thread t2 = null;
            t2 = new Thread(new HealthThreadClass());
            t2.start();
        } catch (Exception e) {
            System.out.println("Exception:" + e);
        }

        try {
            Thread t3 = null;
            t3 = new Thread(new MemoryBatch());
            t3.start();
        } catch (Exception e) {
            System.out.println("Exception:" + e);
        }

//        try {
//            Thread t3 = null;
//            t3 = new Thread(new CPUBatch());
//            t3.start();
//        } catch (Exception e) {
//            System.out.println("Exception:" + e);
//        }

    }

}
