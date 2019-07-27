package com.sapient.football.league.service;

import java.util.List;

import com.sapient.foodball.league.vo.LeagueTeamVo;

public interface LeagueService {
	public List<LeagueTeamVo> getLeagueStandings(String cName, String tName);
}
