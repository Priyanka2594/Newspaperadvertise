package newspaper.advertisement.system.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import newspaper.advertisement.system.model.Advertise;

/**
 * 
 * repository for Advertisement functionalities
 *
 */

@Repository
public interface AdvertiseRepository extends JpaRepository<Advertise, Integer> {

//	Search Advertise by text entered in textbox(seller)
	public abstract List<Advertise> findByadvertisetitle(String advertisetitle);

//	Read the specific advertise by id(seller)
	public abstract Optional<Advertise> findByadvid(int advid);

//	Delete product by id
	@Modifying
	@Transactional
	@Query("DELETE from Advertise a WHERE a.advid = :advid")
	public abstract void deleteById(int advid);

//	show approved advertise for buyer
	@Modifying
	@Transactional
	@Query(value = "SELECT * FROM advertise_adv WHERE status IN ( 'OPEN', 'APPROVED')", nativeQuery = true)
	public abstract List<Advertise> viewApprovedAdv();


//	User will update advertise details (seller)
//	@Modifying
//	@Transactional
//	@Query(value = "UPDATE Advertise SET advertisetitle = :advertisetitle, price = :price, description = :description, advownername = :advownername WHERE advid = :advid ")
//	public abstract void updateSellerAdv(@Param(value = "advertisetitle") String advertisetitle,
//			@Param(value = "price") double price, @Param(value = "description") String description,
//			@Param(value = "advownername") String advownername, 
//			@Param(value = "advid") int advid);

//	Admin will update status of advertise
	@Modifying
	@Transactional
	@Query("UPDATE Advertise a SET a.status = :status  WHERE a.advid = :advid")
	public abstract void updateStatusAdv(@Param(value = "status") String status, @Param(value = "advid") int advid);

/* find advertise by title
	public abstract List<Advertise> findByAdvertisetitle(String advertisetitle);*/

	
}
