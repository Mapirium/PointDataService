package ch.mapirium.server.pointdata.Service;

import ch.mapirium.server.pointdata.model.PointDataEntity;
import ch.mapirium.server.pointdata.repo.PointDataRepository;
import ch.mapirium.server.pointdata.rest.gateway.PublicIdGateway;
import ch.mapirium.server.pointdata.rest.gateway.PublicIdGroup;
import ch.mapirium.server.pointdata.rest.model.PublicIdResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Verwaltet die Daten eine konkreten Punktes
 */
@Component
public class PointDataService {

    @Autowired
    private PointDataRepository pointDataRepository;

    @Autowired
    private PublicIdGateway publicIdGateway;

    public PointDataEntity createPointData(PointDataEntity newPointData) {
        // Öffentliche ID lösen
        PublicIdResource publicId = publicIdGateway.createNewPublicId(PublicIdGroup.PointData);
        newPointData.setPublicId(publicId.getPublicId());

        // Punkt speichern
        PointDataEntity savedPointData = pointDataRepository.save(newPointData);

        return savedPointData;
    }
}
