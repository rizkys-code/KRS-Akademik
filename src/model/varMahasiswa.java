/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author rizky
 */
public class varMahasiswa {
    private String vNIM;
    private String vNama;
    private String vAlamat;

    public String getvNIM() {
        return vNIM;
    }

    public void setvNIM(String vNIM) {
        this.vNIM = vNIM;
    }

    public String getvNama() {
        return vNama;
    }

    public void setvNama(String vNama) {
        this.vNama = vNama;
    }

    public String getvAlamat() {
        return vAlamat;
    }

    public void setvAlamat(String vAlamat) {
        this.vAlamat = vAlamat;
    }
}
