
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author VIJAY
 */
public class MemoryBatch implements Runnable {

    Connection connection = null;
    PreparedStatement pstmt_memory_log = null;
    PreparedStatement preparedStatement2 = null;
    ResultSet resultSet = null;

    public void run() {

        while (true) {

            System.out.println("Start Memory Log and Update");
            try {
                Thread.sleep(40000);
            } catch (Exception expp) {
                System.out.println("Exception In Thread Sleep BatchProcess :" + expp);
            }

            try {
                // System.out.println("Memeor size:" + SNMPHealth.ram_list.size());
                NodeHealthMon.ram_list_temp.clear();
                NodeHealthMon.ram_list_temp.addAll(NodeHealthMon.ram_list);
                NodeHealthMon.ram_list.clear();

                connection = Datasource55.getConnection();

                String sql_update = "UPDATE node_health_monitoring SET CPU_UTILIZATION=?,TEMPERATURE=?,TOTAL_MEMORY=?,USED_MEMORY=?,FREE_MEMORY=?,MEMORY_UTILIZATION=? WHERE NODE_IP=?";
                preparedStatement2 = connection.prepareStatement(sql_update);

                String sql_insert = "INSERT INTO node_health_history(EVENT_TIMESTAMP,NODE_IP,CPU_UTILIZATION,TEMPERATURE,MEMORY_UTILIZATION,TOTAL_MEMORY,USED_MEMORY,FREE_MEMORY) VALUES (?,?,?,?,?,?,?,?)";
                pstmt_memory_log = connection.prepareStatement(sql_insert);

             

                for (int i = 0; i < NodeHealthMon.ram_list_temp.size(); i++) {
                  
                    preparedStatement2.setString(1, NodeHealthMon.ram_list_temp.get(i).getCpu_val());
                    preparedStatement2.setString(2, NodeHealthMon.ram_list_temp.get(i).getTemp_val());
                    preparedStatement2.setDouble(3, NodeHealthMon.ram_list_temp.get(i).getTotal_processor_ram());
                    preparedStatement2.setDouble(4, NodeHealthMon.ram_list_temp.get(i).getProcessor_used_ram());
                    preparedStatement2.setDouble(5, NodeHealthMon.ram_list_temp.get(i).getProcessor_free_ram());
                    preparedStatement2.setString(6, NodeHealthMon.ram_list_temp.get(i).getProcessor_percent_ram());
                    preparedStatement2.setString(7, NodeHealthMon.ram_list_temp.get(i).getRouter_ip());
                    preparedStatement2.addBatch();

                    //    String sql_insert = "INSERT INTO node_health_history(EVENT_TIMESTAMP.NODE_IP,CPU_UTILIZATION,TEMPERATURE,MEMORY_UTILIZATION) VALUES (?,?,?,?,?)";
                    //LOG
                    pstmt_memory_log.setTimestamp(1, NodeHealthMon.ram_list_temp.get(i).getDatetime());
                    pstmt_memory_log.setString(2, NodeHealthMon.ram_list_temp.get(i).getRouter_ip());
                    pstmt_memory_log.setString(3, NodeHealthMon.ram_list_temp.get(i).getCpu_val());
                    pstmt_memory_log.setString(4, NodeHealthMon.ram_list_temp.get(i).getTemp_val());
                    pstmt_memory_log.setString(5, NodeHealthMon.ram_list_temp.get(i).getProcessor_percent_ram());

                    pstmt_memory_log.setDouble(6, NodeHealthMon.ram_list_temp.get(i).getTotal_processor_ram());
                    pstmt_memory_log.setDouble(7, NodeHealthMon.ram_list_temp.get(i).getProcessor_used_ram());
                    pstmt_memory_log.setDouble(8, NodeHealthMon.ram_list_temp.get(i).getProcessor_free_ram());

                    pstmt_memory_log.addBatch();
                }

                try {
                    int[] count = preparedStatement2.executeBatch();
                    System.out.println("Memeory update count: " + count.length);
                } catch (Exception e) {
                    System.out.println("Exception update RAM");
                }

                try {
                    int[] count_insert = pstmt_memory_log.executeBatch();
                    System.out.println("Memeory History Log  count: " + count_insert.length);
                } catch (Exception e) {
                    System.out.println("Exception insert RAM:"+e);
                }

            } catch (Exception exp) {
                System.out.println("Exception In RAM Update " + exp);
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                    if (preparedStatement2 != null) {
                        preparedStatement2.close();
                    }
                } catch (Exception ep) {
                }
            }

        }

    }
}
