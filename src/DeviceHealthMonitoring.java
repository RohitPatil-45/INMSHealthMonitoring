
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
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
public class DeviceHealthMonitoring implements Runnable {

    List list9 = null;

    public static String username = null;
    public static String authname = null;
    public static String authpass = null;
    public static String privname = null;
    public static String privpass = null;
    String mail_alert = "No";

    DeviceHealthMonitoring(List liste) {
        this.list9 = liste;

    }

    public void run() {

        while (true) {
            try {
                Thread.sleep(10000);
            } catch (Exception e) {
                System.err.println("Exceptionn: " + e.getMessage());
            }

            Iterator it7 = list9.iterator();
            while (it7.hasNext()) {
                try {
                    String device_ip = it7.next().toString().replaceAll(" ", "");
                    //  healthMonNokia(device_ip);
                    //     String device_type = SNMPHealth.cpuhashmap.get(device_ip).get(15);

//                    if (device_type.equals("Cisco")) {
//                        System.out.println("Cisco Device");
                    //  healthMonALphaBridge(device_ip);
                    healthMonCiscoFirewall(device_ip);

                    // healthMonMikrotik(device_ip);
//                    } else if (device_type.equals("FortiGate")) {
//                        System.out.println("FortiGate device");
                    // healthFortigate(device_ip);
//                    } else if (device_type.equals("Allied")) {
//                        System.out.println("Allied device");
//                        healthMonAllied(device_ip);
//                        //  healthMonFortiGate(device_ip);
//                    } else if (device_type.equals("Nokia")) {
//                        System.out.println("Allied device");
//                        healthMonNokia(device_ip);
//                        //  healthMonFortiGate(device_ip);
//                    } else {
//                        System.out.println("Other device");
//                    }
                } catch (Exception e) {
                    System.out.println("Exception:" + e);
                }

            }

        }

    }

