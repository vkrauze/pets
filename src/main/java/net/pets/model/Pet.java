package net.pets.model;

/**
 * Pet POJO
 * 
 */
public class Pet {
	
	private int id;
	private String name;
	private String species;
	private String owner;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", species=" + species 
			 + ", owner=" + owner;
	}
}