import java.io.*
import java.util.Scanner
import kotlin.system.exitProcess

val scan = Scanner(System.`in`)

fun String?.convertToNullIfEmpty(): String? {
    return if (this.isNullOrEmpty()) null else this
}

fun hitungJumlahBaris(): Int {
    var jumlahBaris = 0

    try {
        val fileReader = FileReader("menu.txt")
        val bufferedReader = BufferedReader(fileReader)

        while (bufferedReader.readLine() != null) {
            jumlahBaris++
        }

        bufferedReader.close()
    } catch (e: Exception) {
        println("Terjadi kesalahan: ${e.message}")
    }

    return jumlahBaris
}

fun inputUser(){
    print("Masukan nama pengguna: ")
    val namaUser: String = scan.nextLine()
    val cekNullUser: String? = namaUser.convertToNullIfEmpty()
    if (cekNullUser != null){
        val akunUser = User(namaUser)
        akunUser.loginAkun()
        menuAplikasiUser()
    }else{
        println("Gagal Login!")
    }
}

fun inputAdmin(){
    print("Masukan nama admin: ")
    val namaAdmin: String = scan.nextLine()
    val cekNullAdmin: String? = namaAdmin.convertToNullIfEmpty()
    print("Masukan kata sandi: ")
    val kataSandi: String = scan.nextLine()
    val cekNullSandi: String? = kataSandi.convertToNullIfEmpty()
    if (cekNullAdmin != null && cekNullSandi != null){
        val akunAdmin = Admin(namaAdmin, kataSandi)
        if (akunAdmin.testSandi()){
            menuAplikasiAdmin()
        }
    }else {
        println("Gagal login!")
    }
}

fun loginAplikasi(){
    do {
        print("""
        [1] Masuk Sebagai Pengguna
        [2] Masuk Sebagai Admin
        [3] keluar
        Masukan pilihan anda [1-3]: """.trimIndent())
        val pilihAkun = scan.nextInt()
        scan.nextLine()
        when(pilihAkun){
            1 -> inputUser()
            2 -> inputAdmin()
            3 -> exitProcess(0)
            else -> println("Pilihan tidak tersedia!")
        }
    } while (true)
}

fun menuAplikasiAdmin(){
    val daftarMenu = ListMenu()
    val fileName = "menu.txt"

    var jumlahBaris = hitungJumlahBaris()
    try {
        val fileReader = FileReader(fileName)
        val bufferedReader = BufferedReader(fileReader)

        for (i in 1..jumlahBaris) {
            val line = bufferedReader.readLine()
            val data = line.split(";")
            val isiMenu = Menu(data[0], data[1].toInt())
            daftarMenu.masukkanList(isiMenu)
        }

        fileReader.close()
    } catch (e: FileNotFoundException){
        println("File tidak ditemukan")
    } catch (e: Exception) {
        println("Terjadi kesalahan: ${e.message}")
    }

    do{
        val menu : String = """
            [1] Daftar Menu
            [2] Tambahkan Menu
            [3] Hapus Menu
            [4] Keluar
        """.trimIndent()
        println("Selamat Datang di Aplikasi KantinOnline")
        println("Aplikasi Pesan Makanan Terpercaya!")
        println(menu)
        print("Masukan pilihan anda [1-4]: ")
        val pilihan: Int = scan.nextInt()
        scan.nextLine()
        when(pilihan){
            1 -> {
                println("Menu Makanan Tersedia")
                println("| No   | Nama Makanan       | Harga Makanan   ")
                daftarMenu.daftarMenuMakanan.let {
                    if (it.isNotEmpty()) {
                        it.forEach { menu ->
                            println(
                                """
                            | ${it.indexOf(menu) + 1}    | ${menu.namaMakanan}    | ${menu.hargaMakanan}           
                        """.trimIndent()
                            )
                        }
                    } else {
                        println("Daftar Menu Kosong")
                    }
                }
                println("Ketik apa saja untuk kembali")
                readlnOrNull()
            }
            2 -> {
                print("Masukan Nama Menu Baru: ")
                val namaMakanan: String = scan.nextLine()
                print("Masukan Harga Makanan: ")
                val hargaMakanan:Int = scan.nextInt()
                scan.nextLine()
                daftarMenu.tambahMenuMakanan(namaMakanan, hargaMakanan)
                jumlahBaris += 1
            }
            3 -> {
                print("Masukan Nama Menu yang Dihapus: ")
                val namaMakanan: String = scan.nextLine()
                daftarMenu.hapusMenuMakanan(namaMakanan)
                jumlahBaris -= 1
            }
            4 -> {
                try {
                    val fileWriter = FileWriter(fileName, false)

                    val listDaftarMenu = Array(jumlahBaris) { "menu" }
                    var loop:Int = 0
                    daftarMenu.daftarMenuMakanan.forEach{
                        listDaftarMenu[loop] = ("${it.namaMakanan};${it.hargaMakanan}")
                        loop += 1
                    }

                    for (menu in listDaftarMenu){
                        fileWriter.write(menu)
                        fileWriter.write(System.lineSeparator())
                    }

                    fileWriter.close()
                } catch (e: Exception) {
                    println("Terjadi kesalahan: ${e.message}")
                }
                exitProcess(0)
            }
        }
    }while (true)
}

