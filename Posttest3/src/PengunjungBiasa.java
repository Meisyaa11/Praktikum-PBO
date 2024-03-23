public class PengunjungBiasa extends Pengunjung {
    private boolean diskon;

    public PengunjungBiasa(String nama, int usia, String tanggalKunjungan, boolean diskon) {
        super(nama, usia, tanggalKunjungan);
        this.diskon = diskon;
    }

    public boolean isDiskon() {
        return diskon;
    }

    public void setDiskon(boolean diskon) {
        this.diskon = diskon;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Diskon: " + (diskon ? "Ya" : "Tidak"));
        System.out.println("---------------------------------------");
    }
}