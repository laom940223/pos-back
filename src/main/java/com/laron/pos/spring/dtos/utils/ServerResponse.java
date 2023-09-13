package com.laron.pos.spring.dtos.utils;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ServerResponse<T> {


    private T data;
    private List<AppError> errors;
    private LocalDateTime timestamp ;


}
