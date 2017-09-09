package ch.mapirium.server.pointdata.repo;

import ch.mapirium.server.pointdata.model.PointDataEntity;
import com.vividsolutions.jts.geom.Geometry;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repo für den Zugriff auf die Punkte
 */
public interface PointDataRepository extends CrudRepository<PointDataEntity, Long> {

    @Query("SELECT p FROM PointDataEntity p WHERE p.publicId = :publicId")
    public PointDataEntity findByPublicId(@Param("publicId") String publicId);

    @Query("SELECT p FROM PointDataEntity p WHERE p.mapId = :mapId")
    public List<PointDataEntity> findByMapId(@Param("mapId") String mapId);

    @Query("SELECT p FROM PointDataEntity p WHERE p.mapId = :mapId and within(p.location, :area) = true")
    List<PointDataEntity> findWithing(@Param("mapId") String mapId, @Param("area") Geometry area);
}
