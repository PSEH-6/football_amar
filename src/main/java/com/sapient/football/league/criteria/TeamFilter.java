package com.sapient.football.league.criteria;

import java.util.function.Predicate;

import com.sapient.foodball.league.vo.LeagueTeamVo;

public class TeamFilter implements Predicate<LeagueTeamVo> {
	private String teamName;

	public TeamFilter(String name) {
		this.teamName = name;
	}

	@Override
	public boolean test(LeagueTeamVo vo) {
		return vo.getTeamName().equals(teamName);
	}

}