    public void healthMonCiscoFirewall(String router_ipadress) {
        System.out.println("BESCOM Cisco Firewall Health Monitoring 7PM:" + router_ipadress);
        String snmp_version = "Version2";
        String community_string = "public";
        String health_history_param = "Yes";
        String cpu_percentage = "80";
        String ram_percentage = "80";

        try {
            cpu_percentage = NodeHealthMon.healthMonData.get(router_ipadress).getCPU_THRESHOLD();
        } catch (Exception e) {
            System.out.println("Exception:" + e);
        }

        try {
            ram_percentage = NodeHealthMon.healthMonData.get(router_ipadress).getMEMORY_THRESHOLD();
        } catch (Exception e) {
            System.out.println("Exception:" + e);
        }

        System.out.println("Device ip:" + router_ipadress);
        System.out.println(router_ipadress + ":CPU Percentage:" + cpu_percentage + ":Memory Percentage:" + ram_percentage);
//        
//        Cisco OID
//                 String oid_processor_used = "1.3.6.1.4.1.9.9.48.1.1.1.5.1";
//        String oid_processor_free = "1.3.6.1.4.1.9.9.48.1.1.1.6.1";
//        String oid_cpu = "1.3.6.1.4.1.9.9.109.1.1.1.1.4.1";
//        String oid_tem = "1.3.6.1.4.1.9.9.13.1.3.1.3.1";
//        String oid_memory_total = "1.3.6.1.4.1.9.9.48.1.1.1.5.2";
//        String oid_memory_percentage = "1.3.6.1.4.1.9.9.48.1.1.1.6.2";

        // Alpha brdige
        String oid_processor_used = "1.3.6.1.4.1.9.9.48.1.1.1.5.1"; //used
        String oid_processor_free = "1.3.6.1.4.1.9.9.48.1.1.1.6.1";  //Free
        String oid_cpu = "1.3.6.1.4.1.9.2.1.57.0";  //@

        String oid_tem = "1.3.6.1.4.1.9.9.13.1.3.1.3.1";  //@
//        String oid_memory_total = "1.3.6.1.4.1.9.9.48.1.1.1.5.2";
//        String oid_memory_percentage = "1.3.6.1.4.1.58158.9.48.1.0";   //@

        boolean checksnmp = false;
        String cpu_val = null;
        String temp_val = "";

        SNMPUtilHealth su = null;
        su = new SNMPUtilHealth();

        SNMPUtilVersion3 suversion3 = null;
        suversion3 = new SNMPUtilVersion3();

        try {
            // String cpu_util = "1.3.6.1.4.1.12356.101.4.1.3.0";
            OID cpuOID = null;
            cpuOID = new OID(oid_cpu);

            Target target1 = null;

            if (snmp_version.equals("Version3")) {
                //   System.out.println("in version 3");
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
                //target3 = su3.getTargetVersion3("udp:" + interface_router_ipadress + "/161", user_name,authentication,auth_pass,privacy,privacy_pass,SnmpConstants.version3);
            } else {
                System.out.println("in version 1 & 2");
//                if (snmp_version.equals("Version1")) {
//                    System.out.println("in version 1");
//                    su.start();
//                    target1 = su.getTarget("udp:" + router_ipadress + "/161", community_string, SnmpConstants.version1);
//                    cpu_val = su.BandwidthGetVect(target1, "Out", cpuOID);
////                            target3 = su3.getTarget("udp:" + interface_router_ipadress + "/161", community_string,SnmpConstants.version1);
//                } else {
                System.out.println("in SNMP version 2");
                su.start();
                if (NodeHealthMon.isSimulation) {
                    target1 = su.getTarget("udp:127.0.0.1/161", community_string, SnmpConstants.version2c);
                } else {
                    target1 = su.getTarget("udp:" + router_ipadress + "/161", community_string, SnmpConstants.version2c);
                }

                cpu_val = su.BandwidthGetVect(target1, "Out", cpuOID);
                //target3 = su3.getTarget("udp:" + interface_router_ipadress + "/161", community_string,SnmpConstants.version2c);
                //}

            }

        } catch (Exception e12) {
            System.out.println("CPU OID excep:" + e12);
            //   SNMPHealth.tipsize.add(router_ipadress);
            checksnmp = true;
        } finally {
            try {
//                if (suversion3 != null) {
//                    suversion3.stop();
//                }
                if (su != null) {
                    su.stop();
                }
//                        if (client5 != null) {
//                            client5.stop();
//                        }
//                        if (clientversion2 != null) {
//                            clientversion2.stop();
//                        }
            } catch (Exception ex2) {
                System.out.println("SNMP Close Exception....." + ex2);
            }

        }

        System.out.println(router_ipadress + ":CPU Utilization:" + cpu_val + ":" + oid_cpu);

        // System.out.println("size:" + SNMPHealth.tipsize.size());
        if (checksnmp == false) {
            //   System.out.println("SNMP Enable:"+router_ipadress);

            try {

                OID tempOID = null;
                tempOID = new OID(oid_tem);

                Target target1 = null;

                if (snmp_version.equals("Version3")) {
//                    username = user_name;
//                    authname = authentication;
//                    authpass = auth_pass;
//                    privname = privacy;
//                    privpass = privacy_pass;
//                    suversion3.start();
//                    try {
//                        target1 = suversion3.getTargetVersion3("udp:" + router_ipadress + "/161", user_name, authentication, auth_pass, privacy, privacy_pass, SnmpConstants.version3);
//                    } catch (Exception e) {
//                        e.printStackTrace();;
//                    }
//                    try {
//                        temp_val = suversion3.BandwidthGetVect(target1, "tempOID", tempOID);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
                } else {
//                    if (snmp_version.equals("Version1")) {
//                        su.start();
//                        target1 = su.getTarget("udp:" + router_ipadress + "/161", community_string, SnmpConstants.version1);
//                        temp_val = su.BandwidthGetVect(target1, "Out", tempOID);
////                            target3 = su3.getTarget("udp:" + interface_router_ipadress + "/161", community_string,SnmpConstants.version1);
//                    } else {
                    su.start();
                    if (NodeHealthMon.isSimulation) {
                        target1 = su.getTarget("udp:127.0.0.1/161", community_string, SnmpConstants.version2c);
                    } else {
                        target1 = su.getTarget("udp:" + router_ipadress + "/161", community_string, SnmpConstants.version2c);
                    }
                    temp_val = su.BandwidthGetVect(target1, "Out", tempOID);
//                        //target3 = su3.getTarget("udp:" + interface_router_ipadress + "/161", community_string,SnmpConstants.version2c);
//                    }
//temp_val="0";
                }

            } catch (Exception e12) {
                System.out.println("Temp OID excep:" + e12);
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
            System.out.println(router_ipadress + ":Temprature:" + temp_val + ":" + oid_tem);

            // update cpu
            if (temp_val == null || temp_val.equals("") || temp_val.equals("noSuchObject") || temp_val.equals("noSuchInstance") || cpu_val.equals("Null") || Integer.parseInt(cpu_val) > 100) {
                temp_val = "0";
            }
//            if (cpu_val == null || cpu_val.equals("") || cpu_val.equals("noSuchObject") || cpu_val.equals("noSuchInstance") || cpu_val.equals("Null") || Integer.parseInt(cpu_val) > 100) {
//            } else {
//                System.out.println(router_ipadress + ":Temprature:" + temp_val + ":" + oid_tem);
//                System.out.println("cpu history param:" + health_history_param);
//                //  System.out.println("update cpu222222");
//
//            }

            //Memory 
            String memory_param = "Yes";

            if (memory_param.equals("Yes")) {

                double processor_total_D = 0;
                double processor_used_D = 0;
                double processor_free_D = 0;
                double processor_percentage_D = 0;
                String processor_free_val = "";
                String processor_used_val = "";

                try {
                    OID processor_usedOID = null;
                    processor_usedOID = new OID(oid_processor_used);
                    Target target1 = null;
                    if (snmp_version.equals("Version3")) {
//                        username = user_name;
//                        authname = authentication;
//                        authpass = auth_pass;
//                        privname = privacy;
//                        privpass = privacy_pass;
//                        try {
//                            suversion3.start();
//                            target1 = suversion3.getTargetVersion3("udp:" + router_ipadress + "/161", user_name, authentication, auth_pass, privacy, privacy_pass, SnmpConstants.version3);
//                            processor_used_val = suversion3.BandwidthGetVect(target1, "cpuOID", processor_usedOID);
//                        } catch (Exception e) {
//                            System.out.println("Exception Memory Used:" + e);
//                        }
                    } else if (snmp_version.equals("Version1")) {
                        su.start();
                        target1 = su.getTarget("udp:" + router_ipadress + "/161", community_string, SnmpConstants.version1);
                        processor_used_val = su.BandwidthGetVect(target1, "Out", processor_usedOID);
                    } else {
                        su.start();
                        if (NodeHealthMon.isSimulation) {
                            target1 = su.getTarget("udp:127.0.0.1/161", community_string, SnmpConstants.version2c);
                        } else {
                            target1 = su.getTarget("udp:" + router_ipadress + "/161", community_string, SnmpConstants.version2c);
                        }
                        processor_used_val = su.BandwidthGetVect(target1, "Out", processor_usedOID);
                    }

                } catch (Exception e12) {
                    System.out.println("M Free ex:" + e12);
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

                System.out.println(router_ipadress + ":Memory Used:" + processor_used_val + ":" + oid_processor_used);

                if (processor_used_val.equals("noSuchObject") || processor_used_val.equals("Null") || processor_used_val.equals("noSuchInstance")) {
                } else {
                    try {

                        OID processor_freeOID = null;
                        processor_freeOID = new OID(oid_processor_free);

                        Target target1 = null;

                        if (snmp_version.equals("Version3")) {
//                            username = user_name;
//                            authname = authentication;
//                            authpass = auth_pass;
//                            privname = privacy;
//                            privpass = privacy_pass;
//                            suversion3.start();
//                            try {
//                                target1 = suversion3.getTargetVersion3("udp:" + router_ipadress + "/161", user_name, authentication, auth_pass, privacy, privacy_pass, SnmpConstants.version3);
//                            } catch (Exception e) {
//                                e.printStackTrace();;
//                            }
//                            try {
//                                processor_free_val = suversion3.BandwidthGetVect(target1, "cpuOID", processor_freeOID);
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
                            //target3 = su3.getTargetVersion3("udp:" + interface_router_ipadress + "/161", user_name,authentication,auth_pass,privacy,privacy_pass,SnmpConstants.version3);
                        } else if (snmp_version.equals("Version1")) {
                            su.start();
                            target1 = su.getTarget("udp:" + router_ipadress + "/161", community_string, SnmpConstants.version1);
                            processor_free_val = su.BandwidthGetVect(target1, "Out", processor_freeOID);
//                            target3 = su3.getTarget("udp:" + interface_router_ipadress + "/161", community_string,SnmpConstants.version1);
                        } else {
                            su.start();
                            if (NodeHealthMon.isSimulation) {
                                target1 = su.getTarget("udp:127.0.0.1/161", community_string, SnmpConstants.version2c);
                            } else {
                                target1 = su.getTarget("udp:" + router_ipadress + "/161", community_string, SnmpConstants.version2c);
                            }

                            processor_free_val = su.BandwidthGetVect(target1, "Out", processor_freeOID);

                            //target3 = su3.getTarget("udp:" + interface_router_ipadress + "/161", community_string,SnmpConstants.version2c);
                        }

                    } catch (Exception e12) {
                        System.out.println("M Used ex:" + e12);
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

                    System.out.println(router_ipadress + ":Memeory Free:" + processor_free_val + ":" + oid_processor_free);
//                       try {
//
//                        processor_used_D = Double.parseDouble(processor_used_val); //used memory
//                        processor_free_D = Double.parseDouble(processor_free_val);  //total memory
//                        
//                        processor_total_D = processor_used_D + processor_free_D;
//                        processor_percentage_D = (processor_used_D * 100) / processor_total_D;
//
//                        System.out.println("MemoryTotal:" + processor_total_D);
//                        System.out.println("Memory Used:" + processor_used_D);
//                        System.out.println("Memory Free:" + processor_free_D);
//                        System.out.println("Memory %   :" + processor_percentage_D);
//
//                    } catch (Exception exp) {
//                        System.out.println("Error in Memory Convert " + exp);
//                    }

                    try {

                        processor_used_D = Double.parseDouble(processor_used_val); //used memory
                        processor_total_D = Double.parseDouble(processor_free_val);  //total memory
                        processor_used_D = processor_used_D * 1024;
                        processor_total_D = processor_total_D * 1024;

                        processor_free_D = processor_total_D - processor_used_D;
                        processor_percentage_D = (processor_used_D * 100) / processor_total_D;

                        System.out.println("MemoryTotal:" + processor_total_D);
                        System.out.println("Memory Used:" + processor_used_D);
                        System.out.println("Memory Free:" + processor_free_D);
                        System.out.println("Memory %   :" + processor_percentage_D);

                    } catch (Exception exp) {
                        System.out.println("Error in Memory Convert " + exp);
                    }

                    try {
                        HealthMaster updateBm = null;
                        updateBm = new HealthMaster();
                        updateBm.total_processor_ram = processor_total_D;
                        updateBm.processor_free_ram = processor_free_D;
                        updateBm.processor_used_ram = processor_used_D;
                        updateBm.processor_percent_ram = String.valueOf(processor_percentage_D);
                        updateBm.router_ip = router_ipadress;
                        updateBm.temp_val = temp_val;
                        updateBm.cpu_val = cpu_val;
                        updateBm.datetime = new Timestamp(new java.util.Date().getTime());
                        NodeHealthMon.ram_list.add(updateBm);
                        System.out.println("Update Ram in Batch:" + NodeHealthMon.ram_list.size());
                    } catch (Exception expE) {
                        System.out.println("Exception in RAM list " + expE);
                    }

                    if (cpu_percentage == null) {
                        cpu_percentage = "80";
                    }
                    if (ram_percentage == null) {
                        ram_percentage = "80";
                    }

                    //CPU Threshold Monitoring Start
                    if (cpu_val == null || cpu_val.equals("") || cpu_val.equals("noSuchObject") || cpu_val.equals("noSuchInstance") || cpu_val.equals("Null") || Integer.parseInt(cpu_val) > 100) {

                    } else {

                        int real_cpu_util = 0;
                        int int_percentage_cpu = 0;
                        //double int_percentage_ram = 0;
                        try {

                            real_cpu_util = Integer.parseInt(cpu_val);
                            int_percentage_cpu = Integer.parseInt(cpu_percentage);
                            //   int_percentage_ram = Double.parseDouble(ram_percentage);

                        } catch (Exception exp) {
                            System.out.println("double excep " + exp);
                        }

                        String h_cpustatus = NodeHealthMon.staus_cpumap.get(router_ipadress).toString();
                        String eventMsg1 = "";
                        String netadminMsg = "";
                        String isAffected = "";
                        String problem = "";
                        String serviceId = "cpu_threshold";
                        //  System.out.println("ip@:" + router_ipadress + ":" + h_cpustatus);
                        if (real_cpu_util > int_percentage_cpu && h_cpustatus.equals("Low")) {
                            NodeHealthMon.staus_cpumap.put(router_ipadress, "High");
                            System.out.println(real_cpu_util + ":High CPU Usage#############:" + router_ipadress + mail_alert);
                            String cpu_status = "High";

                            try {
                                isAffected = "1";
                                problem = "problem";
                                eventMsg1 = "Alert: CPU threshold crossed above " + cpu_percentage + "% || " + router_ipadress;
                                netadminMsg = "CPU Threshold : High : " + cpu_val + " / CPU threshold value=" + cpu_percentage + " / CPU status = " + cpu_status + " / ip = " + router_ipadress;
                                insertIntoEventLog(router_ipadress, NodeHealthMon.healthMonData.get(router_ipadress).getDEVICE_NAME(), eventMsg1, 4, "CPU threshold", new Timestamp(System.currentTimeMillis()), netadminMsg, isAffected, problem, serviceId, NodeHealthMon.healthMonData.get(router_ipadress).getDEVICE_TYPE());
                                CPUThresholdAlert(router_ipadress, cpu_status, cpu_percentage, cpu_val);
                            } catch (Exception e) {
                                System.out.println("Exception :" + e);
                            }

                        } else if (real_cpu_util < int_percentage_cpu && h_cpustatus.equals("High")) {
                            System.out.println(real_cpu_util + "##########Normalcpu:" + router_ipadress);
                            NodeHealthMon.staus_cpumap.put(router_ipadress, "Low");
                            String cpu_status = "Low";
                            try {
                                isAffected = "0";
                                problem = "Cleared";
                                eventMsg1 = "Alert: CPU threshold Low : " + cpu_percentage + "% || " + router_ipadress;
                                netadminMsg = "CPU Threshold : Low : " + cpu_val + " / CPU threshold value=" + cpu_percentage + " / CPU status = " + cpu_status + " / ip = " + router_ipadress;
                                insertIntoEventLog(router_ipadress, NodeHealthMon.healthMonData.get(router_ipadress).getDEVICE_NAME(), eventMsg1, 0, "CPU threshold", new Timestamp(System.currentTimeMillis()), netadminMsg, isAffected, problem, serviceId, NodeHealthMon.healthMonData.get(router_ipadress).getDEVICE_TYPE());
                                CPUThresholdAlert(router_ipadress, cpu_status, cpu_percentage, cpu_val);
                            } catch (Exception e) {
                                System.out.println("Exception :" + e);
                            }
                        }

                    }

                    //CPU Threshold Monitoring End
                    //Memrory Threshold
                    double int_percentage_ram = 0;
                    try {
                        int_percentage_ram = Double.parseDouble(ram_percentage);
                    } catch (Exception exp) {
                        System.out.println("double excep " + exp);
                    }

                    //Check RAM Threshold Start
                    String h_ramstatus = NodeHealthMon.staus_rammap.get(router_ipadress).toString();
                    try {
                        String eventMsg1 = "";
                        String netadminMsg = "";
                        String isAffected = "";
                        String problem = "";
                        String serviceId = "memory_threshold";
                        if (processor_percentage_D > int_percentage_ram && h_ramstatus.equals("Low")) {

                            NodeHealthMon.staus_rammap.put(router_ipadress, "High");
                            System.out.println("HIgh RAM###############");
                            //String ram_status = "High RAM Usage";
                            String ram_status = "High";
                            try {
                                isAffected = "1";
                                problem = "problem";
                                eventMsg1 = "Alert: Memory threshold crossed above " + ram_percentage + "% || " + router_ipadress;
                                netadminMsg = "Memory Threshold : High : " + processor_percentage_D + " / Memory threshold value = " + ram_percentage + " / Memory status = " + ram_status + " / ip = " + router_ipadress;
                                insertIntoEventLog(router_ipadress, NodeHealthMon.healthMonData.get(router_ipadress).getDEVICE_NAME(), eventMsg1, 4, "Memory threshold", new Timestamp(System.currentTimeMillis()), netadminMsg, isAffected, problem, serviceId, NodeHealthMon.healthMonData.get(router_ipadress).getDEVICE_TYPE());
                                MemoryThresholdAlert(router_ipadress, ram_status, ram_percentage, String.valueOf(processor_percentage_D));
                            } catch (Exception e) {
                                System.out.println("Exception:" + e);
                            }
                        } else if (processor_percentage_D < int_percentage_ram && h_ramstatus.equals("High")) {
                            //  System.out.println(real_cpu_util + "##########Normal:" + router_ipadress);
                            System.out.println("LOW RAM ##################");
                            NodeHealthMon.staus_rammap.put(router_ipadress, "Low");
                            String ram_status = "Low";
//                                   msgBody1 = "Project Name: NMS\nNode IP:" + device_ip + " has crossed the latency threshold above " + latency_threshold + "ms \nCurrent Latency:" + avg_responce + "ms \nDate:" + logDateTime;
//                msgFormat = "Dear Sir/Madam,\r \nPlease Check For\r \n" + msgBody1 + "\r \nKindly take appropriate action.\r \nThanks And Regards,\r \nCanaris Team";
//                mail_sub_msg = "Alert: Latency threshold crossed above " + latency_threshold + "ms || " + device_ip;
//                
//                log.sendMailAlert(device_ip, logDateTime, msgFormat, mail_sub_msg,"Latency_Threshold", "Normal");
                            try {
                                isAffected = "0";
                                problem = "Cleared";
                                eventMsg1 = "Alert: Memory threshold Low " + ram_percentage + "% || " + router_ipadress;
                                netadminMsg = "Memory Threshold : Low : " + processor_percentage_D + " / Memory threshold value = " + ram_percentage + " / Memory status = " + ram_status + " / ip = " + router_ipadress;
                                insertIntoEventLog(router_ipadress, NodeHealthMon.healthMonData.get(router_ipadress).getDEVICE_NAME(), eventMsg1, 4, "Memory threshold", new Timestamp(System.currentTimeMillis()), netadminMsg, isAffected, problem, serviceId, NodeHealthMon.healthMonData.get(router_ipadress).getDEVICE_TYPE());
                                MemoryThresholdAlert(router_ipadress, ram_status, ram_percentage, String.valueOf(processor_percentage_D));
                            } catch (Exception e) {
                                System.out.println("Exception:" + e);
                            }
                        }

                    } catch (Exception exp) {
                        System.out.println("double excep " + exp);
                    }
                    //Check RAM Threshold End

                }

            }

        }
    }

    public static void CPUThresholdAlert(String device_ip, String cpu_status, String cpu_percentage, String cpu_val) {
        System.out.println("Inside CPUThresholdAlert");
        Connection cpu_con = null;
        PreparedStatement preparedStatement2 = null;
        Timestamp logtime = new Timestamp(new java.util.Date().getTime());
        try {
            cpu_con = Datasource55.getConnection();
            preparedStatement2 = cpu_con.prepareStatement("INSERT INTO cpu_threshold_history(NODE_IP,CPU_STATUS,CPU_THRESHOLD,CPU_UTILIZATION,EVENT_TIMESTAMP) VALUES (?,?,?,?,?)");
            preparedStatement2.setString(1, device_ip);
            preparedStatement2.setString(2, cpu_status);
            preparedStatement2.setString(3, cpu_percentage);
            preparedStatement2.setString(4, cpu_val);
            preparedStatement2.setTimestamp(5, logtime);
            preparedStatement2.executeUpdate();

        } catch (Exception e) {
            System.out.println("sql error3 : " + e);
        } finally {
            try {

                if (preparedStatement2 != null) {
                    preparedStatement2.close();
                }
                if (cpu_con != null) {
                    cpu_con.close();
                }
            } catch (Exception exp) {
                System.out.println("insert cpu log exp:" + exp);
            }
        }

        //Update CPU Alert
        Connection cpuAlert_con = null;
        PreparedStatement preparedStmt_cpualert = null;
        try {
            cpuAlert_con = Datasource55.getConnection();
            preparedStmt_cpualert = cpuAlert_con.prepareStatement("UPDATE node_health_monitoring SET CPU_UTILIZATION=?,CPU_STATUS=?,CPU_UTILIZATION_Generated_Time=?,"
                    + "CPU_UTILIZATION_Cleared_Time=? WHERE NODE_IP=? ");
            preparedStmt_cpualert.setString(1, cpu_val);
            preparedStmt_cpualert.setString(2, cpu_status);
            preparedStmt_cpualert.setTimestamp(3, cpu_status.equalsIgnoreCase("High") ? logtime : null);
            preparedStmt_cpualert.setTimestamp(4, cpu_status.equalsIgnoreCase("Low") ? logtime : null);
            preparedStmt_cpualert.setString(5, device_ip);
            preparedStmt_cpualert.executeUpdate();
        } catch (Exception e) {
            System.out.println("errorrrrrrrrrrrrrrrrrrrrrrrrrrrrr@@@@@@@@@" + e);
        } finally {
            try {

                if (preparedStmt_cpualert != null) {
                    preparedStmt_cpualert.close();
                }
                if (cpuAlert_con != null) {
                    cpuAlert_con.close();
                }
            } catch (Exception exp) {
                System.out.println("insert cpu log exp:" + exp);
            }
        }

    }

    public static void MemoryThresholdAlert(String device_ip, String memory_status, String memory_percentage, String memory_val) {
        System.out.println("Inside MemoryThresholdAlert");
        Connection cpu_con = null;
        PreparedStatement preparedStatement2 = null;
        Timestamp logtime = new Timestamp(new java.util.Date().getTime());
        try {
            cpu_con = Datasource55.getConnection();
            preparedStatement2 = cpu_con.prepareStatement("INSERT INTO memory_threshold_history(NODE_IP,MEMORY_STATUS,MEMORY_THRESHOLD,MEMORY_UTILIZATION,EVENT_TIMESTAMP) VALUES (?,?,?,?,?)");
            preparedStatement2.setString(1, device_ip);
            preparedStatement2.setString(2, memory_status);
            preparedStatement2.setString(3, memory_percentage);
            preparedStatement2.setString(4, memory_val);
            preparedStatement2.setTimestamp(5, logtime);
            preparedStatement2.executeUpdate();

        } catch (Exception e) {
            System.out.println("sql error3 : " + e);
        } finally {
            try {

                if (preparedStatement2 != null) {
                    preparedStatement2.close();
                }
                if (cpu_con != null) {
                    cpu_con.close();
                }
            } catch (Exception exp) {
                System.out.println("insert cpu log exp:" + exp);
            }
        }

        //Update CPU Alert
        Connection cpuAlert_con = null;
        PreparedStatement preparedStmt_cpualert = null;
        try {
            cpuAlert_con = Datasource55.getConnection();
            preparedStmt_cpualert = cpuAlert_con.prepareStatement("UPDATE node_health_monitoring SET MEMORY_UTILIZATION=?,MEMORY_STATUS=?,"
                    + "MEMORY_UTILIZATION_Generated_Time=?,MEMORY_UTILIZATION_Cleared_Time=? WHERE  NODE_IP=? ");
            preparedStmt_cpualert.setString(1, memory_val);
            preparedStmt_cpualert.setString(2, memory_status);
            preparedStmt_cpualert.setTimestamp(3, memory_status.equalsIgnoreCase("High") ? logtime : null);
            preparedStmt_cpualert.setTimestamp(4, memory_status.equalsIgnoreCase("Low") ? logtime : null);
            preparedStmt_cpualert.setString(5, device_ip);
            preparedStmt_cpualert.executeUpdate();
        } catch (Exception e) {
            System.out.println("errorrrrrrrrrrrrrrrrrrrrrrrrrrrrr@@@@@@@@@" + e);
        } finally {
            try {

                if (preparedStmt_cpualert != null) {
                    preparedStmt_cpualert.close();
                }
                if (cpuAlert_con != null) {
                    cpuAlert_con.close();
                }
            } catch (Exception exp) {
                System.out.println("insert cpu log exp:" + exp);
            }
        }

    }

    public void sendMailAlert(String deviceIP, Timestamp eventTime, String msgFormat, String mailSubject, String alertType, String severity) {
        System.out.println("Mail Send:......" + deviceIP);

        PreparedStatement preparedStatement = null;
        Connection connection_mail = null;
        try {
            connection_mail = Datasource55.getConnection();
            String sql = "INSERT INTO ALERTS_JOB (NODE_IP,ALERT_TYPE,MAIL_SUBJECT,MAIL_FORMAT,EVENT_TIMESTAMP,SEVERITY,SEND_FLAG,CUSTOMER) VALUES (?,?,?,?,?,?,?,?)";
            preparedStatement = connection_mail.prepareStatement(sql);
            preparedStatement.setString(1, deviceIP);
            preparedStatement.setString(2, alertType);
            preparedStatement.setString(3, mailSubject);
            preparedStatement.setString(4, msgFormat);
            preparedStatement.setTimestamp(5, eventTime);
            preparedStatement.setString(6, severity);
            preparedStatement.setString(7, "false");
            preparedStatement.setString(8, "NA");
            preparedStatement.executeUpdate();

            System.out.println("Insert mail Record success : ");
        } catch (Exception exp) {
            System.out.println("Exception In Batch Insert " + exp);
        } finally {
            try {
                if (connection_mail != null) {
                    connection_mail.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }

            } catch (Exception ep) {
            }
        }

    }

    public void healthMonMikrotik(String router_ipadress) {
        System.out.println("healthMonMikrotik:" + router_ipadress);
        String snmp_version = "Version2";
        String community_string = "publictest";
        String health_history_param = "Yes";
        String cpu_percentage = "80";
        String ram_percentage = "80";

        try {
            cpu_percentage = NodeHealthMon.healthMonData.get(router_ipadress).getCPU_THRESHOLD();
        } catch (Exception e) {
            System.out.println("Exception:" + e);
        }

        try {
            ram_percentage = NodeHealthMon.healthMonData.get(router_ipadress).getMEMORY_THRESHOLD();
        } catch (Exception e) {
            System.out.println("Exception:" + e);
        }

        System.out.println("Device ip:" + router_ipadress);
        System.out.println(router_ipadress + ":CPU Percentage:" + cpu_percentage + ":Memory Percentage:" + ram_percentage);
//        
//        Cisco OID
//                 String oid_processor_used = "1.3.6.1.4.1.9.9.48.1.1.1.5.1";
//        String oid_processor_free = "1.3.6.1.4.1.9.9.48.1.1.1.6.1";
//        String oid_cpu = "1.3.6.1.4.1.9.9.109.1.1.1.1.4.1";
//        String oid_tem = "1.3.6.1.4.1.9.9.13.1.3.1.3.1";
//        String oid_memory_total = "1.3.6.1.4.1.9.9.48.1.1.1.5.2";
//        String oid_memory_percentage = "1.3.6.1.4.1.9.9.48.1.1.1.6.2";

        // Alpha brdige
        String oid_processor_used = "1.3.6.1.2.1.25.2.3.1.6.65536"; //used
        String oid_processor_free = "1.3.6.1.2.1.25.2.3.1.5.65536";  //total
        String oid_cpu = "1.3.6.1.2.1.25.3.3.1.2.1";  //@

        String oid_tem = "1.3.6.1.4.1.58158.9.181.5.1.4.1.0.1";  //@
//        String oid_memory_total = "1.3.6.1.4.1.9.9.48.1.1.1.5.2";
//        String oid_memory_percentage = "1.3.6.1.4.1.58158.9.48.1.0";   //@

        boolean checksnmp = false;
        String cpu_val = null;
        String temp_val = "";

        SNMPUtilHealth su = null;
        su = new SNMPUtilHealth();

        SNMPUtilVersion3 suversion3 = null;
        suversion3 = new SNMPUtilVersion3();

        try {
            // String cpu_util = "1.3.6.1.4.1.12356.101.4.1.3.0";
            OID cpuOID = null;
            cpuOID = new OID(oid_cpu);

            Target target1 = null;

            if (snmp_version.equals("Version3")) {
                //   System.out.println("in version 3");
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
                //target3 = su3.getTargetVersion3("udp:" + interface_router_ipadress + "/161", user_name,authentication,auth_pass,privacy,privacy_pass,SnmpConstants.version3);
            } else {
                System.out.println("in version 1 & 2");
//                if (snmp_version.equals("Version1")) {
//                    System.out.println("in version 1");
//                    su.start();
//                    target1 = su.getTarget("udp:" + router_ipadress + "/161", community_string, SnmpConstants.version1);
//                    cpu_val = su.BandwidthGetVect(target1, "Out", cpuOID);
////                            target3 = su3.getTarget("udp:" + interface_router_ipadress + "/161", community_string,SnmpConstants.version1);
//                } else {
                System.out.println("in SNMP version 2");
                su.start();
                target1 = su.getTarget("udp:" + router_ipadress + "/161", community_string, SnmpConstants.version2c);
                cpu_val = su.BandwidthGetVect(target1, "Out", cpuOID);
                //target3 = su3.getTarget("udp:" + interface_router_ipadress + "/161", community_string,SnmpConstants.version2c);
                //}

            }

        } catch (Exception e12) {
            System.out.println("CPU OID excep:" + e12);
            //   SNMPHealth.tipsize.add(router_ipadress);
            checksnmp = true;
        } finally {
            try {
//                if (suversion3 != null) {
//                    suversion3.stop();
//                }
                if (su != null) {
                    su.stop();
                }
//                        if (client5 != null) {
//                            client5.stop();
//                        }
//                        if (clientversion2 != null) {
//                            clientversion2.stop();
//                        }
            } catch (Exception ex2) {
                System.out.println("SNMP Close Exception....." + ex2);
            }

        }

        System.out.println(router_ipadress + ":CPU Utilization:" + cpu_val + ":" + oid_cpu);

        // System.out.println("size:" + SNMPHealth.tipsize.size());
        if (checksnmp == false) {
            //   System.out.println("SNMP Enable:"+router_ipadress);

            try {

                OID tempOID = null;
                tempOID = new OID(oid_tem);

                Target target1 = null;

                if (snmp_version.equals("Version3")) {
//                    username = user_name;
//                    authname = authentication;
//                    authpass = auth_pass;
//                    privname = privacy;
//                    privpass = privacy_pass;
//                    suversion3.start();
//                    try {
//                        target1 = suversion3.getTargetVersion3("udp:" + router_ipadress + "/161", user_name, authentication, auth_pass, privacy, privacy_pass, SnmpConstants.version3);
//                    } catch (Exception e) {
//                        e.printStackTrace();;
//                    }
//                    try {
//                        temp_val = suversion3.BandwidthGetVect(target1, "tempOID", tempOID);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
                } else {
//                    if (snmp_version.equals("Version1")) {
//                        su.start();
//                        target1 = su.getTarget("udp:" + router_ipadress + "/161", community_string, SnmpConstants.version1);
//                        temp_val = su.BandwidthGetVect(target1, "Out", tempOID);
////                            target3 = su3.getTarget("udp:" + interface_router_ipadress + "/161", community_string,SnmpConstants.version1);
//                    } else {
//                        su.start();
//                        target1 = su.getTarget("udp:" + router_ipadress + "/161", community_string, SnmpConstants.version2c);
//                        temp_val = su.BandwidthGetVect(target1, "Out", tempOID);
//                        //target3 = su3.getTarget("udp:" + interface_router_ipadress + "/161", community_string,SnmpConstants.version2c);
//                    }
                    temp_val = "0";
                }

            } catch (Exception e12) {
                System.out.println("Temp OID excep:" + e12);
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
            System.out.println(router_ipadress + ":Temprature:" + temp_val + ":" + oid_tem);

            // update cpu
            if (temp_val == null || temp_val.equals("") || temp_val.equals("noSuchObject") || temp_val.equals("noSuchInstance") || cpu_val.equals("Null") || Integer.parseInt(cpu_val) > 100) {
                temp_val = "0";
            }
//            if (cpu_val == null || cpu_val.equals("") || cpu_val.equals("noSuchObject") || cpu_val.equals("noSuchInstance") || cpu_val.equals("Null") || Integer.parseInt(cpu_val) > 100) {
//            } else {
//                System.out.println(router_ipadress + ":Temprature:" + temp_val + ":" + oid_tem);
//                System.out.println("cpu history param:" + health_history_param);
//                //  System.out.println("update cpu222222");
//
//            }

            //Memory 
            String memory_param = "Yes";

            if (memory_param.equals("Yes")) {

                double processor_total_D = 0;
                double processor_used_D = 0;
                double processor_free_D = 0;
                double processor_percentage_D = 0;
                String processor_free_val = "";
                String processor_used_val = "";

                try {
                    OID processor_usedOID = null;
                    processor_usedOID = new OID(oid_processor_used);
                    Target target1 = null;
                    if (snmp_version.equals("Version3")) {
//                        username = user_name;
//                        authname = authentication;
//                        authpass = auth_pass;
//                        privname = privacy;
//                        privpass = privacy_pass;
//                        try {
//                            suversion3.start();
//                            target1 = suversion3.getTargetVersion3("udp:" + router_ipadress + "/161", user_name, authentication, auth_pass, privacy, privacy_pass, SnmpConstants.version3);
//                            processor_used_val = suversion3.BandwidthGetVect(target1, "cpuOID", processor_usedOID);
//                        } catch (Exception e) {
//                            System.out.println("Exception Memory Used:" + e);
//                        }
                    } else if (snmp_version.equals("Version1")) {
                        su.start();
                        target1 = su.getTarget("udp:" + router_ipadress + "/161", community_string, SnmpConstants.version1);
                        processor_used_val = su.BandwidthGetVect(target1, "Out", processor_usedOID);
                    } else {
                        su.start();
                        target1 = su.getTarget("udp:" + router_ipadress + "/161", community_string, SnmpConstants.version2c);
                        processor_used_val = su.BandwidthGetVect(target1, "Out", processor_usedOID);
                    }

                } catch (Exception e12) {
                    System.out.println("M Free ex:" + e12);
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

                System.out.println(router_ipadress + ":Memory Used:" + processor_used_val + ":" + oid_processor_used);

                if (processor_used_val.equals("noSuchObject") || processor_used_val.equals("Null") || processor_used_val.equals("noSuchInstance")) {
                } else {
                    try {

                        OID processor_freeOID = null;
                        processor_freeOID = new OID(oid_processor_free);

                        Target target1 = null;

                        if (snmp_version.equals("Version3")) {
//                            username = user_name;
//                            authname = authentication;
//                            authpass = auth_pass;
//                            privname = privacy;
//                            privpass = privacy_pass;
//                            suversion3.start();
//                            try {
//                                target1 = suversion3.getTargetVersion3("udp:" + router_ipadress + "/161", user_name, authentication, auth_pass, privacy, privacy_pass, SnmpConstants.version3);
//                            } catch (Exception e) {
//                                e.printStackTrace();;
//                            }
//                            try {
//                                processor_free_val = suversion3.BandwidthGetVect(target1, "cpuOID", processor_freeOID);
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
                            //target3 = su3.getTargetVersion3("udp:" + interface_router_ipadress + "/161", user_name,authentication,auth_pass,privacy,privacy_pass,SnmpConstants.version3);
                        } else if (snmp_version.equals("Version1")) {
                            su.start();
                            target1 = su.getTarget("udp:" + router_ipadress + "/161", community_string, SnmpConstants.version1);
                            processor_free_val = su.BandwidthGetVect(target1, "Out", processor_freeOID);
//                            target3 = su3.getTarget("udp:" + interface_router_ipadress + "/161", community_string,SnmpConstants.version1);
                        } else {
                            su.start();
                            target1 = su.getTarget("udp:" + router_ipadress + "/161", community_string, SnmpConstants.version2c);
                            processor_free_val = su.BandwidthGetVect(target1, "Out", processor_freeOID);
                            //target3 = su3.getTarget("udp:" + interface_router_ipadress + "/161", community_string,SnmpConstants.version2c);
                        }

                    } catch (Exception e12) {
                        System.out.println("M Used ex:" + e12);
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

                    System.out.println(router_ipadress + ":Memeory Free:" + processor_free_val + ":" + oid_processor_free);
//                       try {
//
//                        processor_used_D = Double.parseDouble(processor_used_val); //used memory
//                        processor_free_D = Double.parseDouble(processor_free_val);  //total memory
//                        
//                        processor_total_D = processor_used_D + processor_free_D;
//                        processor_percentage_D = (processor_used_D * 100) / processor_total_D;
//
//                        System.out.println("MemoryTotal:" + processor_total_D);
//                        System.out.println("Memory Used:" + processor_used_D);
//                        System.out.println("Memory Free:" + processor_free_D);
//                        System.out.println("Memory %   :" + processor_percentage_D);
//
//                    } catch (Exception exp) {
//                        System.out.println("Error in Memory Convert " + exp);
//                    }

                    try {

                        processor_used_D = Double.parseDouble(processor_used_val); //used memory
                        processor_total_D = Double.parseDouble(processor_free_val);  //total memory
                        processor_used_D = processor_used_D * 1024;
                        processor_total_D = processor_total_D * 1024;

                        processor_free_D = processor_total_D - processor_used_D;
                        processor_percentage_D = (processor_used_D * 100) / processor_total_D;

                        System.out.println("MemoryTotal:" + processor_total_D);
                        System.out.println("Memory Used:" + processor_used_D);
                        System.out.println("Memory Free:" + processor_free_D);
                        System.out.println("Memory %   :" + processor_percentage_D);

                    } catch (Exception exp) {
                        System.out.println("Error in Memory Convert " + exp);
                    }

                    try {
                        HealthMaster updateBm = null;
                        updateBm = new HealthMaster();
                        updateBm.total_processor_ram = processor_total_D;
                        updateBm.processor_free_ram = processor_free_D;
                        updateBm.processor_used_ram = processor_used_D;
                        updateBm.processor_percent_ram = String.valueOf(processor_percentage_D);
                        updateBm.router_ip = router_ipadress;
                        updateBm.temp_val = temp_val;
                        updateBm.cpu_val = cpu_val;
                        updateBm.datetime = new Timestamp(new java.util.Date().getTime());
                        NodeHealthMon.ram_list.add(updateBm);
                        System.out.println("Update Ram in Batch:" + NodeHealthMon.ram_list.size());
                    } catch (Exception expE) {
                        System.out.println("Exception in RAM list " + expE);
                    }

                    if (cpu_percentage == null) {
                        cpu_percentage = "80";
                    }
                    if (ram_percentage == null) {
                        ram_percentage = "80";
                    }

                    //CPU Threshold Monitoring Start
                    if (cpu_val == null || cpu_val.equals("") || cpu_val.equals("noSuchObject") || cpu_val.equals("noSuchInstance") || cpu_val.equals("Null") || Integer.parseInt(cpu_val) > 100) {

                    } else {

                        int real_cpu_util = 0;
                        int int_percentage_cpu = 0;
                        //double int_percentage_ram = 0;
                        try {

                            real_cpu_util = Integer.parseInt(cpu_val);
                            int_percentage_cpu = Integer.parseInt(cpu_percentage);
                            //   int_percentage_ram = Double.parseDouble(ram_percentage);

                        } catch (Exception exp) {
                            System.out.println("double excep " + exp);
                        }

                        String h_cpustatus = NodeHealthMon.staus_cpumap.get(router_ipadress).toString();
                        //  System.out.println("ip@:" + router_ipadress + ":" + h_cpustatus);
                        if (real_cpu_util > int_percentage_cpu && h_cpustatus.equals("Low")) {
                            NodeHealthMon.staus_cpumap.put(router_ipadress, "High");
                            System.out.println(real_cpu_util + ":High CPU Usage#############:" + router_ipadress + mail_alert);
                            String cpu_status = "High";
                            try {
                                CPUThresholdAlert(router_ipadress, cpu_status, cpu_percentage, cpu_val);
                            } catch (Exception e) {
                                System.out.println("Exception :" + e);
                            }

                        } else if (real_cpu_util < int_percentage_cpu && h_cpustatus.equals("High")) {
                            System.out.println(real_cpu_util + "##########Normalcpu:" + router_ipadress);
                            NodeHealthMon.staus_cpumap.put(router_ipadress, "Low");
                            String cpu_status = "Low";
                            try {
                                CPUThresholdAlert(router_ipadress, cpu_status, cpu_percentage, cpu_val);
                            } catch (Exception e) {
                                System.out.println("Exception :" + e);
                            }
                        }

                    }

                    //CPU Threshold Monitoring End
                    //Memrory Threshold
                    double int_percentage_ram = 0;
                    try {
                        int_percentage_ram = Double.parseDouble(ram_percentage);
                    } catch (Exception exp) {
                        System.out.println("double excep " + exp);
                    }

                    //Check RAM Threshold Start
                    String h_ramstatus = NodeHealthMon.staus_rammap.get(router_ipadress).toString();
                    try {
                        if (processor_percentage_D > int_percentage_ram && h_ramstatus.equals("Low")) {

                            NodeHealthMon.staus_rammap.put(router_ipadress, "High");
                            System.out.println("HIgh RAM###############");
                            //String ram_status = "High RAM Usage";
                            String ram_status = "High";
                            try {
                                MemoryThresholdAlert(router_ipadress, ram_status, ram_percentage, String.valueOf(processor_percentage_D));
                            } catch (Exception e) {
                                System.out.println("Exception:" + e);
                            }
                        } else if (processor_percentage_D < int_percentage_ram && h_ramstatus.equals("High")) {
                            //  System.out.println(real_cpu_util + "##########Normal:" + router_ipadress);
                            System.out.println("LOW RAM ##################");
                            NodeHealthMon.staus_rammap.put(router_ipadress, "Low");
                            String ram_status = "Low";
//                                   msgBody1 = "Project Name: NMS\nNode IP:" + device_ip + " has crossed the latency threshold above " + latency_threshold + "ms \nCurrent Latency:" + avg_responce + "ms \nDate:" + logDateTime;
//                msgFormat = "Dear Sir/Madam,\r \nPlease Check For\r \n" + msgBody1 + "\r \nKindly take appropriate action.\r \nThanks And Regards,\r \nCanaris Team";
//                mail_sub_msg = "Alert: Latency threshold crossed above " + latency_threshold + "ms || " + device_ip;
//                
//                log.sendMailAlert(device_ip, logDateTime, msgFormat, mail_sub_msg,"Latency_Threshold", "Normal");
                            try {
                                MemoryThresholdAlert(router_ipadress, ram_status, ram_percentage, String.valueOf(processor_percentage_D));
                            } catch (Exception e) {
                                System.out.println("Exception:" + e);
                            }
                        }

                    } catch (Exception exp) {
                        System.out.println("double excep " + exp);
                    }
                    //Check RAM Threshold End

                }

            }

        }
    }

    public void healthMonALphaBridge(String router_ipadress) {

        String snmp_version = "Version2";
        String community_string = "public";
        String health_history_param = "Yes";
        String cpu_percentage = "80";
        String ram_percentage = "80";

        try {
            cpu_percentage = NodeHealthMon.healthMonData.get(router_ipadress).getCPU_THRESHOLD();
        } catch (Exception e) {
            System.out.println("Exception:" + e);
        }

        try {
            ram_percentage = NodeHealthMon.healthMonData.get(router_ipadress).getMEMORY_THRESHOLD();
        } catch (Exception e) {
            System.out.println("Exception:" + e);
        }

        System.out.println("Device ip:" + router_ipadress);
        System.out.println(router_ipadress + ":CPU Percentage:" + cpu_percentage + ":Memory Percentage:" + ram_percentage);
//        
//        Cisco OID
//                 String oid_processor_used = "1.3.6.1.4.1.9.9.48.1.1.1.5.1";
//        String oid_processor_free = "1.3.6.1.4.1.9.9.48.1.1.1.6.1";
//        String oid_cpu = "1.3.6.1.4.1.9.9.109.1.1.1.1.4.1";
//        String oid_tem = "1.3.6.1.4.1.9.9.13.1.3.1.3.1";
//        String oid_memory_total = "1.3.6.1.4.1.9.9.48.1.1.1.5.2";
//        String oid_memory_percentage = "1.3.6.1.4.1.9.9.48.1.1.1.6.2";

        // Alpha brdige
        //  String oid_processor_used = "1.3.6.1.4.1.9.9.48.1.1.1.5.1";
        //  String oid_processor_free = "1.3.6.1.4.1.9.9.48.1.1.1.6.1";
        String oid_cpu = "1.3.6.1.4.1.58158.9.109.1.1.1.1.4.1";  //@
        String oid_tem = "1.3.6.1.4.1.58158.9.181.5.1.4.1.0.1";  //@
        String oid_memory_total = "1.3.6.1.4.1.58158.9.48.9.0";  //@
        String oid_memory_percentage = "1.3.6.1.4.1.58158.9.48.1.0";   //@

        boolean checksnmp = false;
        String cpu_val = null;
        String temp_val = "";

        SNMPUtilHealth su = null;
        su = new SNMPUtilHealth();

        SNMPUtilVersion3 suversion3 = null;
        suversion3 = new SNMPUtilVersion3();

        try {
            // String cpu_util = "1.3.6.1.4.1.12356.101.4.1.3.0";
            OID cpuOID = null;
            cpuOID = new OID(oid_cpu);

            Target target1 = null;

            if (snmp_version.equals("Version3")) {
                //   System.out.println("in version 3");
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
                //target3 = su3.getTargetVersion3("udp:" + interface_router_ipadress + "/161", user_name,authentication,auth_pass,privacy,privacy_pass,SnmpConstants.version3);
            } else {
                System.out.println("in version 1 & 2");
                if (snmp_version.equals("Version1")) {
                    System.out.println("in version 1");
                    su.start();
                    target1 = su.getTarget("udp:" + router_ipadress + "/161", community_string, SnmpConstants.version1);
                    cpu_val = su.BandwidthGetVect(target1, "Out", cpuOID);
//                            target3 = su3.getTarget("udp:" + interface_router_ipadress + "/161", community_string,SnmpConstants.version1);
                } else {
                    System.out.println("in SNMP version 2");
                    su.start();
                    target1 = su.getTarget("udp:" + router_ipadress + "/161", community_string, SnmpConstants.version2c);
                    cpu_val = su.BandwidthGetVect(target1, "Out", cpuOID);
                    //target3 = su3.getTarget("udp:" + interface_router_ipadress + "/161", community_string,SnmpConstants.version2c);
                }

            }

        } catch (Exception e12) {
            System.out.println("CPU OID excep:" + e12);
            //   SNMPHealth.tipsize.add(router_ipadress);
            checksnmp = true;
        } finally {
            try {
                if (suversion3 != null) {
                    suversion3.stop();
                }
                if (su != null) {
                    su.stop();
                }
//                        if (client5 != null) {
//                            client5.stop();
//                        }
//                        if (clientversion2 != null) {
//                            clientversion2.stop();
//                        }
            } catch (Exception ex2) {
                System.out.println("SNMP Close Exception....." + ex2);
            }

        }

        System.out.println(router_ipadress + ":CPU Utilization:" + cpu_val + ":" + oid_cpu);

        // System.out.println("size:" + SNMPHealth.tipsize.size());
        if (checksnmp == false) {
            //   System.out.println("SNMP Enable:"+router_ipadress);

            try {

                OID tempOID = null;
                tempOID = new OID(oid_tem);

                Target target1 = null;

                if (snmp_version.equals("Version3")) {
//                    username = user_name;
//                    authname = authentication;
//                    authpass = auth_pass;
//                    privname = privacy;
//                    privpass = privacy_pass;
//                    suversion3.start();
//                    try {
//                        target1 = suversion3.getTargetVersion3("udp:" + router_ipadress + "/161", user_name, authentication, auth_pass, privacy, privacy_pass, SnmpConstants.version3);
//                    } catch (Exception e) {
//                        e.printStackTrace();;
//                    }
//                    try {
//                        temp_val = suversion3.BandwidthGetVect(target1, "tempOID", tempOID);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
                } else if (snmp_version.equals("Version1")) {
                    su.start();
                    target1 = su.getTarget("udp:" + router_ipadress + "/161", community_string, SnmpConstants.version1);
                    temp_val = su.BandwidthGetVect(target1, "Out", tempOID);
//                            target3 = su3.getTarget("udp:" + interface_router_ipadress + "/161", community_string,SnmpConstants.version1);
                } else {
                    su.start();
                    target1 = su.getTarget("udp:" + router_ipadress + "/161", community_string, SnmpConstants.version2c);
                    temp_val = su.BandwidthGetVect(target1, "Out", tempOID);
                    //target3 = su3.getTarget("udp:" + interface_router_ipadress + "/161", community_string,SnmpConstants.version2c);
                }

            } catch (Exception e12) {
                System.out.println("Temp OID excep:" + e12);
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
            System.out.println(router_ipadress + ":Temprature:" + temp_val + ":" + oid_tem);

            // update cpu
            if (temp_val == null || temp_val.equals("") || temp_val.equals("noSuchObject") || temp_val.equals("noSuchInstance") || cpu_val.equals("Null") || Integer.parseInt(cpu_val) > 100) {
                temp_val = "0";
            }
//            if (cpu_val == null || cpu_val.equals("") || cpu_val.equals("noSuchObject") || cpu_val.equals("noSuchInstance") || cpu_val.equals("Null") || Integer.parseInt(cpu_val) > 100) {
//            } else {
//                System.out.println(router_ipadress + ":Temprature:" + temp_val + ":" + oid_tem);
//                System.out.println("cpu history param:" + health_history_param);
//                //  System.out.println("update cpu222222");
//
//            }

            //Memory 
            String memory_param = "Yes";

            if (memory_param.equals("Yes")) {

                double processor_total_D = 0;
                double processor_used_D = 0;
                double processor_free_D = 0;
                double processor_percentage_D = 0;

                String processor_total_val = "";
                //  String processor_used_val = "";
                String processor_percentage = "";

                try {
                    OID processor_percentageOID = null;
                    processor_percentageOID = new OID(oid_memory_percentage);
                    Target target1 = null;
                    if (snmp_version.equals("Version3")) {

//                        try {
//                            suversion3.start("udp:" + router_ipadress + "/161", user_name, authentication, auth_pass, privacy, privacy_pass, SnmpConstants.version3);
//                            target1 = suversion3.getTargetVersion3("udp:" + router_ipadress + "/161", user_name, authentication, auth_pass, privacy, privacy_pass, SnmpConstants.version3);
//                            processor_percentage = suversion3.BandwidthGetVect(target1, "cpuOID", processor_percentageOID);
//                        } catch (Exception e) {
//                            System.out.println("Exception Memory Used:" + e);
//                        }
                    } else if (snmp_version.equals("Version1")) {
                        su.start();
                        target1 = su.getTarget("udp:" + router_ipadress + "/161", community_string, SnmpConstants.version1);
                        processor_percentage = su.BandwidthGetVect(target1, "Out", processor_percentageOID);
                    } else {
                        su.start();
                        target1 = su.getTarget("udp:" + router_ipadress + "/161", community_string, SnmpConstants.version2c);
                        processor_percentage = su.BandwidthGetVect(target1, "Out", processor_percentageOID);
                    }

                } catch (Exception e12) {
                    System.out.println("M Free ex:" + e12);
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

                System.out.println(router_ipadress + ":Memory Percentage:" + processor_percentage + ":" + oid_memory_percentage);

                if (processor_percentage.equals("noSuchObject") || processor_percentage.equals("Null") || processor_percentage.equals("noSuchInstance")) {
                } else {
                    try {

                        OID processor_totalOID = null;
                        processor_totalOID = new OID(oid_memory_total);

                        Target target1 = null;

                        if (snmp_version.equals("Version3")) {
//                            username = user_name;
//                            authname = authentication;
//                            authpass = auth_pass;
//                            privname = privacy;
//                            privpass = privacy_pass;
//                            suversion3.start("udp:" + router_ipadress + "/161", user_name, authentication, auth_pass, privacy, privacy_pass, SnmpConstants.version3);
//                            try {
//                                target1 = suversion3.getTargetVersion3("udp:" + router_ipadress + "/161", user_name, authentication, auth_pass, privacy, privacy_pass, SnmpConstants.version3);
//                            } catch (Exception e) {
//                                e.printStackTrace();;
//                            }
//                            try {
//                                processor_total_val = suversion3.BandwidthGetVect(target1, "cpuOID", processor_totalOID);
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
                            //target3 = su3.getTargetVersion3("udp:" + interface_router_ipadress + "/161", user_name,authentication,auth_pass,privacy,privacy_pass,SnmpConstants.version3);
                        } else if (snmp_version.equals("Version1")) {
                            su.start();
                            target1 = su.getTarget("udp:" + router_ipadress + "/161", community_string, SnmpConstants.version1);
                            processor_total_val = su.BandwidthGetVect(target1, "Out", processor_totalOID);
//                            target3 = su3.getTarget("udp:" + interface_router_ipadress + "/161", community_string,SnmpConstants.version1);
                        } else {
                            su.start();
                            target1 = su.getTarget("udp:" + router_ipadress + "/161", community_string, SnmpConstants.version2c);
                            processor_total_val = su.BandwidthGetVect(target1, "Out", processor_totalOID);
                            //target3 = su3.getTarget("udp:" + interface_router_ipadress + "/161", community_string,SnmpConstants.version2c);
                        }

                    } catch (Exception e12) {
                        System.out.println("M Used ex:" + e12);
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

                    System.out.println(router_ipadress + ":Memeory Total(KB):" + processor_total_val + ":" + oid_memory_total);

                    try {
                        processor_total_D = Double.parseDouble(processor_total_val);
                        processor_percentage_D = Double.parseDouble(processor_percentage);
                        processor_used_D = (processor_percentage_D * processor_total_D) / 100;
                        processor_free_D = processor_total_D - processor_used_D;

                        System.out.println("MemoryTotal:" + processor_total_D);
                        System.out.println("Memory Used:" + processor_used_D);
                        System.out.println("Memory Free:" + processor_free_D);
                        System.out.println("Memory %   :" + processor_percentage_D);

                    } catch (Exception exp) {
                        System.out.println("Error in Memory Convert " + exp);
                    }

                    try {
                        HealthMaster updateBm = null;
                        updateBm = new HealthMaster();
                        updateBm.total_processor_ram = processor_total_D;
                        updateBm.processor_free_ram = processor_free_D;
                        updateBm.processor_used_ram = processor_used_D;
                        updateBm.processor_percent_ram = String.valueOf(processor_percentage_D);
                        updateBm.router_ip = router_ipadress;
                        updateBm.temp_val = temp_val;
                        updateBm.cpu_val = cpu_val;
                        updateBm.datetime = new Timestamp(new java.util.Date().getTime());
                        NodeHealthMon.ram_list.add(updateBm);
                        System.out.println("Update Ram in Batch:" + NodeHealthMon.ram_list.size());
                    } catch (Exception expE) {
                        System.out.println("Exception in RAM list " + expE);
                    }

                    if (cpu_percentage == null) {
                        cpu_percentage = "80";
                    }
                    if (ram_percentage == null) {
                        ram_percentage = "80";
                    }

                    //CPU Threshold Monitoring Start
                    if (cpu_val == null || cpu_val.equals("") || cpu_val.equals("noSuchObject") || cpu_val.equals("noSuchInstance") || cpu_val.equals("Null") || Integer.parseInt(cpu_val) > 100) {

                    } else {

                        int real_cpu_util = 0;
                        int int_percentage_cpu = 0;
                        //double int_percentage_ram = 0;
                        try {

                            real_cpu_util = Integer.parseInt(cpu_val);
                            int_percentage_cpu = Integer.parseInt(cpu_percentage);
                            //   int_percentage_ram = Double.parseDouble(ram_percentage);

                        } catch (Exception exp) {
                            System.out.println("double excep " + exp);
                        }

                        String h_cpustatus = NodeHealthMon.staus_cpumap.get(router_ipadress).toString();
                        //  System.out.println("ip@:" + router_ipadress + ":" + h_cpustatus);
                        if (real_cpu_util > int_percentage_cpu && h_cpustatus.equals("Low")) {
                            NodeHealthMon.staus_cpumap.put(router_ipadress, "High");
                            System.out.println(real_cpu_util + ":High CPU Usage#############:" + router_ipadress + mail_alert);
                            String cpu_status = "High";
                            try {
                                CPUThresholdAlert(router_ipadress, cpu_status, cpu_percentage, cpu_val);
                            } catch (Exception e) {
                                System.out.println("Exception :" + e);
                            }

                        } else if (real_cpu_util < int_percentage_cpu && h_cpustatus.equals("High")) {
                            System.out.println(real_cpu_util + "##########Normalcpu:" + router_ipadress);
                            NodeHealthMon.staus_cpumap.put(router_ipadress, "Low");
                            String cpu_status = "Low";
                            try {
                                CPUThresholdAlert(router_ipadress, cpu_status, cpu_percentage, cpu_val);
                            } catch (Exception e) {
                                System.out.println("Exception :" + e);
                            }
                        }

                    }

                    //CPU Threshold Monitoring End
                    //Memrory Threshold
                    double int_percentage_ram = 0;
                    try {
                        int_percentage_ram = Double.parseDouble(ram_percentage);
                    } catch (Exception exp) {
                        System.out.println("double excep " + exp);
                    }

                    //Check RAM Threshold Start
                    String h_ramstatus = NodeHealthMon.staus_rammap.get(router_ipadress).toString();
                    try {
                        if (processor_percentage_D > int_percentage_ram && h_ramstatus.equals("Low")) {

                            NodeHealthMon.staus_rammap.put(router_ipadress, "High");
                            System.out.println("HIgh RAM###############");
                            //String ram_status = "High RAM Usage";
                            String ram_status = "High";
                            try {
                                MemoryThresholdAlert(router_ipadress, ram_status, ram_percentage, String.valueOf(processor_percentage_D));
                            } catch (Exception e) {
                                System.out.println("Exception:" + e);
                            }
                        } else if (processor_percentage_D < int_percentage_ram && h_ramstatus.equals("High")) {
                            //  System.out.println(real_cpu_util + "##########Normal:" + router_ipadress);
                            System.out.println("LOW RAM ##################");
                            NodeHealthMon.staus_rammap.put(router_ipadress, "Low");
                            String ram_status = "Low";
                            try {
                                MemoryThresholdAlert(router_ipadress, ram_status, ram_percentage, String.valueOf(processor_percentage_D));
                            } catch (Exception e) {
                                System.out.println("Exception:" + e);
                            }
                        }

                    } catch (Exception exp) {
                        System.out.println("double excep " + exp);
                    }
                    //Check RAM Threshold End

                }

            }

        }
    }

    public void healthFortigate(String router_ipadress) {

        System.out.println("healthFortigate@:" + router_ipadress);
        String snmp_version = "Version2";
        String community_string = "indiraivf";
        String health_history_param = "Yes";
        String cpu_percentage = "80";
        String ram_percentage = "80";

        try {
            cpu_percentage = NodeHealthMon.healthMonData.get(router_ipadress).getCPU_THRESHOLD();
        } catch (Exception e) {
            System.out.println("Exception:" + e);
        }

        try {
            ram_percentage = NodeHealthMon.healthMonData.get(router_ipadress).getMEMORY_THRESHOLD();
        } catch (Exception e) {
            System.out.println("Exception:" + e);
        }

        System.out.println("Device ip:" + router_ipadress);
        System.out.println(router_ipadress + ":CPU Percentage:" + cpu_percentage + ":Memory Percentage:" + ram_percentage);
//        
//        Cisco OID
//                 String oid_processor_used = "1.3.6.1.4.1.9.9.48.1.1.1.5.1";
//        String oid_processor_free = "1.3.6.1.4.1.9.9.48.1.1.1.6.1";
//        String oid_cpu = "1.3.6.1.4.1.9.9.109.1.1.1.1.4.1";
//        String oid_tem = "1.3.6.1.4.1.9.9.13.1.3.1.3.1";
//        String oid_memory_total = "1.3.6.1.4.1.9.9.48.1.1.1.5.2";
//        String oid_memory_percentage = "1.3.6.1.4.1.9.9.48.1.1.1.6.2";

        // Alpha brdige
        //  String oid_processor_used = "1.3.6.1.4.1.9.9.48.1.1.1.5.1";
        //  String oid_processor_free = "1.3.6.1.4.1.9.9.48.1.1.1.6.1";
        String oid_cpu = "1.3.6.1.4.1.12356.101.4.1.3.0";  //@
        String oid_tem = "1.3.6.1.4.1.58158.9.181.5.1.4.1.0.1";  //@
        String oid_memory_total = "1.3.6.1.4.1.12356.101.4.1.5.0";  //@
        String oid_memory_percentage = "1.3.6.1.4.1.12356.101.4.1.4.0";   //@

        boolean checksnmp = false;
        String cpu_val = null;
        String temp_val = "";

        SNMPUtilHealth su = null;
        su = new SNMPUtilHealth();

        SNMPUtilVersion3 suversion3 = null;
        suversion3 = new SNMPUtilVersion3();

        try {
            // String cpu_util = "1.3.6.1.4.1.12356.101.4.1.3.0";
            OID cpuOID = null;
            cpuOID = new OID(oid_cpu);

            Target target1 = null;

            if (snmp_version.equals("Version3")) {
                //   System.out.println("in version 3");
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
                //target3 = su3.getTargetVersion3("udp:" + interface_router_ipadress + "/161", user_name,authentication,auth_pass,privacy,privacy_pass,SnmpConstants.version3);
            } else {
                System.out.println("in version 1 & 2");
                if (snmp_version.equals("Version1")) {
                    System.out.println("in version 1");
                    su.start();
                    target1 = su.getTarget("udp:" + router_ipadress + "/161", community_string, SnmpConstants.version1);
                    cpu_val = su.BandwidthGetVect(target1, "Out", cpuOID);
//                            target3 = su3.getTarget("udp:" + interface_router_ipadress + "/161", community_string,SnmpConstants.version1);
                } else {
                    System.out.println("in SNMP version 2");
                    su.start();
                    target1 = su.getTarget("udp:" + router_ipadress + "/161", community_string, SnmpConstants.version2c);
                    cpu_val = su.BandwidthGetVect(target1, "Out", cpuOID);
                    //target3 = su3.getTarget("udp:" + interface_router_ipadress + "/161", community_string,SnmpConstants.version2c);
                }

            }

        } catch (Exception e12) {
            System.out.println("CPU OID excep:" + e12);
            //   SNMPHealth.tipsize.add(router_ipadress);
            checksnmp = true;
        } finally {
            try {
                if (suversion3 != null) {
                    suversion3.stop();
                }
                if (su != null) {
                    su.stop();
                }
//                        if (client5 != null) {
//                            client5.stop();
//                        }
//                        if (clientversion2 != null) {
//                            clientversion2.stop();
//                        }
            } catch (Exception ex2) {
                System.out.println("SNMP Close Exception....." + ex2);
            }

        }

        System.out.println(router_ipadress + ":CPU Utilization:" + cpu_val + ":" + oid_cpu);

        // System.out.println("size:" + SNMPHealth.tipsize.size());
        if (checksnmp == false) {
            //   System.out.println("SNMP Enable:"+router_ipadress);

            try {

                OID tempOID = null;
                tempOID = new OID(oid_tem);

                Target target1 = null;

                if (snmp_version.equals("Version3")) {
//                    username = user_name;
//                    authname = authentication;
//                    authpass = auth_pass;
//                    privname = privacy;
//                    privpass = privacy_pass;
//                    suversion3.start();
//                    try {
//                        target1 = suversion3.getTargetVersion3("udp:" + router_ipadress + "/161", user_name, authentication, auth_pass, privacy, privacy_pass, SnmpConstants.version3);
//                    } catch (Exception e) {
//                        e.printStackTrace();;
//                    }
//                    try {
//                        temp_val = suversion3.BandwidthGetVect(target1, "tempOID", tempOID);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
                } else if (snmp_version.equals("Version1")) {
                    su.start();
                    target1 = su.getTarget("udp:" + router_ipadress + "/161", community_string, SnmpConstants.version1);
                    temp_val = su.BandwidthGetVect(target1, "Out", tempOID);
//                            target3 = su3.getTarget("udp:" + interface_router_ipadress + "/161", community_string,SnmpConstants.version1);
                } else {
                    su.start();
                    target1 = su.getTarget("udp:" + router_ipadress + "/161", community_string, SnmpConstants.version2c);
                    temp_val = su.BandwidthGetVect(target1, "Out", tempOID);
                    //target3 = su3.getTarget("udp:" + interface_router_ipadress + "/161", community_string,SnmpConstants.version2c);
                }

            } catch (Exception e12) {
                System.out.println("Temp OID excep:" + e12);
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
            System.out.println(router_ipadress + ":Temprature:" + temp_val + ":" + oid_tem);

            // update cpu
            if (temp_val == null || temp_val.equals("") || temp_val.equals("noSuchObject") || temp_val.equals("noSuchInstance") || cpu_val.equals("Null") || Integer.parseInt(cpu_val) > 100) {
                temp_val = "0";
            }
//            if (cpu_val == null || cpu_val.equals("") || cpu_val.equals("noSuchObject") || cpu_val.equals("noSuchInstance") || cpu_val.equals("Null") || Integer.parseInt(cpu_val) > 100) {
//            } else {
//                System.out.println(router_ipadress + ":Temprature:" + temp_val + ":" + oid_tem);
//                System.out.println("cpu history param:" + health_history_param);
//                //  System.out.println("update cpu222222");
//
//            }

            //Memory 
            String memory_param = "Yes";

            if (memory_param.equals("Yes")) {

                double processor_total_D = 0;
                double processor_used_D = 0;
                double processor_free_D = 0;
                double processor_percentage_D = 0;

                String processor_total_val = "";
                //  String processor_used_val = "";
                String processor_percentage = "";

                try {
                    OID processor_percentageOID = null;
                    processor_percentageOID = new OID(oid_memory_percentage);
                    Target target1 = null;
                    if (snmp_version.equals("Version3")) {

//                        try {
//                            suversion3.start("udp:" + router_ipadress + "/161", user_name, authentication, auth_pass, privacy, privacy_pass, SnmpConstants.version3);
//                            target1 = suversion3.getTargetVersion3("udp:" + router_ipadress + "/161", user_name, authentication, auth_pass, privacy, privacy_pass, SnmpConstants.version3);
//                            processor_percentage = suversion3.BandwidthGetVect(target1, "cpuOID", processor_percentageOID);
//                        } catch (Exception e) {
//                            System.out.println("Exception Memory Used:" + e);
//                        }
                    } else if (snmp_version.equals("Version1")) {
                        su.start();
                        target1 = su.getTarget("udp:" + router_ipadress + "/161", community_string, SnmpConstants.version1);
                        processor_percentage = su.BandwidthGetVect(target1, "Out", processor_percentageOID);
                    } else {
                        su.start();
                        target1 = su.getTarget("udp:" + router_ipadress + "/161", community_string, SnmpConstants.version2c);
                        processor_percentage = su.BandwidthGetVect(target1, "Out", processor_percentageOID);
                    }

                } catch (Exception e12) {
                    System.out.println("M Free ex:" + e12);
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

                System.out.println(router_ipadress + ":Memory Percentage:" + processor_percentage + ":" + oid_memory_percentage);

                if (processor_percentage.equals("noSuchObject") || processor_percentage.equals("Null") || processor_percentage.equals("noSuchInstance")) {
                } else {
                    try {

                        OID processor_totalOID = null;
                        processor_totalOID = new OID(oid_memory_total);

                        Target target1 = null;

                        if (snmp_version.equals("Version3")) {
//                            username = user_name;
//                            authname = authentication;
//                            authpass = auth_pass;
//                            privname = privacy;
//                            privpass = privacy_pass;
//                            suversion3.start("udp:" + router_ipadress + "/161", user_name, authentication, auth_pass, privacy, privacy_pass, SnmpConstants.version3);
//                            try {
//                                target1 = suversion3.getTargetVersion3("udp:" + router_ipadress + "/161", user_name, authentication, auth_pass, privacy, privacy_pass, SnmpConstants.version3);
//                            } catch (Exception e) {
//                                e.printStackTrace();;
//                            }
//                            try {
//                                processor_total_val = suversion3.BandwidthGetVect(target1, "cpuOID", processor_totalOID);
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
                            //target3 = su3.getTargetVersion3("udp:" + interface_router_ipadress + "/161", user_name,authentication,auth_pass,privacy,privacy_pass,SnmpConstants.version3);
                        } else if (snmp_version.equals("Version1")) {
                            su.start();
                            target1 = su.getTarget("udp:" + router_ipadress + "/161", community_string, SnmpConstants.version1);
                            processor_total_val = su.BandwidthGetVect(target1, "Out", processor_totalOID);
//                            target3 = su3.getTarget("udp:" + interface_router_ipadress + "/161", community_string,SnmpConstants.version1);
                        } else {
                            su.start();
                            target1 = su.getTarget("udp:" + router_ipadress + "/161", community_string, SnmpConstants.version2c);
                            processor_total_val = su.BandwidthGetVect(target1, "Out", processor_totalOID);
                            //target3 = su3.getTarget("udp:" + interface_router_ipadress + "/161", community_string,SnmpConstants.version2c);
                        }

                    } catch (Exception e12) {
                        System.out.println("M Used ex:" + e12);
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

                    System.out.println(router_ipadress + ":Memeory Total(KB):" + processor_total_val + ":" + oid_memory_total);

                    try {
                        processor_total_D = Double.parseDouble(processor_total_val);
                        processor_percentage_D = Double.parseDouble(processor_percentage);
                        processor_used_D = (processor_percentage_D * processor_total_D) / 100;
                        processor_free_D = processor_total_D - processor_used_D;

                    } catch (Exception exp) {
                        System.out.println("Error in Memory Convert " + exp);
                    }

                    try {
                        processor_total_D = processor_total_D * 1024;
                        processor_used_D = processor_used_D * 1024;
                        processor_free_D = processor_free_D * 1024;

                    } catch (Exception e) {
                        System.out.println("exception byte");
                    }
                    System.out.println("MemoryTotal:" + processor_total_D);
                    System.out.println("Memory Used:" + processor_used_D);
                    System.out.println("Memory Free:" + processor_free_D);
                    System.out.println("Memory %   :" + processor_percentage_D);

                    try {
                        HealthMaster updateBm = null;
                        updateBm = new HealthMaster();
                        updateBm.total_processor_ram = processor_total_D;
                        updateBm.processor_free_ram = processor_free_D;
                        updateBm.processor_used_ram = processor_used_D;
                        updateBm.processor_percent_ram = String.valueOf(processor_percentage_D);
                        updateBm.router_ip = router_ipadress;
                        updateBm.temp_val = temp_val;
                        updateBm.cpu_val = cpu_val;
                        updateBm.datetime = new Timestamp(new java.util.Date().getTime());
                        NodeHealthMon.ram_list.add(updateBm);
                        System.out.println("Update Ram in Batch:" + NodeHealthMon.ram_list.size());
                    } catch (Exception expE) {
                        System.out.println("Exception in RAM list " + expE);
                    }

                    if (cpu_percentage == null) {
                        cpu_percentage = "80";
                    }
                    if (ram_percentage == null) {
                        ram_percentage = "80";
                    }

                    //CPU Threshold Monitoring Start
                    if (cpu_val == null || cpu_val.equals("") || cpu_val.equals("noSuchObject") || cpu_val.equals("noSuchInstance") || cpu_val.equals("Null") || Integer.parseInt(cpu_val) > 100) {

                    } else {

                        int real_cpu_util = 0;
                        int int_percentage_cpu = 0;
                        //double int_percentage_ram = 0;
                        try {

                            real_cpu_util = Integer.parseInt(cpu_val);
                            int_percentage_cpu = Integer.parseInt(cpu_percentage);
                            //   int_percentage_ram = Double.parseDouble(ram_percentage);

                        } catch (Exception exp) {
                            System.out.println("double excep " + exp);
                        }

                        String h_cpustatus = NodeHealthMon.staus_cpumap.get(router_ipadress).toString();
                        //  System.out.println("ip@:" + router_ipadress + ":" + h_cpustatus);
                        if (real_cpu_util > int_percentage_cpu && h_cpustatus.equals("Low")) {
                            NodeHealthMon.staus_cpumap.put(router_ipadress, "High");
                            System.out.println(real_cpu_util + ":High CPU Usage#############:" + router_ipadress + mail_alert);
                            String cpu_status = "High";
                            try {
                                CPUThresholdAlert(router_ipadress, cpu_status, cpu_percentage, cpu_val);
                            } catch (Exception e) {
                                System.out.println("Exception :" + e);
                            }

                        } else if (real_cpu_util < int_percentage_cpu && h_cpustatus.equals("High")) {
                            System.out.println(real_cpu_util + "##########Normalcpu:" + router_ipadress);
                            NodeHealthMon.staus_cpumap.put(router_ipadress, "Low");
                            String cpu_status = "Low";
                            try {
                                CPUThresholdAlert(router_ipadress, cpu_status, cpu_percentage, cpu_val);
                            } catch (Exception e) {
                                System.out.println("Exception :" + e);
                            }
                        }

                    }

                    //CPU Threshold Monitoring End
                    //Memrory Threshold
                    double int_percentage_ram = 0;
                    try {
                        int_percentage_ram = Double.parseDouble(ram_percentage);
                    } catch (Exception exp) {
                        System.out.println("double excep " + exp);
                    }

                    //Check RAM Threshold Start
                    String h_ramstatus = NodeHealthMon.staus_rammap.get(router_ipadress).toString();
                    try {
                        if (processor_percentage_D > int_percentage_ram && h_ramstatus.equals("Low")) {

                            NodeHealthMon.staus_rammap.put(router_ipadress, "High");
                            System.out.println("HIgh RAM###############");
                            //String ram_status = "High RAM Usage";
                            String ram_status = "High";
                            try {
                                MemoryThresholdAlert(router_ipadress, ram_status, ram_percentage, String.valueOf(processor_percentage_D));
                            } catch (Exception e) {
                                System.out.println("Exception:" + e);
                            }
                        } else if (processor_percentage_D < int_percentage_ram && h_ramstatus.equals("High")) {
                            //  System.out.println(real_cpu_util + "##########Normal:" + router_ipadress);
                            System.out.println("LOW RAM ##################");
                            NodeHealthMon.staus_rammap.put(router_ipadress, "Low");
                            String ram_status = "Low";
                            try {
                                MemoryThresholdAlert(router_ipadress, ram_status, ram_percentage, String.valueOf(processor_percentage_D));
                            } catch (Exception e) {
                                System.out.println("Exception:" + e);
                            }
                        }

                    } catch (Exception exp) {
                        System.out.println("double excep " + exp);
                    }
                    //Check RAM Threshold End

                }

            }

        }
    }

    public void insertIntoEventLog(String router_ipadress, String device_name, String eventMsg1, int severity, String serviceName, Timestamp timestamp, String netadminMsg, String isAffected, String problem, String serviceId, String device_type) {
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;
        Connection connection = null;
        try {
            connection = Datasource55.getConnection();
            preparedStatement1 = connection.prepareStatement("INSERT INTO event_log (device_id, device_name, service_name, event_msg, netadmin_msg, severity,"
                    + " event_timestamp, acknowledgement_status, isAffected, Problem_Clear, Service_ID, Device_Type) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
            preparedStatement1.setString(1, router_ipadress);
            preparedStatement1.setString(2, device_name);
            preparedStatement1.setString(3, serviceName);
            preparedStatement1.setString(4, eventMsg1);
            preparedStatement1.setString(5, netadminMsg);
            preparedStatement1.setInt(6, severity);
            preparedStatement1.setTimestamp(7, timestamp);
            preparedStatement1.setBoolean(8, false);
            preparedStatement1.setString(9, isAffected);
            preparedStatement1.setString(10, problem);
            preparedStatement1.setString(11, serviceId);
            preparedStatement1.setString(12, device_type);
            preparedStatement1.executeUpdate();

        } catch (Exception e) {
            System.out.println(router_ipadress + "inserting in event log Exception:" + e);
        } finally {
            try {
                if (preparedStatement1 != null) {
                    preparedStatement1.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception exp) {
                System.out.println("excep:" + exp);
            }
        }

        try {
            if ("Cleared".equalsIgnoreCase(problem)) {

                String updateQuery = "UPDATE event_log\n"
                        + "SET\n"
                        + "    Cleared_event_timestamp = ?,\n"
                        // + "    netadmin_msg = ?,\n"
                        + "netadmin_msg = CONCAT(netadmin_msg, ' => ', ?),\n"
                        + "    isAffected = ?\n"
                        + "WHERE\n"
                        + "    ID = (\n"
                        + "        SELECT id_alias.ID\n"
                        + "        FROM (\n"
                        + "            SELECT ID\n"
                        + "            FROM event_log\n"
                        + "            WHERE service_id = ?\n"
                        + "              AND device_id = ?\n"
                        + "            AND isaffected = '1' ORDER BY ID DESC\n"
                        + "            LIMIT 1\n"
                        + "        ) AS id_alias\n"
                        + "    )\n"
                        + ";";

                connection = Datasource55.getConnection();

                preparedStatement2 = connection.prepareStatement(updateQuery);
                preparedStatement2.setTimestamp(1, timestamp);

                preparedStatement2.setString(2, netadminMsg); // To Do
                preparedStatement2.setString(3, "0");
                preparedStatement2.setString(4, serviceId);
                preparedStatement2.setString(5, router_ipadress);

                preparedStatement2.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("Exception in update event log = " + e);
        } finally {
            try {
                if (preparedStatement2 != null) {
                    preparedStatement2.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception exp) {
                System.out.println("excep:" + exp);
            }
        }

    }

}
