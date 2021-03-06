package pl.serkus.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "book")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private int id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description", length = 10000)
	private String description;
	
	@Column(name = "release_date")
	private Date release_date;
	
	@Column(name = "count")
	private int count;
	
	@Column(name = "image_name")
	private String image_name;
	
	@ManyToOne(cascade =
        {
                CascadeType.DETACH,
                CascadeType.MERGE,
                CascadeType.REFRESH,
                CascadeType.PERSIST
        })
	@JoinColumn(name = "author_id")
	private Author author;
	
	@ManyToOne(cascade =
        {
                CascadeType.DETACH,
                CascadeType.MERGE,
                CascadeType.REFRESH,
                CascadeType.PERSIST
        })
	@JoinColumn(name = "category_id")
	private Category category;
	
	@ManyToOne(cascade =
        {
                CascadeType.DETACH,
                CascadeType.MERGE,
                CascadeType.REFRESH,
                CascadeType.PERSIST
        })
	@JoinColumn(name = "publishing_house_id")
	private PublishingHouse publishingHouse;
	
	@OneToMany(mappedBy = "primaryKey.book", cascade = CascadeType.ALL)
	private Set<BorrowedBooks> borrowedBooks = new HashSet<>();
	
	@OneToMany(mappedBy = "id.book", cascade = CascadeType.ALL)
	private Set<ReservedBooks> reservedBooks = new HashSet<>();
	
	@Transient
	private int authorId;
	
	@Transient
	private int categoryId;
	
	@Transient
	private int publishingHouseId;
	
	@Transient
	private String date;
	
	@Transient
	private Date reservation_date;
	
	@Transient
	private Date rental_date;
	
	@Transient
	private Date return_date;
	
	@Transient
	private MultipartFile image;
	
	public Book(){
	}


	public Date getRental_date() {
		return rental_date;
	}

	public void setRental_date(Date rental_date) {
		this.rental_date = rental_date;
	}

	public Date getReturn_date() {
		return return_date;
	}

	public void setReturn_date(Date return_date) {
		this.return_date = return_date;
	}

	public Date getReservation_date() {
		return reservation_date;
	}

	public void setReservation_date(Date reservation_date) {
		this.reservation_date = reservation_date;
	}

	public Set<BorrowedBooks> getBorrowedBooks() {
		return borrowedBooks;
	}

	public void setBorrowedBooks(Set<BorrowedBooks> borrowedBooks) {
		this.borrowedBooks = borrowedBooks;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public String getImage_name() {
		return image_name;
	}

	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getRelease_date() {
		return release_date;
	}

	public void setRelease_date(Date release_date) {
		this.release_date = release_date;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public PublishingHouse getPublishingHouse() {
		return publishingHouse;
	}

	public void setPublishingHouse(PublishingHouse publishingHouse) {
		this.publishingHouse = publishingHouse;
	}

	public int getPublishingHouseId() {
		return publishingHouseId;
	}

	public void setPublishingHouseId(int publishingHouseId) {
		this.publishingHouseId = publishingHouseId;
	}
}
