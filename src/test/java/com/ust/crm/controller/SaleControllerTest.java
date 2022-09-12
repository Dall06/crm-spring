package com.ust.crm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ust.crm.model.Client;
import com.ust.crm.model.Product;
import com.ust.crm.model.Sale;
import com.ust.crm.service.SaleService;
import com.ust.crm.utils.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureRestDocs
@WebMvcTest(SaleController.class)
class SaleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SaleService service;
    @MockBean
    private DateUtil deserializer;

    @Test
    void getSale() throws Exception {
        List<Product> products = Arrays.asList(
                Product.builder().id(1L).name("Papas").category("Alimentos").createdAt(deserializer.get()).registryNumber("200").price(5000).build(),
                Product.builder().id(2L).name("Plumas").category("Papeleria").createdAt(deserializer.get()).registryNumber("300").price(500).build(),
                Product.builder().id(3L).name("Jamon").category("Alimentos").createdAt(deserializer.get()).registryNumber("400").price(100).build()
        );
        given(service.getSale(anyLong())).willReturn(Optional.of(Sale.builder()
                .id(1L)
                .qty(500)
                .client(Client.builder().name("Pedro").email("pedro@mail.com").address("Jerez").employeesQty(30).build())
                .products(products)
                .createdAt(deserializer.getDateTime())
                .build()));

        mockMvc.perform(get("/sale/1")
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.qty", is(500.0)))
                .andExpect(jsonPath("$.id", is(1)))
                .andDo(document("sale/get-sale",
                        pathParameters(
                                parameterWithName("id")
                                        .description("Identificador de la sale")),
                        responseFields(
                                fieldWithPath("id").description("Identificador de la sale"),
                                fieldWithPath("qty").description("Monto de la sale"),
                                fieldWithPath("createdAt").description("Fecha de creacion de la sale")
                        )));
    }

    @Test
    void getSales() throws Exception {

        List<Product> products = Arrays.asList(
                Product.builder().id(1L).name("Papas").category("Alimentos").createdAt(deserializer.get()).registryNumber("200").price(5000).build(),
                Product.builder().id(2L).name("Plumas").category("Papeleria").createdAt(deserializer.get()).registryNumber("300").price(500).build(),
                Product.builder().id(3L).name("Jamon").category("Alimentos").createdAt(deserializer.get()).registryNumber("400").price(100).build()
        );

        List<Sale> sales = Arrays.asList(
                Sale.builder().id(1L).products(products).createdAt(deserializer.getDateTime()).client(
                                Client.builder().id(1).name("Angel").email("angel@mail.com").address("Balcones").employeesQty(50).build())
                        .qty(4000)
                        .createdAt(deserializer.getDateTime())
                        .build(),
                Sale.builder().id(2L).products(products).createdAt(deserializer.getDateTime()).client(
                                Client.builder().id(2).name("Angel").email("angel@mail.com").address("Balcones").employeesQty(50).build())
                        .qty(5000)
                        .createdAt(deserializer.getDateTime())
                        .build()
        );

        given(service.getSales()).willReturn(sales);

        mockMvc.perform(get("/sale/all")
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andDo(document("sale/get-sales",
                        responseFields(
                                fieldWithPath("id").description("Identificador de la sale"),
                                fieldWithPath("qty").description("Monto de la sale"),
                                fieldWithPath("createdAt").description("Fecha de creacion de la sale")
                        )));

    }

    @Test
    void creaSale() throws Exception {

        LocalDateTime ahora = LocalDateTime.of(LocalDate.now(), LocalTime.now());

        List<Product> products = Arrays.asList(
                Product.builder().id(1L).name("Papas").category("Alimentos").createdAt(deserializer.get()).registryNumber("200").price(5000).build(),
                Product.builder().id(2L).name("Plumas").category("Papeleria").createdAt(deserializer.get()).registryNumber("300").price(500).build(),
                Product.builder().id(3L).name("Jamon").category("Alimentos").createdAt(deserializer.get()).registryNumber("400").price(100).build()
        );
        Sale param = Sale.builder().id(1L).qty(800).createdAt(deserializer.getDateTime()).client(
                        Client.builder().name("Angel").email("angel@mail.com").address("Balcones").employeesQty(50).build())
                .products(products)
                .createdAt(deserializer.getDateTime())
                .build();
        Sale response = Sale.builder().id(2L).qty(8000).createdAt(deserializer.getDateTime()).client(
                        Client.builder().name("Omar").email("omar@mail.com").address("Anzures").employeesQty(500).build())
                .products(products)
                .createdAt(deserializer.getDateTime())
                .build();

        given(service.saveSale(param)).willReturn(response);

        mockMvc.perform(post("/sale")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(param)))
                .andExpect(status().isCreated())
                .andDo(document("sale/post-sale",
                        pathParameters(
                                parameterWithName("id")
                                        .description("Identificador de la sale")),
                        responseFields(
                                fieldWithPath("id").description("Identificador de la sale"),
                                fieldWithPath("qty").description("Monto de la sale"),
                                fieldWithPath("createdAt").description("Fecha de creacion de la sale")
                        )));

    }

    @Test
    void actualizaSale() throws Exception {

        List<Product> products = Arrays.asList(
                Product.builder().id(1L).name("Papas").category("Alimentos").createdAt(deserializer.get()).registryNumber("200").price(5000).build(),
                Product.builder().id(2L).name("Plumas").category("Papeleria").createdAt(deserializer.get()).registryNumber("300").price(500).build(),
                Product.builder().id(3L).name("Jamon").category("Alimentos").createdAt(deserializer.get()).registryNumber("400").price(100).build()
        );

        Sale saleParam = Sale.builder().id(1L).qty(800).createdAt(deserializer.getDateTime()).client(
                        Client.builder().name("Angel").email("angel@mail.com").address("Balcones").employeesQty(50).build())
                .products(products)
                .createdAt(deserializer.getDateTime())
                .build();

        mockMvc.perform(put("/sale/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(saleParam)))
                .andExpect(status().isNoContent())
                .andDo(document("sale/put-sale",
                        pathParameters(
                                parameterWithName("id")
                                        .description("Identificador de la sale")),
                        responseFields(
                                fieldWithPath("id").description("Identificador de la sale"),
                                fieldWithPath("qty").description("Monto de la sale"),
                                fieldWithPath("createdAt").description("Fecha de creacion de la sale")
                        )));

    }

    @Test
    void eliminaSale() throws Exception {

        mockMvc.perform(delete("/sale/1")
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNoContent())
                .andDo(document("sale/delete-sale",
                        pathParameters(
                                parameterWithName("id")
                                        .description("Identificador de la sale")),
                        responseFields(
                                fieldWithPath("id").description("Identificador de la sale"),
                                fieldWithPath("qty").description("Monto de la sale"),
                                fieldWithPath("createdAt").description("Fecha de creacion de la sale")
                        )));
    }
}