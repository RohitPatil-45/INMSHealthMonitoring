
import com.sun.org.apache.xerces.internal.dom.DocumentImpl;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author VIJAY
 */
public class HealthThreadClass implements Runnable {

    boolean continuous = true;
    private static ExecutorService executor = null;
    ArrayList ip_branch_list = null;
    int sri = 0;

    static String processor_used_memory = null;
    static String processor_free_memory = null;
    static String processor_total_memory = null;
    static String percentage = null;
    //  static String processor_used_memory2;
    //.  static String processor_free_memory2;
    //    static String io_used_memory2;
    //    static String io_free_memory1 = null;
    //    static String io_free_memory2;
    static String cpu_var = null;
    static String temp_var = null;

    /*Below Code is for Create XML for Router Usage by Vivek Warule on 23 Jun 2017 ** Start**/
    private static Document buildRouterInfoXML2(ResultSet _rs) throws Exception {
        // System.out.println("vkk1");
        Document xmlDoc = null;
        xmlDoc = new DocumentImpl();
        Element rootElement = xmlDoc.createElement("ROUTER_PARAMETER");
        xmlDoc.appendChild(rootElement);

        int i = 0;

        while (_rs.next()) {
            i++;
            Element application = xmlDoc.createElement("SERIAL_NO");
            application.setAttribute("rip", _rs.getString(String.valueOf("IP_ADDRESS")));
            application.setAttribute("rname", _rs.getString(String.valueOf("ROUTER_NAME")));

            //    application.setAttribute("type", _rs.getString(String.valueOf("ROUTER_IP")));
            Element routerip = xmlDoc.createElement("ROUTER_IP");
            Element set_parameter = xmlDoc.createElement("SET_PARAMETER");
            Element router_percent = xmlDoc.createElement("PERCENTAGE");

            routerip.appendChild(xmlDoc.createTextNode(_rs.getString("IP_ADDRESS")));
            set_parameter.appendChild(xmlDoc.createTextNode(_rs.getString("SET_PARAMETER_FOR")));
            router_percent.appendChild(xmlDoc.createTextNode(_rs.getString("PARAMETER_VALUE")));

            application.appendChild(routerip);
            application.appendChild(set_parameter);
            application.appendChild(router_percent);

            rootElement.appendChild(application);

        }
        return xmlDoc;

    }

    private static void printDOM(Document _xmlDoc, File _outputFile) throws Exception {
        OutputFormat outputFormat = null;
        outputFormat = new OutputFormat("XML", "UTF-8", true);
        FileWriter fileWriter = null;
        fileWriter = new FileWriter(_outputFile);
        XMLSerializer xmlSerializer = null;
        xmlSerializer = new XMLSerializer(fileWriter, outputFormat);
        xmlSerializer.asDOMSerializer();
        xmlSerializer.serialize(_xmlDoc.getDocumentElement());

    }

