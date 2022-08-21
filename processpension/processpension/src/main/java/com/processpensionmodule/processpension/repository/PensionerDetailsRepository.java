package com.processpensionmodule.processpension.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.processpensionmodule.processpension.model.PensionerInput;



@Repository
public interface PensionerDetailsRepository extends JpaRepository<PensionerInput, String> {

}