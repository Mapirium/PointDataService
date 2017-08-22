package ch.mapirium.server.pointdata.Service;

import ch.mapirium.server.pointdata.model.FieldDataEntity;
import ch.mapirium.server.pointdata.repo.FieldDataRepository;
import ch.mapirium.server.pointdata.rest.gateway.PublicIdGateway;
import ch.mapirium.server.pointdata.rest.gateway.PublicIdGroup;
import ch.mapirium.server.pointdata.rest.model.PublicIdResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Verwaltet die Feld-Daten
 */
@Component
public class FieldDataService {

    @Autowired
    private FieldDataRepository fieldDataRepository;

    @Autowired
    private PublicIdGateway publicIdGateway;

    public FieldDataEntity createFieldData(FieldDataEntity newFieldData) {
        // Öffentlicher Schlüssel lösen
        PublicIdResource publicId = publicIdGateway.createNewPublicId(PublicIdGroup.FieldData);
        newFieldData.setPublicId(publicId.getPublicId());

        // Speichern
        FieldDataEntity savedFieldData = fieldDataRepository.save(newFieldData);

        return savedFieldData;
    }
}
