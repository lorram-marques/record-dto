package com.lorram.contactmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lorram.contactmanager.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

}
