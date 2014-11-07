package org.imsglobal.caliper.events;

import org.imsglobal.caliper.entities.LearningContext;
import org.imsglobal.caliper.profiles.ReadingProfile;
import org.imsglobal.caliper.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.yammer.dropwizard.testing.JsonHelpers.asJson;
import static com.yammer.dropwizard.testing.JsonHelpers.jsonFixture;
import static org.junit.Assert.assertEquals;

@Category(org.imsglobal.caliper.UnitTest.class)
public class NavigationEventTest {

    private LearningContext learningContext;
    private ReadingProfile profile;
    private NavigationEvent event;
    private static final Logger LOG = LoggerFactory.getLogger(NavigationEventTest.class);

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

        // Build the Learning Context
        learningContext = TestUtils.buildTestLearningContext();

        // Build Reading Profile
        profile = TestUtils.buildTestReadingProfile(learningContext);

        // Add navigation-related properties to profile
        profile = TestUtils.addTestReadingProfileNavigationTarget(profile);

        // Build event
        event = TestUtils.buildTestNavigationEvent(profile);
    }

    @Test
    public void caliperEventSerializesToJSON() throws Exception {
        assertEquals("Test if Navigation event is serialized to JSON with expected values",
        jsonFixture("fixtures/caliperNavigationEvent.json"), asJson(event));
    }
}