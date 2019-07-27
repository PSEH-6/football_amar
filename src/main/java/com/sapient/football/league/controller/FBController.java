package com.sapient.football.league.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.foodball.league.vo.LeagueTeamVo;
import com.sapient.football.league.service.LeagueService;

@RestController
@RequestMapping("/")
public class FBController {

	@Resource
	private LeagueService leagueService;

	@RequestMapping(path = "/stats", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<LeagueTeamVo> stats(@RequestParam(name = "cn", required = false) String cName,
			@RequestParam(name = "tn", required = false) String tName) {
		return leagueService.getLeagueStandings(cName, tName);
	}
}
