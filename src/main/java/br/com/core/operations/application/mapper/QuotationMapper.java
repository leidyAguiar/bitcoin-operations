package br.com.core.operations.application.mapper;

import br.com.core.operations.core.entity.Quotation;
import br.com.core.operations.core.entity.coinbase.response.CoinBaseResponse;
import br.com.core.operations.core.entity.coinbase.response.CoinQuotationsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.Date;
import java.util.List;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public abstract class QuotationMapper {

    public static final QuotationMapper INSTANCE = getMapper(QuotationMapper.class);

    @Mapping(target = "currency", source = "data.currency")
    @Mapping(target = "amount", source = "data.amount")
    public abstract Quotation toEntity(CoinBaseResponse coinBaseResponse);

    @Mapping(target = "date", source = "date", dateFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    @Mapping(target = "quotations", source = "quotations")
    public abstract CoinQuotationsResponse toEntity(Date date, List<Quotation> quotations);
}

