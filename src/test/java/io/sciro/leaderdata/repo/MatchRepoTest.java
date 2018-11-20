package io.sciro.leaderdata.repo;

import com.google.gson.Gson;
import io.sciro.leaderdata.LeaderDataApp;
import io.sciro.leaderdata.entity.Match;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static io.sciro.leaderdata.constant.Constant.APP_BASE_URL;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * PROJECT   : leader-data
 * PACKAGE   : io.sciro.leaderdata.repo
 * USER      : sean
 * DATE      : 17-Sat-Nov-2018
 * TIME      : 12:58
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration(value = "classpath:bootstrap.yml")
@SpringBootTest(classes = LeaderDataApp.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("dev")
public class MatchRepoTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(MatchRepoTest.class);

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;
    private static final String TEST_NAME = "Test-CodeName-1";

    @BeforeAll
    public static void setup(){
        LOGGER.info("Testing MatchRepo...");
    }

    @AfterAll
    public static void teardown(){
        LOGGER.info("Completed MatchRepo tests...");
    }

    @BeforeEach
    public void setUp() {
        this.mockMvc = webAppContextSetup(wac).build();
    }

    @Test
    public void matchRepoTest_1_saveNewMatch() throws Exception {
        final Date date = new Date();
        final Gson gson = new Gson();
        final Match match = new Match();
        match.setCodeName(TEST_NAME);
        match.setTimestamp(date);
        match.setCreated(date);
        match.setLastUpdated(date);
        match.setMe("ROCK");
        match.setPc("ROCK");
        match.setResult('D');
        match.setRound(1L);

//        Resource<Match> matchResource = new Resource<>(match);

        final String payload = gson.toJson(match);

        LOGGER.debug("Match: "+ match.toString());
// {"codeName":"Test-CodeName-1","round":1,"me":"ROCK","pc":"ROCK","result":"D","lastUpdated":"Nov 19, 2018, 9:44:11 PM","created":"Nov 19, 2018, 9:44:11 PM"}
        mockMvc.perform(post(APP_BASE_URL+"/matches")
                .content(payload)
                .contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void matchRepoTest_2_findById() throws Exception {
        mockMvc.perform(get(APP_BASE_URL+"/matches/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.parseMediaType("application/hal+json;charset=UTF-8")));
    }

    @Test
    public void matchRepoTest_3_findAllByCodeName() throws Exception {
        mockMvc.perform(get(APP_BASE_URL+"/matches?findAllByCodeName?codeName="+TEST_NAME)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.parseMediaType("application/hal+json;charset=UTF-8")));
    }


    @Test
    public void matchRepoTest_4_deleteById() throws Exception {
        mockMvc.perform(delete(APP_BASE_URL+"/matches/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void matchRepoTest_5_deleteAllByCodeName() throws Exception {
        final Gson gson = new Gson();
        final Match match = new Match();
        match.setCodeName(TEST_NAME);

        final String json = gson.toJson(match);

        mockMvc.perform(delete(APP_BASE_URL+"/matches/search/deleteAllByCodeName")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }


}