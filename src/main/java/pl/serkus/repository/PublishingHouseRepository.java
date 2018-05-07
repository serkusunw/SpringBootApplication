package pl.serkus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.serkus.model.PublishingHouse;

public interface PublishingHouseRepository extends JpaRepository<PublishingHouse, Integer>{
	public PublishingHouse findByName(String name);
}
