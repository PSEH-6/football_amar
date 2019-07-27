package com.sapient.foodball.league.vo;

import com.fasterxml.jackson.annotation.JsonGetter;

public class LeagueStandingVo {

	private String countryName;
	private Integer leagueId;
	private String leagueName;
	private Integer teamId;
	private String teamName;
	private Integer overAllLeaguePosition;

	@JsonGetter("country_name")
	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	@JsonGetter("league_id")
	public Integer getLeagueId() {
		return leagueId;
	}

	public void setLeagueId(Integer leagueId) {
		this.leagueId = leagueId;
	}

	@JsonGetter("league_name")
	public String getLeagueName() {
		return leagueName;
	}

	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}

	@JsonGetter("team_id")
	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	@JsonGetter("team_name")
	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Integer getOverAllLeaguePosition() {
		return overAllLeaguePosition;
	}

	@JsonGetter("overall_league_position")
	public void setOverAllLeaguePosition(Integer overAllLeaguePosition) {
		this.overAllLeaguePosition = overAllLeaguePosition;
	}

}
