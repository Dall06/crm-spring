package com.ust.crm.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ust.crm.model.Client;
import com.ust.crm.model.Visit;
import com.ust.crm.service.VisitService;
import com.ust.crm.service.SaleService;
import com.ust.crm.utils.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VisitController.class)
@AutoConfigureRestDocs
class VisitControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VisitService visitService;

    @MockBean
    private DateUtil deserializer;

    @Test
    void getVisit() throws Exception {
        Client client = new Client(1L, "Nombre","correo@contacto.com", 10, "Direccion");

        Optional<Visit> visit = Optional.of(Visit.builder().id(1L).client(client).programmedVisitDate(deserializer.get()).address("Direccion").purpose("Proposito").build());

        when(visitService.getVisit(anyLong())).thenReturn(visit);

        mockMvc.perform(get("/visit/{id}", 1)
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.address", is("Direccion")))
                .andExpect(jsonPath("$.purpose", is("Proposito")))

                .andDo(document("visit/get-visits",
                        responseFields(
                                fieldWithPath("id").description("identificador de la visit"),
                                fieldWithPath("client").description("datos del client"),
                                fieldWithPath("programmedVisitDate").description("fecha de la visit"),
                                fieldWithPath("address").description("address de la visit"),
                                fieldWithPath("purpose").description("purpose de la visit"),
                                fieldWithPath("seller").description("seller encargado de la visit"),
                                fieldWithPath("client.id").description("lista de productos id"),
                                fieldWithPath("client.name").description("lista de productos id"),
                                fieldWithPath("client.email").description("lista de productos id"),
                                fieldWithPath("client.employeesQty").description("lista de productos id"),
                                fieldWithPath("client.address").description("lista de productos id")
                        )));;
    }

    @Test
    void getVisits() throws Exception {

        List<Visit> visits = Arrays.asList(
                Visit.builder().id(1L).client(new Client(1L, "Nombre 1","correo1@contacto.com", 10, "Direccion1")).programmedVisitDate(deserializer.get())
                        .address("address 1")
                        .purpose("Propositodela visit 1")
                        .seller("seller 1")
                        .build(),
                Visit.builder().id(2L).client(new Client(2L, "Nombre 2","correo2@contacto.com", 10, "Direccion2"))
                        .programmedVisitDate(deserializer.get())
                        .address("address 2")
                        .purpose("Propositodela visit 2")
                        .seller("seller 2")
                        .build(),
                Visit.builder().id(3L).client(new Client(3L, "Nombre 3","correo3@contacto.com", 10, "Direccion3"))
                        .programmedVisitDate(deserializer.get())
                        .address("address 3")
                        .purpose("purpose 3")
                        .seller("seller 3")
                        .build()
        );

        given(visitService.getVisits()).willReturn(visits);

        mockMvc.perform(get("/visit")
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[0].address", is("address 1")))
                .andExpect(jsonPath("$[2].seller", is("seller 3")))

                .andDo(document("visit/get-visit",
                        responseFields(
                                fieldWithPath("[].id").description("identificador de la visit"),
                                fieldWithPath("[].client").description("datos del client"),
                                fieldWithPath("[].programmedVisitDate").description("fecha de la visit"),
                                fieldWithPath("[].address").description("address de la visit"),
                                fieldWithPath("[].purpose").description("purpose de la visit"),
                                fieldWithPath("[].seller").description("seller encargado de la visit"),
                                fieldWithPath("[].client.id").description("lista de productos id"),
                                fieldWithPath("[].client.name").description("lista de productos id"),
                                fieldWithPath("[].client.email").description("lista de productos id"),
                                fieldWithPath("[].client.employeesQty").description("lista de productos id"),
                                fieldWithPath("[].client.address").description("lista de productos id")

                        )));
    }

    @Test
    void creaVisit() throws Exception {
        Visit visitParametro = Visit.builder().id(1L).client(new Client(1L, "Nombre 1","correo1@contacto.com", 10, "Direccion1"))
                //.programmedVisitDate(deserializer.get())
                .programmedVisitDate(deserializer.get())
                .address("address")
                .purpose("purpose")
                .seller("seller")
                .build();
        Visit visitRespuesta = Visit.builder().id(1L).client(new Client(1L, "Nombre 1","correo1@contacto.com", 10, "Direccion1"))
                .programmedVisitDate(deserializer.get())
                .address("address")
                .purpose("purpose")
                .seller("seller")
                .build();

        given(visitService.saveVisit(visitParametro)).willReturn(visitRespuesta);

        mockMvc.perform(post("/visit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(visitParametro)))
                .andExpect(status().isCreated())

                .andDo(document("visit/post-visit",
                        requestFields(
                                fieldWithPath("id").description("identificador de la visit"),
                                fieldWithPath("client").description("datos del client"),
                                fieldWithPath("programmedVisitDate").description("fecha de la visit"),
                                fieldWithPath("address").description("address de la visit"),
                                fieldWithPath("purpose").description("purpose de la visit"),
                                fieldWithPath("seller").description("seller encargado de la visit"),
                                fieldWithPath("client.id").description("lista de productos id"),
                                fieldWithPath("client.name").description("lista de productos id"),
                                fieldWithPath("client.email").description("lista de productos id"),
                                fieldWithPath("client.employeesQty").description("lista de productos id"),
                                fieldWithPath("client.address").description("lista de productos id")


                        ),
                        responseHeaders(
                                headerWithName("Location").description("La ubicación del recurso (su identificador generado")
                        ))
                );
    }

    @Test
    void actualizaVisit() throws Exception {

        Visit visitParametro = Visit.builder().id(1L).client(new Client(1L, "Nombre 1","correo1@contacto.com", 10, "Direccion1"))
                .programmedVisitDate(deserializer.get())
                .address("programmedVisitDate")
                .purpose("purpose")
                .seller("seller")
                .build();

        mockMvc.perform(put("/visit/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(visitParametro)))
                .andExpect(status().isNoContent())
                .andDo(document("visit/put-visit",
                        pathParameters(
                                parameterWithName("id").description("Identificador de la visit")
                        ),
                        requestFields(
                                fieldWithPath("id").description("identificador de la visit"),
                                fieldWithPath("client").description("datos del client"),
                                fieldWithPath("programmedVisitDate").description("fecha de la visit"),
                                fieldWithPath("address").description("address de la visit"),
                                fieldWithPath("purpose").description("purpose de la visit"),
                                fieldWithPath("seller").description("seller encargado de la visit"),
                                fieldWithPath("client.id").description("lista de productos id"),
                                fieldWithPath("client.name").description("lista de productos id"),
                                fieldWithPath("client.email").description("lista de productos id"),
                                fieldWithPath("client.employeesQty").description("lista de productos id"),
                                fieldWithPath("client.address").description("lista de productos id")
                        )
                ));
    }

    @Test
    void deleteVisit() throws Exception {
        mockMvc.perform(RestDocumentationRequestBuilders.delete("/visit/{id}", 1)
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNoContent())
                .andDo(document("visit/delete-visit",
                        pathParameters(
                                parameterWithName("id").description("Identificador de la visit")
                        )));
    }

}