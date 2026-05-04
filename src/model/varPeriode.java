package model;

public class varPeriode {

    private String vTahunAjaran; // kolom TA di database
    private String vSemester;    // kolom Semester di database

    public String getvTahunAjaran() {
        return vTahunAjaran;
    }

    public void setvTahunAjaran(String vTahunAjaran) {
        this.vTahunAjaran = vTahunAjaran;
    }

    public String getvSemester() {
        return vSemester;
    }

    public void setvSemester(String vSemester) {
        this.vSemester = vSemester;
    }

}