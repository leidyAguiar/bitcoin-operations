package br.com.core.operations.application.usecase;

import br.com.core.operations.application.mapper.QuotationMapper;
import br.com.core.operations.core.entity.Quotation;
import br.com.core.operations.core.entity.coinbase.response.CoinBaseResponse;
import br.com.core.operations.core.entity.coinbase.response.CoinQuotationsResponse;
import br.com.core.operations.core.interfaces.gateway.BitCoinGateway;
import br.com.core.operations.core.interfaces.usecase.FindBitCoinQuotationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FindBitCoinQuotationUseCaseImpl implements FindBitCoinQuotationUseCase {

    private final BitCoinGateway bitCoinGateway;

    @Override
    public Quotation getQuotation(String currency) {
        CoinBaseResponse coinBaseResponse = bitCoinGateway.getQuotation(currency);
        coinBaseResponse.getData().setAmount(coinBaseResponse.getData().getAmount().setScale(2, RoundingMode.HALF_DOWN));
        return QuotationMapper.INSTANCE.toEntity(coinBaseResponse);
    }

    @Override
    public CoinQuotationsResponse getQuotations() {
        List<Quotation> quotations = new ArrayList<>();
        Date date = new Date();
        ArrayList<String> currencyList = new ArrayList<>();
        currencyList.add("BRL");
        currencyList.add("USD");
        currencyList.add("EUR");

        for (String currency : currencyList) {
            quotations.add(getQuotation(currency));
        }

        return QuotationMapper.INSTANCE.toEntity(date, quotations);
    }
}
