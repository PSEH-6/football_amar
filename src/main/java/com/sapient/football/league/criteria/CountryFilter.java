package com.sapient.football.league.criteria;

import java.util.function.Predicate;

import com.sapient.foodball.league.vo.LeagueVo;

public class CountryFilter implements Predicate<LeagueVo> {
	private String countryName;

	public CountryFilter(String name) {
		this.countryName = name;
	}

	@Override
	public boolean test(LeagueVo vo) {
		return vo.getCountryName().equals(countryName);
	}
}
