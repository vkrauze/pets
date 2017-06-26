package net.pets.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import net.pets.model.Pet;

/**
 * Util class. Contains some common methods that can be used
 * for any class
 * 
 */
public class Util {

	/**
	 * Get list of Pets from request.
	 * @param data - json data from request 
	 * @return list of Pets
	 */
	public List<Pet> getPetsFromRequest(Object data){
		List<Pet> list;
		//it is an array - have to cast to array object
		if (data.toString().indexOf('[') > -1){
			list = getListPetsFromJSON(data);
		} else { //it is only one object - cast to object/bean
			Pet pet = getPetFromJSON(data);
			list = new ArrayList<Pet>();
			list.add(pet);
		}
		return list;
	}

	/**
	 * Transform json data format into Pet object
	 * @param data - json data from request
	 * @return 
	 */
	public Pet getPetFromJSON(Object data){
		JSONObject jsonObject = JSONObject.fromObject(data);
		Pet newPet = (Pet) JSONObject.toBean(jsonObject, Pet.class);
		return newPet;
	}

	/**
	 * Transform json data format into list of Pet objects
	 * @param data - json data from request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Pet> getListPetsFromJSON(Object data){
		JSONArray jsonArray = JSONArray.fromObject(data);
		List<Pet> newPets = (List<Pet>) JSONArray.toCollection(jsonArray,Pet.class);
		return newPets;
	}

	/**
	 * Tranform array of numbers in json data format into
	 * list of Integer
	 * @param data - json data from request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getListIdFromJSON(Object data){
		JSONArray jsonArray = JSONArray.fromObject(data);
		List<Integer> idPets = (List<Integer>) JSONArray.toCollection(jsonArray,Integer.class);
		return idPets;
	}
}
