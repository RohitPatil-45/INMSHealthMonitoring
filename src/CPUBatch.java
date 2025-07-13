
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author VIJAY
 */
public class CPUBatch implements Runnable {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    PreparedStatement preparedStatement2 = null;
    ResultSet resultSet = null;
    String sql = null;
    String sql1 = null;

    public void run() {

        while (true) {
            //   System.out.println(" start batch process");

            try {
                Thread.sleep(40000);
            } catch (Exception expp) {
                System.out.println("Exception In Thread Sleep BatchProcess :" + expp);
            }

            try {
                System.out.println("cpu size:" + NodeHealthMon.cpu_temp_list.size());

                NodeHealthMon.cpu_temp_list_temp.clear();
                NodeHealthMon.cpu_temp_list_temp.addAll(NodeHealthMon.cpu_temp_list);

                //  System.out.println("Original Update List Size :" + SNMPHealth.cpu_temp_list.size());
                //   System.out.println("Temp Update List Size : " + SNMPHealth.cpu_temp_list_temp.size());
                NodeHealthMon.cpu_temp_list.clear();

                // System.out.println("cpu temp size:" + SNMPHealth.cpu_temp_list_temp.size());
                connection = Datasource55.getConnection();
                sql = "UPDATE ROUTE SET CPU_UTILIZATION=?,CPU_TEMP=? WHERE ROUTER_IP=?";

                preparedStatement = connection.prepareStatement(sql);
                // System.out.println("start update ");
                for (int i = 0; i < NodeHealthMon.cpu_temp_list_temp.size(); i++) {

                    preparedStatement.setString(1, NodeHealthMon.cpu_temp_list_temp.get(i).getCpu_val());
                    preparedStatement.setString(2, NodeHealthMon.cpu_temp_list_temp.get(i).getTemp_val());
                    preparedStatement.setString(3, NodeHealthMon.cpu_temp_list_temp.get(i).getRouter_ip());
                    System.out.println("Router Ip cpu:" + NodeHealthMon.cpu_temp_list_temp.get(i).getRouter_ip());
                    preparedStatement.addBatch();

                }

                int[] count = preparedStatement.executeBatch();

                //  System.out.println("Update Record CPU/TEMP : " + count.length);
            } catch (Exception exp) {
                System.out.println("Exception In CPU/TEMP Update " + exp);

            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                    if (preparedStatement != null) {
                        preparedStatement.close();
                    }

                } catch (Exception ep) {
                }
            }

        }

    }
}
