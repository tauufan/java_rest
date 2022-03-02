package com.bjb.api.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bjb.api.model.Cim;

public interface CimRepository extends JpaRepository<Cim, Long>{
	public Cim findByCif(String cif);
	public List<Cim> findByNomorid(String nomorid);
	public List<Cim> findByCif_(String cif);
}
