package com.bjb.api.controller;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bjb.api.exeption.error.RecordNotFoundException;
import com.bjb.api.helper.HelperClass;
import com.bjb.api.model.Cim;
import com.bjb.api.repository.CimRepository;
import com.bjb.api.service.AccountBalance.AccountBalance;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@Api(description="Customer Information & Maintenance")
public class CimController {
	
	private final static Logger logger = LoggerFactory.getLogger(CimController.class);
	
	@Autowired
    private CimRepository repository;
	
	// Find
    @GetMapping("/api/cim")
    @ApiOperation(value = "List Cim")
    public ResponseEntity<Map<String, Object>> getAllCims(
    		HttpServletRequest request,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
          ) throws JsonProcessingException{
        logger.info("Api "+request.getMethod()+" CIM "+request.getLocalAddr());
        //test ab
        AccountBalance ab = new AccountBalance();
        
        String res = ab.GetAccountBalance("0411920000100");
        
        System.out.println(res);
        //end test ab
        try {
          List<Cim> cim = new ArrayList<Cim>();
          Pageable paging = PageRequest.of(page, size);
          
          Page<Cim> pageTuts = null;
          pageTuts = repository.findAll(paging);
          cim = pageTuts.getContent();
          int i = 0;
          HelperClass hlp = new HelperClass();
          while (i < cim.size()) {
	    		cim.get(i).setTanggal_lahir_desc(hlp.ConvertFromCentury(cim.get(i).getTanggal_lahir()));
	        	cim.get(i).setTanggal_berakhir_kartu_id_desc(hlp.ConvertFromCentury(cim.get(i).getTanggal_berakhir_kartu_id()));
	        	cim.get(i).setTanggal_buka_nasabah_desc(hlp.ConvertFromCentury(cim.get(i).getTanggal_buka_nasabah()));
	        cim.get(i).setTanggal_berakhir_kitas_kitap_desc(hlp.ConvertFromCentury(cim.get(i).getTanggal_berakhir_kitas_kitap()));
	        	cim.get(i).setTanggal_lahir_pasangan_desc(hlp.ConvertFromCentury(cim.get(i).getTanggal_lahir_pasangan()));
	        	i++;
	      }
          
//          System.out.println(cim.size());
          Map<String, Object> response = new HashMap<>();
          response.put("cim", cim);
          response.put("currentPage", pageTuts.getNumber());
          response.put("totalItems", pageTuts.getTotalElements());
          response.put("totalPages", pageTuts.getTotalPages());
          return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
          return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
      }
	
//    @GetMapping("/api/cim/{cif}")
//    List<Cim> findbyCif(@PathVariable String cif) {
//    	List<Cim> cim = repository.findByCif_(cif);
//        if (cim == null) {
//        	throw new BookNotFoundException(cif);
//		}
//    	return cim;
//    }
    
    @GetMapping("/api/cim/{cif}")
    @ApiOperation(value = "Search CIM By CIF")
    List<Cim> findbyCif(HttpServletRequest request, @PathVariable String cif) {
    	
    	    List<Cim> cim = repository.findByCif_(cif);
	    	if (cim.isEmpty()) {
	        	throw new RecordNotFoundException(cif);
	    	}
	    	int i = 0;
	    	HelperClass hlp = new HelperClass();
	    	while (i < cim.size()) {
	    		cim.get(i).setTanggal_lahir_desc(hlp.ConvertFromCentury(cim.get(i).getTanggal_lahir()));
	        	cim.get(i).setTanggal_berakhir_kartu_id_desc(hlp.ConvertFromCentury(cim.get(i).getTanggal_berakhir_kartu_id()));
	        	cim.get(i).setTanggal_buka_nasabah_desc(hlp.ConvertFromCentury(cim.get(i).getTanggal_buka_nasabah()));
	        cim.get(i).setTanggal_berakhir_kitas_kitap_desc(hlp.ConvertFromCentury(cim.get(i).getTanggal_berakhir_kitas_kitap()));
	        	cim.get(i).setTanggal_lahir_pasangan_desc(hlp.ConvertFromCentury(cim.get(i).getTanggal_lahir_pasangan()));
	        	i++;
	        }
	    	return cim;
    }
    
    @GetMapping("/api/cim/find_by_nomor_id/{nomor_id}")
    @ApiOperation(value = "Search CIM By Id Number")
    List<Cim> findbyNomor_id(HttpServletRequest request, @PathVariable String nomor_id) {
    	
    	    List<Cim> cim = repository.findByNomorid(nomor_id);
	    	if (cim.isEmpty()) {
	        	throw new RecordNotFoundException(nomor_id);
	    	}
	    	int i = 0;
	    	HelperClass hlp = new HelperClass();
	    	while (i < cim.size()) {
	    		cim.get(i).setTanggal_lahir_desc(hlp.ConvertFromCentury(cim.get(i).getTanggal_lahir()));
	        	cim.get(i).setTanggal_berakhir_kartu_id_desc(hlp.ConvertFromCentury(cim.get(i).getTanggal_berakhir_kartu_id()));
	        	cim.get(i).setTanggal_buka_nasabah_desc(hlp.ConvertFromCentury(cim.get(i).getTanggal_buka_nasabah()));
	        cim.get(i).setTanggal_berakhir_kitas_kitap_desc(hlp.ConvertFromCentury(cim.get(i).getTanggal_berakhir_kitas_kitap()));
	        	cim.get(i).setTanggal_lahir_pasangan_desc(hlp.ConvertFromCentury(cim.get(i).getTanggal_lahir_pasangan()));
	        	i++;
	        }
	    	return cim;
    }
    
    //return 201 instead of 200
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/api/cim")
    @ApiOperation(value = "Add Cim")
    ResponseEntity<Object> newCim(@Valid @RequestBody Cim newCim) {
	    	List<Cim> cim = repository.findByCif_(newCim.getCif());
	    	Cim resp = new Cim();
	    	Timestamp now = Timestamp.from(Instant.now());
    		
	    	if (cim.isEmpty()) {
	    		newCim.setCreateby("API");
	    		newCim.setCreatedate(now);
	    		resp = repository.save(newCim);
	    	}else {
	    		newCim.setId(cim.get(0).getId());
	    		newCim.setUpdateby("API");
	    		newCim.setUpdatedate(now);
	    		resp = repository.save(newCim);
	    	}
        return new ResponseEntity<Object>(resp, HttpStatus.CREATED);
    }
    
    @DeleteMapping("/api/cim/deleteByCif/{cif}")
    @ApiOperation(value = "Delete CIM By CIF")
    void deleteCimbyCif(@PathVariable String cif) {
    	List<Cim> cim = repository.findByCif_(cif);
    	if (cim.isEmpty()) {
        	throw new RecordNotFoundException(cif);
    	}
    	repository.deleteById(cim.get(0).getId());
    }
    
    @DeleteMapping("/api/cim/deleteByid/{id}")
    @ApiOperation(value = "Delete CIM By id")
    void deleteCim(@PathVariable Long id) {
    	repository.deleteById(id);
    }
    
 // Save or update
    @PutMapping("/api/cim/bycif/{cif}")
    @ApiOperation(value = "Save Or Update CIM By CIF")
    Cim saveOrUpdateCim(@RequestBody Cim newCim, @PathVariable String cif) {
    			Cim getCif = repository.findByCif(cif);
    			Long id = getCif.getId();
    			
        		return repository.findById(id)
                .map(x -> {
//                    x.setCif(newCim.getCif());
                    x.setHubungan_dengan_bank(newCim.getHubungan_dengan_bank());
                    x.setTempat_lahir(newCim.getTanggal_lahir());
                    x.setTanggal_lahir(newCim.getTanggal_lahir());
                    x.setNama_lengkap(newCim.getNama_lengkap());
                    x.setKode_identitas(newCim.getKode_identitas());
                    x.setNomorid(newCim.getNomorid());
                    x.setTanggal_berakhir_kartu_id(newCim.getTanggal_berakhir_kartu_id());
                    x.setJenis_kelamin(newCim.getJenis_kelamin());
                    x.setPerkerjaan(newCim.getPerkerjaan());
                    x.setAlamat_tempat_berkerja(newCim.getAlamat_tempat_berkerja());
                    x.setKegiatan_usaha_tempat_berkerja(newCim.getKegiatan_usaha_tempat_berkerja());
                    x.setNpwp(newCim.getNpwp());
                    x.setJumlah_penghasilan(newCim.getJumlah_penghasilan());
                    x.setRekening_dibank_lain(newCim.getRekening_dibank_lain());
                    x.setAlamat_email(newCim.getAlamat_email());
                    x.setNo_hp(newCim.getNo_hp());
                    x.setStatus_perkawinan(newCim.getStatus_perkawinan());
                    x.setStatus_pendidikan(newCim.getStatus_pendidikan());
                    x.setAktivitas_transaksi_normal(newCim.getAktivitas_transaksi_normal());
                    x.setTujuan_penggunaan_dana(newCim.getTujuan_penggunaan_dana());
                    x.setGolongan_nasabah(newCim.getGolongan_nasabah());
                    x.setTanggal_buka_nasabah(newCim.getTanggal_buka_nasabah());
                    x.setContact_person(newCim.getContact_person());
                    x.setNama_ibu_kandung(newCim.getNama_ibu_kandung());
                    x.setGolongan_darah(newCim.getGolongan_darah());
                    x.setAlamat_domisili(newCim.getAlamat_domisili());
                    x.setNomor_kitas_kitap(newCim.getNomor_kitas_kitap());
                    x.setTanggal_berakhir_kitas_kitap(newCim.getTanggal_berakhir_kitas_kitap());
                    x.setSumber_pendapatan_dana(newCim.getSumber_pendapatan_dana());
                    x.setSumber_dana_bila_tidak_berkerja(newCim.getSumber_dana_bila_tidak_berkerja());
                    x.setNama_pasangan(newCim.getNama_pasangan());
                    x.setBerkerja_sebagai(newCim.getBerkerja_sebagai());
                    x.setBertindak_untuk(newCim.getBertindak_untuk());
                    x.setNip(newCim.getNip());
                    x.setPersetujuan_prod_layanan(newCim.getPersetujuan_prod_layanan());
                    x.setKewarganegaraan(newCim.getKewarganegaraan());
                    x.setStatus_kependudukan(newCim.getStatus_kependudukan());
                    x.setNik_pasangan(newCim.getNik_pasangan());
                    x.setTanggal_lahir_pasangan(newCim.getTanggal_lahir_pasangan());
                    x.setKode_pekerjaan(newCim.getKode_pekerjaan());
                    x.setTipe_nasabah(newCim.getTipe_nasabah());
                    x.setKode_tempat_lahir(newCim.getKode_tempat_lahir());
                    x.setKode_kabupaten_kota(newCim.getKode_kabupaten_kota());
                    x.setKode_bidang_usaha(newCim.getKode_bidang_usaha());
                    x.setPenghasilan_kotor_per_tahun(newCim.getPenghasilan_kotor_per_tahun());
                    x.setJumlah_tanggungan(newCim.getJumlah_tanggungan());
                    x.setPerjanjian_pisah_harta(newCim.getPerjanjian_pisah_harta());
                    x.setPenghasilan_pertahun(newCim.getPenghasilan_pertahun());
                    x.setIdentitas_pemilik_manfaat(newCim.getIdentitas_pemilik_manfaat());
                    x.setUpdateby("API");
                    Timestamp now = Timestamp.from(Instant.now());
                    x.setUpdatedate(now);
                    
                    return repository.save(x);
                })
                .orElseGet(() -> {
                    return repository.save(newCim);
                });
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
    
    
    
}
