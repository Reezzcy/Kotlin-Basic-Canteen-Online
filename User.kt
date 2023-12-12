class User(namaAkun: String): Akun(namaAkun){
    private val jenisAkun: String = "User"

    override fun loginAkun() {
        super.loginAkun()
        println("Hai $namaAkun anda telah login sebagai $jenisAkun")
    }
}