    /*Below Code is for Create XML for Router Usage by Vivek Warule on 23 Jun 2017 ** End**/
    public void run() {

        List oid_details_list = null;
        processor_used_memory = "";
        processor_free_memory = "";
        processor_total_memory = "";
        percentage = "";
        String router_type = null;
//        try {
//            File fXmlFile = new File("routermemory.xml");
//            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//            Document doc = dBuilder.parse(fXmlFile);
//            doc.getDocumentElement().normalize();
//            NodeList nList = doc.getElementsByTagName("SERIAL_NO");
//            for (int temp = 0; temp < nList.getLength(); temp++) {
//
//                Node nNode = nList.item(temp);
//
//                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
//
//                    Element eElement = (Element) nNode;
//
//                    processor_used_memory = eElement.getElementsByTagName("PROCESSOR_USE").item(0).getTextContent();
//                    processor_free_memory = eElement.getElementsByTagName("PROCESSOR_FREE").item(0).getTextContent();
//                    processor_total_memory = eElement.getElementsByTagName("PROCESSOR_TOTAL").item(0).getTextContent();
//                    percentage = eElement.getElementsByTagName("percentage").item(0).getTextContent();
//                    router_type = (eElement.getElementsByTagName("ROUTER_TYPE").item(0).getTextContent());
//                    cpu_var = eElement.getElementsByTagName("CPU_UTILIZATION").item(0).getTextContent();
//                    temp_var = (eElement.getElementsByTagName("TEMP").item(0).getTextContent());
//
//                    oid_details_list = new ArrayList();
//
//                    oid_details_list.add(processor_used_memory);
//                    oid_details_list.add(processor_free_memory);
//                    oid_details_list.add(processor_total_memory);
//                    oid_details_list.add(percentage);
//                    oid_details_list.add(cpu_var);
//                    oid_details_list.add(temp_var);
//                    NodeHealthMon.snmpOID_hashmap.put(router_type, oid_details_list);
//
//                }
//            }
//
//        } catch (Exception e) {
//            System.out.println("Xml read error 2:" + e);
//        }

        List details_list = null;

        System.out.println(" status router helth class alphbridge 11aug24" );
        ip_branch_list = new ArrayList();
        int pool_size = 0;
        Connection connection5 = null;
        connection5 = Datasource55.getConnection();
        try {
            Statement st1 = connection5.createStatement();
            //   ResultSet r1 = st1.executeQuery("select IP_ADDRESS,BRANCH_NAME,SSA,ZONE_NAME,CUSTOMER,DISTRICT,DEPARTMENT,STATE_UT,COMMUNITY,SNMP_VERSION,USER_NAME,AUTH_NAME,AUTH_PASSWORD,PRIVACY_NAME,PRIVACY_PASSWORD,PORT_NUMBER,ROUTER_TYPE,HEALTH_HISTORY_PARAM,CPU_THRESHOLD,MEMORY_THRESHOLD from ADD_INTERFACE where HEALTH_PARAM='Yes' order by sr_no ASC");
            // ResultSet r21 = st1.executeQuery("select IP_ADDRESS,BRANCH_NAME,SSA,ZONE_NAME,CUSTOMER,DISTRICT,DEPARTMENT,STATE_UT,COMMUNITY,SNMP_VERSION,USER_NAME,AUTH_NAME,AUTH_PASSWORD,PRIVACY_NAME,PRIVACY_PASSWORD,PORT_NUMBER,ROUTER_TYPE,HEALTH_HISTORY_PARAM,CPU_THRESHOLD,MEMORY_THRESHOLD from ADD_INTERFACE where HEALTH_PARAM='Yes' order by sr_no ASC");
            ResultSet r1 = st1.executeQuery("select node.DEVICE_IP,node.DEVICE_TYPE,parm.CPU_THRESHOLD,parm.MEMORY_THRESHOLD,health.CPU_STATUS ,health.MEMORY_STATUS, node.DEVICE_NAME FROM ADD_NODE node JOIN NODE_PARAMETER parm ON node.DEVICE_IP=parm.DEVICE_IP  JOIN node_health_monitoring health ON health.NODE_IP=parm.DEVICE_IP    WHERE node.DEVICE_IP='172.30.0.26' and parm.CPU_HISTORY='Yes' ORDER BY node.ID ");

            //   String router_ipadress = "";
            // String router_branchname = "";
            while (r1.next()) {
                String device_ip = r1.getString(1);
                String device_type = r1.getString(2);
                String cpu_threshold = r1.getString(3);
                String memory_threshol = r1.getString(4);
                String device_name = r1.getString(7);

                String cpu_status = r1.getString(5);
                String memory_status = r1.getString(6);
                

                System.out.println("Device IP DB:" + device_ip);

                ip_branch_list.add(device_ip);

                pool_size = pool_size + 1;

                HealthModel model = new HealthModel();
                model.setCPU_THRESHOLD(cpu_threshold);
                model.setMEMORY_THRESHOLD(memory_threshol);
                model.setDEVICE_TYPE(device_type);
                model.setDEVICE_NAME(device_name);
                

                NodeHealthMon.healthMonData.put(device_ip, model);

                NodeHealthMon.cpu_history_list.put(device_ip, "00");

                if (cpu_status == null) {
                    cpu_status = "Normal";
                }

                if (memory_status == null) {
                    memory_status = "Normal";
                }
                NodeHealthMon.staus_cpumap.put(device_ip, cpu_status);
                NodeHealthMon.staus_rammap.put(device_ip, memory_status);

                /* Create XML Code End    */
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

        /* Below Code for Create Router usage Paramete XMl by Vivek Warule on 23 Jun 2017 **Start*/
        //Bandwidth xml
//        Connection xmlcon2 = null;
//        Statement statement2 = null;
//        ResultSet customerRS2 = null;
//        String SQL2 = "select IP_ADDRESS,ROUTER_NAME,SET_PARAMETER_FOR,PARAMETER_VALUE,BRANCH_NAME from ROUTER_PARAMETER";
//        String OUTPUTFILE2 = "router_usage_parameter.xml";
//        // String thread_xml2 = threadName_xml + "_bw.xml";
//        xmlcon2 = Datasource55.getConnection();
//        try {
//            statement2 = xmlcon2.createStatement();
//            customerRS2 = statement2.executeQuery(SQL2);
//            Document xmlDoc2 = buildRouterInfoXML2(customerRS2);
//            File outputFile2 = null;
//            outputFile2 = new File(OUTPUTFILE2);
//            printDOM(xmlDoc2, outputFile2);
//            // System.out.println("create xml success");
//        } catch (Exception ex) {
//
//            System.out.println("create error " + ex);
//        } finally {
//            if (xmlcon2 != null) {
//                try {
//                    xmlcon2.close();
//                } catch (SQLException e) {
//                    System.out.println(e.getMessage());
//                    e.printStackTrace();
//                    //      The connection could not be closed.");
//                }
//            }
//        }

        /* Below Code for Create Router usage Paramete XMl by Vivek Warule on 23 Jun 2017 **End*/
        System.out.println("snmp link size:" + ip_branch_list.size());
        ArrayList inner_list = null;
        inner_list = new ArrayList();
        ArrayList outer_list = null;
        outer_list = new ArrayList();
        Iterator<Object> main_itr = ip_branch_list.iterator();
        int m = 0;
        while (main_itr.hasNext()) {
            m = m + 1;
            String content_val = main_itr.next().toString();
            inner_list.add(content_val);
            if (m % 1 == 0) {
                String cd = inner_list.toString();
                outer_list.add(cd);
                inner_list.clear();
            }

        }

        if (inner_list.size() != 0) {
            String cd2 = inner_list.toString();
            outer_list.add(cd2);
        }

        int pool_sizee5 = outer_list.size();
        // System.out.println("Thread Pool Size:" + pool_sizee5);
        System.out.println("Thread size:" + outer_list.size());
        Runnable worker = null;
        executor = null;
        executor = Executors.newFixedThreadPool(pool_sizee5);
        Iterator out_itr = outer_list.iterator();
        while (out_itr.hasNext()) {
            String a = out_itr.next().toString();
            String b = a.substring(1, a.length() - 1);
            List<String> myList = null;
            myList = new ArrayList<String>(Arrays.asList(b.split(",")));
            //   System.out.println("list1:" + myList);
            try {
                worker = null;
                worker = new DeviceHealthMonitoring(myList);
                executor.execute(worker);
                Thread.sleep(1000);
            } catch (Exception e) {
                System.err.println("Exceptionn: " + e.getMessage());
            }

        }

    }
}
