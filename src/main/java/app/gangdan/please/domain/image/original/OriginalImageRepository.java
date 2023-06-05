package app.gangdan.please.domain.image.original;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OriginalImageRepository extends JpaRepository<OriginalImage,Long> {

}
