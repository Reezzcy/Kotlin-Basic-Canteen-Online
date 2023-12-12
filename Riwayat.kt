class Riwayat {
    private var dataRiwayat = mutableListOf<Any>()
    private var listTotal = mutableListOf<Int>()

    fun tambahRiwayat(pesanan: Keranjang){
        listTotal.add(pesanan.getTotal())
        dataRiwayat.add(pesanan.getDataKeranjang())
    }

    fun tampilRiwayat(){
            var loop= 1
            for (i in dataRiwayat.indices) {
                val daftarKeranjang = dataRiwayat[i] as List<Menu>
                val totalHarga = listTotal[i]

                println("Pesanan $loop")
                println("| No \t| Nama Makanan \t    | Harga Makanan \t| Jumlah \t| Total")

                for ((index, menu) in daftarKeranjang.withIndex()) {
                    println("| ${index + 1} \t| ${menu.namaMakanan} \t| ${menu.hargaMakanan} \t\t\t| ${menu.jumlahMakanan} \t\t| ${menu.totalHarga}")
                }

                println("Total Pesanan: $totalHarga")
                loop++
        }
    }
}