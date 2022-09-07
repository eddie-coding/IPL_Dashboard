package com.eddie.ipldashboard.repository;

import org.springframework.data.repository.CrudRepository;

import com.eddie.ipldashboard.model.Team;

public interface TeamRepository extends CrudRepository<Team, Long>{

    Team findByTeamName(String teamName);   
}
