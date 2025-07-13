
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
 * @author VIJAY
 */
public class DiscoveBasicInfo implements Runnable {
    
    public static String oid_device_name = "1.3.6.1.2.1.1.5.0";
    public static String oid_device_mac = "1.0.8802.1.1.2.1.3.2.0";
    public static String oid_device_hw_version = "1.0.8802.1.1.2.1.5.4795.1.2.2.0";
    public static String oid_device_firmware_version = "1.0.8802.1.1.2.1.5.4795.1.2.3.0";
    public static String oid_device_sw_version = "1.0.8802.1.1.2.1.5.4795.1.2.4.0";
    public static String oid_device_serial_no = "1.0.8802.1.1.2.1.5.4795.1.2.5.0";
    public static String oid_device_manufacturer = "1.0.8802.1.1.2.1.5.4795.1.2.6.0";
    public static String oid_device_model_name = "1.0.8802.1.1.2.1.5.4795.1.2.7.0";
    public static String oid_device_ram_info = "1.3.6.1.4.1.207.8.4.4.3.21.2.1.1.1";
    public static String oid_device_flash_info = "1.3.6.1.4.1.207.8.4.4.3.21.2.1.2.1";
    
    public static String oid_sw = "1.3.6.1.4.1.207.8.4.4.4.500.2.1.2.0";
    
