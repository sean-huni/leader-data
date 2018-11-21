package io.sciro.leaderdata.repo;

import com.google.gson.Gson;
import io.sciro.leaderdata.LeaderDataApp;
import io.sciro.leaderdata.entity.Match;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static io.sciro.leaderdata.constant.Constant.APP_BASE_URL;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
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
@WebAppConfiguration(value = "classpath:bootstrap.yml")
@SpringBootTest(classes = LeaderDataApp.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ActiveProfiles("dev")
public class MatchRepoTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(MatchRepoTest.class);
    private static final String TEST_NAME_1 = "Test-CodeName-1";
    private static final String TEST_NAME_2 = "Test-CodeName-2";
    private static int id;

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @BeforeClass
    public static void setup() {
        LOGGER.info("Testing MatchRepo...");
    }

    @AfterClass
    public static void teardown() {
        LOGGER.info("Completed MatchRepo tests...");
    }

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(wac).build();
    }

    @Test
    public void a_saveNewMatch() throws Exception {
        final Date date = new Date();
        final Match match1 = new Match(TEST_NAME_1, 1L, "SCISSORS", "PAPER", 'W', date, date, date);
        final Match match2 = new Match(TEST_NAME_2, 1L, "ROCK", "PAPER", 'L', new Date(), new Date(), new Date());
        final Match match3 = new Match(TEST_NAME_1, 2L, "PAPER", "PAPER", 'D', new Date(), new Date(), new Date());
        final Match match4 = new Match(TEST_NAME_1, 3L, "PAPER", "ROCK", 'W', new Date(), new Date(), new Date());

        List<Match> matchList = new ArrayList<>();
        matchList.add(match1);
        matchList.add(match3);
        matchList.add(match2);
        matchList.add(match4);

        for (Match match : matchList) {
            final String payload = new Gson().toJson(match);

            LOGGER.debug("Matches: " + payload);

            MvcResult results = mockMvc.perform(post(APP_BASE_URL + "/matches")
                    .content(payload)
                    .contentType(MediaType.APPLICATION_JSON)).andDo(print())
                    .andExpect(status().isCreated())
                    .andReturn();

            String resp = results.getResponse().getHeader("Location");
            int pos = resp.lastIndexOf("/");
            pos++;
            id = Integer.parseInt(resp.substring(pos));
        }

    }

    @Test
    public void b_findById() throws Exception {
        mockMvc.perform(get(APP_BASE_URL + "/matches/"+id)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.parseMediaType("application/hal+json;charset=UTF-8")));
    }

    @Test
    public void c_findAllByCodeName() throws Exception {
        mockMvc.perform(get(APP_BASE_URL + "/matches/search/findAllByCodeName?codeName=" + TEST_NAME_1)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.parseMediaType("application/hal+json;charset=UTF-8")))
                .andExpect(jsonPath("$._embedded.matches.length()").value(3));
    }

    @Test
    public void d_deleteById() throws Exception {
        mockMvc.perform(delete(APP_BASE_URL + "/matches/"+id)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    /**
     * Ugly workaround to delete a resource via a GET HTTP-METHOD.
     *
     * @throws Exception
     */
    @Test
    public void e_deleteAllByCodeName() throws Exception {
        mockMvc.perform(get(APP_BASE_URL + "/matches/search/deleteAllByCodeName?codeName=" + TEST_NAME_1)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber());
    }
}