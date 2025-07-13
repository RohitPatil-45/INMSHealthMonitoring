/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author VIJAY
 */
public class IPScheduleDao {

    private String activity_name;
    private String scan_type;
    private String scan_from;
    private String scan_to;
    


    public String getActivity_name() {
        return activity_name;
    }

    public void setActivity_name(String activity_name) {
        this.activity_name = activity_name;
    }

    public String getScan_type() {
        return scan_type;
    }

    public void setScan_type(String scan_type) {
        this.scan_type = scan_type;
    }

    public String getScan_from() {
        return scan_from;
    }

    public void setScan_from(String scan_from) {
        this.scan_from = scan_from;
    }

    public String getScan_to() {
        return scan_to;
    }

    public void setScan_to(String scan_to) {
        this.scan_to = scan_to;
    }

}
