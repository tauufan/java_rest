package com.bjb.api.repository2;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bjb.api.model2.CimDwh;

public interface CimDwhRepository extends JpaRepository<CimDwh, Long>{
	
	public List<CimDwh> findByBg0006(String nama_lengkap);
	public List<CimDwh> findByBgcus(String cif);
}
