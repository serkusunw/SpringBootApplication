package pl.serkus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.serkus.model.PublishingHouse;

@Repository("PublishingHouseRepository")
public interface PublishingHouseRepository extends JpaRepository<PublishingHouse, Integer>{
	public PublishingHouse findByName(String name);
}
