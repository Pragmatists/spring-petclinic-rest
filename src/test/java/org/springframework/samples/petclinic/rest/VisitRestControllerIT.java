package org.springframework.samples.petclinic.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VisitRestControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldReturnVisitsScheduledForSpecificDate() {
        ParameterizedTypeReference<Collection<Visit>>  parameterizedTypeReference =
            new ParameterizedTypeReference<Collection<Visit>>(){};
        ResponseEntity<Collection<Visit>> visitsResponse = restTemplate.exchange("/api/visits?date="+LocalDate.now().format(DateTimeFormatter.ISO_DATE), HttpMethod.GET, null, parameterizedTypeReference);
        Collection<Visit> visits = visitsResponse.getBody();
        //FIXME: how can we make good assertion here, assertion on json ?
        //FIXME: spring boot json tests, what about rest assured
        assertThat(visits).hasSize(1);
        assertThat(visits.iterator().next().getDescription()).isEqualTo("spayed");


    }
}
