abstract class Pengunjung {
    private String nama;
    private int usia;
    private String tanggalKunjungan;

    public Pengunjung(String nama, int usia, String tanggalKunjungan) {
        this.nama = nama;
        this.usia = usia;
        this.tanggalKunjungan = tanggalKunjungan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    protected int getUsia() {
        return usia;
    }

    public void setUsia(int usia) {
        this.usia = usia;
    }

    String getTanggalKunjungan() {
        return tanggalKunjungan;
    }

    public void setTanggalKunjungan(String tanggalKunjungan) {
        this.tanggalKunjungan = tanggalKunjungan;
    }

    public abstract void tampilkanInfo();
}