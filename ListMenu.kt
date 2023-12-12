class ListMenu {
    var daftarMenuMakanan = mutableListOf<Menu>()

    fun masukkanList(menuMakanan: Menu){
        daftarMenuMakanan.add(menuMakanan)
    }

    fun tambahMenuMakanan(namaMakanan: String, hargaMakanan:Int){
        val formatMakanan: String = namaMakanan.padEnd(15)
        daftarMenuMakanan.add(Menu(formatMakanan, hargaMakanan))
        println("Menu $namaMakanan telah ditambahkan!")
    }

    fun hapusMenuMakanan(namaMakanan: String){
        daftarMenuMakanan.removeIf { it.namaMakanan == namaMakanan.padEnd(15)}
        println("Menu $namaMakanan telah dihapus!")
    }
}