fun menuAplikasiUser(){
    val daftarMenu = ListMenu()
    val fileName = "menu.txt"

    val jumlahBaris = hitungJumlahBaris()
    try {
        val fileReader = FileReader(fileName)
        val bufferedReader = BufferedReader(fileReader)

        for (i in 1..jumlahBaris) {
            val line = bufferedReader.readLine()
            val data = line.split(";")
            val isiMenu = Menu(data[0], data[1].toInt())
            daftarMenu.masukkanList(isiMenu)
        }

        fileReader.close()
    } catch (e: FileNotFoundException){
        println("File tidak ditemukan")
    } catch (e: Exception) {
        println("Terjadi kesalahan: ${e.message}")
    }

    val keranjangSaya = Keranjang(daftarMenu.daftarMenuMakanan)
    val riwayatSaya = Riwayat()

    do {
        val menu : String = """
            [1] Pilih Menu Makanan
            [2] Keranjang
            [3] Riwayat Pesanan
            [4] Keluar
        """.trimIndent()
        println("Selamat Datang di Aplikasi KantinOnline")
        println("Aplikasi Pesan Makanan Terpercaya!")
        println(menu)
        print("Masukan pilihan anda [1-4]: ")
        val pilihan: Int = scan.nextInt()
        when(pilihan){
            1 -> {
                println("Menu Makanan Tersedia")
                println("| No   | Nama Makanan       | Harga Makanan   ")
                daftarMenu.daftarMenuMakanan.let {
                    if (it.isNotEmpty()) {
                        it.forEach { menu ->
                            println(
                                """
                            | ${it.indexOf(menu) + 1}    | ${menu.namaMakanan}    | ${menu.hargaMakanan}           
                        """.trimIndent()
                            )
                        }
                    } else {
                        println("Daftar Menu Kosong")
                    }
                }
                println("[${jumlahBaris+1}] Kembail")
                do{
                    print("Pilih menu anda [1-${jumlahBaris+1}]: ")
                    val pilihMenu: Int = scan.nextInt()
                    if (pilihMenu in 1..jumlahBaris){
                        print("Jumlah menu anda: ")
                        val jumlahMenu: Int = scan.nextInt()
                        keranjangSaya.masukanData(pilihMenu, jumlahMenu)
                    }else if (pilihMenu == jumlahBaris+1){
                        break
                    }else {
                        println("Menu Tidak Tersedia!")
                    }} while (true)}
            2 -> {
                println("Keranjang")
                keranjangSaya.tampilKeranjang()
                println("""
                    [1] Hapus Makanan
                    [2] Pesan Semua
                    [3] Kembali
                """.trimIndent())
                do {
                    print("Pilih menu anda [1-3]: ")
                    val pilihKranjang: Int = scan.nextInt()
                    if (pilihKranjang == 1) {
                        print("Pilih menu yang ingin dihapus: ")
                        val hapusMenu = scan.nextInt()
                        keranjangSaya.hapusData(hapusMenu-1)
                    }else if (pilihKranjang == 2){
                        riwayatSaya.tambahRiwayat(keranjangSaya)
                        val total: Int = keranjangSaya.getTotal()
                        keranjangSaya.pesanSekarang()
                        println("Pesanan telah di pesan! Total pembayaran $total")
                    }else if (pilihKranjang == 3){
                        break
                    }else {
                        println("Menu Tidak Tersedia!")
                    }} while (true)}
            3 -> {
                println("Riwayat Pesanan")
                riwayatSaya.tampilRiwayat()
                println("Ketik apa saja untuk kembali")
                readlnOrNull()
            }
            4 -> exitProcess(0)
            else -> println("Pilihan Tidak Tersedia!")
        }
    } while (true)
}

fun main() {
    loginAplikasi()
}

//        Manual Pengguna
//
//        KantinOnline
//
//        1. Program akan menerima input dari pengguna aplikasi untuk menetukan ingin masuk sebagai admin/pembeli
//        2. Jika masuk sebagai pembeli, maka pengguna dapat menggunakan fitur  Pilih Menu Makanan, Lihat Keranjang, dan Lihat Riwayat.
//        3. Pilih Menu Makanan akan membuat objek makanan yang dipilih dan memasukkannya ke Keranjang.
//        4. Pada Keranjang pengguna dapat menghapus atau memesan menu yang telah dipilih.
//        5. Pada Riwayat terdapat daftar makanan yang telah dipesan sebelumnya.
//        6. Jika masuk sebagai admiin, maka pengguna dapat menggunakan fitur Lihat Daftar Menu, Tambah Menu, dan Hapus Menu.
//        7. Menu Lihat Daftar Menu digunakan untuk melihat daftar menu makanan saat ini.
//        8. Tambah Menu digunakan untuk menambahkan menu makanan pada Daftar Menu.
//        9. Hapus Menu digunakan untuk menghapus menu makanan pada Daftar Menu.