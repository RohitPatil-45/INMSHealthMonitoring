/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author testsys
 */
public class HealthModel {

    private static final long serialVersionUID = -2264642949863409860L;

    private String DEVICE_IP;
    private String DEVICE_NAME;
    private String DEVICE_TYPE;
    private String CPU_THRESHOLD;
    private String MEMORY_THRESHOLD;
    private String CPU_STATUS;
    private String MEMORY_STATUS;
    private String CPU_HISTORY;
    private String MEMORY_HISTORY;

    public String getDEVICE_TYPE() {
        return DEVICE_TYPE;
    }

    public void setDEVICE_TYPE(String DEVICE_TYPE) {
        this.DEVICE_TYPE = DEVICE_TYPE;
    }

    public String getDEVICE_IP() {
        return DEVICE_IP;
    }

    public void setDEVICE_IP(String DEVICE_IP) {
        this.DEVICE_IP = DEVICE_IP;
    }

    public String getCPU_THRESHOLD() {
        return CPU_THRESHOLD;
    }

    public void setCPU_THRESHOLD(String CPU_THRESHOLD) {
        this.CPU_THRESHOLD = CPU_THRESHOLD;
    }

    public String getMEMORY_THRESHOLD() {
        return MEMORY_THRESHOLD;
    }

    public void setMEMORY_THRESHOLD(String MEMORY_THRESHOLD) {
        this.MEMORY_THRESHOLD = MEMORY_THRESHOLD;
    }

    public String getCPU_STATUS() {
        return CPU_STATUS;
    }

    public void setCPU_STATUS(String CPU_STATUS) {
        this.CPU_STATUS = CPU_STATUS;
    }

    public String getMEMORY_STATUS() {
        return MEMORY_STATUS;
    }

    public void setMEMORY_STATUS(String MEMORY_STATUS) {
        this.MEMORY_STATUS = MEMORY_STATUS;
    }

    public String getCPU_HISTORY() {
        return CPU_HISTORY;
    }

    public void setCPU_HISTORY(String CPU_HISTORY) {
        this.CPU_HISTORY = CPU_HISTORY;
    }

    public String getMEMORY_HISTORY() {
        return MEMORY_HISTORY;
    }

    public void setMEMORY_HISTORY(String MEMORY_HISTORY) {
        this.MEMORY_HISTORY = MEMORY_HISTORY;
    }

    public String getDEVICE_NAME() {
        return DEVICE_NAME;
    }

    public void setDEVICE_NAME(String DEVICE_NAME) {
        this.DEVICE_NAME = DEVICE_NAME;
    }
    
    

}
