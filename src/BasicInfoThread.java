
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
public class BasicInfoThread implements Runnable {

    String router_ipadress;

    public BasicInfoThread(String ip) {
        router_ipadress = ip;
    }

    public void run() {
        System.out.println("Basic Info Device IP:" + router_ipadress);

        String community_string = null;
        String snmp_version = null;
        String user_name = null;
        String authentication = null;
        String auth_pass = null;
        String privacy = null;
        String privacy_pass = null;
        try {
            community_string = NodeHealthMon.device_discover_map.get(router_ipadress).get(7);
            snmp_version = NodeHealthMon.device_discover_map.get(router_ipadress).get(8);
            user_name = NodeHealthMon.device_discover_map.get(router_ipadress).get(9);
            authentication = NodeHealthMon.device_discover_map.get(router_ipadress).get(10);
            auth_pass = NodeHealthMon.device_discover_map.get(router_ipadress).get(11);
            privacy = NodeHealthMon.device_discover_map.get(router_ipadress).get(12);
            privacy_pass = NodeHealthMon.device_discover_map.get(router_ipadress).get(13);
        } catch (Exception e) {
            System.out.println(":" + router_ipadress + "hash map excep:" + e);
        }

        SNMPUtilHealth su = null;
        su = new SNMPUtilHealth();

        SNMPUtilVersion3 suversion3 = null;
        suversion3 = new SNMPUtilVersion3();

        String var_device_name = null;
        String var_device_mac = null;
        String var_device_hw_version = null;
        String var_device_firmware_version = null;
        String var_device_sw_version = null;
        String var_device_serial_no = null;
        String var_device_manufacturer = null;
        String var_odevice_model_name = null;
        String var_device_ram_info = null;
        String var_device_flash_info = null;
        String var_sw = null;
        System.out.println("snmp_version:" + snmp_version + ":" + community_string);
        try {
            String oid_val = DiscoveBasicInfo.oid_device_name;
            OID cpuOID = null;
            cpuOID = new OID(oid_val);
            Target target1 = null;

            if (snmp_version.equals("Version3")) {
                suversion3.start();
                try {
                    target1 = suversion3.getTargetVersion3("udp:" + router_ipadress + "/161", user_name, authentication, auth_pass, privacy, privacy_pass, SnmpConstants.version3);
                    var_device_name = suversion3.BandwidthGetVect(target1, "cpuOID", cpuOID);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                if (snmp_version.equals("Version1")) {
                    su.start();
                    target1 = su.getTarget("udp:" + router_ipadress + "/161", community_string, SnmpConstants.version1);
                    var_device_name = su.BandwidthGetVect(target1, "Out", cpuOID);
                } else {

                    System.out.println("Version2 basic info:" + router_ipadress);
                    su.start();
                    target1 = su.getTarget("udp:" + router_ipadress + "/161", community_string, SnmpConstants.version2c);
                    var_device_name = su.BandwidthGetVect(target1, "Out", new OID(DiscoveBasicInfo.oid_device_name));
                    System.out.println(router_ipadress+":switch name:"+var_device_name);
                    var_device_mac = su.BandwidthGetVect(target1, "Out", new OID(DiscoveBasicInfo.oid_device_mac));
                    var_device_hw_version = su.BandwidthGetVect(target1, "Out", new OID(DiscoveBasicInfo.oid_device_hw_version));
                    var_device_firmware_version = su.BandwidthGetVect(target1, "Out", new OID(DiscoveBasicInfo.oid_device_firmware_version));
                    var_device_sw_version = su.BandwidthGetVect(target1, "Out", new OID(DiscoveBasicInfo.oid_device_sw_version));
                    var_device_serial_no = su.BandwidthGetVect(target1, "Out", new OID(DiscoveBasicInfo.oid_device_serial_no));
                    var_device_manufacturer = su.BandwidthGetVect(target1, "Out", new OID(DiscoveBasicInfo.oid_device_manufacturer));
                    var_odevice_model_name = su.BandwidthGetVect(target1, "Out", new OID(DiscoveBasicInfo.oid_device_model_name));
                    var_device_ram_info = su.BandwidthGetVect(target1, "Out", new OID(DiscoveBasicInfo.oid_device_ram_info));
                    var_device_flash_info = su.BandwidthGetVect(target1, "Out", new OID(DiscoveBasicInfo.oid_device_flash_info));
                    var_sw = su.BandwidthGetVect(target1, "Out", new OID(DiscoveBasicInfo.oid_sw));
                }

            }

        } catch (Exception e12) {
            System.out.println("Exception SNMP basic Info:" + e12);
        } finally {
            try {
                if (suversion3 != null) {
                    suversion3.stop();
                }
                if (su != null) {
                    su.stop();
                }
            } catch (Exception ex2) {
                System.out.println("SNMP Close Exception....." + ex2);
            }

        }

        System.out.println(router_ipadress + ":Device Name:" + var_device_name);
        System.out.println(router_ipadress + ":Device Mac:" + var_device_mac);
        System.out.println(router_ipadress + ":var_device_hw_version:" + var_device_firmware_version);
        System.out.println(router_ipadress + ":var_device_sw_version:" + var_device_sw_version);
        System.out.println(router_ipadress + ":var_device_hw_version:" + var_device_hw_version);
        System.out.println(router_ipadress + ":var_device_serial_no:" + var_device_serial_no);
        System.out.println(router_ipadress + ":var_device_manufacturer:" + var_device_manufacturer);
        System.out.println(router_ipadress + ":var_odevice_model_name:" + var_odevice_model_name);
        System.out.println(router_ipadress + ":var_device_ram_info:" + var_device_ram_info);
        System.out.println(router_ipadress + ":var_device_flash_info:" + var_device_flash_info);
        System.out.println(router_ipadress + ":var_sw:" + var_sw);

        Connection con = null;
        PreparedStatement stmt5 = null;
        try {
            con = Datasource55.getConnection();
            stmt5 = con.prepareStatement("UPDATE add_interface SET DEVICE_NAME=?,DEVICE_MAC=?,DEVICE_SERIAL_NO=?,DEVICE_MANUFACTURER=?,DEVICE_MODEL_NAME=?,DEVICE_HW_VERSION=?,DEVICE_SW_VERSION=?,DEVICE_FIRMWARE_VERSION=?,DEVICE_RAM_DETAILS=?,DEVICE_FLASH_DETAILS=?,DEVICE_SW=? WHERE ip_address='" + router_ipadress + "'");
            stmt5.setString(1, var_device_name);
            stmt5.setString(2, var_device_mac);
            stmt5.setString(3, var_device_serial_no);
            stmt5.setString(4, var_device_manufacturer);
            stmt5.setString(5, var_odevice_model_name);
            stmt5.setString(6, var_device_hw_version);
            stmt5.setString(7, var_device_sw_version);
            stmt5.setString(8, var_device_firmware_version);
            stmt5.setString(9, var_device_ram_info);
            stmt5.setString(10, var_device_flash_info);
            stmt5.setString(11, var_sw);
            stmt5.executeUpdate();
            System.out.println("Update Basic info Success:" + router_ipadress);
        } catch (SQLException e) {
            System.out.println("Exception update basic info:" + e);
        } finally {
            if (stmt5 != null) {
                try {
                    stmt5.close();
                } catch (Exception e) {
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                }
            }
        }

    }

}
