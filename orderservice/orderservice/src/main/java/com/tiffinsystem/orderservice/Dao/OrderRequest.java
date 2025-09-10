package com.tiffinsystem.orderservice.Dao;

import java.time.LocalDateTime;

public record OrderRequest( Long user_id,
                            Long product_id,
                            Integer quantity,
                            Double price
                          ) {
}
