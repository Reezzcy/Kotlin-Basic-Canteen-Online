class Admin(namaAkun: String, masukSandi: String): Akun(namaAkun){
    private val jenisAkun: String = "Admin"
    private val cobaSandi: String = masukSandi
    private val kataSandi: String = "111222333"

    override fun loginAkun() {
        super.loginAkun()
        println("Hai $namaAkun anda telah login sebagai $jenisAkun")
    }

    fun testSandi(): Boolean{
        return if (cobaSandi == kataSandi){
            loginAkun()
            true
        } else {
            println("Nama dan Sandi Tidak Sesuai!")
            false
        }
    }
}