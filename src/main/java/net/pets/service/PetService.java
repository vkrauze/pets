package net.pets.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import java.io.IOException;

import net.pets.dao.PetDAO;
import net.pets.model.Pet;
import net.pets.util.Util;

/**
 * Pet Business Delegate
 * 
 */
public class PetService {
	
	private PetDAO petDAO;
	private Util util;

	/**
	 * Get all pets
	 * @return
	 */
	public List<Pet> getPetList() throws IOException {

		return petDAO.getPets();
	}
	
	/**
	 * Create new Pet/Pets
	 * @param data - json data from request
	 * @return created pets
	 * @throws ParseException 
	 */
	public List<Pet> create(Object data) throws ParseException, IOException {
		
		List<Pet> newPets = new ArrayList<Pet>();
		List<Pet> list = util.getPetsFromRequest(data);
		for (Pet pet : list){
			newPets.add(petDAO.addPet(pet));
		}
		return newPets;
	}
	
	/**
	 * Update pet/pets
	 * @param data - json data from request
	 * @return updated pets
	 * @throws ParseException 
	 */
	public List<Pet> update(Object data) throws ParseException, IOException {
		List<Pet> returnPets = new ArrayList<Pet>();
		List<Pet> updatedPets = util.getPetsFromRequest(data);
		for (Pet pet : updatedPets){
			returnPets.add(petDAO.updatePet(pet));
		}
		return returnPets;
	}
	
	/**
	 * Delete pet/pets
	 * @param data - json data from request
	 */
	public void delete(Object data) throws IOException {
		//it is an array - have to cast to array object
		if (data.toString().indexOf('[') > -1){
			List<Integer> deletePets = util.getListIdFromJSON(data);
			for (Integer id : deletePets){
				petDAO.deletePet(id);
			}
		} else { //it is only one object - cast to object/bean
			Integer id = Integer.parseInt(data.toString());
			petDAO.deletePet(id);
		}
	}

	/**
	 * Spring use - DI
	 * @param petDAO
	 */
	public void setPetDAO(PetDAO petDAO) {
		this.petDAO = petDAO;
	}

	/**
	 * Spring use - DI
	 * @param util
	 */
	public void setUtil(Util util) {
		this.util = util;
	}
}