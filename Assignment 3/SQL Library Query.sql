/*Question #1*/
SELECT bookcop.noOfCopies FROM tbl_book_copies bookcop, 
tbl_library_branch libranch, tbl_book book
WHERE libranch.branchName='Sharpstown' 
AND book.title='The Lost Tribe' AND book.bookId=bookcop.bookId
AND libranch.branchId=bookcop.branchId

/*Question #2*/
SELECT branchName, noOfCopies 
FROM ((tbl_book NATURAL JOIN tbl_book_copies) NATURAL JOIN 
tbl_library_branch)
WHERE title='The Lost Tribe'

/*Question #3*/
SELECT name FROM tbl_borrower 
WHERE cardNo NOT IN (SELECT cardNo FROM tbl_book_loans)

/*Question #4*/
SELECT book.title, borrower.name, borrower.address 
FROM tbl_book book, tbl_borrower borrower, tbl_book_loans bookloan, 
tbl_library_branch libranch WHERE libranch.branchName='Sharpstown' AND
bookloan.dueDate='2015-06-24 08:24:35' AND book.bookId=bookloan.bookId
AND borrower.cardNo=bookloan.cardNo

/*Question #5*/
SELECT branch.branchName, COUNT(*) FROM tbl_library_branch branch,
tbl_book_loans bookloan WHERE bookloan.branchId=branch.branchId GROUP BY branch.branchName

/*Question #6*/
SELECT borrower.name, borrower.address, COUNT(*)
FROM tbl_borrower borrower, tbl_book_loans bookloan
WHERE bookloan.cardNo=borrower.cardNo
GROUP BY borrower.cardNo, borrower.name, borrower.address
HAVING COUNT(*)>5;

/*Question #7*/
SELECT title, noOfCopies 
FROM((((tbl_book_authors NATURAL JOIN tbl_book) NATURAL JOIN 
tbl_book_copies) NATURAL JOIN tbl_library_branch) NATURAL JOIN
tbl_author) WHERE 
authorName='Stephen King' AND branchName='Central';	
