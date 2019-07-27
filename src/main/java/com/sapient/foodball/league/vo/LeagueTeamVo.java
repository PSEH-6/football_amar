package com.sapient.foodball.league.vo;

import com.fasterxml.jackson.annotation.JsonGetter;

public class LeagueTeamVo {

	private Integer teamKey;
	private String teamName;
	private Integer countryId;
	private String countryName;
	private Integer leagueId;
	private String leagueName;

	@JsonGetter("team_key")
	public Integer getTeamKey() {
		return teamKey;
	}

	public void setTeamKey(Integer teamKey) {
		this.teamKey = teamKey;
	}

	@JsonGetter("team_name")
	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public Integer getLeagueId() {
		return leagueId;
	}

	public void setLeagueId(Integer leagueId) {
		this.leagueId = leagueId;
	}

	public String getLeagueName() {
		return leagueName;
	}

	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}

}
