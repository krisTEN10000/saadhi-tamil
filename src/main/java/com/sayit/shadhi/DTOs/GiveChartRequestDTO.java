package com.sayit.shadhi.DTOs;

import java.util.List;

public record GiveChartRequestDTO(long astrologerId , List<Long> chartIds) {
}
