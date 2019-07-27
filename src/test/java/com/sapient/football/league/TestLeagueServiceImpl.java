package com.sapient.football.league;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.foodball.league.vo.LeagueStandingVo;
import com.sapient.foodball.league.vo.LeagueTeamVo;
import com.sapient.foodball.league.vo.LeagueVo;
import com.sapient.football.league.service.LeagueServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestLeagueServiceImpl {

	private LeagueServiceImpl impl = new LeagueServiceImpl();

	@Mock
	private ObjectMapper objectMapper;

	@Before
	public void before() throws Exception {
		assertNotNull(objectMapper);
		impl.setObjectMapper(objectMapper);

		List<LeagueVo> leagues = new ArrayList<>();
		createLeague(leagues);

		List<LeagueTeamVo> leagueStandings = new ArrayList<>();
		createLeagueStandings(leagueStandings);

		when(objectMapper.readValue(any(InputStream.class), any(TypeReference.class))).thenReturn(leagues,
				leagueStandings, leagues, leagueStandings);

	}

	@SafeVarargs
	public static <List> Answer<List> getAnswerForSubsequentCalls(final List... mockArr) {
		return new Answer<List>() {
			private int count = 0, size = mockArr.length;

			public List answer(InvocationOnMock invocation) throws Throwable {
				List mock = null;
				for (; count < size && mock == null; count++) {
					mock = mockArr[count];
				}

				return mock;
			}
		};
	}

	private void createLeagueStandings(List<LeagueTeamVo> leagueStandings) {
		LeagueTeamVo vo = new LeagueTeamVo();
		vo.setTeamKey(100);
		vo.setTeamName("team100");
		leagueStandings.add(vo);
	}

	private void createLeague(List<LeagueVo> leagues) {
		LeagueVo vo = new LeagueVo();
		vo.setCountryId(1);
		vo.setCountryName("country1");
		vo.setLeagueId(1);
		vo.setLeagueName("league1");
		leagues.add(vo);
	}

	@Test
	public void testWithAllLeaguesTeams() {
		List<LeagueTeamVo> leagueTeams = impl.getLeagueStandings(null, null);

		assertNotNull(leagueTeams);
		assertFalse(leagueTeams.isEmpty());
		assertEquals(1, leagueTeams.size());
	}

	@Test
	public void testForCountries() {
		List<LeagueTeamVo> leagueTeams = impl.getLeagueStandings("country1", null);
		assertNotNull(leagueTeams);
		assertFalse(leagueTeams.isEmpty());
		assertEquals(1, leagueTeams.size());

		leagueTeams = impl.getLeagueStandings("country2", null);
		assertNotNull(leagueTeams);
		assertTrue(leagueTeams.isEmpty());
		assertEquals(0, leagueTeams.size());
	}

	@Test
	public void testWithTeam() {
		List<LeagueTeamVo> leagueTeams = impl.getLeagueStandings(null, "team100");

		assertNotNull(leagueTeams);
		assertFalse(leagueTeams.isEmpty());
		assertEquals(1, leagueTeams.size());

		leagueTeams = impl.getLeagueStandings(null, "team200");

		assertNotNull(leagueTeams);
		assertTrue(leagueTeams.isEmpty());
		assertEquals(0, leagueTeams.size());
	}
}
