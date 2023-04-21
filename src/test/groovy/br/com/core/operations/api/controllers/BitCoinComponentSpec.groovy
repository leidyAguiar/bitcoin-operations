package br.com.core.operations.api.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import spock.lang.Specification

@AutoConfigureWireMock
@SpringBootTest
@AutoConfigureMockMvc
class BitCoinComponentSpec extends Specification {

    @Autowired
    MockMvc mockMvc

    def "Deve retornar sucesso ao fazer uma requisição a API informando a moeda BRL"() {
        given: "Desejo saber o valor do Bitcoin em Reais"
        var currency = "BRL"

        when: "Executo a chamada HTTP GET para a API de Cotações"
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/bitcoin-operations/quotation/{currency}", currency))
                .andReturn()

        then: "Devo receber status code igual a 200 OK"
        mvcResult.getResponse().getStatus() == HttpStatus.OK.value()
    }

    def "Deve retornar sucesso ao fazer uma requisição get a API"() {
        when: "Invocar o endpoint"
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/bitcoin-operations/quotations"))
                .andReturn()

        then: "Devo receber status code igual a 200 OK"
        mvcResult.getResponse().getStatus() == HttpStatus.OK.value()
    }
}


