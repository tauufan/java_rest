package com.bjb.api.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Cim {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(value = "id", hidden = true)
	private Long id;
	@NotEmpty(message = "Tidak boleh kosong!")
    @Length(max = 6, min = 6)
	private String cif;
	private String hubungan_dengan_bank;
	private String tempat_lahir;
	private String tanggal_lahir;
	@Transient
	private String tanggal_lahir_desc;
	private String nama_lengkap;
	private String kode_identitas;
	private String nomor_id;
	private String tanggal_berakhir_kartu_id;
	@Transient
	private String tanggal_berakhir_kartu_id_desc;
	private String jenis_kelamin;
	private String perkerjaan;
	private String alamat_tempat_berkerja;
	private String kegiatan_usaha_tempat_berkerja;
	private String npwp;
	private String jumlah_penghasilan;
	private String rekening_dibank_lain;
	private String alamat_email;
	private String no_hp;
	private String status_perkawinan;
	private String status_pendidikan;
	private String aktivitas_transaksi_normal;
	private String tujuan_penggunaan_dana;
	private String golongan_nasabah;
	private String tanggal_buka_nasabah;
	@Transient
	private String tanggal_buka_nasabah_desc;
	private String contact_person;
	private String nama_ibu_kandung;
	private String golongan_darah;
	private String alamat_domisili;
	private String nomor_kitas_kitap;
	private String tanggal_berakhir_kitas_kitap;
	@Transient
	private String tanggal_berakhir_kitas_kitap_desc;
	private String sumber_pendapatan_dana;
	private String sumber_dana_bila_tidak_berkerja;
	private String nama_pasangan;
	private String berkerja_sebagai;
	private String bertindak_untuk;
	private String nip;
	private String persetujuan_prod_layanan;
	private String kewarganegaraan;
	private String status_kependudukan;
	private String nik_pasangan;
	private String tanggal_lahir_pasangan;
	@Transient
	private String tanggal_lahir_pasangan_desc;
	private String kode_pekerjaan;
	private String tipe_nasabah;
	private String kode_tempat_lahir;
	private String kode_kabupaten_kota;
	private String kode_bidang_usaha;
	private String penghasilan_kotor_per_tahun;
	private String jumlah_tanggungan;
	private String perjanjian_pisah_harta;
	private String penghasilan_pertahun;
	private String identitas_pemilik_manfaat;
	@ApiModelProperty(value = "createby", hidden = true)
	private String createby;
	@ApiModelProperty(value = "createdate", hidden = true)
	private Timestamp createdate;
	@ApiModelProperty(value = "updateby", hidden = true)
	private String updateby;
	@ApiModelProperty(value = "updatedate", hidden = true)
	private Timestamp updatedate;
	
	public Cim() {
    }
	
	public Cim(@NotBlank String cif, String hubungan_dengan_bank, String tempat_lahir, String tanggal_lahir,
			String tanggal_lahir_desc, String nama_lengkap, String kode_identitas, String nomor_id,
			String tanggal_berakhir_kartu_id, String tanggal_berakhir_kartu_id_desc, String jenis_kelamin,
			String perkerjaan, String alamat_tempat_berkerja, String kegiatan_usaha_tempat_berkerja, String npwp,
			String jumlah_penghasilan, String rekening_dibank_lain, String alamat_email, String no_hp,
			String status_perkawinan, String status_pendidikan, String aktivitas_transaksi_normal,
			String tujuan_penggunaan_dana, String golongan_nasabah, String tanggal_buka_nasabah,
			String tanggal_buka_nasabah_desc, String contact_person, String nama_ibu_kandung, String golongan_darah,
			String alamat_domisili, String nomor_kitas_kitap, String tanggal_berakhir_kitas_kitap,
			String tanggal_berakhir_kitas_kitap_desc, String sumber_pendapatan_dana,
			String sumber_dana_bila_tidak_berkerja, String nama_pasangan, String berkerja_sebagai,
			String bertindak_untuk, String nip, String persetujuan_prod_layanan, String kewarganegaraan,
			String status_kependudukan, String nik_pasangan, String tanggal_lahir_pasangan,
			String tanggal_lahir_pasangan_desc, String kode_pekerjaan, String tipe_nasabah, String kode_tempat_lahir,
			String kode_kabupaten_kota, String kode_bidang_usaha, String penghasilan_kotor_per_tahun,
			String jumlah_tanggungan, String perjanjian_pisah_harta, String penghasilan_pertahun,
			String identitas_pemilik_manfaat, String createby, Timestamp createdate, String updateby,
			Timestamp updatedate) {
		super();
		this.cif = cif;
		this.hubungan_dengan_bank = hubungan_dengan_bank;
		this.tempat_lahir = tempat_lahir;
		this.tanggal_lahir = tanggal_lahir;
		this.tanggal_lahir_desc = tanggal_lahir_desc;
		this.nama_lengkap = nama_lengkap;
		this.kode_identitas = kode_identitas;
		this.nomor_id = nomor_id;
		this.tanggal_berakhir_kartu_id = tanggal_berakhir_kartu_id;
		this.tanggal_berakhir_kartu_id_desc = tanggal_berakhir_kartu_id_desc;
		this.jenis_kelamin = jenis_kelamin;
		this.perkerjaan = perkerjaan;
		this.alamat_tempat_berkerja = alamat_tempat_berkerja;
		this.kegiatan_usaha_tempat_berkerja = kegiatan_usaha_tempat_berkerja;
		this.npwp = npwp;
		this.jumlah_penghasilan = jumlah_penghasilan;
		this.rekening_dibank_lain = rekening_dibank_lain;
		this.alamat_email = alamat_email;
		this.no_hp = no_hp;
		this.status_perkawinan = status_perkawinan;
		this.status_pendidikan = status_pendidikan;
		this.aktivitas_transaksi_normal = aktivitas_transaksi_normal;
		this.tujuan_penggunaan_dana = tujuan_penggunaan_dana;
		this.golongan_nasabah = golongan_nasabah;
		this.tanggal_buka_nasabah = tanggal_buka_nasabah;
		this.tanggal_buka_nasabah_desc = tanggal_buka_nasabah_desc;
		this.contact_person = contact_person;
		this.nama_ibu_kandung = nama_ibu_kandung;
		this.golongan_darah = golongan_darah;
		this.alamat_domisili = alamat_domisili;
		this.nomor_kitas_kitap = nomor_kitas_kitap;
		this.tanggal_berakhir_kitas_kitap = tanggal_berakhir_kitas_kitap;
		this.tanggal_berakhir_kitas_kitap_desc = tanggal_berakhir_kitas_kitap_desc;
		this.sumber_pendapatan_dana = sumber_pendapatan_dana;
		this.sumber_dana_bila_tidak_berkerja = sumber_dana_bila_tidak_berkerja;
		this.nama_pasangan = nama_pasangan;
		this.berkerja_sebagai = berkerja_sebagai;
		this.bertindak_untuk = bertindak_untuk;
		this.nip = nip;
		this.persetujuan_prod_layanan = persetujuan_prod_layanan;
		this.kewarganegaraan = kewarganegaraan;
		this.status_kependudukan = status_kependudukan;
		this.nik_pasangan = nik_pasangan;
		this.tanggal_lahir_pasangan = tanggal_lahir_pasangan;
		this.tanggal_lahir_pasangan_desc = tanggal_lahir_pasangan_desc;
		this.kode_pekerjaan = kode_pekerjaan;
		this.tipe_nasabah = tipe_nasabah;
		this.kode_tempat_lahir = kode_tempat_lahir;
		this.kode_kabupaten_kota = kode_kabupaten_kota;
		this.kode_bidang_usaha = kode_bidang_usaha;
		this.penghasilan_kotor_per_tahun = penghasilan_kotor_per_tahun;
		this.jumlah_tanggungan = jumlah_tanggungan;
		this.perjanjian_pisah_harta = perjanjian_pisah_harta;
		this.penghasilan_pertahun = penghasilan_pertahun;
		this.identitas_pemilik_manfaat = identitas_pemilik_manfaat;
		this.createby = createby;
		this.createdate = createdate;
		this.updateby = updateby;
		this.updatedate = updatedate;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getCif() {
		return cif;
	}


	public void setCif(String cif) {
		this.cif = cif;
	}


	public String getHubungan_dengan_bank() {
		return hubungan_dengan_bank;
	}


	public void setHubungan_dengan_bank(String hubungan_dengan_bank) {
		this.hubungan_dengan_bank = hubungan_dengan_bank;
	}


	public String getTempat_lahir() {
		return tempat_lahir;
	}


	public void setTempat_lahir(String tempat_lahir) {
		this.tempat_lahir = tempat_lahir;
	}


	public String getTanggal_lahir() {
		return tanggal_lahir;
	}


	public void setTanggal_lahir(String tanggal_lahir) {
		this.tanggal_lahir = tanggal_lahir;
	}


	public String getTanggal_lahir_desc() {
		return tanggal_lahir_desc;
	}


	public void setTanggal_lahir_desc(String tanggal_lahir_desc) {
		this.tanggal_lahir_desc = tanggal_lahir_desc;
	}


	public String getNama_lengkap() {
		return nama_lengkap;
	}


	public void setNama_lengkap(String nama_lengkap) {
		this.nama_lengkap = nama_lengkap;
	}


	public String getKode_identitas() {
		return kode_identitas;
	}


	public void setKode_identitas(String kode_identitas) {
		this.kode_identitas = kode_identitas;
	}


	public String getNomor_id() {
		return nomor_id;
	}


	public void setNomor_id(String nomor_id) {
		this.nomor_id = nomor_id;
	}


	public String getTanggal_berakhir_kartu_id() {
		return tanggal_berakhir_kartu_id;
	}


	public void setTanggal_berakhir_kartu_id(String tanggal_berakhir_kartu_id) {
		this.tanggal_berakhir_kartu_id = tanggal_berakhir_kartu_id;
	}


	public String getTanggal_berakhir_kartu_id_desc() {
		return tanggal_berakhir_kartu_id_desc;
	}


	public void setTanggal_berakhir_kartu_id_desc(String tanggal_berakhir_kartu_id_desc) {
		this.tanggal_berakhir_kartu_id_desc = tanggal_berakhir_kartu_id_desc;
	}


	public String getJenis_kelamin() {
		return jenis_kelamin;
	}


	public void setJenis_kelamin(String jenis_kelamin) {
		this.jenis_kelamin = jenis_kelamin;
	}


	public String getPerkerjaan() {
		return perkerjaan;
	}


	public void setPerkerjaan(String perkerjaan) {
		this.perkerjaan = perkerjaan;
	}


	public String getAlamat_tempat_berkerja() {
		return alamat_tempat_berkerja;
	}


	public void setAlamat_tempat_berkerja(String alamat_tempat_berkerja) {
		this.alamat_tempat_berkerja = alamat_tempat_berkerja;
	}


	public String getKegiatan_usaha_tempat_berkerja() {
		return kegiatan_usaha_tempat_berkerja;
	}


	public void setKegiatan_usaha_tempat_berkerja(String kegiatan_usaha_tempat_berkerja) {
		this.kegiatan_usaha_tempat_berkerja = kegiatan_usaha_tempat_berkerja;
	}


	public String getNpwp() {
		return npwp;
	}


	public void setNpwp(String npwp) {
		this.npwp = npwp;
	}


	public String getJumlah_penghasilan() {
		return jumlah_penghasilan;
	}


	public void setJumlah_penghasilan(String jumlah_penghasilan) {
		this.jumlah_penghasilan = jumlah_penghasilan;
	}


	public String getRekening_dibank_lain() {
		return rekening_dibank_lain;
	}


	public void setRekening_dibank_lain(String rekening_dibank_lain) {
		this.rekening_dibank_lain = rekening_dibank_lain;
	}


	public String getAlamat_email() {
		return alamat_email;
	}


	public void setAlamat_email(String alamat_email) {
		this.alamat_email = alamat_email;
	}


	public String getNo_hp() {
		return no_hp;
	}


	public void setNo_hp(String no_hp) {
		this.no_hp = no_hp;
	}


	public String getStatus_perkawinan() {
		return status_perkawinan;
	}


	public void setStatus_perkawinan(String status_perkawinan) {
		this.status_perkawinan = status_perkawinan;
	}


	public String getStatus_pendidikan() {
		return status_pendidikan;
	}


	public void setStatus_pendidikan(String status_pendidikan) {
		this.status_pendidikan = status_pendidikan;
	}


	public String getAktivitas_transaksi_normal() {
		return aktivitas_transaksi_normal;
	}


	public void setAktivitas_transaksi_normal(String aktivitas_transaksi_normal) {
		this.aktivitas_transaksi_normal = aktivitas_transaksi_normal;
	}


	public String getTujuan_penggunaan_dana() {
		return tujuan_penggunaan_dana;
	}


	public void setTujuan_penggunaan_dana(String tujuan_penggunaan_dana) {
		this.tujuan_penggunaan_dana = tujuan_penggunaan_dana;
	}


	public String getGolongan_nasabah() {
		return golongan_nasabah;
	}


	public void setGolongan_nasabah(String golongan_nasabah) {
		this.golongan_nasabah = golongan_nasabah;
	}


	public String getTanggal_buka_nasabah() {
		return tanggal_buka_nasabah;
	}


	public void setTanggal_buka_nasabah(String tanggal_buka_nasabah) {
		this.tanggal_buka_nasabah = tanggal_buka_nasabah;
	}


	public String getTanggal_buka_nasabah_desc() {
		return tanggal_buka_nasabah_desc;
	}


	public void setTanggal_buka_nasabah_desc(String tanggal_buka_nasabah_desc) {
		this.tanggal_buka_nasabah_desc = tanggal_buka_nasabah_desc;
	}


	public String getContact_person() {
		return contact_person;
	}


	public void setContact_person(String contact_person) {
		this.contact_person = contact_person;
	}


	public String getNama_ibu_kandung() {
		return nama_ibu_kandung;
	}


	public void setNama_ibu_kandung(String nama_ibu_kandung) {
		this.nama_ibu_kandung = nama_ibu_kandung;
	}


	public String getGolongan_darah() {
		return golongan_darah;
	}


	public void setGolongan_darah(String golongan_darah) {
		this.golongan_darah = golongan_darah;
	}


	public String getAlamat_domisili() {
		return alamat_domisili;
	}


	public void setAlamat_domisili(String alamat_domisili) {
		this.alamat_domisili = alamat_domisili;
	}


	public String getNomor_kitas_kitap() {
		return nomor_kitas_kitap;
	}


	public void setNomor_kitas_kitap(String nomor_kitas_kitap) {
		this.nomor_kitas_kitap = nomor_kitas_kitap;
	}


	public String getTanggal_berakhir_kitas_kitap() {
		return tanggal_berakhir_kitas_kitap;
	}


	public void setTanggal_berakhir_kitas_kitap(String tanggal_berakhir_kitas_kitap) {
		this.tanggal_berakhir_kitas_kitap = tanggal_berakhir_kitas_kitap;
	}


	public String getTanggal_berakhir_kitas_kitap_desc() {
		return tanggal_berakhir_kitas_kitap_desc;
	}


	public void setTanggal_berakhir_kitas_kitap_desc(String tanggal_berakhir_kitas_kitap_desc) {
		this.tanggal_berakhir_kitas_kitap_desc = tanggal_berakhir_kitas_kitap_desc;
	}


	public String getSumber_pendapatan_dana() {
		return sumber_pendapatan_dana;
	}


	public void setSumber_pendapatan_dana(String sumber_pendapatan_dana) {
		this.sumber_pendapatan_dana = sumber_pendapatan_dana;
	}


	public String getSumber_dana_bila_tidak_berkerja() {
		return sumber_dana_bila_tidak_berkerja;
	}


	public void setSumber_dana_bila_tidak_berkerja(String sumber_dana_bila_tidak_berkerja) {
		this.sumber_dana_bila_tidak_berkerja = sumber_dana_bila_tidak_berkerja;
	}


	public String getNama_pasangan() {
		return nama_pasangan;
	}


	public void setNama_pasangan(String nama_pasangan) {
		this.nama_pasangan = nama_pasangan;
	}


	public String getBerkerja_sebagai() {
		return berkerja_sebagai;
	}


	public void setBerkerja_sebagai(String berkerja_sebagai) {
		this.berkerja_sebagai = berkerja_sebagai;
	}


	public String getBertindak_untuk() {
		return bertindak_untuk;
	}


	public void setBertindak_untuk(String bertindak_untuk) {
		this.bertindak_untuk = bertindak_untuk;
	}


	public String getNip() {
		return nip;
	}


	public void setNip(String nip) {
		this.nip = nip;
	}


	public String getPersetujuan_prod_layanan() {
		return persetujuan_prod_layanan;
	}


	public void setPersetujuan_prod_layanan(String persetujuan_prod_layanan) {
		this.persetujuan_prod_layanan = persetujuan_prod_layanan;
	}


	public String getKewarganegaraan() {
		return kewarganegaraan;
	}


	public void setKewarganegaraan(String kewarganegaraan) {
		this.kewarganegaraan = kewarganegaraan;
	}


	public String getStatus_kependudukan() {
		return status_kependudukan;
	}


	public void setStatus_kependudukan(String status_kependudukan) {
		this.status_kependudukan = status_kependudukan;
	}


	public String getNik_pasangan() {
		return nik_pasangan;
	}


	public void setNik_pasangan(String nik_pasangan) {
		this.nik_pasangan = nik_pasangan;
	}


	public String getTanggal_lahir_pasangan() {
		return tanggal_lahir_pasangan;
	}


	public void setTanggal_lahir_pasangan(String tanggal_lahir_pasangan) {
		this.tanggal_lahir_pasangan = tanggal_lahir_pasangan;
	}


	public String getTanggal_lahir_pasangan_desc() {
		return tanggal_lahir_pasangan_desc;
	}


	public void setTanggal_lahir_pasangan_desc(String tanggal_lahir_pasangan_desc) {
		this.tanggal_lahir_pasangan_desc = tanggal_lahir_pasangan_desc;
	}



	public String getKode_pekerjaan() {
		return kode_pekerjaan;
	}



	public void setKode_pekerjaan(String kode_pekerjaan) {
		this.kode_pekerjaan = kode_pekerjaan;
	}



	public String getTipe_nasabah() {
		return tipe_nasabah;
	}



	public void setTipe_nasabah(String tipe_nasabah) {
		this.tipe_nasabah = tipe_nasabah;
	}



	public String getKode_tempat_lahir() {
		return kode_tempat_lahir;
	}



	public void setKode_tempat_lahir(String kode_tempat_lahir) {
		this.kode_tempat_lahir = kode_tempat_lahir;
	}



	public String getKode_kabupaten_kota() {
		return kode_kabupaten_kota;
	}



	public void setKode_kabupaten_kota(String kode_kabupaten_kota) {
		this.kode_kabupaten_kota = kode_kabupaten_kota;
	}



	public String getKode_bidang_usaha() {
		return kode_bidang_usaha;
	}



	public void setKode_bidang_usaha(String kode_bidang_usaha) {
		this.kode_bidang_usaha = kode_bidang_usaha;
	}



	public String getPenghasilan_kotor_per_tahun() {
		return penghasilan_kotor_per_tahun;
	}



	public void setPenghasilan_kotor_per_tahun(String penghasilan_kotor_per_tahun) {
		this.penghasilan_kotor_per_tahun = penghasilan_kotor_per_tahun;
	}



	public String getJumlah_tanggungan() {
		return jumlah_tanggungan;
	}



	public void setJumlah_tanggungan(String jumlah_tanggungan) {
		this.jumlah_tanggungan = jumlah_tanggungan;
	}



	public String getPerjanjian_pisah_harta() {
		return perjanjian_pisah_harta;
	}



	public void setPerjanjian_pisah_harta(String perjanjian_pisah_harta) {
		this.perjanjian_pisah_harta = perjanjian_pisah_harta;
	}



	public String getPenghasilan_pertahun() {
		return penghasilan_pertahun;
	}



	public void setPenghasilan_pertahun(String penghasilan_pertahun) {
		this.penghasilan_pertahun = penghasilan_pertahun;
	}



	public String getIdentitas_pemilik_manfaat() {
		return identitas_pemilik_manfaat;
	}



	public void setIdentitas_pemilik_manfaat(String identitas_pemilik_manfaat) {
		this.identitas_pemilik_manfaat = identitas_pemilik_manfaat;
	}


	public String getCreateby() {
		return createby;
	}


	public void setCreateby(String createby) {
		this.createby = createby;
	}


	public Timestamp getCreatedate() {
		return createdate;
	}


	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}


	public String getUpdateby() {
		return updateby;
	}


	public void setUpdateby(String updateby) {
		this.updateby = updateby;
	}


	public Timestamp getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Timestamp updatedate) {
		this.updatedate = updatedate;
	}

	
	
}
