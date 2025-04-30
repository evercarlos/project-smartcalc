package com.tec.smartcalc.mapper;

import com.tec.smartcalc.domain.dto.CallHistoryRequestDto;
import com.tec.smartcalc.domain.dto.CallHistoryResponseDto;
import com.tec.smartcalc.domain.entity.CallHistory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CallHistoryMapper {

    CallHistoryMapper MAPPER = Mappers.getMapper(CallHistoryMapper.class);


    CallHistoryResponseDto callHistoryResponseDto(CallHistory callHistory);


    CallHistory toCallHistory(CallHistoryRequestDto callHistoryRequestDto);
}
