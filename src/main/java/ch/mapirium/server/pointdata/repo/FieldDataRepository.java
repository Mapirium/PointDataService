package ch.mapirium.server.pointdata.repo;

import ch.mapirium.server.pointdata.model.FieldDataEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository für den Zugriff auf die Feld-Daten
 */
public interface FieldDataRepository extends CrudRepository<FieldDataEntity, Long> {
    @Query("SELECT f FROM FieldDataEntity f WHERE f.publicId = :publicId")
    public FieldDataEntity findByPublicId(@Param("publicId") String publicId);

    @Query("SELECT f FROM FieldDataEntity f JOIN f.pointData p WHERE p.publicId = :pointId")
    public List<FieldDataEntity> findByPointId(@Param("pointId") String pointId);
}
