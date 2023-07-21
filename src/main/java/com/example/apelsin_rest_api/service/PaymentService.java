package com.example.apelsin_rest_api.service;

import com.example.apelsin_rest_api.dto.ApiResponse;
import com.example.apelsin_rest_api.dto.PaymentDto;
import com.example.apelsin_rest_api.entity.Invoice;
import com.example.apelsin_rest_api.entity.Payment;
import com.example.apelsin_rest_api.repositary.InvoiceRepositary;
import com.example.apelsin_rest_api.repositary.PaymentRepositary;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {
    @Autowired
     PaymentRepositary paymentRepositary;
    @Autowired
    InvoiceRepositary invoiceRepositary;
    public ApiResponse add(PaymentDto dto) {
        Invoice invoice = new Invoice();
        invoice.setId(dto.getInvId());
        Invoice save = invoiceRepositary.save(invoice);
        Payment payment = new Payment();
        payment.setInvoice(save);
        payment.setAmount(dto.getAmmount());
        payment.setTime(dto.getTime());
        paymentRepositary.save(payment);
        return new ApiResponse("Added", true);
    }

    public ApiResponse edit(Integer id, PaymentDto dto) {

        Optional<Payment> byId = paymentRepositary.findById(id);
        if (byId.isEmpty()) return new ApiResponse(" Not found!", false);
        Payment payment = byId.get();
        payment.setTime(dto.getTime());
        payment.setAmount(dto.getAmmount());
        Invoice invoice = payment.getInvoice();
        payment.setInvoice(invoice);
        paymentRepositary.save(payment);
        return new ApiResponse("Edited", true);
    }
}
