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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@AssociationOverrides({
    @AssociationOverride(name = "id.user",
        joinColumns = @JoinColumn(name = "user_id")),
    @AssociationOverride(name = "id.book",
        joinColumns = @JoinColumn(name = "book_id")) })
@Table(name = "Reserved_Books")
public class ReservedBooks  implements Serializable{

	private static final long serialVersionUID = 6953928350970817813L;

	@EmbeddedId
	private ReservedBooksId id = new ReservedBooksId();
	
	@Column(name = "reservation_date")
	private Date reservationDate;
	
    @Transient
    public User getUser() {
        return getId().getUser();
    }
 
    public void setUser(User user) {
    	getId().setUser(user);
    }
 
    @Transient
    public Book getBook() {
        return getId().getBook();
    }
 
    public void setBook(Book book) {
    	getId().setBook(book);
    }

	public ReservedBooksId getId() {
		return id;
	}

	public void setId(ReservedBooksId id) {
		this.id = id;
	}

	public Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}
}

@Embeddable
class ReservedBooksId implements Serializable{
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
