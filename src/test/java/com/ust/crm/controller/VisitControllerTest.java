package com.ust.crm.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ust.crm.model.Client;
import com.ust.crm.model.Visit;
import com.ust.crm.service.VisitService;
import com.ust.crm.utils.DateUtil;
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
import com.fasterxml.jackson.databind.ObjectMapper;

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

        given(visitService.getVisit(anyLong())).willReturn(Optional.of(Visit.builder().id(1L).client(client).programmedVisitDate(deserializer.getDateTime()).address("Direccion").purpose("Proposito").build()));

        mockMvc.perform(get("/visit/{id}", 1)
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.address", is("Direccion")))
                .andExpect(jsonPath("$.purpose", is("Nombre")))

                .andDo(document("visit/get-visits",
                        responseFields(
                                fieldWithPath("[].id").description("identificador de la visit"),
                                fieldWithPath("[].client").description("datos del client"),
                                fieldWithPath("[].programmedVisitDate").description("fecha de la visit"),
                                fieldWithPath("[].address").description("address de la visit"),
                                fieldWithPath("[].purpose").description("purpose de la visit"),
                                fieldWithPath("[].seller").description("seller encargado de la visit")
                        )));;
    }

    @Test
    void getVisits() throws Exception {

        List<Visit> visits = Arrays.asList(
                Visit.builder().id(1L).client(new Client(1L, "Nombre 1","correo1@contacto.com", 10, "Direccion1")).programmedVisitDate(deserializer.getDateTime())
                        .address("Direcciondelavisit 1")
                        .purpose("Propositodela visit 1")
                        .seller("Vendedor 1")
                        .build(),
                Visit.builder().id(2L).client(new Client(2L, "Nombre 2","correo2@contacto.com", 10, "Direccion2"))
                        .programmedVisitDate(deserializer.getDateTime())
                        .address("Direcciondelavisit 2")
                        .purpose("Propositodela visit 2")
                        .seller("Vendedor 2")
                        .build(),
                Visit.builder().id(3L).client(new Client(3L, "Nombre 3","correo3@contacto.com", 10, "Direccion3"))
                        .programmedVisitDate(deserializer.getDateTime())
                        .address("Direcciondelavisit 3")
                        .purpose("Propositodelavisit 3")
                        .seller("Vendedor 3")
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
                .andExpect(jsonPath("$[0].address", is("Direcciondelavisit 1")))
                .andExpect(jsonPath("$[2].seller", is("Vendedor 3")))

                .andDo(document("visit/post-visit",
                        requestFields(
                                fieldWithPath("[].id").description("identificador de la visit"),
                                fieldWithPath("[].client").description("datos del client"),
                                fieldWithPath("[].programmedVisitDate").description("fecha de la visit"),
                                fieldWithPath("[].address").description("address de la visit"),
                                fieldWithPath("[].purpose").description("purpose de la visit"),
                                fieldWithPath("[].seller").description("seller encargado de la visit")

                        ),
                        responseHeaders(
                                headerWithName("Location").description("La ubicación del recurso (su identificador generado")
                        )));
    }

    @Test
    void creaVisit() throws Exception {

      /*  ObjectMapper o = new ObjectMapper();
        o.registerModule(new JavaTimeModule());*/
        // LocalDateTime fecha = LocalDateTime.parse("2023-10-10T 10:10:10.100");
       /* String dateTime = "2023-08-16T10:15:30+08:00";
        LocalDateTime d = LocalDateTime.parse(dateTime);*/

        /*DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        LocalDateTime session = LocalDateTime.parse("2019-12-15T15:14:21.629", formatter);*/
        //LocalDate fecha = LocalDate.now();
        Visit visitParametro = Visit.builder().id(1L).client(new Client(1L, "Nombre 1","correo1@contacto.com", 10, "Direccion1"))
                //.programmedVisitDate(deserializer.get())
                .programmedVisitDate(deserializer.getDateTime())
                .address("Direcciondelavisit")
                .purpose("Propositodelavisit")
                .seller("Vendedor")
                .build();
        Visit visitRespuesta = Visit.builder().id(1L).client(new Client(1L, "Nombre 1","correo1@contacto.com", 10, "Direccion1"))
                .programmedVisitDate(deserializer.getDateTime())
                .address("Direcciondelavisit")
                .purpose("Propositodelavisit")
                .seller("Vendedor")
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
                                fieldWithPath("seller").description("seller encargado de la visit")


                        ),
                        responseHeaders(
                                headerWithName("Location").description("La ubicación del recurso (su identificador generado")
                        ))
                );
    }

    @Test
    void actualizaVisit() throws Exception {

        Visit visitParametro = Visit.builder().id(1L).client(new Client(1L, "Nombre 1","correo1@contacto.com", 10, "Direccion1"))
                .programmedVisitDate(deserializer.getDateTime())
                .address("Direcciondelavisit")
                .purpose("Propositodelavisit")
                .seller("Vendedor")
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
                                fieldWithPath("seller").description("seller encargado de la visit")
                        )
                ));
    }

    @Test
    void eliminaVisit() throws Exception {
        mockMvc.perform(delete("/visit/{id}", 1)
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNoContent())

                .andDo(document("visit/delete-visit",
                        pathParameters(
                                parameterWithName("id").description("Identificador de la visit")
                        )));
    }

}