package ch.mapirium.server.pointdata.rest.gateway;

/**
 * Gruppe, zu welcher der öffentlicher Schlüssel gehört. Dieser definiert das Prefix innerhalb der ID
 */
public enum PublicIdGroup {
    FieldData("FI"),
    PointData("PO");

    private String prefix;

    PublicIdGroup(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }
}
