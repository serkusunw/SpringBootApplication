package pl.serkus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.serkus.model.Address;

@Repository("AddressRepository")
public interface AddressRepository  extends JpaRepository<Address, Integer>{
}
