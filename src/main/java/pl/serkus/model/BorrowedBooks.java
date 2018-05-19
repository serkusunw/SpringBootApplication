package pl.serkus.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@AssociationOverrides({
    @AssociationOverride(name = "primaryKey.user",
        joinColumns = @JoinColumn(name = "user_id")),
    @AssociationOverride(name = "primaryKey.book",
        joinColumns = @JoinColumn(name = "book_id")) })
@Table(name = "Borrowed_Books")
public class BorrowedBooks implements Serializable{
	
	private static final long serialVersionUID = 6668903272456514846L;

	@EmbeddedId
	private BorrowedBooksId primaryKey = new BorrowedBooksId();
	
	@Column(name = "status")
	private boolean status;
	
	@Column(name = "return_date")
	private Date returnDate;
	
	@Column(name = "rental_date")
	private Date rentalDate;
	

	public BorrowedBooks() {
	}
	

	public BorrowedBooksId getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(BorrowedBooksId primaryKey) {
		this.primaryKey = primaryKey;
	}

	@Transient
	public Book getBook() {
		return getPrimaryKey().getBook();
	}

	public void setBook(Book book) {
		getPrimaryKey().setBook(book);
	}

	@Transient
	public User getUser() {
		return getPrimaryKey().getUser();
	}

	public void setUser(User user) {
		getPrimaryKey().setUser(user);
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public Date getRentalDate() {
		return rentalDate;
	}

	public void setRentalDate(Date rentalDate) {
		this.rentalDate = rentalDate;
	}
}

@Embeddable
class BorrowedBooksId implements Serializable{
	private static final long serialVersionUID = 533791180614467842L;

	@ManyToOne(cascade = CascadeType.ALL)
	private Book book;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}