package com.iset.bp.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.iset.bp.entities.Operation;

	public interface OperationRepository extends JpaRepository<Operation,Long>{

		@Query("select o from Operation o where o.compte.numcompte=:x")
		public Page<Operation> listOperation(@Param("x")String codeCompte,Pageable pageable);
		
}
