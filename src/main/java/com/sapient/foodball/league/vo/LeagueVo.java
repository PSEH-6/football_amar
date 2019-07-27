package com.sapient.foodball.league.vo;

import com.fasterxml.jackson.annotation.JsonGetter;

public class LeagueVo {

	private Integer countryId;
	private String countryName;
	private Integer leagueId;
	private String leagueName;
	private String leagueSeason;

	@JsonGetter("country_id")
	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

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

	@JsonGetter("league_season")
	public String getLeagueSeason() {
		return leagueSeason;
	}

	public void setLeagueSeason(String leagueSeason) {
		this.leagueSeason = leagueSeason;
	}

}
