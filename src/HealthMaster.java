
import java.sql.Date;
import java.sql.Timestamp;

//package SNMPHealth;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author VIVEK
 */
public class HealthMaster {

    String router_ip = null;
    String router_name = null;
    String branch_name = null;
    String current_bandwidth = null;
    Date cdate = null;
    String ctime = null;
    String interface_name = null;
    String interface_outOctate = null;
    String interfaceinOctate = null;
    String ssa = null;
    String zone_name = null;
    String customer_name = null;
    String customer_sname = null;
    String district = null;
    String department = null;
    String state_name = null;
    String last_change_time = null;
    String cpu_val = null;
    String temp_val = null;
    double total_processor_ram = 0;
    double processor_free_ram = 0;
    double processor_used_ram = 0;
    String processor_percent_ram = null;
    double total_disk_ram = 0;
    double disk_free_ram = 0;
    double disk_used_ram = 0;
    double disk_percent_ram = 0;

    int device_buffer = 0;
    


    
    
    Timestamp datetime = null;

    public int getDevice_buffer() {
        return device_buffer;
    }

    public void setDevice_buffer(int device_buffer) {
        this.device_buffer = device_buffer;
    }

    
    
    
    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    public double getDisk_percent_ram() {
        return disk_percent_ram;
    }

    public void setDisk_percent_ram(double disk_percent_ram) {
        this.disk_percent_ram = disk_percent_ram;
    }

    public double getTotal_disk_ram() {
        return total_disk_ram;
    }

    public void setTotal_disk_ram(double total_disk_ram) {
        this.total_disk_ram = total_disk_ram;
    }

    public double getDisk_free_ram() {
        return disk_free_ram;
    }

    public void setDisk_free_ram(double disk_free_ram) {
        this.disk_free_ram = disk_free_ram;
    }

    public double getDisk_used_ram() {
        return disk_used_ram;
    }

    public void setDisk_used_ram(double disk_used_ram) {
        this.disk_used_ram = disk_used_ram;
    }

    public double getProcessor_free_ram() {
        return processor_free_ram;
    }

    public void setProcessor_free_ram(double processor_free_ram) {
        this.processor_free_ram = processor_free_ram;
    }

    public String getProcessor_percent_ram() {
        return processor_percent_ram;
    }

    public void setProcessor_percent_ram(String processor_percent_ram) {
        this.processor_percent_ram = processor_percent_ram;
    }

    public double getProcessor_used_ram() {
        return processor_used_ram;
    }

    public void setProcessor_used_ram(double processor_used_ram) {
        this.processor_used_ram = processor_used_ram;
    }

    public double getTotal_processor_ram() {
        return total_processor_ram;
    }

    public void setTotal_processor_ram(double total_processor_ram) {
        this.total_processor_ram = total_processor_ram;
    }

    public String getCpu_val() {
        return cpu_val;
    }

    public void setCpu_val(String cpu_val) {
        this.cpu_val = cpu_val;
    }

    public String getTemp_val() {
        return temp_val;
    }

    public void setTemp_val(String temp_val) {
        this.temp_val = temp_val;
    }

    public String getLast_change_time() {
        return last_change_time;
    }

    public void setLast_change_time(String last_change_time) {
        this.last_change_time = last_change_time;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getCurrent_bandwidth() {
        return current_bandwidth;
    }

    public void setCurrent_bandwidth(String current_bandwidth) {
        this.current_bandwidth = current_bandwidth;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_sname() {
        return customer_sname;
    }

    public void setCustomer_sname(String customer_sname) {
        this.customer_sname = customer_sname;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getInterface_name() {
        return interface_name;
    }

    public void setInterface_name(String interface_name) {
        this.interface_name = interface_name;
    }

    public String getInterface_outOctate() {
        return interface_outOctate;
    }

    public void setInterface_outOctate(String interface_outOctate) {
        this.interface_outOctate = interface_outOctate;
    }

    public String getInterfaceinOctate() {
        return interfaceinOctate;
    }

    public void setInterfaceinOctate(String interfaceinOctate) {
        this.interfaceinOctate = interfaceinOctate;
    }

    public String getRouter_ip() {
        return router_ip;
    }

    public void setRouter_ip(String router_ip) {
        this.router_ip = router_ip;
    }

    public String getRouter_name() {
        return router_name;
    }

    public void setRouter_name(String router_name) {
        this.router_name = router_name;
    }

    public String getSsa() {
        return ssa;
    }

    public void setSsa(String ssa) {
        this.ssa = ssa;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public String getZone_name() {
        return zone_name;
    }

    public void setZone_name(String zone_name) {
        this.zone_name = zone_name;
    }
}
