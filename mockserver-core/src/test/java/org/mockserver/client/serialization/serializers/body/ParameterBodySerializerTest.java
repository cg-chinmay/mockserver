package org.mockserver.client.serialization.serializers.body;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.mockserver.client.serialization.ObjectMapperFactory;
import org.mockserver.client.serialization.model.ParameterBodyDTO;
import org.mockserver.model.XPathBody;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockserver.model.Not.not;
import static org.mockserver.model.Parameter.param;
import static org.mockserver.model.ParameterBody.params;

public class ParameterBodySerializerTest {

    @Test
    public void shouldSerializeXPathBodyDTO() throws JsonProcessingException {
        assertThat(ObjectMapperFactory.createObjectMapper().writeValueAsString(params(
                        param("queryStringParameterOneName", "queryStringParameterOneValueOne", "queryStringParameterOneValueTwo"),
                        param("queryStringParameterTwoName", "queryStringParameterTwoValue")
                )),
                is("{" +
                        "\"type\":\"PARAMETERS\"," +
                        "\"value\":[" +
                        "{\"name\":\"queryStringParameterOneName\",\"values\":[\"queryStringParameterOneValueOne\",\"queryStringParameterOneValueTwo\"]}," +
                        "{\"name\":\"queryStringParameterTwoName\",\"values\":[\"queryStringParameterTwoValue\"]}" +
                        "]" +
                        "}"));
    }

    @Test
    public void shouldSerializeXPathBodyDTOWithNot() throws JsonProcessingException {
        assertThat(ObjectMapperFactory.createObjectMapper().writeValueAsString(not(params(
                        param("queryStringParameterOneName", "queryStringParameterOneValueOne", "queryStringParameterOneValueTwo"),
                        param("queryStringParameterTwoName", "queryStringParameterTwoValue")
                ))),
                is("{" +
                        "\"not\":true," +
                        "\"type\":\"PARAMETERS\"," +
                        "\"value\":[" +
                        "{\"name\":\"queryStringParameterOneName\",\"values\":[\"queryStringParameterOneValueOne\",\"queryStringParameterOneValueTwo\"]}," +
                        "{\"name\":\"queryStringParameterTwoName\",\"values\":[\"queryStringParameterTwoValue\"]}" +
                        "]" +
                        "}"));
    }

    @Test
    public void shouldSerializeXPathBodyDTOWithNotParameter() throws JsonProcessingException {
        assertThat(ObjectMapperFactory.createObjectMapper().writeValueAsString(params(
                        org.mockserver.model.Not.not(param("queryStringParameterOneName", "queryStringParameterOneValueOne", "queryStringParameterOneValueTwo")),
                        param("queryStringParameterTwoName", "queryStringParameterTwoValue")
                )),
                is("{" +
                        "\"type\":\"PARAMETERS\"," +
                        "\"value\":[" +
                        "{\"not\":true,\"name\":\"queryStringParameterOneName\",\"values\":[\"queryStringParameterOneValueOne\",\"queryStringParameterOneValueTwo\"]}," +
                        "{\"name\":\"queryStringParameterTwoName\",\"values\":[\"queryStringParameterTwoValue\"]}" +
                        "]" +
                        "}"));
    }

}