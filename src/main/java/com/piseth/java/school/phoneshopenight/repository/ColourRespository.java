package com.piseth.java.school.phoneshopenight.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.piseth.java.school.phoneshopenight.entity.Colour;

@Repository
public interface ColourRespository extends JpaRepository<Colour, Long>,JpaSpecificationExecutor<Colour>{	

}
