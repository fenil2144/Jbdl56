package com.example.minorProject1.serviceImpl;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.minorProject1.enums.TransactionType;
import com.example.minorProject1.exceptions.TransactionServiceException;
import com.example.minorProject1.models.Book;
import com.example.minorProject1.models.Student;
import com.example.minorProject1.models.Transaction;
import com.example.minorProject1.repository.TransactionRepositoryInterf;
import com.example.minorProject1.service.BookServiceInterf;
import com.example.minorProject1.service.StudentServiceInterf;
import com.example.minorProject1.service.TransactionServiceInterf;

@Service
public class TransactionServiceImpl implements TransactionServiceInterf {
	
	@Autowired
	StudentServiceInterf studentServiceInterf;
	
	@Autowired
	BookServiceInterf bookServiceInterf;
	
	@Autowired
	TransactionRepositoryInterf transactionRepositoryInterf;
	
	@Value("${book.maxAllowedDays}")
	int maxAllowedDays;

	@Override
	public String transact(int studentId, int bookId, String transactionType) {
		
//		Issue
//		1) Student is valid?
//		2) Book is Valid
//		3) Book is Available
//		4) Make Transaction
//		5) Make the Book Unavailable
//
//
//		Return
//		1) Student is valid?
//		2) Book is Valid
//		3) Book is issued to this Student Only
//		4) Calculating Fine if any
//		5) Make Transaction
//		6) Make the Book Available
		
		Optional<Student> student = studentServiceInterf.findById(studentId);
		
		if(!student.isPresent()) {
			throw new TransactionServiceException("Student Not Present in the Library");
		}
		
		Optional<Book> book = bookServiceInterf.findById(bookId);
		if(!book.isPresent())
			throw new TransactionServiceException("Book Not Present in the Library");
		
		if(TransactionType.valueOf(transactionType).equals(TransactionType.ISSUE)) {
			if(book.get().getStudent() != null)
				throw new TransactionServiceException("Book Not Available for Issue");
			
			Transaction transaction = Transaction.builder()
					.externalId(UUID.randomUUID().toString())
					.transactionType(TransactionType.ISSUE).payment(book.get().getCost())
					.book(book.get()).student(student.get()).build();
			
			transactionRepositoryInterf.save(transaction);
			
			book.get().setStudent(student.get());
			bookServiceInterf.save(book.get());
			
			return transaction.getExternalId();

		}
		else if(transactionType.equalsIgnoreCase(TransactionType.RETURN.toString())) {
			if(book.get().getStudent() == null ) {
				throw new TransactionServiceException("Book Not Issued to Any Student");
			}
			if(book.get().getStudent().getId() != studentId)
				throw new TransactionServiceException("Book Not Issued to the Student");
			
			//select top 1 from transaction where book_id=bookId and student_id=studentId and transaction_type=0 order by transaction_date desc;
			Transaction issueTxn = transactionRepositoryInterf
	                .findTopByBookAndStudentAndTransactionTypeOrderByIdDesc(book.get(), student.get(), TransactionType.ISSUE);
			
			Transaction transaction = Transaction.builder()
					.externalId(UUID.randomUUID().toString())
					.transactionType(TransactionType.RETURN).payment(book.get().getCost()-calculateFine(issueTxn))
					.book(book.get()).student(student.get()).build();
			
			transactionRepositoryInterf.save(transaction);
			book.get().setStudent(null);
			bookServiceInterf.save(book.get());

			return transaction.getExternalId();
		}
		return null;
	}
	
	private double calculateFine(Transaction issueTxn) {
		long issueTime = issueTxn.getCreatedOn().getTime();
		long returnTime = System.currentTimeMillis();
		
		long diff = returnTime - issueTime;
		long daysPassed = TimeUnit.DAYS.convert(diff,TimeUnit.MILLISECONDS);
		if(daysPassed> maxAllowedDays) {
			return ((daysPassed-maxAllowedDays)*10);
		}
		return 0.0;
	}

}
