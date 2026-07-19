**APLIKASI PEMINJAMAN LAB PC**

Mata Kuliah : Pemrograman Berbasis Objek (PBO)
Semester : 4
Tahun Akademik : 2025/2026
Teknologi : Java, JSP, Servlet, MySQL, Apache Tomcat
Build Tool : Maven
IDE : Apache NetBeans



**DAFTAR ISI**

1. Software yang dibutuhkan
2. Tahap 1, install semua software
3. Tahap 2, setup database MySQL
4. Tahap 3, buka project di NetBeans
5. Tahap 4, konfigurasi koneksi database
6. Tahap 5, setup Tomcat di NetBeans
7. Tahap 6, set server untuk project
8. Tahap 7, build dan run project
9. Struktur package
10. Fitur aplikasi
11. Daftar validasi
12. Troubleshooting







1. **SOFTWARE YANG DIBUTUHKAN**



Semua software di bawah ini wajib diinstall semuanya, karena saling berkaitan satu sama lain.

* JDK (Java) versi 17 LTS, untuk mengkompilasi dan menjalankan kode Java. Download di adoptium.net
* Apache Tomcat versi 10.1, sebagai server yang menjalankan aplikasi web di browser. Download di tomcat.apache.org
* MySQL Server versi 8, sebagai database penyimpan data PC, peminjam, dan transaksi. Download di dev.mysql.com/downloads/installer
* MySQL Workbench versi 8, tool untuk menjalankan script SQL, sudah termasuk dalam installer MySQL
* Apache NetBeans IDE versi 21 atau lebih baru, sebagai IDE untuk menulis dan menjalankan kode







2. **TAHAP 1, INSTALL SEMUA SOFTWARE**



Lakukan berurutan dari atas ke bawah, jangan loncat urutan.



2.1 Install JDK 17

* Buka adoptium.net
* Pilih Temurin 17 LTS, klik tombol Latest Release
* Download installer sesuai OS, Windows pakai file msi, Mac pakai file pkg
* Jalankan installer, klik Next terus sampai Finish
* Cek instalasi dengan buka CMD atau Terminal, ketik java -version
Harus muncul output seperti openjdk version 17.x.x
Kalau belum muncul, restart komputer lalu coba lagi



2.2 Install Apache Tomcat 10.1

* Buka tomcat.apache.org
* Di panel kiri klik Tomcat 10, scroll ke bagian Binary Distributions
* Download, Windows pakai 32 bit atau 64 bit Windows Service Installer file exe, Mac atau Linux pakai Core tar.gz
* Jalankan installer Windows, saat muncul pilihan komponen centang semua lalu Next
* Port biarkan default 8080, lanjut Next
* Pada bagian Java Virtual Machine, klik Browse, arahkan ke folder JDK yang baru diinstall, contoh C:\\Program Files\\Eclipse Adoptium\\jdk-17.x.x-hotspot
* Pilih folder install, biarkan default, lalu Install
* Setelah selesai, jangan centang Run Apache Tomcat, klik Finish
* Catat lokasi folder Tomcat, contoh C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1, karena akan dibutuhkan saat konfigurasi di NetBeans



2.3 Install MySQL 8 dan MySQL Workbench

* Buka dev.mysql.com/downloads/installer
* Download MySQL Installer for Windows, pilih yang ukuran sekitar 2 MB, bukan yang full offline
* Jalankan installer, pilih Developer Default, klik Execute, tunggu proses download dan install selesai, bisa 5 sampai 10 menit
* Klik Next sampai tahap Accounts and Roles
* Isi MySQL Root Password dengan password yang mudah diingat
* Catat password ini baik baik, akan dibutuhkan di file DBConnection.java dan tidak bisa dikembalikan kalau lupa
* Klik Next terus, Execute, lalu Finish
* MySQL Workbench otomatis terinstall bersamaan dengan MySQL Server



2.4 Install Apache NetBeans

* Buka netbeans.apache.org/front/main/download
* Download versi terbaru, NetBeans 21 atau lebih baru, Windows pakai file exe, Mac pakai file pkg
* Jalankan installer, Next, Next
* Pada bagian JDK for the NetBeans IDE, pastikan mengarah ke JDK 17 yang sudah diinstall tadi, lalu Next, Install, Finish







3\.	**TAHAP 2, SETUP DATABASE MYSQL**



