/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author velox
 */
public class SnmpOID {

    String router_type;
    String interface_out = null;
    String interface_in = null;
    String interface_crc = null;

    public String getInterface_crc() {
        return interface_crc;
    }

    public void setInterface_crc(String interface_crc) {
        this.interface_crc = interface_crc;
    }

    public String getInterface_in() {
        return interface_in;
    }

    public void setInterface_in(String interface_in) {
        this.interface_in = interface_in;
    }

    public String getInterface_out() {
        return interface_out;
    }

    public void setInterface_out(String interface_out) {
        this.interface_out = interface_out;
    }
     public String getRouter_type() {
        return router_type;
    }

    public void setRouter_type(String router_type) {
        this.router_type = router_type;
    }

}
