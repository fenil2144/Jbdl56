package com.example.MinorProject.serviceImpl;

import com.example.MinorProject.enums.TransactionType;
import com.example.MinorProject.exceptions.TransactionServiceException;
import com.example.MinorProject.models.Book;
import com.example.MinorProject.models.Student;
import com.example.MinorProject.models.Transaction;
import com.example.MinorProject.repository.TransactionRepositoryInterf;
import com.example.MinorProject.service.BookServiceInterf;
import com.example.MinorProject.service.StudentServiceInterf;
import com.example.MinorProject.service.TransactionServiceInterf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionServiceInterf {

    @Autowired
    TransactionRepositoryInterf transactionRepositoryInterf;

    @Autowired
    StudentServiceInterf studentServiceInterf;

    @Autowired
    BookServiceInterf bookServiceInterf;

    @Override
    public String transact(int studentId, int bookId, String transactionType) {

        //Issue
//        1) Student is Valid?
//        2) Book is Valid
//        3) Book is Available

        Optional<Student> student = studentServiceInterf.findById(studentId);
        if(!student.isPresent()) {
            throw new TransactionServiceException("Student Not Present in Library");
        }

        Optional<Book> book = bookServiceInterf.findById(bookId);
        if(!book.isPresent())
            throw new TransactionServiceException("Book Not Present in Library");

        if(TransactionType.valueOf(transactionType).equals(TransactionType.ISSUE)){

                if(book.get().getStudent() != null)
                    throw new TransactionServiceException("Book Is Not Available for Issue");

                Transaction transaction = Transaction.builder()
                        .externalId(UUID.randomUUID().toString())
                                .transactionType(TransactionType.ISSUE)
                                .payment(book.get().getCost())
                                .book(book.get()).student(student.get()).build();
                transactionRepositoryInterf.save(transaction);

                book.get().setStudent(student.get());
                bookServiceInterf.save(book.get());
                return transaction.getExternalId();

        }else{

        }

        return null;
    }
}