* Buka MySQL Workbench
* Klik koneksi Local instance MySQL 8.0 yang sudah ada otomatis
* Masukkan password MySQL yang dibuat saat install, klik OK
* Klik menu File, pilih Open SQL Script
* Cari dan pilih file labpc\_db.sql yang ada di folder database, di dalam folder project ini hasil ekstrak zip
* File SQL akan terbuka di editor, klik tombol Run All, ikonnya petir di toolbar, atau tekan Ctrl Shift Enter
* Tunggu sampai selesai, di panel Output bawah pastikan semua baris menampilkan status sukses tanpa error merah
* Di panel kiri Navigator, bagian Schemas, klik ikon Refresh
* Pastikan database labpc\_db sudah muncul dengan 3 tabel yaitu pc, peminjam, dan transaksi

Catatan, database sudah berisi 5 data PC awal, PC-01 sampai PC-05, sebagai contoh.







4. **TAHAP 3, BUKA PROJECT DI NETBEANS**



* Ekstrak file LabPCApp.zip ke folder yang mudah diakses, contoh C:\\ProyekKuliah\\LabPCApp
* Buka Apache NetBeans
* Klik menu File, pilih Open Project
* Arahkan ke folder LabPCApp hasil ekstrak tadi
* Klik Open Project, NetBeans akan otomatis mendeteksi ini sebagai project Maven
* Di panel Projects, klik kanan nama project LabPCApp
* Pilih Build with Dependencies
* Tunggu proses download dependency Maven selesai, butuh koneksi internet, lihat progress di panel Output bawah, tunggu sampai muncul tulisan BUILD SUCCESS







5\.	**TAHAP 4, KONFIGURASI KONEKSI DATABASE**



* Di panel Projects, expand folder sampai ketemu Source Packages, lalu com.labpc.repository, lalu file DBConnection.java
* Double klik DBConnection.java untuk membuka di editor
* Cari bagian berikut, sekitar baris 10 sampai 13

private static final String URL = jdbc:mysql://localhost:3306/labpc\_db?useSSL=false\&serverTimezone=Asia/Jakarta
private static final String USERNAME = root
private static final String PASSWORD = password (ini yang harus diganti)

* Ganti nilai PASSWORD dengan password MySQL kamu sendiri
Kalau saat install MySQL kamu tidak membuat password, isi saja dengan tanda kutip kosong
* Tekan Ctrl S untuk menyimpan







6\.	**TAHAP 5, SETUP TOMCAT DI NETBEANS**



* Klik menu Tools, pilih Servers
* Klik tombol Add Server
* Pada dropdown Server, pilih Apache Tomcat or TomEE, klik Next
* Pada Server Location, klik Browse
* Arahkan ke folder Tomcat yang diinstall tadi, contoh C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1, lalu klik Open
* Isi kolom Username dengan admin
* Isi kolom Password dengan admin, ini untuk Tomcat Manager, isi bebas
* Klik Finish
* Klik Close







7\.	**TAHAP 6, SET SERVER UNTUK PROJECT**



* Di panel Projects, klik kanan nama project LabPCApp
* Pilih Properties
* Di panel kiri jendela Properties, klik Run
* Pada dropdown Server, pilih Apache Tomcat 10.1 yang baru saja ditambahkan
* Pada Context Path, pastikan isinya slash LabPCApp
* Klik OK







8\.	**TAHAP 7, BUILD DAN RUN PROJECT**



Build terlebih dahulu

* Klik kanan nama project, pilih Clean and Build
* Tunggu sampai panel Output menampilkan tulisan BUILD SUCCESS



Jalankan project

* Klik kanan nama project, pilih Run, atau tekan tombol panah hijau di toolbar atas
* NetBeans akan otomatis membuka browser dan mengarah ke alamat
http://localhost:8080/LabPCApp/



Urutan testing yang benar

* Buka menu Data PC, tambah beberapa PC, contoh PC-01, PC-02
* Buka menu Transaksi, isi form peminjaman, klik Pinjam Sekarang
* Cek status PC berubah menjadi DIPINJAM
* Coba pinjam PC yang sama lagi, harus muncul pesan error double booking
* Klik tombol Kembalikan, status PC kembali menjadi TERSEDIA
* Cek riwayat transaksi, status transaksi berubah menjadi SELESAI







9\.	**STRUKTUR PACKAGE**