    public void run() {
        
        System.out.println("Start Basic Info switch discover");
        ArrayList ip_branch_list = new ArrayList();
        int pool_size = 0;
        Connection connection5 = null;
        List details_list = null;
        
        try {
            connection5 = Datasource55.getConnection();
            Statement st1 = connection5.createStatement();
            ResultSet r1 = st1.executeQuery("select IP_ADDRESS,BRANCH_NAME,SSA,ZONE_NAME,CUSTOMER,DISTRICT,DEPARTMENT,STATE_UT,COMMUNITY,SNMP_VERSION,USER_NAME,AUTH_NAME,AUTH_PASSWORD,PRIVACY_NAME,PRIVACY_PASSWORD,PORT_NUMBER,ROUTER_TYPE,HEALTH_HISTORY_PARAM,CPU_THRESHOLD,MEMORY_THRESHOLD from ADD_INTERFACE  order by sr_no ASC");
            String router_ipadress = "";
            while (r1.next()) {
                router_ipadress = r1.getString(1);
                System.out.println("Device IP Discover:" + router_ipadress);
                ip_branch_list.add(router_ipadress);
                details_list = new ArrayList();
                pool_size = pool_size + 1;
                router_ipadress = r1.getString(1);
                details_list.add(r1.getString(2));
                details_list.add(r1.getString(3));
                details_list.add(r1.getString(4));
                details_list.add(r1.getString(5));
                details_list.add(r1.getString(6));
                details_list.add(r1.getString(7));
                details_list.add(r1.getString(8));
                details_list.add(r1.getString(9));
                details_list.add(r1.getString(10));
                details_list.add(r1.getString(11));
                details_list.add(r1.getString(12));
                details_list.add(r1.getString(13));
                details_list.add(r1.getString(14));
                details_list.add(r1.getString(15));
                details_list.add(r1.getString(16));
                details_list.add(r1.getString(17));
                details_list.add(r1.getString(18));
                details_list.add(r1.getString(19));  //cpu threshold
                details_list.add(r1.getString(20));  // memeory threshold
                NodeHealthMon.device_discover_map.put(router_ipadress, details_list);
            }
            System.out.println("total ip:" + ip_branch_list.size());
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            if (connection5 != null) {
                try {
                    connection5.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    
                }
                
            }
        }
        
        Iterator<Object> main_itr = ip_branch_list.iterator();
        while (main_itr.hasNext()) {
            String device_ip = main_itr.next().toString();
            try {
//                getDeviceDetails(device_ip);
                Thread.sleep(3000);
                Thread t1 = new Thread(new BasicInfoThread(device_ip));
                t1.start();
            } catch (Exception e) {
                System.out.println("Exception");
            }
        }
        
    }
    
//    public void getDeviceDetails(String router_ipadress) {
//        System.out.println("Basic Info Device IP:" + router_ipadress);
//        
//        String community_string = null;
//        String snmp_version = null;
//        String user_name = null;
//        String authentication = null;
//        String auth_pass = null;
//        String privacy = null;
//        String privacy_pass = null;
//        try {
//            community_string = SNMPHealth.device_discover_map.get(router_ipadress).get(7);
//            snmp_version = SNMPHealth.device_discover_map.get(router_ipadress).get(8);
//            user_name = SNMPHealth.device_discover_map.get(router_ipadress).get(9);
//            authentication = SNMPHealth.device_discover_map.get(router_ipadress).get(10);
//            auth_pass = SNMPHealth.device_discover_map.get(router_ipadress).get(11);
//            privacy = SNMPHealth.device_discover_map.get(router_ipadress).get(12);
//            privacy_pass = SNMPHealth.device_discover_map.get(router_ipadress).get(13);
//        } catch (Exception e) {
//            System.out.println(":" + router_ipadress + "hash map excep:" + e);
//        }
//        
//        SNMPUtilHealth su = null;
//        su = new SNMPUtilHealth();
//        
//        SNMPUtilVersion3 suversion3 = null;
//        suversion3 = new SNMPUtilVersion3();
//        
//        String var_device_name = null;
//        String var_device_mac = null;
//        String var_device_hw_version = null;
//        String var_device_firmware_version = null;
//        String var_device_sw_version = null;
//        String var_device_serial_no = null;
//        String var_device_manufacturer = null;
//        String var_odevice_model_name = null;
//        String var_device_ram_info = null;
//        String var_device_flash_info = null;
//        String var_sw = null;
//        System.out.println("snmp_version:" + snmp_version + ":" + community_string);
//        try {
//            String oid_val = oid_device_name;
//            OID cpuOID = null;
//            cpuOID = new OID(oid_val);
//            Target target1 = null;
//            
//            if (snmp_version.equals("Version3")) {
//                suversion3.start();
//                try {
//                    target1 = suversion3.getTargetVersion3("udp:" + router_ipadress + "/161", user_name, authentication, auth_pass, privacy, privacy_pass, SnmpConstants.version3);
//                    var_device_name = suversion3.BandwidthGetVect(target1, "cpuOID", cpuOID);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            } else {
//                if (snmp_version.equals("Version1")) {
//                    su.start();
//                    target1 = su.getTarget("udp:" + router_ipadress + "/161", community_string, SnmpConstants.version1);
//                    var_device_name = su.BandwidthGetVect(target1, "Out", cpuOID);
//                } else {
//                    
//                    System.out.println("Version2 basic info:" + router_ipadress);
//                    su.start();
//                    target1 = su.getTarget("udp:" + router_ipadress + "/161", community_string, SnmpConstants.version2c);
//                    var_device_name = su.BandwidthGetVect(target1, "Out", new OID(oid_device_name));
//                    var_device_mac = su.BandwidthGetVect(target1, "Out", new OID(oid_device_mac));
//                    var_device_hw_version = su.BandwidthGetVect(target1, "Out", new OID(oid_device_hw_version));
//                    var_device_firmware_version = su.BandwidthGetVect(target1, "Out", new OID(oid_device_firmware_version));
//                    var_device_sw_version = su.BandwidthGetVect(target1, "Out", new OID(oid_device_sw_version));
//                    var_device_serial_no = su.BandwidthGetVect(target1, "Out", new OID(oid_device_serial_no));
//                    var_device_manufacturer = su.BandwidthGetVect(target1, "Out", new OID(oid_device_manufacturer));
//                    var_odevice_model_name = su.BandwidthGetVect(target1, "Out", new OID(oid_device_model_name));
//                    var_device_ram_info = su.BandwidthGetVect(target1, "Out", new OID(oid_device_ram_info));
//                    var_device_flash_info = su.BandwidthGetVect(target1, "Out", new OID(oid_device_flash_info));
//                    var_sw = su.BandwidthGetVect(target1, "Out", new OID(oid_sw));
//                }
//                
//            }
//            
//        } catch (Exception e12) {
//            System.out.println("Exception SNMP basic Info:" + e12);
//        } finally {
//            try {
//                if (suversion3 != null) {
//                    suversion3.stop();
//                }
//                if (su != null) {
//                    su.stop();
//                }
//            } catch (Exception ex2) {
//                System.out.println("SNMP Close Exception....." + ex2);
//            }
//            
//        }
//        
//        System.out.println(router_ipadress + ":Device Name:" + var_device_name);
//        System.out.println(router_ipadress + ":Device Mac:" + var_device_mac);
//        System.out.println(router_ipadress + ":var_device_hw_version:" + var_device_firmware_version);
//        System.out.println(router_ipadress + ":var_device_sw_version:" + var_device_sw_version);
//        System.out.println(router_ipadress + ":var_device_hw_version:" + var_device_hw_version);
//        System.out.println(router_ipadress + ":var_device_serial_no:" + var_device_serial_no);
//        System.out.println(router_ipadress + ":var_device_manufacturer:" + var_device_manufacturer);
//        System.out.println(router_ipadress + ":var_odevice_model_name:" + var_odevice_model_name);
//        System.out.println(router_ipadress + ":var_device_ram_info:" + var_device_ram_info);
//        System.out.println(router_ipadress + ":var_device_flash_info:" + var_device_flash_info);
//        System.out.println(router_ipadress + ":var_sw:" + var_sw);
//        
//        Connection con = null;
//        PreparedStatement stmt5 = null;
//        try {
//            con = Datasource55.getConnection();
//            stmt5 = con.prepareStatement("UPDATE add_interface SET DEVICE_NAME=?,DEVICE_MAC=?,DEVICE_SERIAL_NO=?,DEVICE_MANUFACTURER=?,DEVICE_MODEL_NAME=?,DEVICE_HW_VERSION=?,DEVICE_SW_VERSION=?,DEVICE_FIRMWARE_VERSION=?,DEVICE_RAM_DETAILS=?,DEVICE_FLASH_DETAILS=?,DEVICE_SW=? WHERE ip_address='" + router_ipadress + "'");
//            stmt5.setString(1, var_device_name);
//            stmt5.setString(2, var_device_mac);
//            stmt5.setString(3, var_device_serial_no);
//            stmt5.setString(4, var_device_manufacturer);
//            stmt5.setString(5, var_odevice_model_name);
//            stmt5.setString(6, var_device_hw_version);
//            stmt5.setString(7, var_device_sw_version);
//            stmt5.setString(8, var_device_firmware_version);
//            stmt5.setString(9, var_device_ram_info);
//            stmt5.setString(10, var_device_flash_info);
//            stmt5.setString(11, var_sw);
//            stmt5.executeUpdate();
//            System.out.println("Update Basic info Success:" + router_ipadress);
//        } catch (SQLException e) {
//            System.out.println("Exception update basic info:" + e);
//        } finally {
//            if (stmt5 != null) {
//                try {
//                    stmt5.close();
//                } catch (Exception e) {
//                }
//            }
//            if (con != null) {
//                try {
//                    con.close();
//                } catch (Exception e) {
//                }
//            }
//        }
//        
//    }

//    public void getSNMPDetails()
//    {
//
//        SNMPUtilHealth su = null;
//        su = new SNMPUtilHealth();
//
//        SNMPUtilVersion3 suversion3 = null;
//        suversion3 = new SNMPUtilVersion3();
//
//        try {
//            String cpu_util = oid_cpu;
//            OID cpuOID = null;
//            cpuOID = new OID(cpu_util);
//            Target target1 = null;
//
//            if (snmp_version.equals("Version3")) {
//                username = user_name;
//                authname = authentication;
//                authpass = auth_pass;
//                privname = privacy;
//                privpass = privacy_pass;
//                suversion3.start();
//                try {
//                    target1 = suversion3.getTargetVersion3("udp:" + router_ipadress + "/161", user_name, authentication, auth_pass, privacy, privacy_pass, SnmpConstants.version3);
//                } catch (Exception e) {
//                    e.printStackTrace();;
//                }
//                try {
//                    cpu_val = suversion3.BandwidthGetVect(target1, "cpuOID", cpuOID);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                //target3 = su3.getTargetVersion3("udp:" + interface_router_ipadress + "/161", user_name,authentication,auth_pass,privacy,privacy_pass,SnmpConstants.version3);
//            } else {
//                System.out.println("in version 1 & 2");
//                if (snmp_version.equals("Version1")) {
//                    System.out.println("in version 1");
//                    su.start();
//                    target1 = su.getTarget("udp:" + router_ipadress + "/161", community_string, SnmpConstants.version1);
//                    cpu_val = su.BandwidthGetVect(target1, "Out", cpuOID);
//                } else {
//                    System.out.println("in version 2");
//                    su.start();
//                    target1 = su.getTarget("udp:" + router_ipadress + "/161", community_string, SnmpConstants.version2c);
//                    cpu_val = su.BandwidthGetVect(target1, "Out", cpuOID);
//                }
//
//            }
//
//        } catch (Exception e12) {
//            System.out.println("Exception SNMP:" + e12);
//        } finally {
//            try {
//                if (suversion3 != null) {
//                    suversion3.stop();
//                }
//                if (su != null) {
//                    su.stop();
//                }
//            } catch (Exception ex2) {
//                System.out.println("SNMP Close Exception....." + ex2);
//            }
//
//        }
//
//        System.out.println(router_ipadress + ":CPU Utilization:" + cpu_val + ":" + oid_cpu);
//
//    }
}
