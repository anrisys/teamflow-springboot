package com.anrisys.teamflow.shared.common;

import java.util.Objects;
import java.util.UUID;

public class PublicID {
	private final UUID id;

	public PublicID(UUID value) {
		this.id = Objects.requireNonNull(value, "PublicID cannot be null");
	}
	
	public static PublicID generate() {
		return new PublicID(UUID.randomUUID());
	}
	
	public static PublicID from(String value) {
		try {
			return new PublicID(UUID.fromString(value));
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Invalid PublicID: " + value, e);
		}
	}
	
    public UUID getValue() { return id;}
    public String toString() { return id.toString(); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublicID publicId = (PublicID) o;
        return Objects.equals(id, publicId.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
}
