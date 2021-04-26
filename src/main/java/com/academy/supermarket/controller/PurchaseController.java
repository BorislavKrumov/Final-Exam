package com.academy.supermarket.controller;

import com.academy.supermarket.model.entity.Purchase;
import com.academy.supermarket.model.util.PurchaseParams;
import com.academy.supermarket.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {
    public static final String DATE_FORMAT = "yyyy-MM-dd_HH-mm-ss";
    public static final String CONTENT_DISPOSITION = "Content-Disposition";
    public static final String CONTENT_TYPE_CSV = "text/csv";
    private PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping
    public ResponseEntity<Purchase> createPurchase(@RequestBody PurchaseParams purchaseParams) {
        return new ResponseEntity<>(purchaseService.createPurchase(purchaseParams), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity getAllPurchases() {
        return new ResponseEntity(purchaseService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/csv")
    public ResponseEntity exportPurchasesToCsv(HttpServletResponse response) throws IOException {
        List<Purchase> purchaseList = purchaseService.getAll();
        response.setContentType(CONTENT_TYPE_CSV);
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        String currentDateTime = dateFormat.format(new Date());
        String headerKey = CONTENT_DISPOSITION;
        String headerValue = String.format("attachment; filename=purchases_%s.csv", currentDateTime);
        response.setHeader(headerKey, headerValue);
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] nameMapping = {"id", "superMarketId", "paymentType", "cashAmount", "totalPrice", "exchange", "paymentExecutedTime"};
        csvWriter.writeHeader(nameMapping);

        for (Purchase purchase : purchaseList) {
            csvWriter.write(purchase, nameMapping);
        }

        csvWriter.close();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
