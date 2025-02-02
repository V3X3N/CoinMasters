package com.coinmasters.exceptions;

import com.coinmasters.controller.group.*;
import com.coinmasters.controller.transactions.DeleteTransactionResponse;
import com.coinmasters.controller.user.ChangePasswordResponse;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoSuchUserException.class)
    private ResponseEntity<String> handleNoSuchUserException(NoSuchUserException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    private ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExistsException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(ExpiredJwtException.class)
    private ResponseEntity<String> handleExpiredJwtException(ExpiredJwtException ex){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

    @ExceptionHandler(NoSuchGroupException.class)
    private ResponseEntity<String> handleNoSuchGroupException(NoSuchGroupException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(NoSuchTransactionException.class)
    private ResponseEntity<String> handleNoSuchTransactionException(NoSuchTransactionException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(CannotJoinGroupException.class)
    private ResponseEntity<JoinGroupResponse> handleCannotJoinGroupException(CannotJoinGroupException ex){
        return ResponseEntity.status(ex.getStatus()).body(ex.getJoinGroupResponse());
    }

    @ExceptionHandler(ActionPerformedByNonAdminUserException.class)
    private ResponseEntity<DeleteGroupResponse> handleDeletionByNonAdminUserException(ActionPerformedByNonAdminUserException ex){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(DeleteGroupResponse.builder()
                        .status("Unauthorized")
                        .message(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(TransactionDeletionByNonGroupMemberException.class)
    private ResponseEntity<DeleteTransactionResponse> handleTransactionDeletionByNonGroupMemberException(TransactionDeletionByNonGroupMemberException ex){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(DeleteTransactionResponse.builder()
                        .status("Unauthorized")
                        .message(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    private ResponseEntity<ChangePasswordResponse> handleIncorrectPasswordException(IncorrectPasswordException ex){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ChangePasswordResponse.builder()
                .status("Failure")
                .message(ex.getMessage())
                .build());
    }

    @ExceptionHandler(NothingToChangeException.class)
    private ResponseEntity<NothingToChangeResponse> handleNothingToChangeException(NothingToChangeException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(NothingToChangeResponse.builder()
                        .status("Failure")
                        .message(ex.getMessage())
                .build());
    }
    @ExceptionHandler(UserNotAMemberOfTheGroupException.class)
    private ResponseEntity<NothingToChangeResponse> handleUserNotAMemberOfTheGroupException(UserNotAMemberOfTheGroupException ex){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(NothingToChangeResponse.builder()
                .status("Failure")
                .message(ex.getMessage())
                .build());
    }

    @ExceptionHandler(AdminDeletingException.class)
    private ResponseEntity<RemoveUserResponse> handleAdminDeletingException(AdminDeletingException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RemoveUserResponse.builder()
                .status("Failure")
                .message(ex.getMessage())
                .build());
    }
}
