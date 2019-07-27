package com.sapient.football.league.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.foodball.league.vo.LeagueTeamVo;
import com.sapient.foodball.league.vo.LeagueVo;
import com.sapient.football.league.criteria.CountryFilter;
import com.sapient.football.league.criteria.TeamFilter;

@Service("leagueService")
public class LeagueServiceImpl implements LeagueService {
	private static final Logger logger = LoggerFactory.getLogger(LeagueServiceImpl.class);

	@Resource
	private ObjectMapper objectMapper;

	// should be moved to application.properties
	private String API_KEY = "&APIkey=9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978";
	private String leagues_api_url = "https://apiv2.apifootball.com/?action=get_leagues";
	private String leagues_team_api_url = "https://apiv2.apifootball.com/?action=get_teams&league_id=";

	@Override
	public List<LeagueTeamVo> getLeagueStandings(String cName, String tName) {
		List<LeagueTeamVo> teams = new ArrayList<>();
		try {
			List<LeagueVo> leagues = getLeagues();

			logger.debug("got leagues " + leagues.size());

			Stream<LeagueVo> streams = leagues.parallelStream();
			// applying country filter
			if (cName != null) {
				logger.debug("applying country filter {}", cName);
				streams = streams.filter(new CountryFilter(cName));

			}

			streams.forEach(new Consumer<LeagueVo>() {
				@Override
				public void accept(final LeagueVo vo) {
					Integer leagueId = vo.getLeagueId();
					logger.debug("on leagueId {} ", leagueId);

					try {
						List<LeagueTeamVo> leagueTeams = getLeaguesTeamById(leagueId);
						logger.debug("got leagueTeam {}", leagues.size());

						Stream<LeagueTeamVo> leagueTeamStream = leagueTeams.parallelStream();
						// applying team filter
						if (tName != null) {
							logger.debug("applying team filter {}", tName);
							leagueTeamStream = leagueTeamStream.filter(new TeamFilter(tName));
						}

						leagueTeamStream.forEach(new Consumer<LeagueTeamVo>() {
							@Override
							public void accept(LeagueTeamVo vo1) {
								vo1.setCountryId(vo.getCountryId());
								vo1.setCountryName(vo.getCountryName());
								vo1.setLeagueId(vo.getLeagueId());
								vo1.setLeagueName(vo.getLeagueName());
								teams.add(vo1);
							}
						});
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}

				private List<LeagueTeamVo> getLeaguesTeamById(Integer leagueId)
						throws IOException, JsonParseException, JsonMappingException, MalformedURLException {
					List<LeagueTeamVo> leagueTeams = objectMapper.readValue(
							new URL(leagues_team_api_url + leagueId + API_KEY).openConnection().getInputStream(),
							new TypeReference<List<LeagueTeamVo>>() {
							});
					return leagueTeams;
				}
			});
		} catch (Exception e) {
			logger.error("failed to league data", e);
		}
		return teams;
	}

	private List<LeagueVo> getLeagues()
			throws IOException, JsonParseException, JsonMappingException, MalformedURLException {
		List<LeagueVo> leagues = objectMapper.readValue(
				new URL(leagues_api_url + API_KEY).openConnection().getInputStream(),
				new TypeReference<List<LeagueVo>>() {
				});
		return leagues;
	}

	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

}
