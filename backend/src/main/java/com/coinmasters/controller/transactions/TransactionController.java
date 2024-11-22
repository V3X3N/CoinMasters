package com.coinmasters.controller.transactions;


import com.coinmasters.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {


    private final TransactionService transactionService;


    @GetMapping("/groups/{groupID}")
    public ResponseEntity<GroupTransactionResponse> getGroupsTransactions(@PathVariable Long groupID){
        return ResponseEntity.ok(transactionService.getTransactionOfGroup(groupID));
    }
}
