package com.example.apelsin_rest_api.repositary;

import com.example.apelsin_rest_api.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InvoiceRepositary extends JpaRepository<Invoice, Integer> {
    List<Invoice> findAllByActiveTrue();

    @Query(value = "select * from invoice   join orderer o on o.id = invoice.ord_id " +
            "where date>invoice.issued;", nativeQuery = true)
    List<Invoice> getWrong_date_invoices();

    @Query(value = "select * from invoice where issued>due ", nativeQuery = true)
    List<Invoice> getAllByActiveTrue();


    @Query(value = "select * from payment join invoice i on i.id = payment.inv_id_id" +
            " where i.ammount<payment.amount; ", nativeQuery = true)
    List<Invoice> getOverpaid_invoices();


}
