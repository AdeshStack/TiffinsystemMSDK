package com.tiffinsystem.order2.dao;

public record OrderRequest( Long userId,
                            Long productId,
                            Integer quantity,
                            Double price
                          ) {
}
