package com.piseth.java.school.phoneshopenight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.piseth.java.school.phoneshopenight.entity.Colour;

@Repository
public interface ColourRespository extends JpaRepository<Colour, Long>,JpaSpecificationExecutor<Colour>{	

}
