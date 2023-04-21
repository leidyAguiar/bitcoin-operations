package br.com.core.operations.core.entity.coinbase.response;

import br.com.core.operations.core.entity.Quotation;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CoinQuotationsResponse implements Serializable {

    @JsonProperty("moedaBase")
    private final String currencyBase = "bitcoin";

    @JsonProperty("data")
    private String date;

    @JsonProperty("cotacoes")
    private List<Quotation> quotations;
}
