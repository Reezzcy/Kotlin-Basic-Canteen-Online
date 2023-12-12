class Keranjang(daftarMenu: List<Menu>) {
    private var dataKeranjang = mutableListOf<Menu>()
    private var total: Int = 0
    private var daftarMenuMakanan: List<Menu> = daftarMenu

    fun masukanData(menuBaru: Int, jumlahMenu: Int){
        val inputMenu = daftarMenuMakanan[menuBaru-1]
        dataKeranjang.add(Menu(inputMenu.namaMakanan, inputMenu.hargaMakanan, jumlahMenu, inputMenu.hargaMakanan*jumlahMenu))
        println("$jumlahMenu ${inputMenu.namaMakanan.trim()} telah ditambahkan!")
    }

    fun hapusData(menuHapus: Int){
        if (menuHapus in 0..<dataKeranjang.size) {
            dataKeranjang.removeAt(menuHapus)
            println("Menu telah dihapus.")
        } else {
            println(" tidak valid.")
        }
    }

    fun pesanSekarang(){
        dataKeranjang = mutableListOf()
    }

    fun getDataKeranjang(): List<Menu>{
        return dataKeranjang
    }

    fun getTotal(): Int {
        return total
    }

    fun tampilKeranjang(){
        var loop = 1
        println("| No \t| Nama Makanan \t    | Harga Makanan \t| Jumlah \t| Total")
        dataKeranjang.forEach{
            println("| $loop \t| ${it.namaMakanan} \t| ${it.hargaMakanan} \t\t\t| ${it.jumlahMakanan} \t\t| ${it.totalHarga}")
            loop++
        }
        total = 0
        for (i in dataKeranjang){
            total += i.totalHarga
        }
        println("Total harga: $total")
    }
}