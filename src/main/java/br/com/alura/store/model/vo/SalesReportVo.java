package br.com.alura.store.model.vo;

import java.time.LocalDate;

public class SalesReportVo {
    private final String productName;
    private final Long qntSold;
    private final LocalDate  dateLastSold;

    public SalesReportVo(String productName, Long qntSold, LocalDate dateLastSold) {
        this.productName = productName;
        this.qntSold = qntSold;
        this.dateLastSold = dateLastSold;
    }

    public String getProductName() {
        return productName;
    }

    public Long getQntSold() {
        return qntSold;
    }

    public LocalDate getDateLastSold() {
        return dateLastSold;
    }
}

