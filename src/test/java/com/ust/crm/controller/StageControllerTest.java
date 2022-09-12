package com.ust.crm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ust.crm.model.Stage;
import com.ust.crm.service.StageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureRestDocs
@WebMvcTest(StageController.class)
class StageControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StageService stageService;

    @Test
    void getStage() throws Exception {
        given(stageService.getStage(anyLong())).willReturn(Optional.of(Stage.builder().id(1L).name("NombreStage").order(1).build()));

        mockMvc.perform(get("/stage/1")
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("NombreStage")))
                .andExpect(jsonPath("$.order", is(1)))

                .andDo(document("stage/get-stage",
                        pathParameters(
                                parameterWithName("id").description("Identificador de la stage")
                        ),
                        responseFields(
                                fieldWithPath("id").description("identificador de la stage"),
                                fieldWithPath("name").description("name de a stage"),
                                fieldWithPath("order").description("order de a stage")
                        )));
    }

    @Test
    void getStages() throws Exception {

        List<Stage> stages = Arrays.asList(
                Stage.builder().id(1L).name("NombreStage1").order(1).build(),
                Stage.builder().id(2L).name("NombreStage2").order(2).build(),
                Stage.builder().id(3L).name("NombreStage3").order(3).build()
        );

        given(stageService.getStages()).willReturn(stages);

        mockMvc.perform(get("/stage")
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[0].name", is("NombreStage1")))
                .andExpect(jsonPath("$[2].order", is(3)))

                .andDo(document("stage/get-stage",
                        responseFields(
                                fieldWithPath("[].id").description("identificador de la stage"),
                                fieldWithPath("[].name").description("name de la order"),
                                fieldWithPath("[].order").description("order de la visita")
                        )));
        ;
    }

    @Test
    void saveStage() throws Exception {
        Stage stageParam = Stage.builder().name("NombreStage").order(1).build();
        Stage stageResponse = Stage.builder().id(1L).name("NombreStage").order(1).build();

        given(stageService.saveStage(stageParam)).willReturn(stageResponse);

        mockMvc.perform(post("/stage")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(stageParam)))
                .andExpect(status().isCreated())

                .andDo(document("stage/post-stage",
                        requestFields(
                                fieldWithPath("id").description("El identificador del nuevo cliente"),
                                fieldWithPath("name").description("El name del cliente"),
                                fieldWithPath("order").description("La dirección del cliente")

                        ),
                        responseHeaders(
                                headerWithName("Location").description("La ubicación del recurso (su identificador generado)")
                        ))
                );
    }

    @Test
    void updateStage() throws Exception {

        Stage stageParam = Stage.builder().id(1L).name("NombreStage").order(1).build();

        mockMvc.perform(put("/stage/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(stageParam)))
                .andExpect(status().isNoContent())
                .andDo(document("stage/put-stage",
                        pathParameters(
                                parameterWithName("id").description("Identificador de la stage")
                        ),
                        requestFields(
                                fieldWithPath("id").description("El identificador de la stage"),
                                fieldWithPath("name").description("El namede la stage"),
                                fieldWithPath("order").description("Orden de la visita")
                        )

                ));;
    }

    @Test
    void deleteStage() throws Exception {
        mockMvc.perform(delete("/stage/1")
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNoContent())

                .andDo(document("stage/delete-stage",
                        pathParameters(
                                parameterWithName("id").description("Identificador de la stage")
                        )));


    }


}