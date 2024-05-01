final class PengunjungVIP extends Pengunjung {
    private String memberID;

    public PengunjungVIP(String nama, int usia, String tanggalKunjungan, String memberID) {
        super(nama, usia, tanggalKunjungan);
        this.memberID = memberID;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("Nama: " + getNama());
        System.out.println("Usia: " + getUsia());
        System.out.println("Tanggal Kunjungan: " + getTanggalKunjungan());
        System.out.println("Member ID: " + memberID);
    }
}