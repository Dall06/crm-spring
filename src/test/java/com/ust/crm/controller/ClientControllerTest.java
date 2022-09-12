package com.ust.crm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ust.crm.model.Client;
import com.ust.crm.service.ClientService;
import com.ust.crm.utils.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WebMvcTest(ClientController.class)
@AutoConfigureRestDocs
class ClientControllerTest {
    @Autowired
    private MockMvc mockMvc;

    //@Autowired
    //private TestRestTemplate restTemplate;

    @MockBean
    private ClientService clientService;

    @Test
    void getClient() throws Exception {
        given(clientService.getClient(anyLong()))
                .willReturn(Optional.of(Client.builder()
                        .id(1L).name("Alec")
                        .email("Alec@mail.com")
                        .employeesQty(20)
                        .address("Monjaraz").build()));

        mockMvc.perform(get("/client/{id}", 1)
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.email", is("Alec@mail.com")))
                .andExpect(jsonPath("$.name", is("Alec")))
                .andDo(document("client/get-client",
                        pathParameters(
                                parameterWithName("id")
                                        .description("Identificador del client")),
                        responseFields(
                                fieldWithPath("id").description("Identificador del client"),
                                fieldWithPath("name").description("Nombre del client"),
                                fieldWithPath("email").description("Correo de contacto del client"),
                                fieldWithPath("employeesQty").description("Número de trabajadores del client"),
                                fieldWithPath("address").description("Domicilio del client")
                        )));

    }

    @Test
    void getClients() throws Exception {

        List<Client> clients = Arrays.asList(
                Client.builder().id(1L).name("Juan").address("Jardines").employeesQty(10).email("Juan@mail.com").build(),
                Client.builder().id(2L).name("Tomas").address("Oriental").employeesQty(10).email("Tomas@mail.com").build(),
                Client.builder().id(3L).name("Raul").address("Aurora").employeesQty(10).email("Raul@mail.com").build()
        );

        given(clientService.getClients()).willReturn(clients);

        mockMvc.perform(get("/client/all")
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[0].email", is("Juan@mail.com")))
                .andExpect(jsonPath("$[2].name", is("Raul")))

                .andDo(document("client/get-clients",
                        responseFields(
                                fieldWithPath("[].id").description("Identificador del client"),
                                fieldWithPath("[].name").description("Nombre del client"),
                                fieldWithPath("[].email").description("Correo de contacto del client"),
                                fieldWithPath("[].employeesQty").description("Número de trabajadores del client"),
                                fieldWithPath("[].address").description("Domicilio del client")
                        )));

    }

    @Test
    void createClient() throws Exception {
        Client clientParam = Client.builder().name("Juan").address("Rio").employeesQty(20).email("Juan@client.com").build();
        Client clientResponse = Client.builder().id(1L).name("Manuel").address("Lagos").employeesQty(20).email("Manuel@client.com").build();

        given(clientService.saveClient(clientParam)).willReturn(clientResponse);

        mockMvc.perform(post("/client/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(clientParam)))
                .andExpect(status().isCreated())

                .andDo(document("client/post-client",
                        requestFields(
                                fieldWithPath("id").description("Identificador del client"),
                                fieldWithPath("name").description("Nombre del client"),
                                fieldWithPath("email").description("Correo de contacto del client"),
                                fieldWithPath("employeesQty").description("Número de trabajadores del client"),
                                fieldWithPath("address").description("Domicilio del client")
                        ),
                        responseHeaders(
                                headerWithName("Location").description("Client generated successfully")
                        )));

    }

    @Test
    void updateClient() throws Exception {

        Client clientParam = Client.builder()
                .id(1L).name("Nombre")
                .address("Direccion")
                .employeesQty(20)
                .email("contacto@client.com").build();

        mockMvc.perform(put("/client/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(clientParam)))
                .andExpect(status().isNoContent())

                .andDo(document("client/put-client",
                        pathParameters(
                                parameterWithName("id")
                                        .description("Identificador del client")),
                        requestFields(
                                fieldWithPath("id").description("Identificador del client"),
                                fieldWithPath("name").description("Nombre del client"),
                                fieldWithPath("email").description("Correo de contacto del client"),
                                fieldWithPath("employeesQty").description("Número de trabajadores del client"),
                                fieldWithPath("address").description("Domicilio del client")
                        )));

    }

    @Test
    void deleteClient() throws Exception {
        mockMvc.perform(delete("/client/{id}",1)
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNoContent())

                .andDo(document("client/delete-client",
                        pathParameters(
                                parameterWithName("id").description("Identificador del cliente")
                        )));
    }

}