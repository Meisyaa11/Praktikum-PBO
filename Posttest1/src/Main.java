import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

public class Main {
    private static ArrayList<Pengunjung> dataPengunjung = new ArrayList<>();
    private static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
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
        System.out.println("5. Keluar");
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
                            tampilkanDataPengunjung2();
                            updateDataPengunjung();
                            break;
                        case 4:
                            tampilkanDataPengunjung2();
                            hapusDataPengunjung();
                            break;
                        case 5:
                            clear();
                            System.out.println("==========================================");
                            System.out.println("TERIMA KASIH TELAH MENGGUNAKAN PROGRAM INI");
                            System.out.println("==========================================");
                            System.exit(0);
                            break;
                        default:
                            clear();
                            System.out.println("Pilihan tidak valid, silakan masukkan angka antara 1 hingga 5.");
                            System.out.println("Tekan enter untuk melanjutkan...");
                            input.readLine();
                            break;
                    }
                } catch (NumberFormatException e) {
                    clear();
                    System.out.println("Masukkan angka!");
                    System.out.println("Tekan enter untuk melanjutkan...");
                    input.readLine();
                }
            } else {
                clear();
                System.out.println("Masukkan angka!");
                System.out.println("Tekan enter untuk melanjutkan...");
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

            dataPengunjung.add(new Pengunjung(nama, usia, tanggal));
        }

        indexPengunjung += batasan;

        System.out.println("\nData pengunjung telah ditambahkan!!!");
        System.out.println("Tekan enter untuk melanjutkan...");
        input.readLine();
    }

    private static void tampilkanDataPengunjung() throws IOException {
        clear();
        if (dataPengunjung.isEmpty()) {
            System.out.println("Tidak ada data pengunjung.\n");
        } else {
            System.out.println("Data Pengunjung:\n");
            for (Pengunjung pengunjung : dataPengunjung) {
                pengunjung.tampilkanInfo();
            }
        }
        System.out.println("Tekan enter untuk melanjutkan...");
        input.readLine();
    }

    private static void tampilkanDataPengunjung2() throws IOException {
        clear();
        if (dataPengunjung.isEmpty()) {
            System.out.println("Tidak ada data pengunjung.\n");
        } else {
            System.out.println("Data Pengunjung:\n");
            for (Pengunjung pengunjung : dataPengunjung) {
                pengunjung.tampilkanInfo();
            }
        }
    }

    private static void updateDataPengunjung() throws IOException {
        if (dataPengunjung.isEmpty()) {
            System.out.println("Tidak ada data pengunjung untuk diperbarui.\n");
            System.out.println("Tekan enter untuk kembali...");
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

        System.out.println("Tekan enter untuk kembali...");
        input.readLine();
    }

    private static void hapusDataPengunjung() throws IOException {
        if (dataPengunjung.isEmpty()) {
            System.out.println("Tidak ada data pengunjung untuk dihapus.\n");
            System.out.println("Tekan enter untuk kembali...");
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

        System.out.println("Tekan enter untuk kembali...");
        input.readLine();
    }
}