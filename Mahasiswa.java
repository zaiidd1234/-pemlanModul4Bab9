public class Mahasiswa {
    private String namaLengkap;
    private String tanggalLahir;
    private String nomorPendaftaran;
    private String noTelp;
    private String alamat;
    private String email;

    public Mahasiswa(String namaLengkap, String tanggalLahir, String nomorPendaftaran, String noTelp, String alamat, String email) {
        this.namaLengkap = namaLengkap;
        this.tanggalLahir = tanggalLahir;
        this.nomorPendaftaran = nomorPendaftaran;
        this.noTelp = noTelp;
        this.alamat = alamat;
        this.email = email;
    }

    public String getNamaLengkap() {
        return namaLengkap; 
    }

    public String getTanggalLahir(){
        return tanggalLahir; 
    }

    public String getNomorPendaftaran(){
        return nomorPendaftaran; 
    }

    public String getNoTelp(){
        return noTelp; 
    }

    public String getAlamat(){ 
        return alamat; 
    }
    
    public String getEmail(){
        return email; 
    }
}