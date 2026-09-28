package com.srieshwar.bloodbank.repository;

import com.srieshwar.bloodbank.entity.IssueUnitMap;
import com.srieshwar.bloodbank.entity.IssueUnitMapId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueUnitMapRepository extends JpaRepository<IssueUnitMap, IssueUnitMapId> {
}