Folder utama project bernama LabPCApp, di dalamnya ada beberapa bagian.

Folder database, berisi file labpc\_db.sql yaitu script SQL untuk membuat database.

Folder src/main/java/com/labpc/model, berisi PC.java, Peminjam.java, dan Transaksi.java. Ini adalah class yang merepresentasikan data.

Folder src/main/java/com/labpc/repository, berisi DBConnection.java (tempat mengedit password), PCRepository.java, PeminjamRepository.java, dan TransaksiRepository.java. Bagian ini menangani akses ke database MySQL.

Folder src/main/java/com/labpc/service, berisi PCService.java, PeminjamService.java, dan TransaksiService.java. Bagian ini berisi logika bisnis dan validasi data.

Folder src/main/java/com/labpc/exception, berisi DataTidakValidException.java, PCTidakTersediaException.java, DoubleBookingException.java, dan TransaksiTidakAktifException.java. Ini adalah custom exception untuk menangani error.

Folder src/main/java/com/labpc/ui, berisi PCServlet.java, PeminjamServlet.java, dan TransaksiServlet.java. Ini adalah servlet yang berfungsi sebagai controller.

Folder src/main/webapp/css, berisi style.css untuk tampilan seluruh halaman.

Folder src/main/webapp/pc, berisi list.jsp yaitu halaman daftar dan tambah PC.

Folder src/main/webapp/peminjam, berisi list.jsp yaitu halaman daftar dan tambah peminjam.

Folder src/main/webapp/transaksi, berisi riwayat.jsp yaitu halaman form peminjaman dan riwayat.

Folder src/main/webapp/WEB-INF, berisi web.xml yaitu konfigurasi web application.

File src/main/webapp/index.jsp adalah halaman dashboard utama.

File pom.xml adalah konfigurasi Maven dan dependency.

File README.md adalah panduan ini.







10. **FITUR APLIKASI**



* Tambah unit PC baru, ada di halaman Data PC
* Hapus unit PC, ada di halaman Data PC
* Lihat status PC, TERSEDIA, DIPINJAM, atau MAINTENANCE, ada di halaman Data PC
* Tambah data peminjam, ada di halaman Data Peminjam
* Hapus data peminjam, ada di halaman Data Peminjam
* Buat transaksi peminjaman baru, ada di halaman Transaksi
* Kembalikan PC, menutup transaksi aktif, ada di halaman Transaksi
* Lihat riwayat semua transaksi, aktif maupun selesai, ada di halaman Transaksi







11. **DAFTAR VALIDASI**

* Kode PC tidak boleh kosong, ada di PCService.tambahPC()
* Kode PC harus unik, tidak boleh duplikat, ada di PCService.tambahPC()
* NIM tidak boleh kosong, ada di TransaksiService.buatPeminjaman()
* Durasi jam harus lebih dari 0, ada di TransaksiService.buatPeminjaman()
* PC harus berstatus TERSEDIA untuk bisa dipinjam, ada di TransaksiService.buatPeminjaman()
* Tidak boleh double booking pada PC yang sama, ada di TransaksiService.buatPeminjaman()
* Pengembalian hanya untuk transaksi yang masih aktif, ada di TransaksiService.kembalikan()







**12.	TROUBLESHOOTING**



* Kalau perintah java -version tidak dikenali, restart komputer setelah install JDK
* Kalau ada error koneksi database saat run, pastikan MySQL Server sedang berjalan, buka Services di Windows, cari MySQL80, lalu klik Start
* Kalau muncul pesan password salah di DBConnection.java, buka file tersebut, sesuaikan PASSWORD dengan password MySQL kamu
* Kalau port 8080 sudah dipakai, matikan aplikasi lain yang memakai port itu, atau restart komputer
* Kalau muncul BUILD FAILED saat Clean and Build, klik kanan project, pilih Maven, lalu Reload Project, lalu build ulang
* Kalau halaman tidak ditemukan atau error 404, pastikan Context Path di Project Properties sudah diisi slash LabPCApp
* Kalau Tomcat tidak muncul di daftar Server, ulangi Tahap 5, pastikan folder Tomcat yang dipilih benar







\***Catatan penting**: satu satunya file yang perlu diubah sebelum menjalankan aplikasi adalah DBConnection.java, sesuaikan PASSWORD dengan password MySQL yang dibuat saat install. File lainnya tidak perlu diubah.

