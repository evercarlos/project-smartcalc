package com.tec.smartcalc.service.impl;

import com.tec.smartcalc.domain.dto.CallHistoryRequestDto;
import com.tec.smartcalc.domain.dto.CallHistoryResponseDto;
import com.tec.smartcalc.exception.CalcErrorType;
import com.tec.smartcalc.exception.TransactionException;
import com.tec.smartcalc.helper.CallHistoryHelper;
import com.tec.smartcalc.mapper.CallHistoryMapper;
import com.tec.smartcalc.repository.CallHistoryRepository;
import com.tec.smartcalc.service.CallHistoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class CallHistoryServiceImpl implements CallHistoryService {

    private final CallHistoryRepository callHistoryRepository;

    public CallHistoryServiceImpl(CallHistoryRepository callHistoryRepository) {
        this.callHistoryRepository = callHistoryRepository;
    }

    @Override
    public Page<CallHistoryResponseDto> findAllPageable(Pageable pageable) {

        Sort sort = pageable.getSort().isUnsorted() ? Sort.by("id") : pageable.getSort();
        if (!CallHistoryHelper.validateSorName(sort)) {
            throw new TransactionException(HttpStatus.BAD_REQUEST, CalcErrorType.HISTORY_400_1.getDescription());
        }
        return callHistoryRepository.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort))
                .map(CallHistoryMapper.MAPPER::callHistoryResponseDto);
    }

    @Override
    public List<CallHistoryResponseDto> findAll() {
        return callHistoryRepository.findAll().stream()
                .map(CallHistoryMapper.MAPPER::callHistoryResponseDto).toList();
    }

    @Async
    @Override
    public CompletableFuture<Void> saveAsync(CallHistoryRequestDto callHistoryRequestDto) {
        try {
            var request = CallHistoryMapper.MAPPER.toCallHistory(callHistoryRequestDto);
            callHistoryRepository.save(request);
            return CompletableFuture.completedFuture(null);
        } catch (Exception e) {
            return CompletableFuture.failedFuture(e);
        }
    }
}
