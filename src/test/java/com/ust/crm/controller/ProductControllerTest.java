package com.ust.crm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ust.crm.model.Product;
import com.ust.crm.service.ProductService;
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
@WebMvcTest(ProductController.class)
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    //@Autowired
    //private TestRestTemplate restTemplate;

    @MockBean
    private ProductService service;
    @MockBean
    private DateUtil deserializer;

    @Test
    void getProduct() throws Exception {
        given(service.getProduct(anyLong())).willReturn(Optional.of(Product.builder().id(1L).
                name("Papas").category("Alimentos").createdAt(LocalDate.now()).registryNumber("200").price(5000).build()));

        mockMvc.perform(get("/producto/1")
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.category", is("Alimentos")))
                .andExpect(jsonPath("$.registryNumber", is("200")))
                .andExpect(jsonPath("$.price", is(5000.0)))

                .andDo(document("producto/get-producto",
                        pathParameters(
                                parameterWithName("productoId")
                                        .description("Identificador del producto")),
                        responseFields(
                                fieldWithPath("id").description("Identificador del cliente"),
                                fieldWithPath("name").description("Nombre del cliente"),
                                fieldWithPath("category").description("Correo de contacto del cliente"),
                                fieldWithPath("price").description("Número de trabajadores del cliente"),
                                fieldWithPath("registryNumber").description("Domicilio del cliente"),
                                fieldWithPath("createdAt").description("Fecha de creacion del producto")
                        )));

    }


    @Test
    void getProducts() throws Exception {

        List<Product> productos = Arrays.asList(
                Product.builder().id(1L).name("Papas").category("Alimentos").createdAt(deserializer.get()).registryNumber("200").price(5000).build(),
                Product.builder().id(2L).name("Plumas").category("Papeleria").createdAt(deserializer.get()).registryNumber("300").price(500).build(),
                Product.builder().id(3L).name("Jamon").category("Alimentos").createdAt(deserializer.get()).registryNumber("400").price(100).build()
        );

        given(service.getProducts()).willReturn(productos);

        mockMvc.perform(get("/producto/all")
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andDo(document("producto/get-productos",
                        responseFields(
                                fieldWithPath("id").description("Identificador del cliente"),
                                fieldWithPath("name").description("Nombre del cliente"),
                                fieldWithPath("category").description("Correo de contacto del cliente"),
                                fieldWithPath("price").description("Número de trabajadores del cliente"),
                                fieldWithPath("registryNumber").description("Domicilio del cliente"),
                                fieldWithPath("createdAt").description("Fecha de creacion del producto")
                        )));

    }

    @Test
    void creaProduct() throws Exception {
        //String formattedDate = today.format(DateTimeFormatter.ofPattern("dd-MMM-yy")
        Product param = Product.builder().name("Papas").category("Alimentos").createdAt(deserializer.get()).registryNumber("200").price(1000).build();
        Product response = Product.builder().id(1L).name("Papas").category("Alimentos").createdAt(LocalDate.now()).registryNumber("200").price(1000).build();

        given(service.saveProduct(param)).willReturn(response);

        mockMvc.perform(post("/producto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(param)))
                .andExpect(status().isCreated())
                .andDo(document("producto/post-producto",
                        pathParameters(
                                parameterWithName("productoId")
                                        .description("Identificador del producto")),
                        responseFields(
                                fieldWithPath("id").description("Identificador del producto"),
                                fieldWithPath("name").description("Nombre del producto"),
                                fieldWithPath("category").description("Correo de contacto del producto"),
                                fieldWithPath("price").description("Número de trabajadores del producto"),
                                fieldWithPath("registryNumber").description("Domicilio del producto"),
                                fieldWithPath("createdAt").description("Fecha de creacion del producto")
                        )));

    }

    @Test
    void actualizaProduct() throws Exception {

        Product clienteParametro = Product.builder().id(1L).name("Papas").category("Alimentos").createdAt(deserializer.get()).registryNumber("200").price(5000).build();

        mockMvc.perform(put("/producto/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(clienteParametro)))
                .andExpect(status().isNoContent())
                .andDo(document("producto/put-producto",
                        pathParameters(
                                parameterWithName("productoId")
                                        .description("Identificador del producto")),
                        responseFields(
                                fieldWithPath("id").description("Identificador del producto"),
                                fieldWithPath("name").description("Nombre del producto"),
                                fieldWithPath("category").description("Correo de contacto del producto"),
                                fieldWithPath("price").description("Número de trabajadores del producto"),
                                fieldWithPath("registryNumber").description("Domicilio del producto"),
                                fieldWithPath("createdAt").description("Fecha de creacion del producto")
                        )));

    }

    @Test
    void eliminaProduct() throws Exception {

        mockMvc.perform(delete("/producto/1")
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNoContent())

                .andDo(document("producto/delete-producto",
                        pathParameters(
                                parameterWithName("productoId")
                                        .description("Identificador del producto")),
                        responseFields(
                                fieldWithPath("id").description("Identificador del producto"),
                                fieldWithPath("name").description("Nombre del producto"),
                                fieldWithPath("category").description("Correo de contacto del producto"),
                                fieldWithPath("price").description("Número de trabajadores del producto"),
                                fieldWithPath("registryNumber").description("Domicilio del producto"),
                                fieldWithPath("createdAt").description("Fecha de creacion del producto")
                        )));
    }

}