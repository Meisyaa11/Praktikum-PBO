import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

interface PengelolaData {
    void simpanData();
    static void hapusSemuaData(ArrayList<Pengunjung> dataPengunjung) {
        dataPengunjung.clear();
        System.out.println("Semua data pengunjung berhasil dihapus!");
    }
}

public class Main implements PengelolaData {
    private static final ArrayList<Pengunjung> dataPengunjung = new ArrayList<>();
    private static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    private static int indexPengunjung = 0;

    private static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void displayMenu() {
        clear();
        System.out.println("Menu:");
        System.out.println("1. Tambah data pengunjung");
        System.out.println("2. Tampilkan data pengunjung");
        System.out.println("3. Update data pengunjung");
        System.out.println("4. Hapus data pengunjung");
        System.out.println("5. Hapus semua data pengunjung");
        System.out.println("6. Keluar");
        System.out.print("Masukkan pilihan anda >> ");
    }

    public static void main(String[] args) throws IOException {
        while (true) {
            displayMenu();
            String inputStr = input.readLine();

            if (!inputStr.isEmpty()) {
                try {
                    int pilih = Integer.parseInt(inputStr);
                    switch (pilih) {
                        case 1:
                            tambahDataPengunjung();
                            break;
                        case 2:
                            tampilkanDataPengunjung();
                            break;
                        case 3:
                            tampilkanDataPengunjung();
                            updateDataPengunjung();
                            break;
                        case 4:
                            tampilkanDataPengunjung();
                            hapusDataPengunjung();
                            break;
                        case 5:
                            hapusSemuaData();
                            break;
                        case 6:
                            clear();
                            System.out.println("==========================================");
                            System.out.println("TERIMA KASIH TELAH MENGGUNAKAN PROGRAM INI");
                            System.out.println("==========================================");
                            System.exit(0);
                            break;
                        default:
                            clear();
                            System.out.println("Pilihan tidak valid, silakan masukkan angka antara 1 hingga 6.");
                            System.out.print("Tekan enter untuk melanjutkan...");
                            input.readLine();
                            break;
                    }
                } catch (NumberFormatException e) {
                    clear();
                    System.out.println("Masukkan angka!");
                    System.out.print("Tekan enter untuk melanjutkan...");
                    input.readLine();
                }
            } else {
                clear();
                System.out.println("Masukkan angka!");
                System.out.print("Tekan enter untuk melanjutkan...");
                input.readLine();
            }
        }
    }

    private static void tambahDataPengunjung() throws IOException {
        clear();
        System.out.println("======================");
        System.out.println("Tambah data pengunjung");
        System.out.println("======================\n");

        System.out.print("Masukkan jumlah pengunjung yang akan didata >> ");
        int batasan = Integer.parseInt(input.readLine());

        for (int i = 1; i <= batasan; i++) {
            System.out.println("\nData Pengunjung ke-" + (indexPengunjung + i) + ":");
            System.out.print("Masukkan Nama pengunjung >> ");
            String nama = input.readLine();
            System.out.print("Masukkan Usia pengunjung >> ");
            int usia = Integer.parseInt(input.readLine());
            System.out.print("Masukkan Tanggal Kunjungan (dd/mm/yyyy) >> ");
            String tanggal = input.readLine();

            System.out.print("Apakah pengunjung VIP? (y/n) >> ");
            String vip = input.readLine();
            if (vip.equalsIgnoreCase("y")) {
                System.out.print("Masukkan Member ID >> ");
                String memberID = input.readLine();
                dataPengunjung.add(new PengunjungVIP(nama, usia, tanggal, memberID));
            } else {
                System.out.print("Apakah mendapatkan diskon? (y/n) >> ");
                String diskonStr = input.readLine();
                boolean diskon = diskonStr.equalsIgnoreCase("y");
                dataPengunjung.add(new PengunjungBiasa(nama, usia, tanggal, diskon));
            }
        }

        indexPengunjung += batasan;

        System.out.println("\nData pengunjung telah ditambahkan!!!");
        System.out.print("Tekan enter untuk melanjutkan...");
        input.readLine();
    }

    private static void tampilkanDataPengunjung(boolean tambahkanEnter) throws IOException {
        clear();
        if (dataPengunjung.isEmpty()) {
            System.out.println("Tidak ada data pengunjung.\n");
        } else {
            System.out.println("Data Pengunjung:\n");
            for (Pengunjung pengunjung : dataPengunjung) {
                pengunjung.tampilkanInfo();
                System.out.println("Status: " + (pengunjung instanceof PengunjungVIP ? "VIP" : "Biasa"));
                System.out.println("---------------------------------------");
            }
        }

        if (tambahkanEnter) {
            System.out.print("Tekan enter untuk melanjutkan...");
            input.readLine();
        }
    }

    private static void tampilkanDataPengunjung() throws IOException {
        tampilkanDataPengunjung(true);
    }

    private static void updateDataPengunjung() throws IOException {
        if (dataPengunjung.isEmpty()) {
            System.out.println("Tidak ada data pengunjung untuk diperbarui.\n");
            System.out.print("Tekan enter untuk kembali...");
            input.readLine();
            return;
        }

        System.out.print("Masukkan nama pengunjung yang akan diupdate datanya >> ");
        String namaToUpdate = input.readLine();

        boolean found = false;
        for (Pengunjung pengunjung : dataPengunjung) {
            if (pengunjung.getNama().equalsIgnoreCase(namaToUpdate)) {
                System.out.print("Masukkan usia baru >> ");
                int newUsia = Integer.parseInt(input.readLine());
                pengunjung.setUsia(newUsia);
                System.out.println("Data pengunjung berhasil diupdate!\n");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Nama pengunjung tidak ditemukan!\n");
        }

        System.out.print("Tekan enter untuk kembali...");
        input.readLine();
    }

    private static void hapusDataPengunjung() throws IOException {
        if (dataPengunjung.isEmpty()) {
            System.out.println("Tidak ada data pengunjung untuk dihapus.\n");
            System.out.print("Tekan enter untuk kembali...");
            input.readLine();
            return;
        }

        System.out.print("Masukkan nama pengunjung yang akan dihapus datanya >> ");
        String namaToDelete = input.readLine();

        Iterator<Pengunjung> iterator = dataPengunjung.iterator();
        boolean found = false;
        while (iterator.hasNext()) {
            Pengunjung pengunjung = iterator.next();
            if (pengunjung.getNama().equalsIgnoreCase(namaToDelete)) {
                iterator.remove();
                System.out.println("Data pengunjung berhasil dihapus!\n");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Nama pengunjung tidak ditemukan!\n");
        }

        System.out.print("Tekan enter untuk kembali...");
        input.readLine();
    }

    @Override
    public void simpanData() {
        // Simpan data pengunjung ke dalam suatu tempat
        // Contoh sederhana hanya mencetak konfirmasi
        System.out.println("Data pengunjung berhasil disimpan.");
    }

    public static void hapusSemuaData() {
        PengelolaData.hapusSemuaData(dataPengunjung); // Panggil method static dari interface
        System.out.print("Tekan enter untuk melanjutkan...");
        try {
            input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}