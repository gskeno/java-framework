package com.gsonkeno.javagenerics;

import lombok.Data;

@Data
public class SupplierOffShelfEvent implements Event{
    private String supplierId;
}
