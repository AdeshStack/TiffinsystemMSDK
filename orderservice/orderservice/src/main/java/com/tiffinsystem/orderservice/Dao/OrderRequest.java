package com.tiffinsystem.orderservice.Dao;

import java.time.LocalDateTime;

public record OrderRequest( Long userId,
                            Long productId,
                            Integer quantity,
                            Double price
                          ) {
}
