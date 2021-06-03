package com.iset.bp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iset.bp.entities.Client;


public interface ClientRepository extends JpaRepository<Client,Long>{

}
